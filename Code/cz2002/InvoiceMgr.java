package cz2002;

import java.util.*;
/**
 * Invoice management in restaurant
 * @author wongl
 *
 */
public class InvoiceMgr{
	
	/**
	 * All invoices in restaurant
	 */
	private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	
	/**
	 * staff name
	 */
	private String staffName;
	/**
	 * staff manager to get staff
	 */
	private StaffMgr staffMgr;
	
	/**
	 * creates invoice manager
	 * @param staffMgr staff manager of restaurant
	 */
	public InvoiceMgr(StaffMgr staffMgr) {
		this.staffMgr = staffMgr;
	}
	
	
	/** 
	 * Gets list of all invoices in restaurant
	 * @return ArrayList of Invoice objects list of invoices
	 */
	public ArrayList<Invoice> getInvoiceList(){
		return invoiceList;
	}
	
	
	/** 
	 * creates invoice for customer
	 * @param orderMgr order manager
	 * @param tableMgr table manager
	 * @param tableNo table number of customer
	 * @param membership membership 
	 */
	public void addInvoice(OrderMgr orderMgr,TableMgr tableMgr,int tableNo,Membership membership) {


		System.out.println("\nWhich Staff is ending this order? (Enter StaffID): ");
		int staffID = RRPSS.sc.nextInt();
		
		for(Staff s : staffMgr.getStaffList()) {
			if(s.getStaffID() == staffID) {
				staffName = s.getStaffName();
			}
		}
		Invoice invoice = new Invoice(orderMgr, tableMgr, staffName, tableNo, membership);
		invoiceList.add(invoice);
		invoice.createQuantityList();
		System.out.println("Invoice for tableNo " + tableNo + " has been added!");


	}
	
	
	/** 
	 * prints invoice for customer at the particular table number
	 * @param tableMgr table manager
	 * @param tableNo table number
	 */
	public void printInvoice(TableMgr tableMgr,int tableNo) {
		String custNo = tableMgr.getTable(tableNo).getCustomer().getCustomerContact();
		for (int i = 0; i < invoiceList.size(); i++) {
			if (invoiceList.get(i).getTableNo() == tableNo && invoiceList.get(i).getCustNo().equals(custNo)) {
				invoiceList.get(i).print();
				return;
			}

		}
		System.out.println("No Invoice for tableNo " + tableNo);
		
	}
	
	/**
	 * prints all invoices in restaurant
	 */
	public void printAllInvoice() {
		for(int i=0;i<invoiceList.size();i++) {
			invoiceList.get(i).print();
		}
	}
	
}
	
	