package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class TabAsymmetric extends JPanel implements ActionListener {
	private String[] listAlgorithms = { "RSA" };
	private String[] listMode = { "ECB" };
	private String[] listPadding = { "PKCS1Padding",
			"OAEPWithSHA-1AndMGF1Padding", "OAEPWithSHA-256AndMGF1Padding" };
	private String[] listKeySize = { "1024", "2048", "3072", "4069" };
	private JPanel pnOption, pnKey, pnEncypt, pnAlgorithms, pnKeySize, pnMode,
			pnPadding, pnOptionKey, pnContainerKey, pnFieldPublic,
			pnFieldPrivate, pnFilePublic, pnFilePrivte, pnFileKey;
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblPadding,
			lblInputKeyPublic, lblInputKeyPrivate;
	private Choice choiceAlgorithms, choiceKeySize, choiceMode, choicePadding;
	private JButton btnCreateKey, btnImportPublicKey, btnImportPrivatekey,
			btnCopyPrivateKey, btnCopyPublicKey;
	private JRadioButton rdString, rdFile;
	private JTextField txtPublicKey, txtPrivateKey;
	private Dimension dmTxtKey, dmBtnCopy, dmBtnCreate, dmChoose;
	private OptionEncryptUI optionEncryptUI;
	File fileChoose;
	JFileChooser jFileChoose;

	public TabAsymmetric() {
		setLayout(new BorderLayout());
		createComponent();
		addComponent();

		setChoice(listAlgorithms, choiceAlgorithms);
		setChoice(listKeySize, choiceKeySize);
		setChoice(listMode, choiceMode);
		setChoice(listPadding, choicePadding);
		addHandle();
	}

	public void createComponent() {
		dmTxtKey = new Dimension(510, 40);
		dmBtnCopy = new Dimension(60, 40);
		dmBtnCreate = new Dimension(120, 40);
		dmChoose = new Dimension(140, 40);
		pnOption = new JPanel();
		pnAlgorithms = new JPanel();
		pnKeySize = new JPanel();
		pnMode = new JPanel();
		pnPadding = new JPanel();
		pnKey = new JPanel(new BorderLayout());
		pnEncypt = new JPanel();
		lblAlgorithms = new JLabel("Algorithms:");
		lblKeySize = new JLabel("Key size:");
		lblMode = new JLabel("Mode");
		lblPadding = new JLabel("Padding");
		choiceAlgorithms = new Choice();
		choiceKeySize = new Choice();
		choiceMode = new Choice();
		choicePadding = new Choice();
		btnCreateKey = new JButton("Create key");
		pnOptionKey = new JPanel();
		pnContainerKey = new JPanel(new BorderLayout());
		rdString = new JRadioButton("String");
		rdFile = new JRadioButton("File");
		rdString.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdString);
		bg.add(rdFile);
		txtPublicKey = new JTextField();
		txtPrivateKey = new JTextField();
		btnCopyPrivateKey = new JButton();
		btnCopyPublicKey = new JButton();
		btnCopyPrivateKey.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnCopyPublicKey.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		pnFieldPublic = new JPanel();
		pnFieldPrivate = new JPanel();
		pnFilePublic = new JPanel();
		pnFilePrivte = new JPanel();
		pnFileKey = new JPanel(new BorderLayout());
		lblInputKeyPublic = new JLabel();
		lblInputKeyPrivate = new JLabel();
		btnImportPublicKey = new JButton("Choose key public");
		btnImportPrivatekey = new JButton("Choose key private");
		optionEncryptUI = new OptionEncryptUI();
		jFileChoose = new JFileChooser();
	}

	public void addComponent() {

		pnAlgorithms.add(lblAlgorithms);
		pnAlgorithms.add(choiceAlgorithms);
		pnKeySize.add(lblKeySize);
		pnKeySize.add(choiceKeySize);
		pnMode.add(lblMode);
		pnMode.add(choiceMode);
		pnPadding.add(lblPadding);
		pnPadding.add(choicePadding);
		pnOption.add(pnAlgorithms);
		pnOption.add(pnAlgorithms);
		pnOption.add(pnKeySize);
		pnOption.add(pnMode);
		pnOption.add(pnPadding);
		pnKey.add(pnOptionKey, BorderLayout.NORTH);
		pnKey.add(pnContainerKey);
		pnOptionKey.add(rdString);
		pnOptionKey.add(rdFile);

		pnFieldPublic.add(btnCreateKey);
		pnFieldPublic.add(txtPublicKey);
		pnFieldPublic.add(btnCopyPublicKey);

		pnFieldPrivate.add(txtPrivateKey);
		pnFieldPrivate.add(btnCopyPrivateKey);

		pnContainerKey.add(pnFieldPublic, BorderLayout.NORTH);
		pnContainerKey.add(pnFieldPrivate);

		pnFilePrivte.add(lblInputKeyPrivate);
		pnFilePrivte.add(btnImportPrivatekey);

		pnFilePublic.add(lblInputKeyPublic);
		pnFilePublic.add(btnImportPublicKey);

		pnFileKey.add(pnFilePublic, BorderLayout.NORTH);
		pnFileKey.add(pnFilePrivte);
		add(pnOption, BorderLayout.NORTH);
		add(pnKey, BorderLayout.CENTER);
		add(optionEncryptUI, BorderLayout.SOUTH);
		optionEncryptUI.getTxtPlain().setSize(7, 50);
		optionEncryptUI.setPreferredSize(new Dimension(750, 265));
		Border optionLine = BorderFactory.createTitledBorder("Option");
		pnOption.setBorder(optionLine);
		setBorder(new EmptyBorder(5, 10, 10, 10));
		((TitledBorder) pnOption.getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));
		Border keyLine = BorderFactory.createTitledBorder("Key");
		pnKey.setBorder(keyLine);
		((TitledBorder) pnKey.getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));
		lblAlgorithms.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblKeySize.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMode.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblPadding.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnCopyPrivateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCopyPublicKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnImportPrivatekey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnImportPublicKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblInputKeyPrivate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblInputKeyPublic.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCreateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		pnAlgorithms.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnKeySize.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnMode.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnPadding.setBorder(new EmptyBorder(0, 5, 0, 5));
		choiceAlgorithms.setFocusable(false);
		choiceKeySize.setFocusable(false);
		choiceMode.setFocusable(false);
		choicePadding.setFocusable(false);

		pnFieldPrivate.setBorder(new EmptyBorder(-5, 130, 0, 5));
		txtPublicKey.setPreferredSize(dmTxtKey);
		txtPrivateKey.setPreferredSize(dmTxtKey);
		btnCreateKey.setPreferredSize(dmBtnCreate);
		btnCopyPublicKey.setPreferredSize(dmBtnCopy);
		btnCopyPrivateKey.setPreferredSize(dmBtnCopy);
		btnImportPublicKey.setPreferredSize(dmChoose);
		btnImportPrivatekey.setPreferredSize(dmChoose);
		optionEncryptUI.getBtnChooseInput().setPreferredSize(dmChoose);
		optionEncryptUI.getBtnChooseOutput().setPreferredSize(dmChoose);
	}

	public void addHandle() {
		rdFile.addActionListener(this);
		rdString.addActionListener(this);
		rdString.setActionCommand("rdString");
		rdFile.setActionCommand("rdFile");
		btnImportPrivatekey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					fileChoose = openFile();
					lblInputKeyPrivate.setText(fileChoose.getAbsolutePath());
				}catch(Exception ex){
					System.out.println("Cancel choose file");
				}

			}
		});
		btnImportPublicKey.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					fileChoose = openFile();
					lblInputKeyPublic.setText(fileChoose.getAbsolutePath());
				}catch(Exception ex){
					System.out.println("Cancel choose file");
				}
				
			}
		});
	}

	public File openFile() {
		int select = jFileChoose.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			return jFileChoose.getSelectedFile();
		} else {
			System.out.println("Cancel");
			return null;
		}
	}

	public void setChoice(String[] listValue, Choice choice) {
		choice.removeAll();
		for (String algo : listValue) {
			choice.add(algo);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rdString")) {
			System.out.println("rdString");
			pnKey.remove(pnFileKey);
			pnKey.add(pnContainerKey);
			pnKey.revalidate();
			pnKey.repaint();
		} else {
			System.out.println("rdFile");
			pnKey.remove(pnContainerKey);
			pnKey.add(pnFileKey);
			pnKey.revalidate();
			pnKey.repaint();

		}

	}
}
