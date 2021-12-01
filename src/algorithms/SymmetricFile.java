package algorithms;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;



/**
 * @author Admin
 *
 */
public class SymmetricFile {
	SecretKey key;
//	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public void encrypt(String sourceFile, String destFile) throws Exception {

		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] input = new byte[64];
			int bytesRead;
			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}
			byte[] output = cipher.doFinal();
			if (output != null)
				fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Encrypted");

		} else {
			System.out.println("This is not a file");

		}
	}

	public void decrypt(String sourceFile, String destFile) throws Exception {

		if (key == null)
			throw new FileNotFoundException("Key not found");
		File file = new File(sourceFile);
		if (file.isFile()) {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] input = new byte[64];
			int bytesRead = 0;
			while ((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null) {
					fos.write(output);
				}
			}
			byte[] output = cipher.doFinal();
			if (output != null)
				fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Decrypted");

		} else {
			System.out.println("This is not a file");

		}
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

	public SecretKey getKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		key = keyGenerator.generateKey();
		return this.key;
	}
	public static void main(String[] args) throws Exception {
		SymmetricFile tuyen = new SymmetricFile();
		tuyen.getKey();
		System.out.println(tuyen.key);
//		String sourceFile = new String("F:\\test\\s\\video.mp4");
//		String destFile = new String("F:\\test\\d\\video.mp4");
//		String destFile1 = new String("F:\\test\\d\\v.mp4");
//		tuyen.encrypt(sourceFile, destFile);
//		tuyen.decrypt(destFile, destFile1);
	}
}
