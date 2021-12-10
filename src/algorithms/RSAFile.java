package algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class RSAFile {
	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();
	static SecureRandom srandom = new SecureRandom();
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private String algorithms, mode, padding;
	private int keySize;
	Cipher ci;
	int ivSize;

	public RSAFile(String algorithms, int keySize, String mode, String padding)
			throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, IOException {
		this.algorithms = algorithms;
		this.keySize = keySize;
		this.mode = mode;
		this.padding = padding;
		ci = Cipher.getInstance(this.algorithms + "/" + this.mode + "/"
				+ this.padding);
		if (algorithms.equalsIgnoreCase("AES")) {
			ivSize = 16;
		} else {
			ivSize = 8;
		}
		doGenkey();
	}

	private void processFile(Cipher ci, InputStream in, OutputStream out)
			throws IOException, IllegalBlockSizeException, BadPaddingException {
		byte[] ibuf = new byte[1024];
		int len;
		while ((len = in.read(ibuf)) != -1) {
			byte[] obuf = ci.update(ibuf, 0, len);
			if (obuf != null)
				out.write(obuf);

		}
		byte[] obuf = ci.doFinal();
		if (obuf != null)
			out.write(obuf);
		out.close();

	}

	public void doGenkey() throws FileNotFoundException, IOException,
			NoSuchAlgorithmException {

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();
		privateKey = kp.getPrivate();
		publicKey = kp.getPublic();

	}

	public void doGenkey(String path) throws FileNotFoundException,
			IOException, NoSuchAlgorithmException {
		File dest = new File(path);
		if (!dest.exists()) {
			dest.mkdirs();

		}
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();

		try (FileOutputStream out = new FileOutputStream(path + "/private.key")) {
			out.write(encoder.encodeToString(kp.getPrivate().getEncoded())
					.getBytes(StandardCharsets.UTF_8));

		}
		try (FileOutputStream out = new FileOutputStream(path + "/public.key")) {
			out.write(encoder.encodeToString(kp.getPublic().getEncoded())
					.getBytes(StandardCharsets.UTF_8));

		}

	}

	public PrivateKey readPrivateKey(String path) throws IOException,
			InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String prikeyString = new String(bytes, StandardCharsets.UTF_8);
		bytes = decoder.decode(prikeyString);
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		System.out.println("Read Private key success!");
		return kf.generatePrivate(ks);
	}

	public PublicKey readPublicKey(String path) throws InvalidKeySpecException,
			NoSuchAlgorithmException, IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String pubkeyString = new String(bytes, StandardCharsets.UTF_8);
		bytes = decoder.decode(pubkeyString);
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		System.out.println("Read Public key success!");

		return kf.generatePublic(ks);

	}

	public void doEncryptRSAWithAES( String inputFile,
			String outputFile) throws NoSuchAlgorithmException,
			FileNotFoundException, IOException, InvalidKeyException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {
		System.out.println("thuat toan"+algorithms);
		KeyGenerator kgen = KeyGenerator.getInstance(algorithms);
		kgen.init(keySize);
		SecretKey skey = kgen.generateKey();
		byte[] iv = new byte[ivSize];
		srandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		try (FileOutputStream out = new FileOutputStream(outputFile)) {
			{
				Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);
				byte[] b = cipher.doFinal(skey.getEncoded());
				out.write(b);
				System.out.println("AES key Length" + b.length);
			}

			out.write(iv);
			System.out.println("IV Length:" + iv.length);

			ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
			try (FileInputStream in = new FileInputStream(inputFile)) {
				processFile(ci, in, out);
			}
		}
	}

	public void doDeCryptRSAWithAES( String inputFile,
			String outputFile) throws FileNotFoundException, IOException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {
		try (FileInputStream in = new FileInputStream(inputFile)) {
			SecretKeySpec skey = null;
			{
				Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
				byte[] b = new byte[256];
				in.read(b);
				byte[] keyb = cipher.doFinal(b);
				skey = new SecretKeySpec(keyb, algorithms);

			}
			byte[] iv = new byte[ivSize];
			in.read(iv);
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			ci.init(Cipher.DECRYPT_MODE, skey, ivspec);

			try (FileOutputStream out = new FileOutputStream(outputFile)) {
				processFile(ci, in, out);
			}
		}
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey convertStringKeyToPrivateKey(String stored) {
		try {
			byte[] data = decoder.decode(stored);
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
			KeyFactory fact = KeyFactory.getInstance("RSA");
			return fact.generatePrivate(spec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Private key valid", "Error",
					JOptionPane.ERROR_MESSAGE);

			return null;
		}

	}

	public PublicKey convertStringKeyToPublicKey(String stored) {
		try {
			byte[] data = decoder.decode(stored);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
			KeyFactory fact = KeyFactory.getInstance("RSA");
			return fact.generatePublic(spec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Public key valid", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public String convertKeytoString(Key key) {
		byte[] arrKey = key.getEncoded();
		String encodedKey = encoder.encodeToString(arrKey);
		return encodedKey;
	}
	public String getPublicKeyWithString() {
		return convertKeytoString(publicKey);
	}

	public String getPrivateKeyWithString() {
		return convertKeytoString(privateKey);
	}
	public static void main(String[] args) throws InvalidKeySpecException,
			NoSuchAlgorithmException, IOException, InvalidKeyException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {
		RSAFile rsa = new RSAFile("Blowfish", 448, "PCBC", "PKCS5Padding");
		rsa.doGenkey();
		rsa.doEncryptRSAWithAES( "F:\\test\\tuyen.txt",
				"F:\\test\\tuyen1.txt");
		rsa.doDeCryptRSAWithAES("F:\\test\\tuyen1.txt",
				"F:\\test\\tuyen11.txt");
	}
}
