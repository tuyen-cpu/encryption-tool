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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import algorithms.Symmetric;

import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import javax.swing.border.BevelBorder;

public class OptionEncryptUI extends JPanel implements ActionListener {
	private JButton btnChooseInput, btnChooseOutput, btnCopy, btnSave;
	private JRadioButton rdField, rdFile;
	private JPanel pnRadio, pnContainer, pnKeyField, pnKeyFile, pnFileInput,
			pnFileOutput, pnCipher, pnCipherContainer, pnBtnOutput, pnBtnInput,
			pnBtn;
	private OptionSelectEncryptOrDecrypt pnSelectEnOrDe;
	private Dimension dimContainer, dimPlainText, dimRadioButton, dimBtnChoose;
	private JFileChooser fileKey;
	private JLabel lblResult;
	private JTextArea txtPlain, txtCipher;
	private JScrollPane scrollPlain, scrollCipher;
	private File fileInput, fileOutput, fileSave;
	private static Base64.Decoder decoder = Base64.getDecoder();

	public OptionEncryptUI() {
		dimContainer = new Dimension(740, 360);
		dimRadioButton = new Dimension(60, 20);
		dimBtnChoose = new Dimension(100, 40);
		pnBtnOutput = new JPanel();
		pnBtnInput = new JPanel();
		pnBtn = new JPanel();
		pnSelectEnOrDe = new OptionSelectEncryptOrDecrypt();

		rdField = new JRadioButton("Encrypt with text");
		rdFile = new JRadioButton("Encrypt with file");

		pnFileOutput = new JPanel(new BorderLayout());
		pnFileInput = new JPanel(new BorderLayout());
		pnCipher = new JPanel(new BorderLayout());
		btnChooseInput = new JButton("File Input");
		btnChooseOutput = new JButton("File Output");
		btnCopy = new JButton();
		btnCopy.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnSave = new JButton();
		btnSave.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/icon-save.png")));
		btnCopy.setPreferredSize(new Dimension(60, 40));
		btnSave.setPreferredSize(new Dimension(60, 40));
		pnRadio = new JPanel();
		pnContainer = new JPanel();
		pnKeyField = new JPanel();
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		fileKey.setCurrentDirectory(fileKey.getFileSystemView()
				.getParentDirectory(new File("D:\\")));
		txtPlain = new JTextArea(5, 60);
		txtCipher = new JTextArea(4, 50);
		txtPlain.setLineWrap(true);
		txtPlain.setWrapStyleWord(true);
		txtCipher.setLineWrap(true);
		txtCipher.setWrapStyleWord(true);
		pnCipherContainer = new JPanel(new BorderLayout());
		lblResult = new JLabel("Result:", SwingConstants.CENTER);

		// remove focus painted button
		btnChooseInput.setFocusPainted(false);
		btnChooseOutput.setFocusPainted(false);
		rdField.setFocusPainted(false);
		rdFile.setFocusPainted(false);
		scrollPlain = new JScrollPane(txtPlain);
		scrollCipher = new JScrollPane(txtCipher);
		scrollPlain
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPlain
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollCipher
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollCipher
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
		// rdField.setPreferredSize(dimRadioButton);
		// rdFile.setPreferredSize(dimRadioButton);
		setPreferredSize(dimContainer);
		btnChooseInput.setPreferredSize(dimBtnChoose);
		btnChooseOutput.setPreferredSize(dimBtnChoose);
		pnKeyField.setPreferredSize(new Dimension(700, 100));

		// add event radio button
		rdField.addActionListener(this);
		rdFile.addActionListener(this);
		pnSelectEnOrDe.getRdEncrypt().addActionListener(this);
		pnSelectEnOrDe.getRdDecrypt().addActionListener(this);
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
		pnBtn.setLayout(new GridLayout(0, 1));
		pnBtn.add(btnCopy);
		pnBtn.add(btnSave);
		pnCipher.add(pnCipherContainer);

		pnCipher.add(pnBtn, BorderLayout.EAST);
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
	
		pnContainer.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(200, 200, 200), new Color(200, 200, 200)), new EmptyBorder(10, 10, 10, 10)));
setBorder(new EmptyBorder(20,20,20,20));
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
					boolean stop = true;
					do {
						fileOutput = openFile();
						if (fileOutput.exists()) {
							int dialogResult = JOptionPane.showConfirmDialog(
									MainGUI.frame,
									"File "
											+ fileOutput.getName()
											+ " already exists, do you want to replace this file?",
									"Warning", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (dialogResult == JOptionPane.YES_OPTION) {
								btnChooseOutput.setText(fileOutput
										.getAbsolutePath());
								stop = true;
								System.out.println("Replace file!");
							} else {
								stop = false;
								System.out.println("No replace file!");
							}
						} else {
							btnChooseOutput.setText(fileOutput
									.getAbsolutePath());
						}
					} while (!stop);
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
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!txtCipher.getText().equalsIgnoreCase("")) {
					saveFile();
				} else {
					JOptionPane
							.showMessageDialog(MainGUI.frame, "Empty result!",
									"Error", JOptionPane.ERROR_MESSAGE);
				}

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
		int select = fileKey.showOpenDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
			return fileKey.getSelectedFile();
		} else {
			System.out.println("Cancel");
			return null;
		}
	}

	public void saveFile() {
		int select = fileKey.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save into: "
					+ fileKey.getSelectedFile().getName());
			fileSave = fileKey.getSelectedFile();
			if (fileSave.exists()) {

				int dialogResult = JOptionPane.showConfirmDialog(this, "File "
						+ fileSave.getName()
						+ " already exists, do you want to replace this file?",
						"Warning", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {

					try (FileOutputStream fos = new FileOutputStream(fileSave)) {
						fos.write(decoder.decode(txtCipher.getText()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("replace!");
				} else {
					System.out.println("no replace!");
				}
			} else {
				try (FileOutputStream fos = new FileOutputStream(fileSave)) {
					fos.write(decoder.decode(txtCipher.getText()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Saved!");
			}

		} else {
			System.out.println("Cancel");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "rdField":
			pnContainer.removeAll();
			pnContainer.add(pnKeyField);
			pnContainer.revalidate();
			pnContainer.repaint();
			break;
		case "rdFile":
			pnContainer.removeAll();
			pnContainer.add(pnKeyFile);
			pnContainer.revalidate();
			pnContainer.repaint();
			break;
		case "rdEncrypt":
			rdField.setText("Encrypt with text");
			rdFile.setText("Encrypt with file");
			break;
		case "rdDecrypt":
			rdField.setText("Decrypt with text");
			rdFile.setText("Decrypt with file");
			break;
		default:
			break;
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
