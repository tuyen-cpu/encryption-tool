package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.border.EmptyBorder;

import algorithms.Hash;

public class TabHash extends JPanel implements ActionListener {
	private JTextArea txtString;
	private JTextField txtResult, txtCompare;
	private JRadioButton rdString, rdFile;
	private JPanel pnRd, pnContainer, pnInput, pnCompareContainer, pnCompare,
			pnFileInput, pnOutput, pnAlgorithms;
	private JLabel lblFileInput, lblCompare, lblResult;
	private JButton btnFileInput, btnCopy, btnCheckCompare;
	private JScrollPane scrollTxtInput;
	private JFileChooser jFileChoose;
	private File fileInput;
	private JComboBox choiceAlgorithms;
	private String[] listAlgorithms = { Hash.MD5, Hash.SHA_1, Hash.SHA_224,
			Hash.SHA_256, Hash.SHA_384, Hash.SHA_512_224, Hash.SHA_512_256 };

	public TabHash() {

		setLayout(new BorderLayout());
		// create component
		txtString = new JTextArea();
		txtResult = new JTextField();
		rdString = new JRadioButton("String");
		rdFile = new JRadioButton("File");
		lblFileInput = new JLabel();
		btnFileInput = new JButton("Choose file");
		btnCopy = new JButton();
		btnCopy.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		pnCompareContainer = new JPanel(new BorderLayout());
		txtCompare = new JTextField();
		lblCompare = new JLabel("Compare with:");
		btnCheckCompare = new JButton("Check");
		pnRd = new JPanel();
		pnFileInput = new JPanel(new GridBagLayout());
		rdString.setSelected(true);
		scrollTxtInput = new JScrollPane(txtString);
		pnContainer = new JPanel(new BorderLayout());
		pnCompare = new JPanel(new BorderLayout());
		lblResult = new JLabel("Hash code:");
		jFileChoose = new JFileChooser();
		pnAlgorithms = new JPanel();
		choiceAlgorithms = new JComboBox(listAlgorithms);
		// group radio button
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdString);
		bg.add(rdFile);
		// add component
		pnRd.add(rdString);
		pnRd.add(rdFile);
		pnInput = new JPanel(new BorderLayout());
		pnOutput = new JPanel(new BorderLayout());
		pnFileInput.add(lblFileInput);
		pnFileInput.add(btnFileInput);
		btnFileInput.setPreferredSize(new Dimension(150, 40));
		// txtString.setPreferredSize(new Dimension(600,400));
		// pnInput.add(pnFileInput, BorderLayout.CENTER);
		pnInput.add(scrollTxtInput);
		pnOutput.add(lblResult, BorderLayout.WEST);
		pnOutput.add(txtResult);
		pnOutput.add(btnCopy, BorderLayout.EAST);
		pnInput.add(pnOutput, BorderLayout.SOUTH);
		pnContainer.add(pnInput);
		add(pnRd, BorderLayout.NORTH);
		add(pnContainer, BorderLayout.CENTER);
		add(pnCompareContainer, BorderLayout.SOUTH);
		pnCompare.add(lblCompare, BorderLayout.WEST);
		pnCompare.add(txtCompare);
		pnCompare.add(btnCheckCompare, BorderLayout.EAST);
		pnCompareContainer.add(pnCompare, BorderLayout.NORTH);
		pnAlgorithms.add(choiceAlgorithms);
		pnCompareContainer.add(pnAlgorithms);
	choiceAlgorithms.setFocusable(false);
		
		choiceAlgorithms.setPreferredSize(new Dimension(150,40));
		lblFileInput.setMaximumSize(new Dimension(10, 40));
		pnCompareContainer.setPreferredSize(new Dimension(650, 190));
		btnCopy.setPreferredSize(new Dimension(60, 40));
		btnCheckCompare.setPreferredSize(new Dimension(80, 40));
		lblResult.setPreferredSize(new Dimension(85, 40));
		lblCompare.setPreferredSize(new Dimension(85, 40));
		
		choiceAlgorithms.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtString.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtResult.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtCompare.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnFileInput.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnCheckCompare.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblResult.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblCompare.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFileInput.setFont(new Font("Dialog", Font.PLAIN, 13));

		lblFileInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		pnRd.setBorder(new EmptyBorder(0, 0, 10, 0));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		pnOutput.setBorder(new EmptyBorder(15, 5, 0, 0));
		pnCompare.setBorder(new EmptyBorder(0, 5, 0, 0));
		handleOptionStringOrFile();
		addHandle();
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

	public void addHandle() {
		btnCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				copyIntoClipBoard(txtResult.getText());

			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtResult.setFocusable(false);
				txtResult.setFocusable(true);
				txtCompare.setFocusable(false);
				txtCompare.setFocusable(true);

			}
		});
		txtCompare.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("tuyen");

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("tuyen1");

			}
		});
		btnFileInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fileInput = openFile();
					lblFileInput.setText(fileInput.getAbsolutePath());
				} catch (Exception ex) {
					System.out.println("Cancel choose file!");
				}

			}
		});
		btnCheckCompare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtCompare.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Empty text",
							"Dialog", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			if(txtResult.getText().equals(txtCompare.getText())){
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/success.png"));
               
				JOptionPane.showMessageDialog(
                        null,
                        "Matched",
                        "Success", JOptionPane.INFORMATION_MESSAGE,
                        icon);
				
			}else{
				JOptionPane.showMessageDialog(null, "Mismatched",
						"Dialog", JOptionPane.ERROR_MESSAGE);
			}
				
			}
		});
	}

	public void handleOptionStringOrFile() {
		rdString.setActionCommand("rdString");
		rdFile.setActionCommand("rdFile");
		rdString.addActionListener(this);
		rdFile.addActionListener(this);
	}

	public void copyIntoClipBoard(String txt) {
		StringSelection stringSelection = new StringSelection(txt);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rdString")) {
			System.out.println("rdString");
			pnInput.remove(pnFileInput);
			pnInput.add(scrollTxtInput);
			pnInput.revalidate();
			pnInput.repaint();
		} else {
			System.out.println("rdFile");
			pnInput.remove(scrollTxtInput);
			pnInput.add(pnFileInput);
			pnInput.revalidate();
			pnInput.repaint();

		}

	}

	public JTextArea getTxtString() {
		return txtString;
	}

	public void setTxtString(JTextArea txtString) {
		this.txtString = txtString;
	}

	public JTextField getTxtResult() {
		return txtResult;
	}

	public void setTxtResult(JTextField txtResult) {
		this.txtResult = txtResult;
	}

	public JTextField getTxtCompare() {
		return txtCompare;
	}

	public void setTxtCompare(JTextField txtCompare) {
		this.txtCompare = txtCompare;
	}

	public JRadioButton getRdString() {
		return rdString;
	}

	public void setRdString(JRadioButton rdString) {
		this.rdString = rdString;
	}

	public JRadioButton getRdFile() {
		return rdFile;
	}

	public void setRdFile(JRadioButton rdFile) {
		this.rdFile = rdFile;
	}

	public JPanel getPnRd() {
		return pnRd;
	}

	public void setPnRd(JPanel pnRd) {
		this.pnRd = pnRd;
	}

	public JPanel getPnContainer() {
		return pnContainer;
	}

	public void setPnContainer(JPanel pnContainer) {
		this.pnContainer = pnContainer;
	}

	public JPanel getPnInput() {
		return pnInput;
	}

	public void setPnInput(JPanel pnInput) {
		this.pnInput = pnInput;
	}

	public JPanel getPnCompareContainer() {
		return pnCompareContainer;
	}

	public void setPnCompareContainer(JPanel pnCompareContainer) {
		this.pnCompareContainer = pnCompareContainer;
	}

	public JPanel getPnCompare() {
		return pnCompare;
	}

	public void setPnCompare(JPanel pnCompare) {
		this.pnCompare = pnCompare;
	}

	public JPanel getPnFileInput() {
		return pnFileInput;
	}

	public void setPnFileInput(JPanel pnFileInput) {
		this.pnFileInput = pnFileInput;
	}

	public JPanel getPnOutput() {
		return pnOutput;
	}

	public void setPnOutput(JPanel pnOutput) {
		this.pnOutput = pnOutput;
	}

	public JPanel getPnAlgorithms() {
		return pnAlgorithms;
	}

	public void setPnAlgorithms(JPanel pnAlgorithms) {
		this.pnAlgorithms = pnAlgorithms;
	}

	public JLabel getLblFileInput() {
		return lblFileInput;
	}

	public void setLblFileInput(JLabel lblFileInput) {
		this.lblFileInput = lblFileInput;
	}

	public JLabel getLblCompare() {
		return lblCompare;
	}

	public void setLblCompare(JLabel lblCompare) {
		this.lblCompare = lblCompare;
	}

	public JLabel getLblResult() {
		return lblResult;
	}

	public void setLblResult(JLabel lblResult) {
		this.lblResult = lblResult;
	}

	public JButton getBtnFileInput() {
		return btnFileInput;
	}

	public void setBtnFileInput(JButton btnFileInput) {
		this.btnFileInput = btnFileInput;
	}

	public JButton getBtnCopy() {
		return btnCopy;
	}

	public void setBtnCopy(JButton btnCopy) {
		this.btnCopy = btnCopy;
	}

	public JButton getBtnCheckCompare() {
		return btnCheckCompare;
	}

	public void setBtnCheckCompare(JButton btnCheckCompare) {
		this.btnCheckCompare = btnCheckCompare;
	}

	public JScrollPane getScrollTxtInput() {
		return scrollTxtInput;
	}

	public void setScrollTxtInput(JScrollPane scrollTxtInput) {
		this.scrollTxtInput = scrollTxtInput;
	}

	public JFileChooser getjFileChoose() {
		return jFileChoose;
	}

	public void setjFileChoose(JFileChooser jFileChoose) {
		this.jFileChoose = jFileChoose;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}



	public JComboBox getChoiceAlgorithms() {
		return choiceAlgorithms;
	}

	public void setChoiceAlgorithms(JComboBox choiceAlgorithms) {
		this.choiceAlgorithms = choiceAlgorithms;
	}

	public String[] getListAlgorithms() {
		return listAlgorithms;
	}

	public void setListAlgorithms(String[] listAlgorithms) {
		this.listAlgorithms = listAlgorithms;
	}
	
}
