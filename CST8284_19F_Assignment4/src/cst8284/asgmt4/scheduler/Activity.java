/* 
 * Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: Activity
 * Date: 24/11/2019
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/** 
 * Generates {@code Activity} objects that represent the activity assigned for an appointment.
 * Consists of a description and category of the work to be performed during the appointment
 * 
 * @author	Mohamed El Sherif
 * @version 1.2
 * 
 */
public class Activity implements Serializable {

	/**
	 * The user id for deserialization of bytes when reconstructed back into objects. Value = {@value #serialVersionUID} 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The description of the work to be done
	 */
	private String descriptionOfWork;
	/**
	 * The category of the work to be done on the patient during the appointment
	 */
	private String category;
	
	/**
	 * Constructs a new Activity object that consists of the description and the category of the activity
	 * 
	 * @param	description a description of the work to be performed 
	 * @param	category the category of the work to be performed
	 */
	//CONSTRUCTOR
	public Activity(String description, String category) {
		this.setCategory(category);
		this.setDescription(description);
	}
	
	/**
	 * Returns the description of the work to be performed
	 * 
	 * @return	The description of the work to be performed
	 */
	// GETTER. Get the description of work done
	public String getDescription() {
		return this.descriptionOfWork;
	}
	
	/**
	 * Sets the description of the work to be performed 
	 * 
	 * @param	s the description of the work to be performed
	 */
	// SETTER. Sets the description of work
	public void setDescription(String s) {
		this.descriptionOfWork = s;
	}
	
	/**
	 * Returns the category of the work to be performed
	 * 
	 * @return	the category of the work to be performed
	 */
	// GETTER. Get the category
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * Sets the category of the work to be performed
	 * 
	 * @param	s the category of the work to be performed
	 */
	// SETTER. Sets the category
	public void setCategory(String s) {
		this.category = s;
	}
	
	/**
	 * Returns the activity as a string consisting of its category and description
	 * 
	 * @return	the category and description of the activity as a string
	 */
	@Override
	public String toString() {
		return this.getCategory() +"\n" + this.getDescription();
	}
	
	
}
