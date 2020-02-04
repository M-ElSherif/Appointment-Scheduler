/*
 * Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: TelephoneNumber
 * Date: 24/11/2019
 * 
 * Aminah Nuraini, (2019), https://stackoverflow.com/questions/36831572/using-regex-for-validating-first-and-last-names-in-java
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/**
 * Generates TelephoneNumber objects that consist of a three digit area code,
 * followed by a three digit prefix followed by a four digit line number
 * 
 * @author Mohamed El Sherif
 * @version 1.3
 * 
 */

public class TelephoneNumber implements Serializable {

	/**
	 * The user id for deserialization of bytes when reconstructed back into objects. Value = {@value #serialVersionUID} 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The first three digits of the telephone number
	 */
	private int areaCode; // first 3 digits of the telephone number
	/**
	 * The second three digits of the telephone number
	 */
	private int prefix; // second 3 digits of the telephone number
	/**
	 * The last three digits of the telephone number
	 */
	private int lineNumber; // last 4 digits of the telephone number

	/**
	 * Constructs a new TelphoneNumber object by taking a String representing the
	 * phone number. Validates the phone number to ensure it is of correct format and is not empty or null.
	 * Parameter String is then split and set into areaCode, a prefix and a
	 * lineNumber. 
	 * 
	 * @param phoneNumber string to be split into area code, prefix and line number
	 */
	public TelephoneNumber(String phoneNumber) {
		this.checkNumber(phoneNumber); // check phone number to ensure it is correct
		String[] str = phoneNumber.split("(-)");
		this.setAreaCode(Integer.parseInt(str[0]));
		this.setPrefix(Integer.parseInt(str[1]));
		this.setLineNumber(Integer.parseInt(str[2]));
	}

	/**
	 * Checks if parameter phoneNumber is of correct format AAA-PPP-NNNN 
	 * 
	 * @param phoneNumber the telephone number consisting of the area code, prefix and line number
	 * @throws BadAppointmentDataException if phoneNumber not of correct format
	 * AAA-PPP-NNNN, or starts with a "0" or "1", or contains characters other than
	 * numbers or '-', or if empty or null
	 */
	public void checkNumber(String phoneNumber) {
	String msg = "", description = "";	// initialize exception message and description strings
	boolean check = true;
	if (!phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) { 
		check = false;
		msg = "Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number";
		description = "Incorrect format";
	}
	if (phoneNumber.startsWith("0") || phoneNumber.startsWith("1")) {
		check = false;
		msg = "Area code can’t start with a ‘0’ or a ‘1’";
		description = "Invalid number";
	}
	if (!phoneNumber.matches("[0-9-]+")) {
		check = false;
		msg = "Telephone numbers can only contain numbers or the character '-'";
		description = "Bad character(s) in input string";
	}
	if (phoneNumber.isEmpty() || phoneNumber == null) {
		check = false;
		msg = "Must enter a value";
		description = "Empty or null value entered";
	}
	if (!check)
		throw new BadAppointmentDataException(msg, description);
}

	/**
	 * Returns the areaCode, the first three digits of the telephone number
	 * 
	 * @return The areaCode, first three digits of the telephone number
	 */
	// GETTER. Gets the area code
	public int getAreaCode() {
		return this.areaCode;
	}

	/**
	 * Sets the areaCode, the first three digits of the telephone number
	 * 
	 * @param areaCode first three digits of the telephone number
	 */
	// SETTER. Sets the area code
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Returns the prefix, the second three digits of the telephone number
	 * 
	 * @return The prefix, second three digits of the telephone number
	 */
	// GETTER. Get the number prefix
	public int getPrefix() {
		return this.prefix;
	}

	/**
	 * Sets the prefix, the second three digits of the telephone number
	 * 
	 * @param prefix the second three digits of the telephone number
	 */
	// SETTER. Set the number prefix
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	/**
	 * Returns the lineNumber, the last three digits of the telephone number
	 * 
	 * @return The lineNumber, the last three digits of the telephone number
	 */
	// GETTER. Get the line number
	public int getLineNumber() {
		return this.lineNumber;
	}

	/**
	 * Sets the lineNumber, the last three digits of the telephone number
	 * 
	 * @param lineNumber the last three digits of the telephone number
	 */
	// SETTER. Set the line number
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Returns the telephone number as a string in the format (AAA) PPP-NNNN
	 * 
	 * @return string representing the telephone number in the format (AAA) PPP-NNNN
	 */
	@Override
	public String toString() {
		return "(" + this.getAreaCode() + ") " + this.getPrefix() + "-" + this.getLineNumber();
	}

}
