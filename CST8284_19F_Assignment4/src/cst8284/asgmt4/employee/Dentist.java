/* Course Name: CST 8284 Object Oriented Programming
 * Student Name: Mohamed El Sherif
 * Class name: Dentist
 * Date: 24/11/2019
 */

package cst8284.asgmt4.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * {@code Dentist} class is a subclass of the {@code Employee} class. 
 * A Dentist has a full name and a related selection of activities
 * that can be selected for appointments
 * 
 * @author Mohamed El Sherif
 * @version 1.1
 */
public class Dentist extends Employee {
	
	/**
	 * A scanner object to read input for user selections
	 */
	private static Scanner scan = new Scanner(System.in);
	/**
	 * The assessment activity type option. Value = {@value ASSESSMENT} 
	 */
	private static final int ASSESSMENT = 1;
	/**
	 * The filling activity type option. Value = {@value FILLING} 
	 */
	private static final int FILLING = 2;
	/**
	 * The crown activity type option. Value = {@value CROWN} 
	 */
	private static final int CROWN = 3;
	/**
	 * The cosmetic repair activity type option. Value = {@value COSMETIC_REPAIR} 
	 */
	private static final int COSMETIC_REPAIR= 4;
	
	/**
	 * Constructs a new {@code Dentist} object and sets the full name of the dentist
	 * 
	 * @param fullName the dentist's full name
	 */
	// CONSTRUCTOR
	public Dentist(String fullName) {
		super(fullName);
	}
	
	/**
	 * Prompts the user with argument {@code String} message and returns the user's entered input as a string
	 * 
	 * @param s the prompt message
	 * @return the user's input
	 */
	// METHOD. Prints the parameter string and prompts the user for a response
	private static String getResponse(String s) {
		System.out.printf(s);
		return (scan.nextLine());
	}
	
	/**
	 * Displays a menu of activity categories and returns the selected type of activity to be performed by the dentist as a {@code String}
	 * 
	 * @return the activity category
	 */
	// GETTER. Returns the activity type
	public ArrayList<String> getActivityType() {
		// prompt the user to select a category
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Assessment");
		categories.add("Filling");
		categories.add("Crown");
		categories.add("Cosmetic Repair");
		return categories;
	}
	
//	/**
//	 * Displays a menu of activity categories and returns the selected type of activity to be performed by the dentist as a {@code String}
//	 * 
//	 * @return the activity category
//	 */
//	// GETTER. Returns the activity type
//	public String getActivityType() {
//		// prompt the user to select a category
//		String prompt = ("Enter a category from the following menu:\n"
//				+ "1.Assesment\n"
//				+ "2.Filling\n"
//				+ "3.Crown\n"
//				+ "4.Cosmetic Repair\n");
//		int input = Integer.parseInt(getResponse(prompt));
//		String category = "";
//		// return activity type based on user selection
//		switch(input) {
//		case ASSESSMENT:
//			category = "Assessment";
//			break;
//		case FILLING:
//			category = "Filling";
//			break;
//		case CROWN: 
//			category = "Crown";
//			break;
//		case COSMETIC_REPAIR:
//			category = "Cosmetic Repair";
//			break;	
//		}
//		return category;
//	}
	
	
}
