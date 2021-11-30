package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

import algorithms.ColorPublic;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.io.File;

public class OptionKeyUI extends JPanel implements ActionListener {
	JButton btnCreateKey, btnCopy, btnChooseFile;
	JTextField txtKey;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer, pnKeyField, pnKeyFile;
	Dimension dimContainer, dimKeyField, dimBtnCreateKey, dimBtnCopyKey,
			dimRadioButton;
	JFileChooser fileKey;
	JLabel lblKeyFile;
	File fileInputKey;

	public OptionKeyUI() {
		// Create component
		rdField = new JRadioButton("Key");
		rdFile = new JRadioButton("Import key");
		rdField.setFocusPainted(false);
		rdFile.setFocusPainted(false);
		btnCreateKey = new JButton("Create Key");
		btnCopy = new JButton("Copy key");
		btnChooseFile = new JButton("Choose file");
		lblKeyFile = new JLabel();
		txtKey = new JTextField();
		pnRadio = new JPanel();
		pnContainer = new JPanel();
		pnKeyField = new JPanel();
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		// remove focus painted button
		btnCreateKey.setFocusPainted(false);
		btnCopy.setFocusPainted(false);
		btnChooseFile.setFocusPainted(false);

		dimContainer = new Dimension(740, 108);
		dimKeyField = new Dimension(500, 30);
		dimBtnCreateKey = new Dimension(100, 30);
		dimBtnCopyKey = new Dimension(80, 30);
		dimRadioButton = new Dimension(60, 20);

		// default select radio
		rdField.setSelected(true);
		// set Font
		rdField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCreateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnChooseFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtKey.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblKeyFile.setFont(new Font("Dialog", Font.PLAIN, 14));
		// action command
		rdField.setActionCommand("rdField");
		rdFile.setActionCommand("rdFile");
		// Group radio button
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdField);
		bg.add(rdFile);

		txtKey.setPreferredSize(dimKeyField);
		btnCreateKey.setPreferredSize(dimBtnCreateKey);
		btnCopy.setPreferredSize(dimBtnCopyKey);
		btnChooseFile.setPreferredSize(dimBtnCreateKey);
		rdField.setPreferredSize(dimRadioButton);
		rdFile.setPreferredSize(new Dimension(100, 20));
		setPreferredSize(dimContainer);
		// add event radio button
		rdField.addActionListener(this);
		rdFile.addActionListener(this);
		btnChooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile();
			}
		});
		btnCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt = txtKey.getText();
				if (!txt.equals(""))
					copyIntoClipBoard(txt);

			}
		});
		
		//set color
		pnContainer.setBackground( Color.decode(ColorPublic.BACKGROUND_COLOR));
		pnKeyField.setBackground( Color.decode(ColorPublic.BACKGROUND_COLOR));
		pnKeyFile.setBackground( Color.decode(ColorPublic.BACKGROUND_COLOR));
		pnRadio.setBackground( Color.decode(ColorPublic.BACKGROUND_COLOR));
		// lblKeyFile.setBorder(BorderFactory.createEtchedBorder());
		lblKeyFile.setBorder(new EmptyBorder(0, 0, 0, 10));
		// set layout panel
		setLayout(new BorderLayout());
		// add component
		pnRadio.add(rdField);
		pnRadio.add(rdFile);
		add(pnRadio, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		pnKeyField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnKeyField.add(btnCreateKey);
		pnKeyField.add(txtKey);
		pnKeyField.add(btnCopy);
		pnContainer.add(pnKeyField);
		// pnContainer.add(pnKeyFile);
		pnKeyFile.setLayout(new BorderLayout());
		pnKeyFile.add(lblKeyFile, BorderLayout.WEST);
		pnKeyFile.add(btnChooseFile, BorderLayout.CENTER);
		// set border title
		Border blackline = BorderFactory.createTitledBorder("Key");
		setBorder(blackline);
		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));
	}

	public void copyIntoClipBoard(String txt) {
		StringSelection stringSelection = new StringSelection(txt);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public void openFile() {
		int select = fileKey.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			System.out.println("file: " + fileKey.getSelectedFile().getName());
			lblKeyFile.setText("" + fileKey.getSelectedFile().getName());
			fileInputKey = fileKey.getSelectedFile();
		} else {
			System.out.println("Cancel");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rdField")) {
			pnContainer.removeAll();
			pnContainer.add(pnKeyField);
			pnContainer.revalidate();
			pnContainer.repaint();
		} else {
			// fileKey.showSaveDialog(null);
			pnContainer.removeAll();

			pnContainer.add(pnKeyFile);
			pnContainer.revalidate();
			pnContainer.repaint();

		}

	}

	public JButton getBtnCreateKey() {
		return btnCreateKey;
	}

	public void setBtnCreateKey(JButton btnCreateKey) {
		this.btnCreateKey = btnCreateKey;
	}

	public JButton getBtnCopy() {
		return btnCopy;
	}

	public void setBtnCopy(JButton btnCopy) {
		this.btnCopy = btnCopy;
	}

	public JButton getBtnChooseFile() {
		return btnChooseFile;
	}

	public void setBtnChooseFile(JButton btnChooseFile) {
		this.btnChooseFile = btnChooseFile;
	}

	public String getTxtKey() {
		return txtKey.getText();
	}

	public void setTxtKey(String txtKey) {
		this.txtKey.setText(txtKey);
	}

	public JRadioButton getRdField() {
		return rdField;
	}

	public void setRdField(JRadioButton rdField) {
		this.rdField = rdField;
	}

	public JRadioButton getRdFile() {
		return rdFile;
	}

	public void setRdFile(JRadioButton rdFile) {
		this.rdFile = rdFile;
	}

	public JPanel getPnRadio() {
		return pnRadio;
	}

	public void setPnRadio(JPanel pnRadio) {
		this.pnRadio = pnRadio;
	}

	public JPanel getPnContainer() {
		return pnContainer;
	}

	public void setPnContainer(JPanel pnContainer) {
		this.pnContainer = pnContainer;
	}

	public JPanel getPnKeyField() {
		return pnKeyField;
	}

	public void setPnKeyField(JPanel pnKeyField) {
		this.pnKeyField = pnKeyField;
	}

	public JPanel getPnKeyFile() {
		return pnKeyFile;
	}

	public void setPnKeyFile(JPanel pnKeyFile) {
		this.pnKeyFile = pnKeyFile;
	}

	public Dimension getDimContainer() {
		return dimContainer;
	}

	public void setDimContainer(Dimension dimContainer) {
		this.dimContainer = dimContainer;
	}

	public Dimension getDimKeyField() {
		return dimKeyField;
	}

	public void setDimKeyField(Dimension dimKeyField) {
		this.dimKeyField = dimKeyField;
	}

	public Dimension getDimBtnCreateKey() {
		return dimBtnCreateKey;
	}

	public void setDimBtnCreateKey(Dimension dimBtnCreateKey) {
		this.dimBtnCreateKey = dimBtnCreateKey;
	}

	public Dimension getDimBtnCopyKey() {
		return dimBtnCopyKey;
	}

	public void setDimBtnCopyKey(Dimension dimBtnCopyKey) {
		this.dimBtnCopyKey = dimBtnCopyKey;
	}

	public Dimension getDimRadioButton() {
		return dimRadioButton;
	}

	public void setDimRadioButton(Dimension dimRadioButton) {
		this.dimRadioButton = dimRadioButton;
	}

	public JFileChooser getFileKey() {
		return fileKey;
	}

	public void setFileKey(JFileChooser fileKey) {
		this.fileKey = fileKey;
	}

	public JLabel getLblKeyFile() {
		return lblKeyFile;
	}

	public void setLblKeyFile(JLabel lblKeyFile) {
		this.lblKeyFile = lblKeyFile;
	}

	public File getFileInputKey() {
		return fileInputKey;
	}

	public void setFileInputKey(File fileInputKey) {
		this.fileInputKey = fileInputKey;
	}
	
}
