package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class OptionKey extends JPanel implements ActionListener {
	JButton btnCreateKey;
	JTextField txtKey;
	JRadioButton rdField, rdFile;
	JPanel pnRadio, pnContainer, pnKeyField, pnKeyFile;
	Dimension dimContainer;

	public OptionKey() {
		dimContainer = new Dimension(740, 100);
		rdField = new JRadioButton("Key");
		rdFile = new JRadioButton("Key file");
		
		pnRadio = new JPanel();
		
		rdField.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdField.setActionCommand("rdField");
		rdFile.setActionCommand("rdFile");
		ButtonGroup bg = new ButtonGroup();

		bg.add(rdField);
		bg.add(rdFile);

		pnKeyField = new JPanel();
		pnKeyFile = new JPanel();
		pnContainer = new JPanel();

		btnCreateKey = new JButton("Create Key");
		txtKey = new JTextField();
		
		// add event radio button
		rdField.addActionListener(this);
		rdFile.addActionListener(this);
		setPreferredSize(dimContainer);
		setLayout(new BorderLayout());

		pnRadio.add(rdField);
		pnRadio.add(rdFile);
		add(pnRadio,BorderLayout.NORTH);
		add(pnContainer,BorderLayout.CENTER);
		pnKeyField.add(btnCreateKey);
		pnKeyField.add(txtKey);
		
		Border blackline = BorderFactory.createTitledBorder("Key");
		setBorder(blackline);
		((TitledBorder) getBorder()).setTitleFont(new Font("Dialog",
				Font.PLAIN, 13));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rdField")) {
			pnContainer.removeAll();
			pnContainer.add(pnKeyField);
			pnContainer.revalidate();
			pnContainer.repaint();
		} else {
			pnContainer.removeAll();
			pnContainer.add(pnKeyFile);
			pnContainer.revalidate();
			pnContainer.repaint();
		}

	}
}
