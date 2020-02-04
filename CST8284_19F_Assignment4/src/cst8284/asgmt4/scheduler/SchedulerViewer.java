package cst8284.asgmt4.scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SchedulerViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	private static final JTextArea scrollText = new JTextArea();
	private static JFrame  frame;

	public static void launch() {
		frame = new JFrame();
		frame.setTitle("Scheduling appointments");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);

		frame.add(getWestPanel(), BorderLayout.WEST);
		frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(screenX, screenY));

		frame.pack();
		frame.setVisible(true);

	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}
	

	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;
//		controlPanel.add(setWestPanelBtns("    Display Appointment    ", e -> AppointmentDialog.showAppointmentDialog()));
		controlPanel.add(setWestPanelBtns("    Save Appointment      ", new SaveAptDialogHandler()));
		controlPanel.add(setWestPanelBtns("    Display Appointment   ", new DisplayAptDialogHandler()));
		controlPanel.add(setWestPanelBtns("   Display Schedule  ", new DailyScheduleDialogHandler()));
		controlPanel.add(setWestPanelBtns("      Save Appointments to File      ", new SaveAptstoFileHandler()));
		controlPanel.add(setWestPanelBtns("      Load Appointments from File      ", new LoadAptsFromFileHandler()));
		controlPanel.add(setWestPanelBtns("      Exit      ", new ExitHandler(frame)));

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	public static void setJTextArea(String s) {
		scrollText.setText(s);
	}

	public static void reloadJTextArea() {
		scrollText.setText("");
	}

	
	//--------INNER CLASSES---------------------------
	
	public static class SaveAptstoFileHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments")) {
				JOptionPane.showMessageDialog(new JFrame(), "Appointments saved to file!", "Save Appointments", JOptionPane.PLAIN_MESSAGE);
				SchedulerViewer.reloadJTextArea();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Appointments FAILED to save to file!", "Save Appointments", JOptionPane.PLAIN_MESSAGE);
				SchedulerViewer.reloadJTextArea();
			}
		}
	}
	
	public static class LoadAptsFromFileHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Scheduler.loadAppointmentsFromFile("CurrentAppointments", Scheduler.getAppointments())) {
				JOptionPane.showMessageDialog(new JFrame(), "Appointments loaded from file!", "Load Appointments", JOptionPane.PLAIN_MESSAGE);
				SchedulerViewer.reloadJTextArea();
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Appointments FAILED to load from file!", "Load Appointments", JOptionPane.PLAIN_MESSAGE);
				SchedulerViewer.reloadJTextArea();
			}
		}
	}
	
	public static class SaveAptDialogHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SaveDialog.saveAppointmentDialog();
			SchedulerViewer.reloadJTextArea();
		}
	}

	public static class DisplayAptDialogHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AppointmentDialog.showAppointmentDialog();
			SchedulerViewer.reloadJTextArea();
		}

	}
	
	public static class DailyScheduleDialogHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SchedulerViewer.reloadJTextArea();
			DailyScheduleDialog.showDailyScheduleDialog();
		}
		
	}
	
	public static class ExitHandler implements ActionListener {
		
		private JFrame frame;
		
		public ExitHandler(JFrame frame) {
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments");
			frame.dispose();
		}
	}

}
