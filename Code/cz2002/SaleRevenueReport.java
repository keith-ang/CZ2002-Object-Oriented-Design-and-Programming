package cz2002;
import java.util.*;

/**
 * Sales Revenue Report of restaurant
 * @author wongl
 *
 */
public class SaleRevenueReport {

	/**
	 * invoice manager of restaurant
	 */
	private InvoiceMgr invoiceMgr;
	
	/**
	 * stores all the items customer purchased with respective quantities
	 */
	private ArrayList<ItemQuantity> quantityList = new ArrayList<ItemQuantity>();
	
	/**
	 * constructor for sales revenue report
	 * @param invoiceMgr invoice manager of restaurant
	 */
	public SaleRevenueReport(InvoiceMgr invoiceMgr){
		this.invoiceMgr = invoiceMgr;
	}
	
	/**
	 * Gets total revenue of restaurant of a particular day
	 * @return total amount earned for a particular day
	 */
	public double getTotalRevenueDay(){
		double rev = 0;
		boolean inputValid = false;
		int month=0,day=0;
		
		while(!inputValid) {
			try {
				
				Calendar now = Calendar.getInstance();
				Calendar temp = new GregorianCalendar();
			
				System.out.println("Enter Month: ");
				String input = RRPSS.sc.next();
				month = Integer.parseInt(input);
				System.out.println("Enter Day: ");
				input = RRPSS.sc.next(); 
				day = Integer.parseInt(input);
				
				temp.setLenient(false);
				temp.set(2021, month-1, day);
				if(now.getTime().before(temp.getTime())) throw new IllegalArgumentException();
				
				inputValid = true;
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid input.");
			}catch(IllegalArgumentException e) {
				System.out.println("Please do not enter a future date.");
			}
		}
				
		for(Invoice i : invoiceMgr.getInvoiceList()){
			if( i.getTimeStamp().get(Calendar.MONTH) == month-1 && i.getTimeStamp().get(Calendar.DAY_OF_MONTH) == day) {
				rev += i.getFinalAmount();
			}
		}	
		System.out.println("Total Revenue for " + day + "/" + month + "/2021 = $" + String.format("%5.2f", rev));
		return rev;
	}

	/**
	 * Gets total revenue of the particular month
	 * @return total revenue of particular month
	 */
	public double getTotalRevenueMonth() {
	
		double rev = 0;
		boolean inputValid = false;
		int month=0;
		
		while(!inputValid) {
			try {
				
				Calendar now = Calendar.getInstance();
				Calendar temp = new GregorianCalendar();
			
				System.out.println("Enter month: ");
				String input = RRPSS.sc.next();
				month = Integer.parseInt(input);
				temp.setLenient(false);
				temp.set(Calendar.MONTH, month-1);;
				//System.out.println(temp.getTime());
				if(now.getTime().before(temp.getTime())) throw new IllegalArgumentException();
				
				inputValid = true;
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid input.");
			}catch (IllegalArgumentException e) {
				System.out.println("Please do not enter a future month.");
			}
		}
				
		for(Invoice i : invoiceMgr.getInvoiceList()){
			if((i.getTimeStamp()).get(Calendar.MONTH) == month-1) {
				rev += i.getFinalAmount();
			}
		}
		System.out.println("Total Revenue for " + month + "/2021 = $" + String.format("%5.2f", rev));
		return rev;
	}

	/**
	 * Gets total quantity that customer purchased for a particular day
	 */
	public void getTotalQtyDay() {
		
		boolean inputValid = false;
		int month=0,day=0;
		
		while(!inputValid) {
			try {
				
				Calendar now = Calendar.getInstance();
				Calendar temp = new GregorianCalendar();
			
				System.out.println("Enter Month: ");
				String input = RRPSS.sc.next();
				month = Integer.parseInt(input);
				System.out.println("Enter Day: ");
				input = RRPSS.sc.next(); 
				day = Integer.parseInt(input);
				
				temp.setLenient(false);
				temp.set(2021, month-1, day);
				if(now.getTime().before(temp.getTime())) throw new IllegalArgumentException();
				
				inputValid = true;
				
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid input.");
			}catch(IllegalArgumentException e) {
				System.out.println("Please do not enter a future date.");
			}
			
		}
		
		quantityList.clear();
		
		if(invoiceMgr.getInvoiceList().size() == 0 ) {
			System.out.println("No sale this month");
			return;
		}
		
		int a,b;
		
		for (a = 0; a < invoiceMgr.getInvoiceList().size(); a++) {
			Invoice invoice = invoiceMgr.getInvoiceList().get(a);
			if (invoice.getTimeStamp().get(Calendar.MONTH) == month - 1) {
				for (b = 0; b < invoice.getQuantityList().size(); b++) {
					ItemQuantity item = invoice.getQuantityList().get(b);
					quantityList.add(item);
				}
			}
		}
		
		quantityList.sort(Comparator.comparing(ItemQuantity::getName));
		/*
		for(i=0;i<quantityList.size();i++) {
			System.out.printf("%d %-30s %5.2f\n",quantityList.get(i).getQuantity(),quantityList.get(i).getName(),quantityList.get(i).getPrice());
		}*/
		int count,j,k;
		count = quantityList.get(0).getQuantity();
		for(k=0;k<quantityList.size();k++) {
			count = quantityList.get(k).getQuantity();
			ItemQuantity item = quantityList.get(k);
			for(j=k+1;j<quantityList.size();j++) {
				if(item.getName().equals(quantityList.get(j).getName()))
					count+=quantityList.get(j).getQuantity();
				else {
					System.out.printf("%d %-30s %5.2f\n",count,quantityList.get(k).getName(),count*quantityList.get(k).getPrice());
					k=j-1;
					break;
				}
			}
			
			if(j==quantityList.size()) {
				System.out.printf("%d %-30s %5.2f\n",count,quantityList.get(k).getName(),count*quantityList.get(k).getPrice());
				break;
			}
		}
	}

	/**
	 * Gets total quantity customers purchased for a particular month
	 */
	public void getTotalQtyMonth() {
		int month = 0;
		boolean inputValid = false;
		
		while(!inputValid) {
			try {
				
				Calendar now = Calendar.getInstance();
				Calendar temp = new GregorianCalendar();
			
				System.out.println("Enter month: ");
				String input = RRPSS.sc.next();
				month = Integer.parseInt(input);
				temp.setLenient(false);
				temp.set(Calendar.MONTH, month-1);;
				//System.out.println(temp.getTime());
				if(now.getTime().before(temp.getTime())) throw new IllegalArgumentException();
				
				inputValid = true;
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid input.");
			}catch (IllegalArgumentException e) {
				System.out.println("Please do not enter a future month.");
			}
		}
		
		quantityList.clear();
		
		int k,i;
		if(invoiceMgr.getInvoiceList().size() == 0 ) {
			System.out.println("No sale this month");
			return;
		}
		for (k = 0; k < invoiceMgr.getInvoiceList().size(); k++) {
			Invoice invoice = invoiceMgr.getInvoiceList().get(k);
			if (invoice.getTimeStamp().get(Calendar.MONTH) == month - 1) {
				for (i = 0; i < invoice.getQuantityList().size(); i++) {
					ItemQuantity item = invoice.getQuantityList().get(i);
					quantityList.add(item);
				}
			}
		}
		quantityList.sort(Comparator.comparing(ItemQuantity::getName));
		/*
		for(i=0;i<quantityList.size();i++) {
			System.out.printf("%d %-30s %5.2f\n",quantityList.get(i).getQuantity(),quantityList.get(i).getName(),quantityList.get(i).getPrice());
		}*/
		int count,j;
		count = quantityList.get(0).getQuantity();
		for(i=0;i<quantityList.size();i++) {
			count = quantityList.get(i).getQuantity();
			ItemQuantity item = quantityList.get(i);
			for(j=i+1;j<quantityList.size();j++) {
				if(item.getName().equals(quantityList.get(j).getName()))
					count+=quantityList.get(j).getQuantity();
				else {
					System.out.printf("%d %-30s %5.2f\n",count,quantityList.get(i).getName(),count*quantityList.get(i).getPrice());
					i=j-1;
					break;
				}
			}
			
			if(j==quantityList.size()) {
				System.out.printf("%d %-30s %5.2f\n",count,quantityList.get(i).getName(),count*quantityList.get(i).getPrice());
				break;
			}
		}
	}
	

}