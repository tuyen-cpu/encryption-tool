package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javafx.scene.control.Tooltip;
import javafx.stage.Popup;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import com.sun.org.apache.xpath.internal.FoundIndex;

public class OptionKeyUI extends JPanel implements ActionListener {
	JButton btnCreateKey, btnCopy, btnChooseFile, btnSave;
	JTextField txtKey;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer, pnKey, pnKeyField, pnKeyFile,
			pnContainerRadio, pnBtn;
	Dimension dimContainer, dimKeyField, dimBtnCreateKey, dimBtnCopyKey,
			dimRadioButton;
	JFileChooser fileKey;
	File fileInputKey, fileSave;

	public OptionKeyUI() {
		btnCreateKey = new JButton("Create Key");
		btnCreateKey.setToolTipText("Press to create key");
		pnContainerRadio = new JPanel();
		pnKey = new JPanel(new BorderLayout());
		btnChooseFile = new JButton("Choose file");
		btnChooseFile.setToolTipText("Choose a file from your computer");
		txtKey = new JTextField();
		txtKey.setToolTipText("Enter key");

		pnRadio = new JPanel();
		pnBtn = new JPanel();
		pnContainer = new JPanel();
		pnContainer.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, new Color(200, 200, 200),
				new Color(200, 200, 200)), new EmptyBorder(10, 10, 10, 10)));
		pnKeyField = new JPanel(new BorderLayout());
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		fileKey.setCurrentDirectory(fileKey.getFileSystemView()
				.getParentDirectory(new File("C:\\")));
		// remove focus painted button
		btnCreateKey.setFocusPainted(false);
		btnChooseFile.setFocusPainted(false);

		dimKeyField = new Dimension(550, 40);
		dimBtnCreateKey = new Dimension(100, 40);
		dimBtnCopyKey = new Dimension(60, 40);

		dimRadioButton = new Dimension(60, 20);
		btnCreateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnChooseFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtKey.setFont(new Font("Dialog", Font.PLAIN, 14));

		// Group radio button
		ButtonGroup bg = new ButtonGroup();

		txtKey.setPreferredSize(dimKeyField);
		btnCreateKey.setPreferredSize(dimBtnCreateKey);
		btnChooseFile.setPreferredSize(new Dimension(100, 40));

		setPreferredSize(dimContainer);
		btnChooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isStop = false;
				do {
					fileInputKey = openFile();

					if (fileInputKey.exists()) {
						btnChooseFile.setText(fileInputKey.getAbsolutePath());
						isStop = true;
					} else {
						JOptionPane.showMessageDialog(MainGUI.frame, "File "
								+ fileInputKey.getName() + " doesn't exist!",
								"Error", JOptionPane.ERROR_MESSAGE);
						System.out.println("File does not exist!");
						btnChooseFile.setText("Choose file");
						isStop = false;

					}
				} while (!isStop);

			}

		});
		
		txtKey.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				DialogCustom.stopDialog();
			}

			@Override
			public void focusGained(FocusEvent e) {
				if(txtKey.getText().trim().equals("")){
					DialogCustom.showDescription(txtKey, "Enter key");				
				}

			}
		});
		setBorder(new EmptyBorder(20, 20, 0, 20));
		// set layout panel
		setLayout(new BorderLayout());
		pnRadio.setLayout(new BorderLayout(0, 0));
		//
		// Create component
		rdField = new JRadioButton("Text key");
		rdField.setFocusPainted(false);

		// default select radio
		rdField.setSelected(true);
		// set Font
		rdField.setFont(new Font("Dialog", Font.PLAIN, 12));
		// action command
		rdField.setActionCommand("rdField");
		bg.add(rdField);
		// add event radio button
		rdField.addActionListener(this);
		// add component

		pnContainerRadio.add(rdField);
		pnRadio.add(pnContainerRadio);
		rdFile = new JRadioButton("Import file key");
		rdFile.setFocusPainted(false);
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setActionCommand("rdFile");
		bg.add(rdFile);
		rdFile.addActionListener(this);

		pnContainerRadio.add(rdFile);
		pnContainerRadio.setBorder(new EmptyBorder(0, 22, 0, 0));
		add(pnRadio, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		pnContainer.setLayout(new BorderLayout(0, 0));

		pnKey.add(btnCreateKey, BorderLayout.WEST);
		pnKey.add(txtKey);
		btnCopy = new JButton();
		btnCopy.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnCopy.setToolTipText("Copy");
		btnCopy.setFocusPainted(false);
		btnSave = new JButton();
		btnSave.setToolTipText("Save");
		btnSave.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/icon-save.png")));
		btnCopy.setPreferredSize(dimBtnCopyKey);
		btnCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt = txtKey.getText();
				copyIntoClipBoard(txt);

			}
		});
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtKey.getText().equalsIgnoreCase("")) {
					saveFile();
				} else {
					JOptionPane.showMessageDialog(MainGUI.frame, "Empty key!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		pnBtn.setLayout(new BorderLayout(0, 0));

		btnSave.setFocusPainted(false);
		btnSave.setPreferredSize(dimBtnCopyKey);
		pnBtn.add(btnSave, BorderLayout.WEST);
		pnBtn.add(btnCopy);
		pnKeyField.add(pnKey);
		pnKeyField.add(pnBtn, BorderLayout.EAST);
		pnContainer.add(pnKeyField);
		// pnContainer.add(pnKeyFile);
		pnKeyFile.setLayout(new BorderLayout());

		pnKeyFile.add(btnChooseFile);
		// set border title
		// Border blackline = BorderFactory.createTitledBorder("Key");
		// setBorder(blackline);
		// ((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
		// Font.PLAIN, 13));
	}

	public void copyIntoClipBoard(String txt) {
		StringSelection stringSelection = new StringSelection(txt);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		DialogCustom.showShortDialog(btnCopy, "Copied!");
	}

	// public void openFile() {
	//
	// int select = fileKey.showOpenDialog(this);
	// if (select == JFileChooser.APPROVE_OPTION) {
	// System.out.println("file: " + fileKey.getSelectedFile().getName());
	// btnChooseFile.setText("" + fileKey.getSelectedFile().getName());
	// fileInputKey = fileKey.getSelectedFile();
	// } else {
	// System.out.println("Cancel");
	// }
	//
	// }
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
					writeFile(txtKey.getText(), fileSave);
					System.out.println("replace!");
				} else {
					System.out.println("no replace!");
				}
			} else {
				writeFile(txtKey.getText(), fileSave);
				System.out.println("Saved!");
			}

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

	// Write txt into file
	public void writeFile(String txt, File file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(txt);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public File getFileInputKey() {
		return fileInputKey;
	}

	public void setFileInputKey(File fileInputKey) {
		this.fileInputKey = fileInputKey;
	}

}
