package algorithms;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static String MD5 = "MD5";
	public static String SHA_1 = "SHA-1";
	public static String SHA_224 = "SHA-224";
	public static String SHA_256 = "SHA-256";
	public static String SHA_384 = "SHA-384";
	public static String SHA_512_224 = "SHA-512/224";
	public static String SHA_512_256 = "SHA-512/256";
//	public static String SHA3_224 = "SHA3-224";
	public  String hash(String input) {
		return hash(input, "SHA-256");
	}

	public  String hash(String input, String algorithms) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithms);
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			return hashtext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
	public String hashFile(String file,String algorithms) throws NoSuchAlgorithmException, IOException  {
		MessageDigest digest = MessageDigest.getInstance(algorithms);
		InputStream is = new FileInputStream(new File(file));
		DigestInputStream dis = new DigestInputStream(is, digest);
		
		byte[] buffer = new byte[1024];
		
		int read = dis.read(buffer);
		while (read != -1) {
			read = dis.read(buffer);

		}
		dis.close();
		is.close();
		BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
		String hashtext = number.toString(16);
		return hashtext;
	}
}
