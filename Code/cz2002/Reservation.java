package cz2002;

import java.util.*;

/**
 * Represents an reservation created for a table in the Restaurant
 * @author xinhui
 */

public class Reservation {
	/**
	 * Time of reservation
	 */
	private Calendar dateTime;
	/**
	 * No. of pax in the reservation
	 */
	private int noOfPax;
	/**
	 * Customer who booked the reservation
	 */
	private Customer customer;
	/**
	 * Table number the customers are assigned to
	 */
	private int tableNo;
	/**
	 * Unique reservationID given to each reservation
	 */
	private int reservationID;  

	/**
	 * creates new reservation
	 * @param dateTime reservation time
	 * @param noOfPax number of pax
	 * @param customer customer
	 * @param tableNo table number
	 * @param reservationID reservation ID
	 */
	public Reservation(Calendar dateTime, int noOfPax, Customer customer, int tableNo, int reservationID) {
		this.dateTime = dateTime;
		this.noOfPax = noOfPax;
		this.customer = customer;
		this.tableNo = tableNo;
		this.reservationID = reservationID;
	}

	/**
	 * Gets reservation time
	 * @return Calendar reservation time
	 */
	public Calendar getDateTime() {
		return this.dateTime;
	}

	/**
	 * Gets number of pax in the reservatiom
	 * @return int number of pax
	 */
	public int getNoOfPax() {
		return this.noOfPax;
	}

	/**
	 * Gets reservationID
	 * @return int reservationID
	 */
	public int getReservationID() {
		return this.reservationID;
	}

	/**
	 * Gets the customer who booked the reservation
	 * @return Customer customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Gets table number assigned for that reservation
	 * @return int table number
	 */
	public int getTableNo() {
		return this.tableNo;
	}

}