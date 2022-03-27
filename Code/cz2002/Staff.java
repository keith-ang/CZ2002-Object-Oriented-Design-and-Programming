package cz2002;
/**
 * Represents a staff worker in the restaurant
 * @author Keith
 *
 */
public class Staff {
	/** 
	 * Name of Staff
	 */
	private String staffName;
	/**
	 * Gender of staff
	 */
	private char staffGender;
	/**
	 * ID of staff
	 */
	private int staffID;
	/**
	 * Job title of staff
	 */
	private String staffRole;
		
	/**
	 * Creates new staff worker in the restaurant
	 * @param staffName name of staff
	 * @param staffGender gender of staff
	 * @param staffID ID of staff
	 * @param staffRole role of staff
	 */
	public Staff(String staffName, char staffGender, int staffID, String staffRole) {
		this.staffName = staffName;
		this.staffID = staffID;
		this.staffGender = staffGender;
		this.staffRole = staffRole;	
	} 

	/**
	 * Gets name of a staff
	 * @return String staff name
	 */
	public String getStaffName() {
		return this.staffName;
	}

	/** 
	 * sets the name of a staff
	 * @param StaffName name of staff
	 */
	public void setStaffName(String StaffName) {
		this.staffName = StaffName;
	}

	/** 
	 * gets the gender of a staff
	 * @return char staff gender
	 */
	public char getStaffGender() {
		return this.staffGender;
	}

	/**
	 * sets the gender of a staff
	 * @param StaffGender gender of staff
	 */
	public void setStaffGender(char StaffGender) {
		this.staffGender = StaffGender;
	}

	/**
	 * gets the ID of a staff
	 * @return int staff ID
	 */
	public int getStaffID() {
		return this.staffID;
	}

	/**
	 * sets the ID of a staff
	 * @param StaffID ID of staff
	 */
	public void setStaffID(int StaffID) {
		this.staffID = StaffID;
	}

	/**
	 * gets the role of a staff
	 * @return String staff role
	 */
	public String getStaffRole() {
		return this.staffRole;
	}

	/**
	 * sets the role of a staff
	 * @param StaffRole role of staff
	 */
	public void setStaffRole(String StaffRole) {
		this.staffRole = StaffRole;
	}
	
	
}