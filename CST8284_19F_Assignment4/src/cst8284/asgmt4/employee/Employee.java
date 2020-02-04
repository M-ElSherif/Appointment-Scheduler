/* Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: Employee
 * Date: 24/11/2019
 * 
 */
package cst8284.asgmt4.employee;

import java.util.ArrayList;

/**
 * The {@code Employee} class represents the various employees that can be
 * assigned to the appointment scheduler application. Classes that inherit this class
 * have a set of activities that are associated to the type of employee
 * 
 * @author Mohamed El Sherif
 * @version 1.1
 */
public abstract class Employee {

	/**
	 * The full name of the employee
	 */
	private String fullName;

	/**
	 * Constructs a new {@code Employee} object whose name is unknown
	 */
	protected Employee() {
		this("unknown");
	}

	/**
	 * Constructs a new {@code Employee} object with argument {@code String} as the
	 * name of employee
	 * 
	 * @param fullName the full name of the employee
	 */

	/**
	 * Constructs a new {@code Employee} object and sets the employee's name to specified name
	 * 
	 * @param fullName the full name of the employee
	 */
	protected Employee(String fullName) {
		setName(fullName);
	}

	/**
	 * Sets the employee's full name to specified name
	 * 
	 * @param fullName the full name of the employee
	 */
	public void setName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Returns the employee's full name
	 * 
	 * @return employee's full name
	 */
	public String getName() {
		return fullName;
	}

	/**
	 * Returns the type of activity to be performed 
	 * 
	 * @return type of activity
	 */
	public abstract ArrayList<String> getActivityType();

	/**
	 * Returns a {@code String} representing the name of the employee
	 */
	@Override
	public String toString() {
		return getName();
	}

}