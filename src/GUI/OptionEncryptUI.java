package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.io.File;

public class OptionEncryptUI extends JPanel implements ActionListener {
	JButton btnChooseInput, btnChooseOutput, btnCopy;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer, pnKeyField, pnKeyFile, pnFileInput,
			pnFileOutput, pnCipher, pnCipherContainer, pnBtnOutput, pnBtnInput;
	OptionSelectEncryptOrDecrypt pnSelectEnOrDe;
	Dimension dimContainer, dimPlainText, dimRadioButton, dimBtnChoose;
	JFileChooser fileKey;
	JLabel lblResult;
	JTextArea txtPlain, txtCipher;
	JScrollPane scrollPlain, scrollCipher;
	File fileInput, fileOutput;

	public OptionEncryptUI() {
		dimContainer = new Dimension(740, 360);
		dimRadioButton = new Dimension(60, 20);
		dimBtnChoose = new Dimension(100, 40);
		pnBtnOutput = new JPanel();
		pnBtnInput = new JPanel();
		pnSelectEnOrDe = new OptionSelectEncryptOrDecrypt();

		rdField = new JRadioButton("String");
		rdFile = new JRadioButton("File");

		pnFileOutput = new JPanel(new BorderLayout());
		pnFileInput = new JPanel(new BorderLayout());
		pnCipher = new JPanel(new BorderLayout());
		btnChooseInput = new JButton("File Input");
		btnChooseOutput = new JButton("File Output");
		btnCopy = new JButton();
		btnCopy.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnCopy.setPreferredSize(new Dimension(60, 40));
		pnRadio = new JPanel();
		pnContainer = new JPanel();
		pnKeyField = new JPanel();
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		txtPlain = new JTextArea(5, 60);
		txtCipher = new JTextArea(1, 60);
		pnCipherContainer = new JPanel(new BorderLayout());
		lblResult = new JLabel("Result:", SwingConstants.CENTER);

		// remove focus painted button
		btnChooseInput.setFocusPainted(false);
		btnChooseOutput.setFocusPainted(false);
		rdField.setFocusPainted(false);
		rdFile.setFocusPainted(false);
		scrollPlain = new JScrollPane(txtPlain);
		scrollCipher = new JScrollPane(txtCipher);
		// scrollPlain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCipher
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// default select radio
		rdField.setSelected(true);
		// set Font
		rdField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));

		btnChooseInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnChooseOutput.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtPlain.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtCipher.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblResult.setFont(new Font("Dialog", Font.PLAIN, 14));
		// action command
		rdField.setActionCommand("rdField");
		rdFile.setActionCommand("rdFile");
		// Group radio button
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdField);
		bg.add(rdFile);
		// set Size component
		rdField.setPreferredSize(dimRadioButton);
		rdFile.setPreferredSize(dimRadioButton);
		setPreferredSize(dimContainer);
		btnChooseInput.setPreferredSize(dimBtnChoose);
		btnChooseOutput.setPreferredSize(dimBtnChoose);
		pnKeyField.setPreferredSize(new Dimension(700, 100));

		// add event radio button
		rdField.addActionListener(this);
		rdFile.addActionListener(this);

		// lblKeyFile.setBorder(BorderFactory.createEtchedBorder());
		// lblFileInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		// lblFileOutput.setBorder(new EmptyBorder(0, 0, 0, 10));

		// set Margin top
		btnCopy.setBounds(0, 10, 0, 0);
		pnCipher.setBorder(new EmptyBorder(5, 0, 0, 0));
		pnFileInput.setBorder(new EmptyBorder(30, 0, 0, 0));
		pnFileOutput.setBorder(new EmptyBorder(30, 0, 0, 0));
		// set layout panel
		setLayout(new BorderLayout());

		// add component
		pnRadio.add(rdField);
		pnRadio.add(rdFile);
		add(pnRadio, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		pnKeyField.setLayout(new BorderLayout());
		pnCipherContainer.add(scrollCipher);
		pnCipherContainer.add(lblResult, BorderLayout.WEST);
		lblResult.setPreferredSize(new Dimension(60, 40));
		pnCipher.add(pnCipherContainer);
		pnCipher.add(btnCopy, BorderLayout.EAST);
		pnKeyField.add(scrollPlain);
		pnKeyField.add(pnCipher, BorderLayout.SOUTH);
		pnContainer.setLayout(new BorderLayout());

		pnContainer.add(pnKeyField);
		// pnContainer.add(pnKeyFile);
		pnBtnOutput.setLayout(new BorderLayout(0, 0));

		pnBtnOutput.add(btnChooseOutput);
		pnFileOutput.add(pnBtnOutput, BorderLayout.NORTH);
		pnKeyFile.setLayout(new BorderLayout());
		pnBtnInput.setLayout(new BorderLayout(0, 0));

		pnBtnInput.add(btnChooseInput);
		pnFileInput.add(pnBtnInput);

		pnKeyFile.add(pnFileInput, BorderLayout.NORTH);
		pnKeyFile.add(pnFileOutput, BorderLayout.CENTER);
		add(pnSelectEnOrDe, BorderLayout.SOUTH);
		// set border title
		lblResult.setBounds(0, 50, 0, 0);
		setBorder(new EmptyBorder(10, 20, 0, 20));
		// Border blackline = BorderFactory
		// .createTitledBorder("Encrypt or Decrypt");
		// setBorder(blackline);
		// ((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
		// Font.PLAIN, 13));
		addHandle();
	}

	public void addHandle() {
		btnChooseInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileInput = openFile();
					btnChooseInput.setText(fileInput.getAbsolutePath());
					;
				} catch (Exception e) {
					System.out.println("Cancel choose file");
				}
			}
		});
		btnChooseOutput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileOutput = openFile();
					btnChooseOutput.setText(fileOutput.getAbsolutePath());
				} catch (Exception e) {
					System.out.println("Cancel choose file");
				}

			}
		});
		btnCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				copyIntoClipBoard(txtCipher.getText());

			}
		});
	}

	public void copyIntoClipBoard(String txt) {
		StringSelection stringSelection = new StringSelection(txt);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	// get File from computer to App
	public File openFile() {
		int select = fileKey.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			return fileKey.getSelectedFile();
		} else {
			System.out.println("Cancel");
			return null;
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

	public JButton getBtnChooseInput() {
		return btnChooseInput;
	}

	public void setBtnChooseInput(JButton btnChooseInput) {
		this.btnChooseInput = btnChooseInput;
	}

	public JButton getBtnChooseOutput() {
		return btnChooseOutput;
	}

	public void setBtnChooseOutput(JButton btnChooseOutput) {
		this.btnChooseOutput = btnChooseOutput;
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

	public JPanel getPnFileInput() {
		return pnFileInput;
	}

	public void setPnFileInput(JPanel pnFileInput) {
		this.pnFileInput = pnFileInput;
	}

	public JPanel getPnFileOutput() {
		return pnFileOutput;
	}

	public void setPnFileOutput(JPanel pnFileOutput) {
		this.pnFileOutput = pnFileOutput;
	}

	public OptionSelectEncryptOrDecrypt getPnSelectEnOrDe() {
		return pnSelectEnOrDe;
	}

	public Dimension getDimContainer() {
		return dimContainer;
	}

	public void setDimContainer(Dimension dimContainer) {
		this.dimContainer = dimContainer;
	}

	public Dimension getDimPlainText() {
		return dimPlainText;
	}

	public void setDimPlainText(Dimension dimPlainText) {
		this.dimPlainText = dimPlainText;
	}

	public Dimension getDimRadioButton() {
		return dimRadioButton;
	}

	public void setDimRadioButton(Dimension dimRadioButton) {
		this.dimRadioButton = dimRadioButton;
	}

	public Dimension getDimBtnChoose() {
		return dimBtnChoose;
	}

	public void setDimBtnChoose(Dimension dimBtnChoose) {
		this.dimBtnChoose = dimBtnChoose;
	}

	public JFileChooser getFileKey() {
		return fileKey;
	}

	public void setFileKey(JFileChooser fileKey) {
		this.fileKey = fileKey;
	}

	public JTextArea getTxtPlain() {
		return txtPlain;
	}

	public void setTxtPlain(JTextArea txtPlain) {
		this.txtPlain = txtPlain;
	}

	public String getTxtCipher() {
		return this.txtCipher.getText();
	}

	public void setTxtCipher(String txtCipher) {
		this.txtCipher.setText(txtCipher);
	}

	public JScrollPane getScrollPlain() {
		return scrollPlain;
	}

	public void setScrollPlain(JScrollPane scrollPlain) {
		this.scrollPlain = scrollPlain;
	}

	public JScrollPane getScrollCipher() {
		return scrollCipher;
	}

	public void setScrollCipher(JScrollPane scrollCipher) {
		this.scrollCipher = scrollCipher;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public File getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(File fileOutput) {
		this.fileOutput = fileOutput;
	}

}
