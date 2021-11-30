package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import algorithms.ColorPublic;
public class OptionGeneralUI extends JPanel {

	private String[] listAlgorithms = { "AES", "DES", "DESede" };
	private String[] listKeySize = { "128", "192", "256" };
	private String[] listMode = { "CBC", "ECB" };
	private String[] listPadding = { "NoPadding", "PKCS5Padding" };
	private String[] listKeySizeAES = { "128", "192", "256" };
	private String[] listKeySizeDES = { "56" };
	private String[] listKeySizeDESede = { "168" };
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblpadding;
	Choice choiceAlgorithms, choiceKeySize, choiceMode, choicePadding;

	private Dimension dimlbl, dimContainer;
	private JPanel pnAlgorithms, pnKeySize, pnMode, pnPadding;

	public OptionGeneralUI() {
		dimContainer = new Dimension(740, 65);
		dimlbl = new Dimension(67, 30);
		

		choiceAlgorithms = new Choice();
		// choice.setBounds(100, 100, 150, 150);
		
		setChoice(listAlgorithms, choiceAlgorithms);
		choiceKeySize = new Choice();
		setChoice(listKeySize, choiceKeySize);
		choiceMode = new Choice();
		setChoice(listMode, choiceMode);
		choicePadding = new Choice();
		setChoice(listPadding, choicePadding);
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

		Border blackline = BorderFactory.createTitledBorder("Option");
		setBorder(blackline);
		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));

	}

	public void setChoice(String[] listValue, Choice choice) {
		choice.removeAll();
		for (String algo : listValue) {
			choice.add(algo);
		}
	}

	public void addComponentIntoPanelKeySize() {
		pnKeySize.add(lblKeySize);
		
		pnKeySize.add(choiceKeySize);
		pnKeySize.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		
	}

	public void changeContentChoice() {

		switch (choiceAlgorithms.getSelectedItem()) {
		case "AES":
			listKeySize = listKeySizeAES;
			setChoice(listKeySize, choiceKeySize);
			System.out.println("AES");
			break;
		case "DES":
			listKeySize = listKeySizeDES;
			setChoice(listKeySize, choiceKeySize);
			System.out.println("DES");
			break;
		case "DESede":
			listKeySize = listKeySizeDESede;
			setChoice(listKeySize, choiceKeySize);
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

	public Choice getChoiceAlgorithms() {
		return choiceAlgorithms;
	}

	public void setChoiceAlgorithms(Choice choiceAlgorithms) {
		this.choiceAlgorithms = choiceAlgorithms;
	}

	public Choice getChoiceKeySize() {
		return choiceKeySize;
	}

	public void setChoiceKeySize(Choice choiceKeySize) {
		this.choiceKeySize = choiceKeySize;
	}

	public Choice getChoiceMode() {
		return choiceMode;
	}

	public void setChoiceMode(Choice choiceMode) {
		this.choiceMode = choiceMode;
	}

	public Choice getChoicePadding() {
		return choicePadding;
	}

	public void setChoicePadding(Choice choicePadding) {
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
