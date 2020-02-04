/*
 * Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: SortAppointmentByCalendar
 * Date: 24/11/2019
 * 
 */
package cst8284.asgmt4.scheduler;

import java.util.Comparator;

/**
 * {@code SortAppointmentByCalendar} class allows for appointments to be sorted
 * by date when used as a comparator for sorting appointment lists
 * 
 * @author Mohamed El Sherif
 * @version 1.1
 *
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {

	/**
	 * Compares the two argument {@code Appointment} objects and returns an
	 * {@code int} based on the following:
	 * <ul>
	 * <li>-1 if appointment 1 date and time precedes appointment 2 date and time
	 * <li>0 if appointment 1 and appointment 2 have the same date and time
	 * <li>1 if appointment 2 precedes appointment 1
	 * </ul>
	 * 
	 * @return an integer value representing the comparison between the argument
	 *         appointments
	 */
	@Override
	public int compare(Appointment o1, Appointment o2) {
		return o1.getAptDate().compareTo(o2.getAptDate());
	}
}
