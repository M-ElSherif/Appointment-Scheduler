/* Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: SchedulerLauncher
 * Date: 17/9/2019
 *
 *
 * Neil Coffey, (2019), https://stackoverflow.com/questions/740418/how-do-i-catch-this-exception-in-swing
 *
 */

package cst8284.asgmt4.scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cst8284.asgmt4.employee.Dentist;

/**
 * Launches the appointment scheduler application which is assigned to a
 * specific employee
 * 
 * @author Mohamed El Sherif
 * @version 1.1
 */
public class SchedulerLauncher {

	/**
	 * Execution point of the appointment scheduler application
	 * 
	 * @param args array of arguments for the application
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler(new Dentist("Dr. Andrews"));
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scheduler.launch();
			}
		});
	    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
	    	@Override
	        public void uncaughtException(Thread t, Throwable e) {
	    		JOptionPane.showMessageDialog(new JFrame(), ((BadAppointmentDataException) e).getMessage(), 
	    				((BadAppointmentDataException) e).getDescription(),JOptionPane.PLAIN_MESSAGE);
	        }
	      });
	}
	
	
}
