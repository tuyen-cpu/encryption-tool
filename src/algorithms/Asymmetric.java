package algorithms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Asymmetric {
	private KeyPair keypair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private String algorithms;
	private String mode;
	private String padding;
	private int keySize;
	private Cipher cipher;
	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();

	public Asymmetric(String algorithms, String mode, String padding,
			int keySize) throws NoSuchAlgorithmException,
			NoSuchPaddingException {
		this.algorithms = algorithms;
		this.mode = mode;
		this.padding = padding;
		this.keySize = keySize;
		cipher = Cipher.getInstance(algorithms + "/" + mode + "/" + padding);
	}

	public String encrypt(String text) throws Exception {
		if (publicKey == null)
			genkey();

		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] plaintext = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plaintext);
		return encoder.encodeToString(cipherText);
	}

	public String decrypt(String text) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] cipherText = decoder.decode(text);
		byte[] plaintext = cipher.doFinal(cipherText);
		String result = new String(plaintext, "UTF-8");
		return result;
	}

	public void encrypt(String sourceFile, String destFile) throws Exception {
		File file = new File(sourceFile);
		if (file.exists()) {
			if (publicKey == null)
				genkey();
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(destFile)));

			byte[] input = new byte[256];

			long length = file.length();
			int byteRead = 0;
			while (length > 0) {
				byteRead = dis.read(input);
				byte[] cipherText = cipher.doFinal(input, 0, byteRead);
				dos.write(cipherText);
				dos.flush();
				length = length - byteRead;
			}
			dos.close();
			dis.close();
			System.out.println("File is encrypted");
		} else {
			System.out.println("Source file is not existed");
		}
	}
	
	public void genkey() {
		KeyPairGenerator keyGenerator = null;
		try {
			keyGenerator = KeyPairGenerator.getInstance(algorithms);
			keyGenerator.initialize(2848);
			keypair = keyGenerator.generateKeyPair();
			publicKey = keypair.getPublic();
			privateKey = keypair.getPrivate();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public PrivateKey readPrivateKey(String path) throws IOException,
			InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String prikeyString = new String(bytes, StandardCharsets.UTF_8);
		bytes = decoder.decode(prikeyString);
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance(algorithms);
		System.out.println("Read Private key success!");
		return kf.generatePrivate(ks);
	}

	public PrivateKey convertStringKeyToPrivateKey(String stored)
			 {
		try{
			byte[] data = decoder.decode(stored);
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
			KeyFactory fact = KeyFactory.getInstance(algorithms);
			return fact.generatePrivate(spec);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Private key valid", "Error",
					JOptionPane.ERROR_MESSAGE);
			
			return null;
		}
		
	}

	public PublicKey convertStringKeyToPublicKey(String stored)
			 {
		try{
			byte[] data = decoder.decode(stored);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
			KeyFactory fact = KeyFactory.getInstance(algorithms);
			return fact.generatePublic(spec);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Public key valid", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}

	public PublicKey readPublicKey(String path) throws InvalidKeySpecException,
			NoSuchAlgorithmException, IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String pubkeyString = new String(bytes, StandardCharsets.UTF_8);
		bytes = decoder.decode(pubkeyString);
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance(algorithms);
		System.out.println("Read Public key success!");

		return kf.generatePublic(ks);

	}

	public KeyPair getKeypair() {
		return keypair;
	}

	public void setKeypair(KeyPair keypair) {
		this.keypair = keypair;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public String getPublicKeyWithString() {
		return convertKeytoString(publicKey);
	}

	public String getPrivateKeyWithString() {
		return convertKeytoString(privateKey);
	}

	public String convertKeytoString(Key key) {
		byte[] arrKey = key.getEncoded();
		String encodedKey = encoder.encodeToString(arrKey);
		return encodedKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}
