package GUI;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

public class OptionPanelUI extends JPanel {

	private String[] listAlgorithms = { "DES", "AA" };
	private String[] listKeySize = { "512", "111" };
	private String[] listMode = { "11", "222" };
	private String[] listPadding = { "11", "222" };
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblpadding;
	Choice choiceAlgorithms, choiceKeySize, choiceMode, choicePadding;
	private Dimension dimlbl, dimChoice, dimContainer;
	private JPanel pnAlgorithms, pnKeySize, pnMode, pnPadding;

	public OptionPanelUI() {
		dimContainer = new Dimension(740, 68);
		dimlbl = new Dimension(67, 30);
		dimChoice = new Dimension(80, 30);

		choiceAlgorithms = new Choice();
		// choice.setBounds(100, 100, 150, 150);
		for (String algo : listAlgorithms) {
			choiceAlgorithms.add(algo);
		}

		choiceKeySize = new Choice();
		for (String algo : listKeySize) {
			choiceKeySize.add(algo);
		}
		choiceMode = new Choice();
		for (String algo : listMode) {
			choiceMode.add(algo);
		}
		choicePadding = new Choice();

		for (String algo : listPadding) {
			choicePadding.add(algo);
		}

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
		/* Set dimension */
		lblAlgorithms.setPreferredSize(dimlbl);
		lblKeySize.setPreferredSize(dimlbl);
		lblMode.setPreferredSize(dimlbl);
		lblpadding.setPreferredSize(dimlbl);

		choiceAlgorithms.setPreferredSize(dimChoice);
		choiceKeySize.setPreferredSize(dimChoice);
		choiceMode.setPreferredSize(dimChoice);
		choicePadding.setPreferredSize(dimChoice);

		setPreferredSize(dimContainer);
		/* Add label & choice into Panel */
		pnAlgorithms.add(lblAlgorithms);
		pnAlgorithms.add(choiceAlgorithms);
		pnAlgorithms.setSize(100, 100);

		pnKeySize.add(lblKeySize);
		pnKeySize.add(choiceKeySize);

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
}
