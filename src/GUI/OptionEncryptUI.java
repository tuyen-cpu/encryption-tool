package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;

public class OptionEncryptUI extends JPanel implements ActionListener {
	JButton btnChooseInput, btnChooseOutput;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer, pnKeyField, pnKeyFile, pnFileInput,
			pnFileOutput;
	Dimension dimContainer, dimPlainText, dimRadioButton, dimBtnChoose;
	JFileChooser fileKey;
	JLabel lblFileInput, lblFileOutput;
	JTextArea txtPlain, txtCipher;
	JScrollPane scrollPlain, scrollCipher;
	private JLabel label;

	public OptionEncryptUI() {
		dimContainer = new Dimension(740, 250);
		dimRadioButton = new Dimension(60, 20);
		dimBtnChoose = new Dimension(100, 30);

		rdField = new JRadioButton("String");
		rdFile = new JRadioButton("File");

		lblFileInput = new JLabel();
		lblFileOutput = new JLabel();

		pnFileOutput = new JPanel();
		pnFileInput = new JPanel();

		btnChooseInput = new JButton("File Input");
		btnChooseOutput = new JButton("File Output");

		pnRadio = new JPanel();
		pnContainer = new JPanel();
		pnKeyField = new JPanel();
		pnKeyFile = new JPanel();
		fileKey = new JFileChooser();
		txtPlain = new JTextArea(7, 33);
		txtCipher = new JTextArea(7, 33);
		// remove focus painted button
		btnChooseInput.setFocusPainted(false);
		btnChooseOutput.setFocusPainted(false);
		scrollPlain = new JScrollPane(txtPlain);
		scrollCipher = new JScrollPane(txtCipher);
		// default select radio
		rdField.setSelected(true);
		// set Font
		rdField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));

		btnChooseInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnChooseOutput.setFont(new Font("Dialog", Font.PLAIN, 12));

		lblFileInput.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFileOutput.setFont(new Font("Dialog", Font.PLAIN, 14));

		txtPlain.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtCipher.setFont(new Font("Monospaced", Font.PLAIN, 16));

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
		pnKeyField.setPreferredSize(new Dimension(700, 200));

		// add event radio button
		rdField.addActionListener(this);
		rdFile.addActionListener(this);

		// lblKeyFile.setBorder(BorderFactory.createEtchedBorder());
		lblFileInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblFileOutput.setBorder(new EmptyBorder(0, 0, 0, 10));

		btnChooseInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile(lblFileInput);

			}
		});
		btnChooseOutput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile(lblFileOutput);

			}
		});
		// set Margin top
		pnFileInput.setBorder(new EmptyBorder(30, 0, 0, 0));
		pnFileOutput.setBorder(new EmptyBorder(30, 0, 0, 0));
		// set layout panel
		setLayout(new BorderLayout());

		// add component
		pnRadio.add(rdField);
		pnRadio.add(rdFile);
		add(pnRadio, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		pnKeyField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pnKeyField.add(scrollPlain);
		pnKeyField.add(scrollCipher);
		pnContainer.setLayout(new BorderLayout());

		 pnContainer.add(pnKeyField);
//		pnContainer.add(pnKeyFile);

		pnFileOutput.add(lblFileOutput);
		pnFileOutput.add(btnChooseOutput);
		pnKeyFile.setLayout(new BorderLayout());

		pnFileInput.add(lblFileInput);
		pnFileInput.add(btnChooseInput);

		pnKeyFile.add(pnFileInput, BorderLayout.NORTH);
		pnKeyFile.add(pnFileOutput, BorderLayout.CENTER);

		// set border title
		Border blackline = BorderFactory
				.createTitledBorder("Encrypt or Decrypt");
		setBorder(blackline);
		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));
	}

	public void copyIntoClipBoard(String txt) {
		StringSelection stringSelection = new StringSelection(txt);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public void openFile(JLabel c) {
		int select = fileKey.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			System.out.println("file: " + fileKey.getSelectedFile().getName());
			c.setText("" + fileKey.getSelectedFile().getName());
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
			lblFileInput.setText("");
			pnContainer.add(pnKeyFile);
			pnContainer.revalidate();
			pnContainer.repaint();

		}

	}
}
