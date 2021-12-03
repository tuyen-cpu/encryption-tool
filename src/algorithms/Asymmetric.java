package algorithms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Asymmetric {
	private KeyPair keypair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private String algorithms;
	private String mode;
	private String padding;
	private int keySize;
	private Cipher cipher;

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
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public String decrypt(String text) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] cipherText = Base64.getDecoder().decode(text);
		byte[] plaintext = cipher.doFinal(cipherText);
		String result = new String(plaintext, "UTF-8");
		return result;
	}

	public void EncryptFile(String sourceFile, String destFile)
			throws Exception {
		File file = new File(sourceFile);
		if (file.exists()) {
			if (publicKey == null)
				genkey();
			Cipher cipher = Cipher.getInstance("RSA");
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
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(2848);
			keypair = keyGenerator.generateKeyPair();
			publicKey = keypair.getPublic();
			privateKey = keypair.getPrivate();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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
		String encodedKey = Base64.getEncoder().encodeToString(arrKey);
		return encodedKey;
	}

	public Key convertStringKeyToSecretKey(String keyStr) {
		byte[] decodKey = Base64.getDecoder().decode(keyStr);
		Key originalKey = new SecretKeySpec(decodKey, 0, decodKey.length,
				algorithms);
		return originalKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = (PublicKey) convertStringKeyToSecretKey(publicKey);
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = (PrivateKey) convertStringKeyToSecretKey(privateKey);
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}


}
