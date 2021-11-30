package algorithms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Symmetric {
	private SecretKey key;
	private Cipher cipher;
	private String algorithm, padding, mode;

	int keySize;

	public Symmetric(String algorithm, String mode, String padding, int keySize)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.algorithm = algorithm;
		this.mode = mode;
		this.padding = padding;
		this.cipher = Cipher
				.getInstance(algorithm + "/" + mode + "/" + padding);
		this.keySize = keySize;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

	public void setKey(String txtKey) {
		byte[] decodedKey = Base64.getDecoder().decode(txtKey);
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length,
				algorithm);
		this.key = key;
	}

	public String getKeyWithString() {
		return convertSecretKeyToStringKey(key);
	}

	public SecretKey createKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(keySize);
		key = keyGenerator.generateKey();
		return key;

	}

	public String convertSecretKeyToStringKey(SecretKey secretKey) {
		byte[] arrKey = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(arrKey);
		return encodedKey;
	}

	public SecretKey convertStringKeyToSecretKey(String keyStr) {
		byte[] decodKey = Base64.getDecoder().decode(keyStr);
		SecretKey originalKey = new SecretKeySpec(decodKey, 0, decodKey.length,
				algorithm);
		return originalKey;
	}

	public String encrypt(String text) throws Exception {
		if (key == null)
			return "";
		try {
			if (padding.trim().equalsIgnoreCase("NoPadding")) {
				while (text.getBytes().length % 16 != 0) {
					text += '\u0020';
				}
			}
			checkSpeckey(Cipher.ENCRYPT_MODE);
			byte[] plaintext = text.getBytes("UTF-8");
			byte[] cipherText = cipher.doFinal(plaintext);
			return Base64.getEncoder().encodeToString(cipherText);
		} catch (Exception e) {
			System.out.println("encrypt lỗi");
			JOptionPane.showMessageDialog(null, "Invalid key",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public String decrypt(String text) throws Exception {
		if (key == null)
			return null;
		try {
			checkSpeckey(Cipher.DECRYPT_MODE);
			byte[] cipherText = Base64.getDecoder().decode(text);
			byte[] plaintext = cipher.doFinal(cipherText);
			String out = new String(plaintext, "UTF-8");
			return out.trim();
		} catch (Exception e) {
			System.out.println("decrypt lôi");
			JOptionPane.showMessageDialog(null, "Invalid key",
					"Error", JOptionPane.ERROR_MESSAGE);
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
			try{
				cipher.init(n, key, ivspec);
				
			}catch(Exception e){
			
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

	public static void main(String[] args) throws Exception {
		/*
		 * AES (128,192,256) DES (56) DESede (168)
		 */

		Symmetric s = new Symmetric("AES", "GCM", "NoPadding", 128);
		s.createKey();
		String ss = s.encrypt("c");
		System.out.println(s.decrypt(ss));

		// Symmetric s1 = new Symmetric("AES", 128);
		// s1.createKey();
		// System.out.println("khóa: "+s1.getKeyWithString());
		// String str1 =new
		// String("Khoa cntt nông lâm tp hồ chi minh 123%^&%^$.");
		// String out = s1.encrypt(str1);
		// System.out.println("Sau khi ma hoa: "+out);
		// System.out.println("Sau khi giải mã: "+s1.decrypt(out));
		//
		// System.out.println("---------");
		//
		// Symmetric s2 = new Symmetric("AES", 256);
		// s2.createKey();
		// System.out.println("khóa: "+s2.getKeyWithString());
		// String str2 =new
		// String("All key generators share the concepts of a keysize and a source of randomness");
		// String out2 = s2.encrypt(str2);
		// System.out.println("Sau khi ma hoa: "+out2);
		// System.out.println("Sau khi giải mã: "+s2.decrypt(out2));
		//
		// System.out.println("---------");
		//
		// Symmetric s3 = new Symmetric("DESede", 168);
		// s3.createKey();
		// System.out.println("khóa: "+s3.getKeyWithString());
		// String str3 =new String("Trần quang Tuyền 123///...~~~");
		// String out3 = s3.encrypt(str3);
		// System.out.println("Sau khi ma hoa: "+out3);
		// System.out.println("Sau khi giải mã: "+s3.decrypt(out3));

	}
}
