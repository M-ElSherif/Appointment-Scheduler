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
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class DailyScheduleDialog extends JFrame {
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

	public static void showDailyScheduleDialog() {
		f = new JFrame("Display daily schedule");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		JTextField date = setRow("Enter Date (entered as DDMMYYYY):", 'd');

		JButton confirmBtn = setBtnRow("Confirm");
		confirmBtn.addActionListener(new DailyScheduleHandler(date));

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
		buttonConstants.gridx = GridBagConstraints.RELATIVE;
		buttonConstants.gridy = 2;
		buttonConstants.gridwidth = 1;
		buttonConstants.anchor = GridBagConstraints.PAGE_END; // bottom of space
		buttonConstants.insets = new Insets(1, 1, 1, 1);
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

	// ---------------INNER CLASSES-------------------
	public static class DailyScheduleHandler implements ActionListener {
		private JTextField date;
		private JTextField time;

		public DailyScheduleHandler(JTextField date) {
			this.date = date;
			this.time = null;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Calendar cal = Scheduler.makeCalendarFromUserInput(date, time, true);
			String dailySchedule = Scheduler.displayDaySchedule(cal);
			SchedulerViewer.setJTextArea(dailySchedule);
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
