package cz2002;

import java.util.*;

/**
 * Represents an Invoice created for a table in the Restaurant
 * Each table has one Invoice for a particular customer
 * implements Print() method to print Invoice
 * @author wongl
 *
 */
public class Invoice implements Printable {
	
	/**
	 * Order manager of restaurant to get MenuItems customer ordered 
	 */
	private OrderMgr orderMgr;
	/**
	 * Table manager of restaurant to get customer details(membership and contact number)
	 */
	private TableMgr tableMgr;
	/**
	 * Name of staff 
	 */
	private String staffName;
	/**
	 * table number for this invioce
	 */
	private int tableNo;
	/**
	 * Membership to get customer membership status
	 */
	private Membership membership;
	/**
	 * Calendar to get time when Invoice is printed
	 */
	private Calendar timeStamp;
	/**
	 * membership status of customer
	 */
	private boolean member;
	/**
	 * customer number for the table to track membership
	 */
	private String custNo;

	/**
	 * Items in a customer's order and its respecitve quantity 
	 */
	private ArrayList<ItemQuantity> quantityList = new ArrayList<ItemQuantity>();

	/**
	 * Creates new invoice for a customer
	 * @param orderMgr order manager 
	 * @param tableMgr table manager
	 * @param staffName staff name
	 * @param tableNo table number
	 * @param membership membership of customer
	 */
	public Invoice(OrderMgr orderMgr,TableMgr tableMgr,String staffName, int tableNo, Membership membership) {
		
		this.orderMgr = orderMgr;
		this.tableMgr = tableMgr;
		this.staffName = staffName;
		this.tableNo = tableNo;
		this.membership = membership;
		this.member = membership.membershipStatus(tableMgr.getTable(this.tableNo).getCustomer().getCustomerContact());
		this.custNo = tableMgr.getTable(tableNo).getCustomer().getCustomerContact();
		
	}
	
	
	/** 
	 * Gets list of items and respective quantity that customer ordered
	 * @return ArrayList of ItemQuantity objects list of items with quantity
	 */
	public ArrayList<ItemQuantity> getQuantityList(){
		return quantityList;
	}
	
	
	/** 
	 * Gets staff name
	 * @return String staff name
	 */
	public String getStaffName() {
		return this.staffName;
	}


	
	/** 
	 * Gets total amount for customer's order
	 * @return double total amount of order
	 */
	public double getTotalAmount() {
		double sum=0;
		for(int i=0;i<quantityList.size();i++) {
			sum += quantityList.get(i).getQuantity()*quantityList.get(i).getPrice();
		}
		return sum;
	}
	
	
	/** 
	 * gets table number for this invoice
	 * @return int table number
	 */
	public int getTableNo() {
		return this.tableNo;
	}
	
	/** 
	 * gets customer number
	 * @return String customer number
	 */
	public String getCustNo() {
		return this.custNo;
	}
	
	
	/** 
	 * gets membership status of customer
	 * @return boolean customer membership
	 */
	public boolean getMembership() {
		if(membership.membershipStatus(tableMgr.getTable(this.tableNo).getCustomer().getCustomerContact()))
				this.member = true;
		else
			this.member = false;
		return this.member;
	}
	
	
	/** 
	 * gets GST amount for the order
	 * @return double GST amount
	 */
	public double getGST() {
		double amount;
		if(member) 
			amount = 0.177* (getTotalAmount()*0.9);
		else
			amount = 0.177*getTotalAmount();
		return amount;
		
	}
	
	
	
	/** 
	 * gets the final total amount including discount/GST
	 * @return double final total amount
	 */
	public double getFinalAmount() {
		double amount;
		if(member)
			amount = 1.177*(getTotalAmount()*0.9);
		else
			amount = 1.177*getTotalAmount();
		return amount;
	}
	
	/**
	 * Using MenuItems in Order foodList to get the Items and its respective quantity
	 */
	public void createQuantityList() {
		
		Order order = orderMgr.getOrder(tableNo);
		int count = 1, i, j;

		for (i = 0; i < order.getFoodList().size(); i++) {
			for (j = i + 1; j < order.getFoodList().size(); j++) {
				if (order.getFoodList().get(i).getName().equals(order.getFoodList().get(j).getName()))
					count++;
				else {
					ItemQuantity itemQuantity = new ItemQuantity(order.getFoodList().get(i), count);
					quantityList.add(itemQuantity);
					// System.out.printf("%d %-30s
					// %5.2f\n",count,order.getItemName(i),count*order.getPrice(i));
					count = 1;
					i = j - 1;
					break;
				}
			}

			if (j == order.getFoodList().size()) {
				ItemQuantity itemQuantity = new ItemQuantity(order.getFoodList().get(i), count);
				quantityList.add(itemQuantity);
				// System.out.printf("%d %-30s %5.2f\n",count,order.getItemName(i),
				// count*order.getPrice(i) );
				break;
			}
		}
	}
	
	/**
	 * prints the invoice
	 */
	public void print() {
		
		Calendar now = Calendar.getInstance();
		this.timeStamp = now;
	
		
		System.out.println();
		System.out.println("------------------------------------\n");
		System.out.println("\t\tNTU \n\t50 Nanyang Ave, 639798");
		System.out.println();
		System.out.println("Staff: " + this.staffName);
		System.out.println(now.getTime());
		System.out.println("Table: " + this.tableNo);
		System.out.println("------------------------------------\n");
		
		
		for(int i=0;i<quantityList.size();i++) {
			System.out.printf("%d %-30s %5.2f\n",quantityList.get(i).getQuantity(),quantityList.get(i).getName(),quantityList.get(i).getQuantity()*quantityList.get(i).getPrice());
		}

		System.out.println();

		System.out.println("----------------------------------------\n");
		System.out.printf("\t\t Sub-total:      %5.2f\n",getTotalAmount());
		if (member) {
			System.out.printf("\t\t After discount: %5.2f\n", 0.9*getTotalAmount());
		}
		
		System.out.printf("\t\t GST:            %5.2f\n",getGST());
		System.out.printf("\t\t TOTAL:          %5.2f\n",getFinalAmount());
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println("\t Thank you for dining with us!");
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println();
	}
	
	
	
	/** 
	 * gets time when invoice printed
	 * @return Calendar time 
	 */
	public Calendar getTimeStamp() {
		return timeStamp;
	}
	/*
	public void setTimeStamp(Calendar time) {
		this.timeStamp = time;
	}
	*/
}