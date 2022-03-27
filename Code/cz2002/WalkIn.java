package cz2002;
import java.util.*;

/**
 * seats customers who come into the restaurant
 * @author celeste
 *
 */

public class WalkIn {
	private TableMgr tableMgr;
	private ReservationMgr rMgr;
	private int TableNo;

	/**
	 * creates walk ins
	 * @param tableMgr table manager
	 * @param rMgr reservation manager
	 */
	WalkIn(TableMgr tableMgr, ReservationMgr rMgr){
		this.tableMgr = tableMgr;
		this.rMgr = rMgr;
	}
	
	/**
	 * gets the table number assigned to the walk in customer
	 * @return int table number
	 */
	public int getTableNo() {
		return this.TableNo;
	}
	
	/**
	 * set the table with number of pax who walk in and the customer
	 * @param t table
	 * @param noOfPax number of pax
	 * @param tableNo table number
	 * @param customer customer
	 */
	public void settingTable(Table t, int noOfPax, int tableNo, Customer customer) {
		t.setNoOfDiners(noOfPax);
		t.setIsOccupied(true);
		t.setCustomer(customer);
		System.out.println("They are assigned to table " + tableNo + ".");
	}
	
	/**
	 * allows walk in customers to occupy the table
	 * @return boolean whether table can be occupied
	 */
	public boolean occupyTable() {
		int tableNo = 0;
		int gotTable = 0;
		
		String answer;
		Calendar now = Calendar.getInstance();
		
		//if walk-in or reservation
		do {
			System.out.println("Did customer reserve table? (yes/no)");
			answer = RRPSS.sc.next();
			if(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) 
				System.out.println("Invalid input!");
		}while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
		
		if(answer.equalsIgnoreCase("yes")) {
			System.out.println("Enter customer name: ");
			String cusName = RRPSS.sc.next();
			while(!cusName.matches("[a-zA-Z]+")) {		//handles invalid customer name
				System.out.println("Please enter a valid name.");
				System.out.println("Enter Customer Name: ");
				cusName = RRPSS.sc.next();
			}
			for (Reservation r : rMgr.getReservationList()){
				if(cusName.equalsIgnoreCase(r.getCustomer().getCustomerName())) {
					tableNo = r.getTableNo();
					Date temp = rMgr.addTime(now.getTime(), 15, "MINUTE");	//customer come in 15minutes before the reservation timing
					if(temp.before(r.getDateTime().getTime())) {
						System.out.println("Customer is too early, please comeback 15mins before the reservation timing.");
						return false;
					}
					System.out.println("Reservation ID is: " + r.getReservationID());	
					for(Table t : tableMgr.getTableList()) {
						if(t.getTableNo() == tableNo) {
							settingTable(t, r.getNoOfPax(), tableNo, r.getCustomer());
							this.TableNo = tableNo;
							rMgr.removeReservation();
							return true;
						}
					}
				}
			}
			System.out.println("Error! Customer did not reserve a table.");
			return false;
		}
		
		else {
			//number of diners
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
				   RRPSS.sc.next();
				}
			}
			
			
			//check if they can leave before the reservation timing
			Date temp = rMgr.addTime(now.getTime(), 1, "HOUR");		//1hours buffer time
			for(Table t : tableMgr.getTableList()) {
				if(t.getTableSize() >= noOfPax && !t.getIsOccupied()) { //need check the reservation timing 
					tableNo = t.getTableNo();
					if(t.getIsReserved()){
						for(Reservation r : rMgr.getReservationList()) {
							if(r.getTableNo() == tableNo) {
								if(temp.before(r.getDateTime().getTime())) {
									Customer customer;
									do {
										System.out.println("Keying in customer details...");
										customer = rMgr.getCustomerDetails(); //customer info
									}while(customer == null);
									settingTable(t, noOfPax, tableNo, customer);	
									this.TableNo = tableNo;
									gotTable=1;
									return true;
								}
								break;
							}
						}
					}
					else {
						Customer customer;
						do {
							System.out.println("Keying in customer details...");
							customer = rMgr.getCustomerDetails(); //customer info
						}while(customer == null);
						settingTable(t, noOfPax, tableNo, customer);
						this.TableNo = tableNo;
						gotTable=1;
						return true;
					}
				}
			}
		}			
		if(tableNo == 0 || gotTable == 0) {
			System.out.println("Fullhouse, comeback  later.");
			return false;
		}
		return false;
	}
	
}