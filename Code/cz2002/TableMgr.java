package cz2002;

import java.util.*;

/**
 * Represents the table management in the restaurant
 * @author Keith
 */

public class TableMgr {
	/**
	 * All tables in the restaurant
	 */
	private ArrayList<Table> tableList = new ArrayList<Table>();
	
	/**
	 * Creates a table manager object
	 */
	//constructor
	public TableMgr() {}
	
	/**
	 * Creates a default number of Table objects and add them into the tableList
	 */
	public void instantiateTableList() {
		tableList.clear();
		for(int i=1; i<=2; i++) {
			Table t = new Table(i, 2, false, false, 0, null);
			tableList.add(t);
		}
		
		for(int i=5; i<=8; i++) {
			Table t = new Table(i, 4, false, false, 0, null);
			tableList.add(t);
		}
		for(int i=9; i<=12; i++) {
			Table t = new Table(i, 6, false, false, 0, null);
			tableList.add(t);
		}
		for(int i=13; i<=16; i++) {
			Table t = new Table(i, 8, false, false, 0, null);
			tableList.add(t);
		}
	}
	
	/**
	 * gets the Table object based on the table number
	 * @param tableNo table number
	 * @return Table table
	 */
	public Table getTable(int tableNo) {
		for (int i = 0; i < tableList.size(); i++)
			if (tableList.get(i).getTableNo() == tableNo)
				return tableList.get(i);
		return null;
	}

	/**
	 * sets the occupied status, number of diners, and customer assigned to this table to empty after customer has made payment and left
	 * @param tableNo table number
	 */
	//clear table
	public void clearTable(int tableNo) {
		Table t = getTable(tableNo);
		t.setIsOccupied(false);
		t.setNoOfDiners(0);
		t.setCustomer(null);
		System.out.println("Table " + tableNo + " is now unoccupied.");

	}
	
	/**
	 * prints a list of all the tables in the restaurant showing their details at the specified time
	 */
	//print tableList
	public void viewTableList() {
		System.out.println("TableNo TableSize NoOfPax Occupied Reserved CustomerName");
		System.out.println("--------------------------------------------------------");
		for(Table t : tableList) {
			if(t.getCustomer() != null) {
			System.out.println(t.getTableNo() + "\t" + t.getTableSize() + "\t  " + t.getNoOfDiners() + "\t  " + t.getIsOccupied() + "\t   " + t.getIsReserved() + "\t  " + t.getCustomer().getCustomerName());
			}
			else System.out.println(t.getTableNo() + "\t" + t.getTableSize() + "\t  " + t.getNoOfDiners() + "\t  " + t.getIsOccupied() + "\t   " + t.getIsReserved() + "\t  " + "null");

		}
	}
	
	/**
	 * sorts the tables in the tableList in ascending order of their table number
	 * @param table table 
	 */
	public void sortTableList(ArrayList<Table> table) {
		Table temp;
		for(int i=1;i<tableList.size();i++) {
			for(int j=i; j>0; j--) {
				if(tableList.get(j).getTableNo()<tableList.get(j-1).getTableNo()) {
					temp = tableList.get(j);
					tableList.set(j, tableList.get(j-1));
					tableList.set(j-1, temp);
				}
			}
		}
	}
	
	/**
	 * gets the table list from the table manager
	 * @return ArrayList of Table objects tableList
	 */
	public ArrayList<Table> getTableList(){
		return tableList;
	}
	
}
		
		
		
		