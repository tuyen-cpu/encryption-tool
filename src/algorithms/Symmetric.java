package algorithms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Symmetric {
	private SecretKey key;
	private Cipher cipher;
	private String algorithm, padding, mode;
	int keySize;
	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();

	public Symmetric(String algorithm, String mode, String padding, int keySize)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new BouncyCastleProvider());
		this.algorithm = algorithm;
		this.mode = mode;
		this.padding = padding;
		try {
			this.cipher = Cipher
					.getInstance(algorithm + "/" + mode + "/" + padding,"BC");
			
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.keySize = keySize;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

	public void setKey(String txtKey) {
		byte[] decodedKey = decoder.decode(txtKey);
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length,
				algorithm);
		this.key = key;
	}

	public String getKeyWithString() {
		return convertSecretKeyToStringKey(key);
	}

	public Key readKey(String path) throws IOException,
			InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		String prikeyString = new String(bytes, StandardCharsets.UTF_8);
		byte[] decodKey = decoder.decode(prikeyString);
		Key originalKey = new SecretKeySpec(decodKey, 0, decodKey.length,
				algorithm);
		return originalKey;
	}

	public SecretKey createKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm,"BC");
		keyGenerator.init(keySize);
		key = keyGenerator.generateKey();
		return key;

	}

	public String convertSecretKeyToStringKey(SecretKey secretKey) {
		byte[] arrKey = secretKey.getEncoded();
		String encodedKey = encoder.encodeToString(arrKey);
		return encodedKey;
	}

	public SecretKey convertStringKeyToSecretKey(String keyStr) {
		byte[] decodKey = decoder.decode(keyStr);
		SecretKey originalKey = new SecretKeySpec(decodKey, 0, decodKey.length,
				algorithm);
		return originalKey;
	}

	public String encrypt(String text) throws Exception {
		if (key == null)
			return "";
		try {
			
			if (padding.trim().equalsIgnoreCase("NoPadding")) {
				
				while (text.getBytes("UTF-8").length % 16 != 0) {
					text += '\u0020';
				}
			}
			checkSpeckey(Cipher.ENCRYPT_MODE);
			byte[] plaintext = text.getBytes("UTF-8");
			byte[] cipherText = cipher.doFinal(plaintext);
			return encoder.encodeToString(cipherText);
		} catch (Exception e) {
			System.out.println("encrypt lỗi");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public String decrypt(String text) throws Exception {
		if (key == null)
			return null;
		try {
			checkSpeckey(Cipher.DECRYPT_MODE);

			byte[] cipherText = decoder.decode(text);
			byte[] plaintext = cipher.doFinal(cipherText);
			String out = new String(plaintext, "UTF-8");
			return out.trim();
		} catch (Exception e) {
			System.out.println("decrypt lôi");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public void checkSpeckey(int n) throws InvalidKeyException,
			InvalidAlgorithmParameterException {
		if (mode.trim().equalsIgnoreCase("ECB")) {
			cipher.init(n, key);
		} else {
			byte[] iv;
			if (algorithm.equalsIgnoreCase("AES")) {
				iv = new byte[16];
			} else {
				iv = new byte[8];
			}
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			try {
				cipher.init(n, key, ivspec);

			} catch (Exception e) {

				System.out.println("checkSpeckey lỗi");
			}
		}

	}

	public int getKeySize() {
		return keySize;
	}

	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}

	public void encrypt(String sourceFile, String destFile) throws Exception {
		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			checkSpeckey(Cipher.ENCRYPT_MODE);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream(destFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] input = new byte[64];
			int bytesRead;
			while ((bytesRead = bis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					bos.write(output);
				}
			}
			byte[] output = cipher.doFinal();
			if (output != null)
				bos.write(output);
			bos.flush();
			bos.close();
			fis.close();
			bis.close();
			fos.close();
			System.out.println("Encrypted file");

		} else {
			System.out.println("This is not a file");

		}
	}

	public void decrypt(String sourceFile, String destFile) throws Exception {

		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			checkSpeckey(Cipher.DECRYPT_MODE);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream(destFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] input = new byte[64];
			int bytesRead = 0;
			while ((bytesRead = bis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					bos.write(output);
				}
			}
			byte[] output = cipher.doFinal();
			if (output != null)
				bos.write(output);
			bos.flush();
			bos.close();
			fis.close();
			bis.close();
			fos.close();
			System.out.println("Decrypted file");

		} else {
			System.out.println("This is not a file");

		}
	}
}
