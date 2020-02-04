package cst8284.asgmt4.scheduler;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class SaveDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final GridBagConstraints textConstants = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1,
			1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
			GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1,
			1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
	private static final GridBagConstraints buttonConstants = new GridBagConstraints();
	private static final GridBagConstraints comboConstants = new GridBagConstraints();
	
	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);
	private static JFrame f;

	// TODO added
	private static Appointment apt;

	public static void saveAppointmentDialog() {
		f = new JFrame("Save an appointment");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		JTextField name = setRow("Enter Client Name (as FirstName LastName):", 'n');
		JTextField phone = setRow("Phone Number (e.g. 613-555-1212):", 'p');
		JTextField date = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
		JTextField time = setRow("Appointment Time:", 't');
		JTextField description = setRow("Activity Description", 'a');
//		JTextField activityCategory = setRow("Activity Category", 'a');
		JComboBox activityCategory = setCombo("Activity Category");

		JButton saveBtn = setBtnRow("Save");
		saveBtn.addActionListener(new saveAptHandler(name, phone, date, time, description, activityCategory));

		JButton exitBtn = setBtnRow("Exit");
		exitBtn.addActionListener(new ExitHandler(f));

		f.pack();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		f.setVisible(true);
	}
	
	private static JComboBox setCombo(String label) {
	String[] cats = {"Select an activity category", "Assessment", "Filling", "Crown", "Cosmetic Repair"};
		JComboBox list = new JComboBox(cats);
		comboConstants.weightx = 2;
		comboConstants.gridx = 1;
		comboConstants.gridy = GridBagConstraints.RELATIVE;
		comboConstants.gridwidth = 2;
		comboConstants.anchor = GridBagConstraints.PAGE_END; //bottom of space
		comboConstants.insets = new Insets(1,1,1,1);
		cp.add(list, comboConstants);
	return list;
	
}
	
	
	private static JButton setBtnRow(String label) {
//		buttonConstants.fill = GridBagConstraints.HORIZONTAL;
		buttonConstants.weightx = 2;
		buttonConstants.gridx = 3;
		buttonConstants.gridy = GridBagConstraints.RELATIVE;
		buttonConstants.gridwidth = 2;
		buttonConstants.anchor = GridBagConstraints.PAGE_END; //bottom of space
		buttonConstants.insets = new Insets(1,1,1,1);
		JButton b;
		cp.add(b = new JButton(label), buttonConstants);
		b.setFont(defaultFont);
		return b;
	}
	
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l;
		JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
		cp.add(t = new JTextField(labelWidth), labelConstants);
		t.setFocusAccelerator(keyboardShortcut);
		return t;
	}

	// ------------INNER CLASS--------------
	public static class saveAptHandler implements ActionListener {
		private JTextField name;
		private JTextField phone;
		private JTextField date;
		private JTextField time;
		private JTextField description;
		private JComboBox activityCategory;

		public saveAptHandler(JTextField name, JTextField phone, JTextField date, JTextField time,
				JTextField description, JComboBox activityCategory) {
			this.name = name;
			this.phone = phone;
			this.date = date;
			this.time = time;
			this.description = description;
			this.activityCategory = activityCategory;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Appointment apt = Scheduler.makeAppointmentFromUserInput(name, phone, date, time, description,
					activityCategory);
			if (Scheduler.saveAppointment(apt)) {
				JOptionPane.showMessageDialog(new JFrame(), "Appointment saved!", "", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Appointment NOT saved!", "", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public static class ExitHandler implements ActionListener {

		private JFrame frame;

		public ExitHandler(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}

}
