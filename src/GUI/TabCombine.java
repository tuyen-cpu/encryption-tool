package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("unused")
public class TabCombine {
	public static String[] listAlgorithms = { "AES", "DES", "DESede", "RC2",
			"Blowfish" };
	public static String[] listKeySize = { "128", "192", "256" };
	public static String[] listMode = { "CBC", "CFB", "PCBC", "OFB", "CTR" };
	public static String[] listPadding = { "PKCS5Padding" };
	public static String[] listKeySizeAES = { "128", "192", "256" };
	public static String[] listKeySizeDES = { "56" };
	public static String[] listKeySizeBlowfish = { "40", "128", "192", "256",
			"448" };

	public static String[] listKeySizeRC2 = { "40", "128", "192", "256",
			"512", "1024" };
	public static String[] listKeySizeDESede = { "168", "112" };

}
