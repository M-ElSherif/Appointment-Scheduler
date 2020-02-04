/* Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: Appointment
 * Date: 24/11/2019
 * 
 * References:
 * Oracle,(2019). Class SimpleDateFormat [Webpage].Retrieved from https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
 * Oracle,(2019). Class Calendar [Webpage].Retrieved from https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The {@code Appointment} class represents the appointments that are scheduled in the application. They consist of:
 * <ul>
 * <li> An appointment date {@code Calendar} object
 * <li> A {@code String} object representing the name of the person attending the appointment
 * <li> A {@code Telephonenumber} object representing the person's telephone number An {@code Activity} object representing the activity to be
 * <li> An {@code Activity} object representing the activity to be performed during the appointment
 * </ul>
 * 
 * @author Mohamed El Sherif
 * @version 1.2
 * 
 */
public class Appointment implements Serializable {

	/**
	 * The appointment date and time
	 */
	private Calendar aptDate;
	/**
	 * The first name of the person scheduled for the appointment
	 */
	private String firstName;
	/**
	 * The last name of the person scheduled for the appointment
	 */
	private String lastName;
	/**
	 * The telephone number of the person scheduled for the appointment
	 */
	private TelephoneNumber phone;
	/**
	 * The activity that will be completed during the appointment
	 */
	private Activity activity;
	/**
	 * The user id for deserialization of bytes when reconstructed back into objects. Value = {@value #serialVersionUID} 
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@code Appointment} object. Takes the date and time of the appointment, name of the person, person's
	 * phone number and the scheduled activity. The full name is split into a firstName and lastName {@code String}.
	 * 
	 * @param cal the appointment calendar date and time
	 * @param fullName full name of the person's first name 
	 * @param phone the person's phone number
	 * @param activity the activity to be performed during the appointment
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity activity) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, activity);
	}

	/**
	 * Constructs a new {@code Appointment} object using the arguments {@code Calendar} (date and time) of the appointment, name of the person attending the appointment, person's
	 * phone number and the scheduled activity.
	 * 
	 * @param cal the appointment calendar date and time
	 * @param firstName the person's first name 
	 * @param lastName the person's last name 
	 * @param phone the person's phone number
	 * @param activity the activity to be performed during the appointment
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
		this.setAptDate(cal);
		setFirstName(firstName.trim());
		setLastName(lastName.trim());
		this.setPhone(phone);
		this.setActivity(activity);
	}

	/**
	 * Returns the {@code Calendar} object representing the appointment date and time
	 * 
	 * @return The appointment date and time
	 */
	public Calendar getAptDate() {
		return aptDate;
	}

	/**
	 * Sets the aptDate with the parameter appointment date specified
	 * 
	 * @param aptDate the appointment date and time to be set
	 */
	public void setAptDate(Calendar aptDate) {
		this.aptDate = aptDate;
	}

	/**
	 * Returns the {@code String} representing the first name of the person scheduled to the appointment
	 * 
	 * @return first name of person attending the appointment
	 */
	public String getFirstName() {
		return firstName;
	}

	
	/**
	 * Sets the first name of the person scheduled for the appointment
	 * 
	 * @param firstName first name of person attending the appointment
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the {@code String} representing the last name of the person scheduled for the appointment
	 * 
	 * @return last name of person attending the appointment
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the person scheduled to the appointment
	 * 
	 * @param lastName last name of person attending the appointment
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	/**
	 * Returns the {@code Telephonenumber} representing the phone number of person scheduled for the appointment
	 * 
	 * @return phone number of person scheduled
	 */
	public TelephoneNumber getPhone() {
		return phone;
	}

	/**
	 * Sets the telephone number of person scheduled for the appointment
	 * 
	 * @param phone phone number of person scheduled for the appointment
	 */
	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}
	
	/**
	 * Returns the activity to be performed during the appointment
	 * 
	 * @return activity to be performed during appointment
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Sets the activity that will be performed during the appointment
	 * 
	 * @param activity activity that will be performed during the appointment
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Returns a {@code String} that consists of the appointment date, the name of the person attending,
	 * the person's phone number, and the activity that will be performed during the appointment
	 */
	@Override
	public String toString() {
		// aptTime.getTime() returns a date object
		// desired output looks like the following: Tue Nov 05 2019 08:00
		SimpleDateFormat formatDate = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
		return "\n" + formatDate.format(this.getAptDate().getTime()).toString() + "\n" + this.getFirstName() + " "
				+ this.getLastName() + "\n" + this.getPhone().toString() + "\n" + this.getActivity().toString() + "\n";
	}

}
