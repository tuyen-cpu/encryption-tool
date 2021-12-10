package GUI;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
public class OptionGeneralUI extends JPanel {

	private String[] listAlgorithms = {"Twofish","AES", "DES", "DESede","RC2","RC4","Blowfish" };
	private String[] listKeySize = { "128", "192", "256" };
	private String[] listMode = { "CBC", "ECB","CFB","PCBC","OFB","CTR"};
	private String[] listPadding = { "PKCS5Padding","NoPadding" };
	private String[] listKeySizeAES = { "128", "192", "256" };
	private String[] listKeySizeDES = { "56" };
	private String[] listKeySizeBlowfish = { "40","128", "192", "256","448"};
	
	private String[] listKeySizeRC2 = { "40","128", "192", "256","512","1024" };
	private String[] listKeySizeDESede = { "168","112" };
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblpadding;
	JComboBox choiceAlgorithms, choiceKeySize, choiceMode, choicePadding;
	DefaultComboBoxModel<String> model ;
	private Dimension dimlbl, dimContainer;
	private JPanel pnAlgorithms, pnKeySize, pnMode, pnPadding;

	public OptionGeneralUI() {
		dimContainer = new Dimension(740, 65);
		dimlbl = new Dimension(67, 30);
		

		choiceAlgorithms = new JComboBox(listAlgorithms);
		choiceKeySize = new JComboBox(listKeySize);
		choiceMode= new JComboBox(listMode);
		choicePadding = new JComboBox(listPadding);
		
		pnAlgorithms = new JPanel();
		pnKeySize = new JPanel();
		pnMode = new JPanel();
		pnPadding = new JPanel();
		/* Create label */
		lblAlgorithms = new JLabel("Algorithms:");
		lblKeySize = new JLabel("Key size:");
		lblMode = new JLabel("Mode:");
		lblpadding = new JLabel("Padding:");
		// Set font
		lblAlgorithms.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblKeySize.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMode.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblpadding.setFont(new Font("Dialog", Font.PLAIN, 13));

		choiceAlgorithms.setFont(new Font("Dialog", Font.PLAIN, 12));
		choiceKeySize.setFont(new Font("Dialog", Font.PLAIN, 12));
		choiceMode.setFont(new Font("Dialog", Font.PLAIN, 12));
		choicePadding.setFont(new Font("Dialog", Font.PLAIN, 12));
		/* Remove Focus Painted */
		choiceAlgorithms.setFocusable(false);
		choiceKeySize.setFocusable(false);
		choiceMode.setFocusable(false);
		choicePadding.setFocusable(false);
		/* Set dimension */
		setPreferredSize(dimContainer);	

		/* Add label & choice into Panel */
		pnAlgorithms.add(lblAlgorithms);
		pnAlgorithms.add(choiceAlgorithms);
		pnAlgorithms.setSize(100, 100);

		addComponentIntoPanelKeySize();
		pnMode.add(lblMode);
		pnMode.add(choiceMode);

		pnPadding.add(lblpadding);
		pnPadding.add(choicePadding);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(pnAlgorithms);
		add(pnKeySize);
		add(pnMode);
		add(pnPadding);
		setBorder(new EmptyBorder(20, 0, 0, 0));
//		Border blackline = BorderFactory.createTitledBorder("Option");
//		setBorder(blackline);
//		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
//				Font.PLAIN, 13));

	}


	public void addComponentIntoPanelKeySize() {
		pnKeySize.add(lblKeySize);
		
		pnKeySize.add(choiceKeySize);
		pnKeySize.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		
	}

	public void changeContentChoice() {

		switch ((String)choiceAlgorithms.getSelectedItem()) {
		case "AES":
			listKeySize = listKeySizeAES;
			model = new DefaultComboBoxModel<String>(listKeySize);
			choiceKeySize.setModel(model);
			System.out.println("AES");
			break;
		case "DES":
			listKeySize = listKeySizeDES;
			model = new DefaultComboBoxModel<String>(listKeySize);
			choiceKeySize.setModel(model);
			System.out.println("DES");
			break;
		case "DESede":
			listKeySize = listKeySizeDESede;
			model = new DefaultComboBoxModel<String>(listKeySize);
			choiceKeySize.setModel(model);
			System.out.println("DESede");
			break;
		case "RC2":
			listKeySize = listKeySizeRC2;
			model = new DefaultComboBoxModel<String>(listKeySize);
			choiceKeySize.setModel(model);
			System.out.println("DESede");
			break;
		case "Blowfish":
			listKeySize = listKeySizeBlowfish;
			model = new DefaultComboBoxModel<String>(listKeySize);
			choiceKeySize.setModel(model);
			System.out.println("DESede");
			break;
		default:
			break;
		}
		
	}

	public String[] getListAlgorithms() {
		return listAlgorithms;
	}

	public void setListAlgorithms(String[] listAlgorithms) {
		this.listAlgorithms = listAlgorithms;
	}

	public String[] getListKeySize() {
		return listKeySize;
	}

	public void setListKeySize(String[] listKeySize) {
		this.listKeySize = listKeySize;
	}

	public String[] getListMode() {
		return listMode;
	}

	public void setListMode(String[] listMode) {
		this.listMode = listMode;
	}

	public String[] getListPadding() {
		return listPadding;
	}

	public void setListPadding(String[] listPadding) {
		this.listPadding = listPadding;
	}

	public JLabel getLblAlgorithms() {
		return lblAlgorithms;
	}

	public void setLblAlgorithms(JLabel lblAlgorithms) {
		this.lblAlgorithms = lblAlgorithms;
	}

	public JLabel getLblKeySize() {
		return lblKeySize;
	}

	public void setLblKeySize(JLabel lblKeySize) {
		this.lblKeySize = lblKeySize;
	}

	public JLabel getLblMode() {
		return lblMode;
	}

	public void setLblMode(JLabel lblMode) {
		this.lblMode = lblMode;
	}

	public JLabel getLblpadding() {
		return lblpadding;
	}

	public void setLblpadding(JLabel lblpadding) {
		this.lblpadding = lblpadding;
	}

	

	public String[] getListKeySizeAES() {
		return listKeySizeAES;
	}

	public void setListKeySizeAES(String[] listKeySizeAES) {
		this.listKeySizeAES = listKeySizeAES;
	}

	public String[] getListKeySizeDES() {
		return listKeySizeDES;
	}

	public void setListKeySizeDES(String[] listKeySizeDES) {
		this.listKeySizeDES = listKeySizeDES;
	}

	public String[] getListKeySizeDESede() {
		return listKeySizeDESede;
	}

	public void setListKeySizeDESede(String[] listKeySizeDESede) {
		this.listKeySizeDESede = listKeySizeDESede;
	}

	public JComboBox getChoiceAlgorithms() {
		return choiceAlgorithms;
	}

	public void setChoiceAlgorithms(JComboBox choiceAlgorithms) {
		this.choiceAlgorithms = choiceAlgorithms;
	}

	public JComboBox getChoiceKeySize() {
		return choiceKeySize;
	}

	public void setChoiceKeySize(JComboBox choiceKeySize) {
		this.choiceKeySize = choiceKeySize;
	}

	public JComboBox getChoiceMode() {
		return choiceMode;
	}

	public void setChoiceMode(JComboBox choiceMode) {
		this.choiceMode = choiceMode;
	}

	public JComboBox getChoicePadding() {
		return choicePadding;
	}

	public void setChoicePadding(JComboBox choicePadding) {
		this.choicePadding = choicePadding;
	}

	public Dimension getDimlbl() {
		return dimlbl;
	}

	public void setDimlbl(Dimension dimlbl) {
		this.dimlbl = dimlbl;
	}

	

	public Dimension getDimContainer() {
		return dimContainer;
	}

	public void setDimContainer(Dimension dimContainer) {
		this.dimContainer = dimContainer;
	}

	public JPanel getPnAlgorithms() {
		return pnAlgorithms;
	}

	public void setPnAlgorithms(JPanel pnAlgorithms) {
		this.pnAlgorithms = pnAlgorithms;
	}

	public JPanel getPnKeySize() {
		return pnKeySize;
	}

	public void setPnKeySize(JPanel pnKeySize) {
		this.pnKeySize = pnKeySize;
	}

	public JPanel getPnMode() {
		return pnMode;
	}

	public void setPnMode(JPanel pnMode) {
		this.pnMode = pnMode;
	}

	public JPanel getPnPadding() {
		return pnPadding;
	}

	public void setPnPadding(JPanel pnPadding) {
		this.pnPadding = pnPadding;
	}

	public static void main(String[] args) {

	}
}
