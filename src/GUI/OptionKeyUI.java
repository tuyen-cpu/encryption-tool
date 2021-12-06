package GUI;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OptionKeyUI extends JPanel implements ActionListener {
	JButton btnCreateKey, btnCopy, btnChooseFile;
	JTextField txtKey;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer,pnKey, pnKeyField, pnKeyFile,pnContainerRadio;
	Dimension dimContainer, dimKeyField, dimBtnCreateKey, dimBtnCopyKey,
			dimRadioButton;
	JFileChooser fileKey;
	File fileInputKey;

	public OptionKeyUI() {
		btnCreateKey = new JButton("Create Key");
		pnContainerRadio = new JPanel();
		btnCopy = new JButton();
		pnKey =new JPanel(new BorderLayout());
		btnCopy.setIcon(new ImageIcon(this.getClass().getResource("/img/copy.png")));
		btnChooseFile = new JButton("Choose file");
		txtKey = new JTextField();
		pnRadio = new JPanel();
		pnContainer = new JPanel();
		pnKeyField = new JPanel(new BorderLayout());
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		// remove focus painted button
		btnCreateKey.setFocusPainted(false);
		btnCopy.setFocusPainted(false);
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
		btnCopy.setPreferredSize(dimBtnCopyKey);
		btnChooseFile.setPreferredSize(dimBtnCreateKey);
	
		setPreferredSize(dimContainer);
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
		
	

		
		setBorder(new EmptyBorder(10,0,0,0));
		pnKeyFile.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnKeyField.setBorder(new EmptyBorder(0, 20, 0, 20));
		// set layout panel
		setLayout(new BorderLayout());
		pnRadio.setLayout(new BorderLayout(0, 0));
		GridBagLayout gbl_pnContainerRadio = new GridBagLayout();
		gbl_pnContainerRadio.columnWidths = new int[]{293, 57, 81, 0};
		gbl_pnContainerRadio.rowHeights = new int[]{24, 0};
		gbl_pnContainerRadio.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnContainerRadio.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnContainerRadio.setLayout(gbl_pnContainerRadio);
		// Create component
		rdField = new JRadioButton("String");
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
				GridBagConstraints gbc_rdField = new GridBagConstraints();
				gbc_rdField.anchor = GridBagConstraints.NORTHWEST;
				gbc_rdField.insets = new Insets(0, 0, 0, 5);
				gbc_rdField.gridx = 1;
				gbc_rdField.gridy = 0;
				pnContainerRadio.add(rdField, gbc_rdField);
		pnRadio.add(pnContainerRadio);
		rdFile = new JRadioButton("Import key");
		rdFile.setFocusPainted(false);
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setActionCommand("rdFile");
		bg.add(rdFile);
		rdFile.addActionListener(this);
		GridBagConstraints gbc_rdFile = new GridBagConstraints();
		gbc_rdFile.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdFile.gridx = 2;
		gbc_rdFile.gridy = 0;
		pnContainerRadio.add(rdFile, gbc_rdFile);
		pnContainerRadio.setBorder(new EmptyBorder(0,22,0,0));
		add(pnRadio, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		pnContainer.setLayout(new BorderLayout(0, 0));
		
		
		pnKey.add(btnCreateKey,BorderLayout.WEST);
		pnKey.add(txtKey);
		pnKeyField.add(pnKey);
		
		pnKeyField.add(btnCopy,BorderLayout.EAST);
		pnContainer.add(pnKeyField);
//		 pnContainer.add(pnKeyFile);
		pnKeyFile.setLayout(new BorderLayout());
		
		pnKeyFile.add(btnChooseFile);
		// set border title
//		Border blackline = BorderFactory.createTitledBorder("Key");
//		setBorder(blackline);
//		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
//				Font.PLAIN, 13));
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
			btnChooseFile.setText("" + fileKey.getSelectedFile().getName());
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



	public File getFileInputKey() {
		return fileInputKey;
	}

	public void setFileInputKey(File fileInputKey) {
		this.fileInputKey = fileInputKey;
	}
	
}
