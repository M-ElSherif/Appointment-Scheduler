/*
 * Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: BadAppointmentDataException
 * Date: 24/11/2019
 * 
 */

package cst8284.asgmt4.scheduler;


/**
 * {@code BadAppointmentDataException} is a subclass of {@code RuntimeException} that can be thrown when the user inputs bad data during appointment scheduling operations
 * 
 * @author Mohamed El Sherif
 * @version 1.1
 *
 */
public class BadAppointmentDataException extends java.lang.RuntimeException {
	
	/**
	 * The user id for deserialization of bytes when reconstructed back into objects. Value = {@value #serialVersionUID} 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The description of the error that is thrown
	 */
	private String Description;
	
	/**
	 * Constructs a new {@code BadAppointmentDataException} with default message and description
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}
	
	/**
	 * Constructs a new {@code BadAppointmentDataException} with specified message and description
	 * related to the corresponding error
	 * 
	 * @param msg the output message of the exception triggered
	 * @param description the description of the exception triggered
	 */
	public BadAppointmentDataException(String msg, String description) {
		super(msg);
		this.Description = description;
	}

	/**
	 * Returns the description of the {@code BadAppointmentDataException}
	 * 
	 * @return the description of the exception
	 */
	public String getDescription() {
		return this.Description;
	}
	
	/**
	 * Sets the description of the {@code BadAppointmentDataException} with specified argument {@code String}
	 * 
	 * @param Description the description of the exception
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	/**
	 * Returns a {@code String} containing the exception description and message
	 */
	@Override
	public String toString() {
		return this.getDescription() + "; " + this.getMessage();
	}
}
