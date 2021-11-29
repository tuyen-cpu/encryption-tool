package algorithms;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Symmetric {
	private SecretKey key;
	private Cipher cipher;
	private String algorithm;
	int keySize;

	public Symmetric(String algorithm, int keySize)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.algorithm = algorithm;
		this.cipher = Cipher.getInstance(algorithm);
		this.keySize = keySize;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
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

	public static String convertSecretKeyToStringKey(SecretKey secretKey) {
		byte[] arrKey = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(arrKey);
		return encodedKey;
	}

	public static SecretKey convertStringKeyToSecretKey(String keyStr) {
		byte[] decodKey = Base64.getDecoder().decode(keyStr);
		SecretKey originalKey = new SecretKeySpec(decodKey, 0,
				decodKey.length, "AES");
		return originalKey;
	}

	public String encrypt(String text) throws Exception {
		if (key == null)
			return "";
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] plaintext = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plaintext);

		return Base64.getEncoder().encodeToString(cipherText);
	}

	public String decrypt(String text) throws Exception {
		if (key == null)
			return null;
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] cipherText = Base64.getDecoder().decode(text);
		byte[] plaintext = cipher.doFinal(cipherText);
		String out = new String(plaintext, "UTF-8");

		return out;
	}

	public int getKeySize() {
		return keySize;
	}

	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}

	public static void main(String[] args) throws Exception {
		/*		AES (128,256)
				DES (56)
				DESede (168)		*/
		
		Symmetric s1 = new Symmetric("AES", 128);
		s1.createKey();
		System.out.println("khóa: "+s1.getKeyWithString());
		String str1 =new String("Khoa cntt nông lâm tp hồ chi minh 123%^&%^$.");
		String out = s1.encrypt(str1);
		System.out.println("Sau khi ma hoa: "+out);
		System.out.println("Sau khi giải mã: "+s1.decrypt(out));
		
		System.out.println("---------");
		
		Symmetric s2 = new Symmetric("AES", 256);
		s2.createKey();
		System.out.println("khóa: "+s2.getKeyWithString());
		String str2 =new String("All key generators share the concepts of a keysize and a source of randomness");
		String out2 = s2.encrypt(str2);
		System.out.println("Sau khi ma hoa: "+out2);
		System.out.println("Sau khi giải mã: "+s2.decrypt(out2));
		
		System.out.println("---------");
		
		Symmetric s3 = new Symmetric("DESede", 168);
		s3.createKey();
		System.out.println("khóa: "+s3.getKeyWithString());
		String str3 =new String("Trần quang Tuyền 123///...~~~");
		String out3 = s3.encrypt(str3);
		System.out.println("Sau khi ma hoa: "+out3);
		System.out.println("Sau khi giải mã: "+s3.decrypt(out3));
		

	}
}
