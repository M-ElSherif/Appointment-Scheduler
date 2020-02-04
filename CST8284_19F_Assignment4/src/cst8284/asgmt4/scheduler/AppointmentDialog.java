package cst8284.asgmt4.scheduler;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

public class AppointmentDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final GridBagConstraints textConstants = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1,
			1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
			GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1,
			1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
	private static final GridBagConstraints buttonConstants = new GridBagConstraints();

	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);
	private static JFrame f;

	public static void showAppointmentDialog() {
		f = new JFrame("Get, set, change or delete an appointment");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		JTextField name = setRow("Enter Client Name (as FirstName LastName):", 'n');
		JTextField phone = setRow("Phone Number (e.g. 613-555-1212):", 'p');
		JTextField date = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
		JTextField time = setRow("Appointment Time:", 't');
		JTextField description = setRow("Activity Description", 'a');
		JTextField activityCategory = setRow("Activity Category", 'a');

		// FIND BUTTON
		JButton findBtn = setBtnRow("Find");
		findBtn.addActionListener(new findAptHandler(date, time));

		// CHANGE BUTTON
		JButton changeBtn = setBtnRow("Change");
		changeBtn.addActionListener(new changeAptDialogHandler(date, time));

		// DELETE BUTTON
		JButton deleteBtn = setBtnRow("Delete");
		deleteBtn.addActionListener(new deleteAptHandler(date, time));

		// EXIT BUTTON
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

	// --------INNER CLASSES---------------------------
	private static class findAptHandler implements ActionListener {

		private JTextField date;
		private JTextField time;

		public findAptHandler(JTextField date, JTextField time) {
			this.date = date;
			this.time = time;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = Scheduler.displayAppointment(Scheduler.makeCalendarFromUserInput(date, time, false));
			SchedulerViewer.setJTextArea(s);
		}
	}

	private static class deleteAptHandler implements ActionListener {

		private JTextField date;
		private JTextField time;

		public deleteAptHandler(JTextField date, JTextField time) {
			this.date = date;
			this.time = time;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Custom button text
			Object[] options = { "Yes", "No", "Cancel" };
			int n = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you wish to delete the appointment?",
					"Delete Appointment", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
					options[2]);
			if (n == 0) {
				Scheduler.deleteAppointment(Scheduler.makeCalendarFromUserInput(date, time, false));
				JOptionPane.showMessageDialog(new JFrame(), "Appointment deleted!", "", JOptionPane.PLAIN_MESSAGE);
			}
			SchedulerViewer.reloadJTextArea();
		}
	}

	private static class changeAptDialogHandler implements ActionListener {

		private JTextField date;
		private JTextField time;

		public changeAptDialogHandler(JTextField date, JTextField time) {
			this.date = date;
			this.time = time;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ChangeAppointmentDialog.showChangeAppointmentDialog(date, time);
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
