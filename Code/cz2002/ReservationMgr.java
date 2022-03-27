package cz2002;

import java.util.*;

/**
 * Reservation management in restaurant
 * @author xinhui
 */

public class ReservationMgr {
	/**
	 * table number
	 */
	int tableNo;
	/**
	 * table manager to get tables
	 */
	private TableMgr tableMgr;
	/**
	 * All reservations in restaurant
	 */
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();	

	/**
	 * creates reservation manager
	 * @param tableMgr table manager of the restaurant
	 */
	public ReservationMgr(TableMgr tableMgr){
		this.tableMgr = tableMgr;
	}
	
	/**
	 * check if there are reservations at a specific table
	 * @param tableNo table number
	 * @param reservationList reservation list
	 * @return Reservation
	 */
	public Reservation reservationFound(int tableNo, ArrayList<Reservation> reservationList) {
		for (Reservation r : reservationList) {
			if (tableNo == r.getTableNo()) return r;
		}
		return null;
	}

	/**
	 * remove reservation
	 */
	public void removeReservation() {
		int removed = 0;
		int stillReserved = 0;
		int reservationID = 0;
		boolean inputInvalid = true;
		
		if (reservationList.size() == 0) {
			System.out.println("There are no reservations");
		} 
		else {
			showReservation();
			do{
				System.out.println("Enter ID of the reservation to be removed: ");
				try {
					reservationID = RRPSS.sc.nextInt();
					inputInvalid = false;
					} catch (InputMismatchException e) {
						System.out.println("Please enter a valid number.");
						RRPSS.sc.nextLine();
					}
			}while(inputInvalid);
			
			int i = 0;
			for (Reservation r : reservationList) {
				if (r.getReservationID() == reservationID) {
					tableNo = r.getTableNo();
					reservationList.remove(i);
					removed = 1;
					System.out.println("Reservation removed sucessfully.");
					break;
				}
				i++;
			}
			// check if that tableNo still have other reservations
			for (Reservation r : reservationList) {
				if (r.getTableNo() == tableNo) {
					stillReserved = 1;
					break;
				}
			}
			
			if (stillReserved == 0) {
				for (Table t : tableMgr.getTableList()) {
					if (t.getTableNo() == tableNo) {
						t.setIsReserved(false);
						break;
						}
				}
			}

			if (removed == 0)
				System.out.println("Reservation not found.");
			
		}
		
	}
	
	/**
	 * check for expired reservations
	 */
	public void refresh() {
		Calendar now = Calendar.getInstance();
		int i=0, removed=0;
		for(Reservation r : reservationList) {
			Date temp = addTime(r.getDateTime().getTime(), 15, "MINUTE");
			
			if(temp.before(now.getTime())) {
				System.out.println("System refreshed.");
				System.out.println("Reservation ID " + r.getReservationID() + " at " + r.getDateTime().getTime()
						+ " has been removed as it has expired.");
				reservationList.remove(i);
				removed =1;
			}
			if(reservationList.size()==0)
				break;
			i++;
		}
		
		if(removed == 0) 
			System.out.println("System refreshed. No reservations were expired.");
	}
	
	/**
	 * Gets the customer's name, contact number, membership status
	 * @return Customer
	 */
	public Customer getCustomerDetails() {

		//enter customer		
		System.out.println("Enter Customer Name: ");
		String cusName = RRPSS.sc.next();
		while(!cusName.matches("[a-zA-Z]+")) {		//handles invalid customer name
			System.out.println("Please enter a valid name.");
			System.out.println("Enter Customer Name: ");
			cusName = RRPSS.sc.next();
		}
		String capCusName = cusName.substring(0,1).toUpperCase() + cusName.substring(1).toLowerCase();		//capitalize customer name
		
		System.out.println("Enter Customer Contact: ");
		String cusContact = RRPSS.sc.next();	
		while(cusContact.length() != 8 || !cusContact.chars().allMatch(Character::isDigit)) {					// handles invalid contact number error
			System.out.println("Please enter a valid contact number.");			
			System.out.println("Enter Customer Contact: ");
			cusContact = RRPSS.sc.next();
		}			
		
		System.out.println("Does the customer have membership? (true/false): ");
		String strCusMem = RRPSS.sc.next().toLowerCase();
		while(!strCusMem.equals("true") && !strCusMem.equals("false")) {		//handles invalid customer membership
			System.out.println("Please enter a valid membership.");
			System.out.println("Does the customer have membership? (true/false): ");
			strCusMem = RRPSS.sc.next().toLowerCase();
		}
		Boolean boolCusMem = Boolean.parseBoolean(strCusMem);
		
		// create customer 
		Customer customer = new Customer(capCusName, cusContact, boolCusMem);
		if(customer.addCustomer()) {
			return customer;
		}
		else {
			return null;
		}
	}

	/**
	 * add reservations
	 * @throws IllegalArgumentException checks whether time entered is valid
	 * @throws InputMismatchException checks for invalid input
	 */
	public void addReservation() throws IllegalArgumentException, InputMismatchException{
		try {
			//enter time
			
			refresh();
			
			Customer customer;
			
			do {
				System.out.println("Keying in customer details...");
				customer = getCustomerDetails();
			}while(customer == null);

			System.out.println("Keying in reservation details...");
			Calendar now = Calendar.getInstance();
			
			Calendar resDateTime;
			do {
				System.out.println("Enter Month: ");
				int month = RRPSS.sc.nextInt(); 
				System.out.println("Enter Day: ");
				int day = RRPSS.sc.nextInt(); 
				System.out.println("Enter Hour: ");
				int hour = RRPSS.sc.nextInt(); 
				if(hour < 9 || hour > 21) {
					System.out.println("Reservations only allowed from 0900-2100");
					throw new IllegalArgumentException();
				}
				System.out.println("Enter Minute: ");
				int minute = RRPSS.sc.nextInt(); 
				if(minute != 00 && minute != 30) throw new IllegalArgumentException();
				
				resDateTime = new GregorianCalendar(2021,month-1,day,hour,minute);
				resDateTime.setLenient(false);						// throws an error if input date is invalid
				
				if((resDateTime.getTime()).before(now.getTime())) {
					System.out.println("You cannot go back in time");		// cannot input an earlier time
				}
			}while((resDateTime.getTime()).before(now.getTime())); 
			
			//enter noOfPax
			
			boolean inputValid = false;
			int noOfPax = 0;
			while(!inputValid) {
				try {					
					System.out.print("Enter number of pax: ");					
					noOfPax = RRPSS.sc.nextInt();
					if(noOfPax<1)
						System.out.println("Invalid number of pax. Please key in again.");
					else if(noOfPax>8) 
						System.out.println("You have exceeded the maximum number of diners allowed. Please key in again.");
					else
						inputValid = true;																	
				}catch(InputMismatchException e) {
				   System.out.println("Invalid input! Please key in again.");	
					RRPSS.sc.nextLine();
				}
			}
			
			//set table number
			
			Date endOfPrevRes;
			Date befRes;
			int getTable=0;
			
			Date current = new Date();
			current = addTime(now.getTime(), 2, "HOUR");
				
			for (Table t : tableMgr.getTableList()) {
				if (t.getTableSize() >= noOfPax) {
					if (t.getIsOccupied()) {
						if (resDateTime.getTime().before(current)) {
							continue;
						}			
					}
					int unavail = 0;	
					for (Reservation r : reservationList) {				
						if (r.getTableNo() == t.getTableNo()) {
							endOfPrevRes = addTime(r.getDateTime().getTime(), 1, "HOUR");
							befRes = addTime(r.getDateTime().getTime(), -1, "HOUR");
								if ((!resDateTime.getTime().after(endOfPrevRes)
									&& !resDateTime.getTime().before(befRes))) {
								unavail = 1;
								break;
							}
						}
					}
						
					if (unavail != 1) {
						tableNo = t.getTableNo();
						t.setIsReserved(true);
						getTable = 1;
						break;
					}
				}
			}
				
			if (getTable == 0) {
				System.out.println("Reservation is full. Please choose another timing.");
				return;
			}
		
	
			//set reservationID
			
			int max=-1;
			for(int i=0; i<reservationList.size(); i++) {
				if(reservationList.get(i).getReservationID() > max) {
					max = reservationList.get(i).getReservationID();
				}
			}
			max++;
			
			
			//create reservation
	
			Reservation r = new Reservation(resDateTime, noOfPax, customer, tableNo, max);
			reservationList.add(r);
			sortDateTime(reservationList);
			
		} catch (IllegalArgumentException e) {
			System.out.println("Please enter a valid date and time, with timing ending with :00 or :30.");
		} catch (InputMismatchException e) {
			System.out.println("User input is not valid.");
		}
		
	}
	
	/**
	 * show all reservations
	 */
	public void showReservation() {
		
		refresh();
		
		if(reservationList.size() == 0) {
			System.out.println("There are no reservations");
		}
		else {
			for (Reservation r : reservationList){
		    	Customer cus = r.getCustomer();
		    	String cusName = cus.getCustomerName();
		    	String cusNum = cus.getCustomerContact();
		    	
		    	Calendar date = r.getDateTime();
		    	String strDate = (date.getTime()).toString();
                
		    	System.out.println("--------------------------------");
		    	System.out.println("Reservation ID: " + r.getReservationID());
		    	System.out.println("Date/Time: " + strDate);
		    	System.out.println("Name: " + cusName);
		    	System.out.println("Contact: " + cusNum);
		    	System.out.println("No. Of Pax: " + r.getNoOfPax());
		    	System.out.println("Table No.: " + r.getTableNo());
		    	System.out.println("--------------------------------");
			}
		}	
	}

	/**
	 * display all table availability at a specific time
	 * @throws IllegalArgumentException checks whether time entered is valid
	 */
	public void displayTableAvail() throws IllegalArgumentException{ 
		
		refresh();
		
		try {
			
			//create the list of all empty tables first
			
			System.out.print("Enter Month: ");
			int month = RRPSS.sc.nextInt(); 
			System.out.print("Enter Day: ");
			int day = RRPSS.sc.nextInt(); 
			System.out.print("Enter Hour: ");
			int hour = RRPSS.sc.nextInt(); 
			System.out.print("Enter Minute: ");
			int minute = RRPSS.sc.nextInt(); 
			Calendar date = new GregorianCalendar(2021,month-1,day,hour,minute);
			date.setLenient(false);
			
			for(Reservation r : reservationList){
				if(r.getDateTime().getTime().equals(date.getTime())) {
					tableMgr.getTable(r.getTableNo()).setIsReserved(true);			//then changing the data of the reserved tables
					tableMgr.getTable(r.getTableNo()).setNoOfDiners(r.getNoOfPax());
					tableMgr.getTable(r.getTableNo()).setCustomer(r.getCustomer());
				}
			}
			System.out.println("Displaying Table List for " + date.getTime());
			tableMgr.viewTableList();
		} catch(IllegalArgumentException e) {
			System.out.println("Please enter a valid date and time, with timing ending with :00 or :30.");
		}
	} 
	
	/**
	 * display availability of a specific table for the whole day
	 */
	public void displayTableAvailbyTable() {
		refresh();
		
		boolean inputInvalid = true;
		int tableNo = 0;
		do{
			System.out.println("Enter Table Number to display availabilty: ");
			try {
				tableNo = RRPSS.sc.nextInt();	
				inputInvalid = false;
			}catch(InputMismatchException e) {
			   System.out.println("Invalid input! Please key in again.");	
			   RRPSS.sc.next();
			}
		}while(inputInvalid);
		
		System.out.println("Enter day to display availability");		
		System.out.print("Enter Month: ");
		int month = RRPSS.sc.nextInt(); 
		System.out.print("Enter Day: ");
		int day = RRPSS.sc.nextInt();

		Calendar date = new GregorianCalendar(2021, month-1,day,9,00);
	
		
		System.out.println("Displaying availability for TABLE " + tableNo + " on " + day + "/" + 
		month + "/" + 2021);
		System.out.println("-----------------------------------------------");
		System.out.println("\t\tTime \t\t IsOccupied");
		System.out.println("-----------------------------------------------");
		
		
		
		for(int i=0; i<25; i++) {
			int found = 0;
			System.out.print(date.getTime() + "\t");
			
			Table table = null;
			for(Table t : tableMgr.getTableList()) {
				if(t.getTableNo() == tableNo) {
					table = t;
					break;
				}
			}	
			Calendar now = Calendar.getInstance();
			if(table.getIsOccupied() == true) {
				Calendar temp1 = (Calendar) date.clone();
				if(now.after(addTime(temp1.getTime(),-30,"MINUTE")) || now.before(addTime(temp1.getTime(),30,"MINUTE"))){
					found = 1;
					System.out.println("Occupied");
				}
			}
			
			for(Reservation r : reservationList) {
				if(r.getTableNo() == tableNo) {
					Calendar temp = (Calendar) date.clone();
					temp.add(Calendar.MINUTE, -30);
					if(r.getDateTime().getTime().equals(date.getTime()) || r.getDateTime().getTime().equals(temp.getTime())) {
						found = 1;
						System.out.println("Occupied");
					}
				}
			}
			if(found == 0) System.out.println("Empty");
			date.add(Calendar.MINUTE, 30);
		}
		
	}
	
	/**
	 * Gets list of all reservations in restaurant
	 * @return ArrayList of Reservation objects list of reservations
	 */
	public ArrayList<Reservation> getReservationList(){
		return reservationList;
	}
	
	/**
	 * adds time
	 * @param date original date
	 * @param time amount of time to add
	 * @param choice minute or hour
	 * @return Date date after adding time
	 */
	public Date addTime(Date date, int time, String choice) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    if(choice == "HOUR")
	    	calendar.add(Calendar.HOUR_OF_DAY, time);
	    else if(choice == "MINUTE")
	    	calendar.add(Calendar.MINUTE, time);
	    return calendar.getTime();
	}
	
	/**
	 * sort the reservation list by time
	 * @param rList reservation list
	 * @return ArrayList of Reservation objects list of reservations
	 */
	public ArrayList<Reservation> sortDateTime(ArrayList<Reservation> rList){
		for(int j=1; j<rList.size(); j++) {
			Reservation current = rList.get(j);
			Calendar key = rList.get(j).getDateTime();
			int i = j-1;
			while(i>=0 && rList.get(i).getDateTime().after(key)) {
				rList.set(i+1, rList.get(i));
				i--;
			}
			rList.set(i+1, current);
		}
		return rList;
	}




}