package cz2002;

/**
 * Represents a table in the restaurant
 * @author Keith
 */
public class Table {
	/**
	 * table number of the table
	 */
	private int tableNo;
	/** 
	 * seating capacity of the table
	 */
	private int tableSize;
	/**
	 * indicates whether table is occupied
	 */
	private boolean isOccupied;
	/**
	 * indicates whether table is reserved
	 */
	private boolean isReserved;
	/** 
	 * number of diners for the table
	 */
	private int noOfDiners;
	/**
	 * Customer object that is assigned to this table
	 */
	private Customer customer;
	
	/**
	 * Creates a new table in the restaurant
	 * @param tableNo table number
	 * @param tableSize size of table
	 * @param isOccupied occupied status
	 * @param isReserved reserved status
	 * @param noOfDiners number of diners
	 * @param customer customer
	 */ 
	public Table(int tableNo, int tableSize, boolean isOccupied, boolean isReserved, int noOfDiners, Customer customer) {
		this.tableNo = tableNo;
		this.tableSize = tableSize;
		this.noOfDiners = noOfDiners;
		this.isOccupied = isOccupied;
		this.isReserved = isReserved;
		this.customer = customer;
	}
	
	/**
	 * gets the seating capacity of this table
	 * @return int Size of Table
	 */
	public int getTableSize() {
		return this.tableSize;
	}
	
	/**
	 * sets the seating capacity for this table
	 * @param tableSize size of table
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}
	
	/**
	 * gets the number of diners for this table
	 * @return int Number of Diners
	 */
	public int getNoOfDiners() {
		return this.noOfDiners;
	}
	
	/**
	 * sets the number of diners for this table
	 * @param noOfDiners number of diners
	 */
	public void setNoOfDiners(int noOfDiners) {
		this.noOfDiners = noOfDiners;
	}
	
	/**
	 * gets the occupied status of this table
	 * @return isOccupied
	 */
	public boolean getIsOccupied() {
		return this.isOccupied;
	}
	
	/**
	 * sets this table to be occupied or not
	 * @param isOccupied occupied status
	 */
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	/**
	 * gets the reserved status of this table
	 * @return boolean isReserved
	 */
	public boolean getIsReserved() {
		return this.isReserved;
	}
	
	/**
	 * sets this table to be reserved or not
	 * @param isReserved reserved status
	 */
	public void setIsReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
	/**
	 * gets the table number of this table
	 * @return int table number
	 */
	public int getTableNo() {
		return this.tableNo;
	}
	
	/**
	 * sets the table number for this table
	 * @param tableNo table number
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/**
	 * gets the customer object assigned to this table
	 * @return Customer customer
	 */
	public Customer getCustomer() { 
			return this.customer; 
	} 
	
	/**
	 * sets the customer object assigned to this table
	 * @param customer Customer 
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}