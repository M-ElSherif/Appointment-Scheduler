/* Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: Scheduler
 * Date: 24/11/2019
 * 
 * SCHEDULER CLASS
 * handles the input and output of information. No Scanner instance in any other class
 * Only Scheduler handles I/O. Other classes may store String data, but all access to theses Strings 
 * is made via the appropriate methods for that class, such as toString().
 *
 *References:
 *Aminah Nuraini, (2019), https://stackoverflow.com/questions/36831572/using-regex-for-validating-first-and-last-names-in-java
 *Poojitha Reddy,(2019).Calendar Class in Java with examples [Webpage]. Retrieved from https://www.geeksforgeeks.org/calendar-class-injava-with-examples/
 *
 */
package cst8284.asgmt4.scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import cst8284.asgmt4.employee.Employee;
import cst8284.asgmt4.scheduler.Appointment;

/**
 * {@code Scheduler} class displays the application menu for the user to perform
 * various functions. This includes:
 * <ul>
 * <li>Saving, editing and deleting appointments
 * <li>Displaying individual appointments
 * <li>Displaying day schedules
 * <li>Saving and loading appointments to and from files
 * </ul>
 * Saving, editing and deleting appointments require user input to set the
 * various attributes of the appointments
 * 
 * 
 * @author Mohamed El Sherif
 * @version 1.3
 *
 */
public class Scheduler implements ActionListener {

	/**
	 * Scanner object used to read user input
	 */
	private static Scanner scan = new Scanner(System.in);
	/**
	 * {@code ArrayList} that holds the saved appointments
	 */
	private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	/**
	 * Employee assigned to the appointment
	 */
	private static Employee employee;

	/**
	 * Constructs a new {@code Scheduler} and assigns an {@code Employee} to it
	 * 
	 * @param emp the employee assigned to the {@code Scheduler} instance
	 */
	// DEFAULT CONSTRUCTOR
	public Scheduler(Employee emp) {
		this.setEmployee(emp);
		loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
	}

	/**
	 * Launches the appointment scheduling application menu and executes menu item
	 * selections until the exit option is selected
	 */
	// METHOD. Launches the scheduling program
	public void launch() {
		Scheduler.loadAppointmentsFromFile("CurrentAppointments", Scheduler.getAppointments());
		SchedulerViewer.launch();
	}

	/**
	 * Sets the employee the scheduler is assigned for
	 * 
	 * @param emp the employee the scheduler is assigned for
	 */
	// SETTER METHOD. Sets the employee
	private void setEmployee(Employee emp) {
		this.employee = emp;
	}

	/**
	 * Returns the employee the scheduler is assigned for
	 * 
	 * @return the employee the schedulers is assigned for
	 */
	// GETTER METHOD. Returns the employee
	public static Employee getEmployee() {
		return employee;
	}

	/**
	 * Saves the specified appointment to the appointments list. Returns true of
	 * appointments has been saved successfully, and false it appointment was not
	 * saved
	 * 
	 * @param apt the appointment to be saved
	 * @return true if appointment was saved, false if appointment was not saved
	 */
	// METHOD. Saves an appointment to the appointments array
	// first checks if appointment exists. If it does, method returns false
	public static boolean saveAppointment(Appointment apt) {
		SortAppointmentByCalendar srt = new SortAppointmentByCalendar(); // sort the appointments first
		if (findAppointment(apt.getAptDate()) == null) {
			getAppointments().add(apt);
			Collections.sort(getAppointments(), srt);
			return true;
		}
		return false;
	}

	/**
	 * Finds the appointment on the specified date and time as provided by the
	 * parameter {@code Calendar} object and deletes the appointment if found.
	 * Returns true if an appointment was found and successfully deleted, returns
	 * false if appointment was not found and deleted
	 * 
	 * @param cal the specified appointment calendar date and time
	 * @return true if appointment successfully found and deleted, false if not
	 */
	// METHOD. Deletes appointment parameter calendar date
	public static boolean deleteAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		if (apt != null) {
			displayAppointment(cal);
			getAppointments().remove(apt);
			return true;
		}
		return false;
	}

	/**
	 * Finds the appointment on the specified date and time provided by the
	 * parameter {@code Calendar} object. If an appointment is found, accepts user
	 * input to change the date and time of the scheduled appointment. Returns true
	 * if appointment was found and it's date and time edited, returns false if
	 * appointment was not found and edited.
	 * 
	 * @param cal the specified calendar date and time
	 * @return true if appointment successfully found and edited, false if not found
	 *         and edited
	 */
	// METHOD. Changes appointment based on parameter calendar date
	public static boolean changeAppointment(JTextField date, JTextField time, JTextField newDate, JTextField newTime) {
		Appointment apt = findAppointment(makeCalendarFromUserInput(date, time, false));
		if (apt != null) {
			Calendar newCal = makeCalendarFromUserInput(newDate, newTime, false);
			apt.setAptDate(newCal);
			return true;
		}
		return false;
	}

	/**
	 * Finds the appointment on the specified date and time provided by the
	 * parameter {@code Calendar} object. If an appointment is found, the
	 * appointment details are printed to screen
	 * 
	 * @param cal the specified calendar date and time
	 * @return returns true if appointment found, false if appointment not found
	 */
	// METHOD. finds the user desired appointment in the appointment array and
	// Display details to the user. If appointment not found, display message to
	// user
	protected static String displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		if (apt != null) {
			return apt.toString();
		} else {
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			return "No appointment scheduled between " + hour + ":00 and " + (hour + 1) + ":00";
		}
	}

	/**
	 * Displays all scheduled appointments for the specified {@code Calendar} day
	 * 
	 * @param cal the specified calendar date
	 */
	// METHOD. Prompts the user for the date and displays the schedule for the
	// desired day
	public static String displayDaySchedule(Calendar cal) {
		String s = "";
		for (int i = 8; i <= 16; i++) {
			cal.set(Calendar.HOUR_OF_DAY, i);
			s += displayAppointment(cal) + "\n";
		}
		return s;
	}

	// Returns the string within argument text field
	public static String getText(JTextField tf) {
		return tf.getText();
	}

	/**
	 * Validates the argument {@code String} to ensure it is of correct format,
	 * length and is not empty or null. Returns true if argument string is valid,
	 * returns false if not valid
	 * 
	 * @param name the string to be validated
	 * @return returns true if string is valid, returns false if string is not valid
	 * @throws BadAppointmentDataException if the string is longer than 30
	 *                                     characters, it contains characters other
	 *                                     than the dash (-), the period (.), and
	 *                                     the apostrophe ('), or if it is empty or
	 *                                     null
	 */
	private static boolean checkName(String name) {
		String msg = "";
		String description = "";
		String firstName = "";
		String lastName = "";
		boolean check = true;
		try {
			firstName = name.trim().split(" ")[0];
			lastName = name.trim().split(" ")[1];
		} catch (Exception ex) {
			throw new BadAppointmentDataException(
					"Name missing first or last name; correct format is FirstName LastName, where FirstName and LastName are separated by 1 whitespace",
					"Incorrect name format");
		}
		if (firstName.length() > 30 || lastName.length() > 30) {
			check = false;
			msg = "Name cannot exceed 30 characters";
			description = "Name exceeds maximum length";
		}
		if (!firstName.matches("^[\\p{L}\\s.'\\-]+$") || !lastName.matches("^[\\p{L}\\s.'\\-]+$")) { // [1]
			check = false;
			msg = "Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')";
			description = "Illegal characters in name";
		}
		if (!check)
			throw new BadAppointmentDataException(msg, description);
		return check;
	}
	
	/**
	 * Creates a new appointment from input responses for the appointment date and
	 * time, the name of the person attending the appointment, the person's
	 * telephone number, and the appointment's activity description and category
	 * 
	 * @return the new {@code Appointment} created
	 */
	// METHOD. prompt the user for all appointment info and then save to
	// appointments array
	public static Appointment makeAppointmentFromUserInput(JTextField name, JTextField phone, JTextField date,
			JTextField time, JTextField description, JComboBox activityCategory) {
		String n = getText(name);
		checkName(n);
		String t = getText(phone);
		TelephoneNumber ph = new TelephoneNumber(t);
		Calendar cal = makeCalendarFromUserInput(date, time, false);
		Activity activity = new Activity(getText(description), activityCategory.getSelectedItem().toString());
		return new Appointment(cal, n, ph, activity);
	}
	
//	/**
//	 * Creates a new appointment from input responses for the appointment date and
//	 * time, the name of the person attending the appointment, the person's
//	 * telephone number, and the appointment's activity description and category
//	 * 
//	 * @return the new {@code Appointment} created
//	 */
//	// METHOD. prompt the user for all appointment info and then save to
//	// appointments array
//	public static Appointment makeAppointmentFromUserInput(JTextField name, JTextField phone, JTextField date,
//			JTextField time, JTextField description, JTextField activityCategory) {
//		String n = getText(name);
//		checkName(n);
//		String t = getText(phone);
//		TelephoneNumber ph = new TelephoneNumber(t);
//		Calendar cal = makeCalendarFromUserInput(date, time, false);
////		Activity activity = new Activity(getResponseTo("Enter Activity: "), getEmployee().getActivityType());
//		Activity activity = new Activity(getText(description), getText(activityCategory));
//		return new Appointment(cal, n, ph, activity);
//	}

	/**
	 * Creates a new {@code Calendar} object from input requested for the
	 * appointment date and time. Validates the input date to ensure it is of
	 * correct format and is not empty or null. If the argument {@code boolean} is
	 * set as false, the time of the appointment is not requested
	 * 
	 * @param suppressHour if set as true user is requested to provide a time, if
	 *                     set as false time is not requested
	 * @return a {@code Calendar} representing the date and time of the appointment
	 * @throws BadAppointmentDataException if the date entered is not if format
	 *                                     "DDMMYY", of if date is empty or null
	 */
	// METHOD. prompt user for the appointment date and hour. User enters date as.
	// Each appointment is 1 hour long
	public static Calendar makeCalendarFromUserInput(JTextField date, JTextField time, boolean suppressHour) {
		Calendar cal = Calendar.getInstance(); // returns a current instance of Calendar
		cal.clear(); // clears the calendar before setting new user input date and hour
		String d = getText(date);
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(d);
		} catch (RuntimeException ex) {
			throw new BadAppointmentDataException("General runtime exception thrown while pasring date from user input",
					"format is DDMMYYYY");
		} catch (ParseException e) {
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY",
					"Bad calendar format");
		}
		int day = Integer.parseInt(d.substring(0, 2));
		int month = Integer.parseInt(d.substring(2, 4)) - 1;
		int year = Integer.parseInt(d.substring(4));
		int hour = 0;
		if (!suppressHour) {
			hour = processTimeString(getText(time));
		}
		cal.set(year, month, day, hour, 0, 0);
		return cal;
	}

	/**
	 * Processes the argument {@code String} and converts it into 24 hour format.
	 * Returns a {@code String} representing the time in the 24 hour format
	 * 
	 * @param t the {@code String} representing the time to be processed
	 * @return a new {@code String} representing the time in 24 hour format
	 */
	// SUPPORT METHOD for makeCalendarFromUserInput(boolean suppressHour)
	// process user input time regardless of format (12 or 24 ).
	// User will only enter a value between 8 am and 4 pm. CALENDAR REQUIRES TIME in
	// 24 HOUR FORMAT
	private static int processTimeString(String t) {
		int time = 0; // initialize time variable
		String[] str = {};
		if (t.contains(":")) {
			str = t.split("(?=\\:)");
			t = str[0];
		}
		if (t.contains("a.m."))
			t = t.replace("a.m.", "").trim();
		if (t.contains("p.m."))
			t = t.replaceAll("p.m.", "").trim();
		time = Integer.parseInt(t);
		if (time < 8)
			time += 12;
		return time;
	}

	/**
	 * Searches the appointment list for the appointment on the specified calendar
	 * date and time. If an appointment is not found, returns null
	 * 
	 * @param cal the specified calendar date and time
	 * @return the appointment found
	 */
	// SUPPORT METHOD. Check if appointment with parameter calendar exists in
	// appointments array. If array is null (no appointments), return null
	// immediately
	public static Appointment findAppointment(Calendar cal) {
		Appointment searchApt = new Appointment(cal, "null null", null, null); // appointment with date to be searched
																				// for in list
		int aptIndex = Collections.binarySearch(getAppointments(), searchApt, new SortAppointmentByCalendar());
		if (aptIndex < 0)
			return null;
		return getAppointments().get(aptIndex);
	}

	/**
	 * Returns the list of appointments saved in the scheduler
	 * 
	 * @return list of scheduled appointments
	 */
	// GETTER. Gets the appointments array
	public static ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * Writes the argument appointments {@code List} into a file of name
	 * {@code String} argument. Returns true if appointments were written into file
	 * successfully, false if not
	 * 
	 * @param apts     the list of appointments to be written into the file
	 * @param saveFile the name of the file within which the appointments will be
	 *                 saved
	 * @return true if appointments list written successfully, false if not
	 */
	// METHOD. Saves the parameter arraylist of appointments to parameter file
	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream fout = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fout);) {
			for (Appointment apt : apts) {
				oos.writeObject(apt);
			}
			return true;
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Loads (reads) the appointments from the file of name {@code String} argument
	 * into argument {@code List}. Returns true if appointments were loaded
	 * successfully into the array, false if not
	 * 
	 * @param sourceFile the name of the file from which appointments are loaded
	 *                   (read)
	 * @param apts       the list into which read appointments are added
	 * @return true if appointments loaded successfully, false if not
	 */
	// METHOD. Loads parameter appointments from file to parameter arraylist
	public static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear();
		Appointment apt; // declare an Appointment variable
		try (FileInputStream fin = new FileInputStream(sourceFile);
				ObjectInputStream ois = new ObjectInputStream(fin);) {
			while (true) {
				apt = (Appointment) (ois.readObject());
				apts.add(apt);
			}
		} catch (EOFException ex) {
			return true;
		} catch (FileNotFoundException ex) {
			// If file does not exist, create a new empty file
			File file = new File("CurrentAppointments.apts");
			try {
				if (!file.createNewFile()) {
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException ex) {
			return false;
		} catch (IOException ex) {
			return false;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
