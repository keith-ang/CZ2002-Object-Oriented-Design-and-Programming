package cz2002;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
//import java.lang.Boolean;
//import java.nio.charset.Charset;

/**
 * Represents a customer that patronage restaurant
 * @author wongl
 *
 */
public class Customer {

	private String customerName;
	private String customerContact;
	private boolean customerMembership;

	/**
	 * Creates a customer that enters the restaurant/reserves a table
	 * @param customerName name of customer
	 * @param customerContact contact number of customer
	 * @param customerMembership membership status of customer
	 */
	public Customer(String customerName, String customerContact, boolean customerMembership) {
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerMembership=customerMembership;
	}

	
	/** 
	 * gets name of customer
	 * @return String cutomer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	
	/** 
	 * gets customer number
	 * @return String number of customer
	 */
	public String getCustomerContact() {
		return customerContact;
	}

	
	/** 
	 * gets customer membership
	 * @return boolean membership of customer
	 */
	public boolean getCustomerMembership() {
		return customerMembership;
	}

	
	/**
	 * Adds a new customer to csv file to store the list of customers that patronage restaurant
	 * @return boolean true if customer added
	 */
	public Boolean addCustomer() {

		String filepath = "src/CustomerList.csv";

		if (readRecord()==0) {
			try {
				FileWriter fw = new FileWriter(filepath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				pw.println(this.customerName + "," + this.customerContact + "," + this.customerMembership);

				pw.flush();
				pw.close();
				/*
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(filepath,Charset.forName("UTF-8"),true));
				
				String line;
				line = "\n" + this.customerName + "," + this.customerContact + "," + this.customerMembership;
				bw.write(line);
				bw.close();*/
				
			} catch (Exception e) {
				System.out.printf("Failed to add customer \n");
				return false;
			}

			System.out.printf("Customer list updated! \n");
			return true;
		} 
		else if (readRecord() == 2) {
			System.out.println("Duplicate Number, please enter again.");
			return false;
		}
		else if (readRecord() == 3) {
			System.out.println("Membership Error, please enter again.");
			return false;
		}
		else {
			System.out.println("Customer is existing");
			return true;
		}
	}

	
	/** 
	 * read csv file to check if customer exists
	 * @return int 0 if customer not in csv file and adds to record
	 */
	public int readRecord() {

		String filepath = "src/CustomerList.csv";

		String CustName = "";
		String CustNum = "";
		try {
			Scanner sc = new Scanner(new File(filepath));
			sc.useDelimiter("[,\n]");

			while (sc.hasNext()) {
				CustName = sc.next();
				CustNum = sc.next();
				String CustMem = sc.next();

			    String text = CustMem.replaceAll("\r", "").replaceAll("\n", "");

				boolean i=false;
				if(this.customerMembership) {
					if(text.equalsIgnoreCase("true") )
						i=true;
				}
				else {
					if(text.equalsIgnoreCase("false") )
						i=true;
				}
				
				if(!CustName.equals(this.customerName) && CustNum.equals(this.customerContact)) {
					//System.out.println("Duplicate Number");
					return 2;
				}
				
				else if (CustName.equals(this.customerName) && CustNum.equals(this.customerContact) && !i) {
					//System.out.println("Membership Error");
					return 3;
				}
				
				else if (CustName.equals(this.customerName) && CustNum.equals(this.customerContact) && i)
					return 1;
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
	
	
	
	
	
	
	
	
	
//
//	/**
//	 * Adds a new customer to csv file to store the list of customers that patronage restaurant
//	 * Able to get customer membership from this csv file
//	 */
//	public void addCustomer() {
//
//		String filepath = "src/sample1.csv";
//
//		if (!readRecord()) {
//			try {
//				FileWriter fw = new FileWriter(filepath, true);
//				BufferedWriter bw = new BufferedWriter(fw);
//				PrintWriter pw = new PrintWriter(bw);
//				pw.println(this.customerName + "," + this.customerContact + "," + this.customerMembership);
//
//				pw.flush();
//				pw.close();
//				/*
//				
//				BufferedWriter bw = new BufferedWriter(new FileWriter(filepath,Charset.forName("UTF-8"),true));
//				
//				String line;
//				line = "\n" + this.customerName + "," + this.customerContact + "," + this.customerMembership;
//				bw.write(line);
//				bw.close();*/
//				
//			} catch (Exception e) {
//				System.out.printf("Failed to add customer \n");
//				return;
//			}
//
//			System.out.printf("Customer list updated! \n");
//		} else
//			System.out.println("Customer is existing");
//
//	}*/
//
//	
//	/** 
//	 * read csv file to check if customer exists
//	 * @return boolean
//	 */
//	public boolean readRecord() {
//
//		String filepath = "src/sample1.csv";
//
//		String CustName = "";
//		String CustNum = "";
//		try {
//			Scanner sc = new Scanner(new File(filepath));
//			sc.useDelimiter("[,\n]");
//
//			while (sc.hasNext()) {
//				CustName = sc.next();
//				CustNum = sc.next();
//				String CustMem = sc.next();
//
//			    String text = CustMem.replaceAll("\r", "").replaceAll("\n", "");
//
//				boolean i=false;
//				if(this.customerMembership) {
//					if(text.equalsIgnoreCase("true") )
//						i=true;
//				}
//				else {
//					if(text.equalsIgnoreCase("false") )
//						i=true;
//				}
//				
//				if(!CustName.equals(this.customerName) && CustNum.equals(this.customerContact)) {
//					System.out.println("Duplicate Number");
//					return true;
//				}
//				
//				else if (CustName.equals(this.customerName) && CustNum.equals(this.customerContact) && !i) {
//					System.out.println("Membership Error");
//					return true;
//				}
//				
//				else if (CustName.equals(this.customerName) && CustNum.equals(this.customerContact) && i)
//					return true;
//				
//				
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	
//}*/