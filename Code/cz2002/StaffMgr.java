package cz2002;

import java.util.*;
import java.io.*;

/**
 * Staff management in the restaurant
 * @author Keith
 *
 */
public class StaffMgr {
	/**
	 * All staff workers in the restaurant
	 */
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	/**
	 * Creates a staff manager object
	 */
	public StaffMgr(){};
	
	/**
	 * returns the staff worker found in the restaurant according to their ID
	 * @param staffID ID of staff
	 * @param staffList list of all the staff workers in the restaurant
	 * @return Staff staff
	 */
	public Staff staffFound(int staffID, ArrayList<Staff> staffList) {
		for (Staff s: staffList) {
			if(staffID == s.getStaffID()) return s;
		}
		return null;
	}
	
	/**
	 * gets the list of all staff workers in the restaurant
	 * @return ArrayList of Staff objects staffList
	 */
	public ArrayList<Staff> getStaffList() {
		return this.staffList;
	}
	
	/**
	 * Reads in the csv file "StaffList.csv", creating Staff objects as defined by the values in each line
	 * Adds each newly created Staff objects into the ArrayList of Staff objects staffList
     * @exception NullPointerException if there is no data in the file.
     * @exception NumberFormatException if the entries in the CSV file are not as expected (e.g. String parsed to Integer).
     * @exception InputMismatchException if the entries in the CSV file are not as expected (e.g. String parsed to Integer).
	 */
	public void instantiateStaffList() {
		String filepath = "src/StaffList.csv";
		String line;
		 
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
			int i=1;
			
			while((line = csvReader.readLine() ) !=null)  {
				String[] data = line.split(",");
				if(i==1) {
					i++;
					continue; //skip first line
				}
				staffList.add(new Staff(data[0], data[1].charAt(0), Integer.parseInt(data[2]), data[3]));

			}
			csvReader.close();
		} catch (FileNotFoundException e) {
            System.out.println("File not found! Please place \"StaffList.csv\" into the root folder of this Java executable.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error!");
			e.printStackTrace();
		} catch (NullPointerException|NumberFormatException e) {
	        System.out.println("Error reading CSV file: Invalid format encountered.");
	    } catch (InputMismatchException e) {
	    	System.out.println("Error reading CSV file: Invalid input type encountered.");
	    } 
	}
	
	/**
	 * Creates a new staff object in the restaurant
	 * @exception NumberFormatException if the input keyed in is not a number
	 * @exception IllegalArgumentException if the input keyed in contains invalid characters 
	 */
	public void addStaff(){
		boolean validInput = false;
		System.out.println("Creating Staff...");
		while(!validInput) {
			try {
			System.out.println("Enter Staff ID: ");
			String input = RRPSS.sc.next();
			int tempID = Integer.parseInt(input);
			while(staffFound(tempID, staffList) != null) {
				System.out.println("Staff ID is taken. Please choose another ID.");			//included invalid input for staff ID
				System.out.println("Enter Staff ID: ");
				input = RRPSS.sc.next();
				tempID = Integer.parseInt(input);
			}
			
			System.out.println("Enter name of Staff: ");				
			String tempName = RRPSS.sc.next().toLowerCase();
			if(!tempName.matches("[a-zA-Z]+")) throw new IllegalArgumentException();				// included invalid name exception
			String capName = tempName.substring(0,1).toUpperCase() + tempName.substring(1);		// capitalise first letter of name
			System.out.println("Enter gender of Staff: M/F");
			char tempGender = RRPSS.sc.next().toUpperCase().charAt(0);
			if(tempGender != 'M' && tempGender != 'F') throw new IllegalArgumentException();
			System.out.println("Enter Job Title of Staff: ");
			String tempStaffRole = RRPSS.sc.next();
			if(!tempStaffRole.matches("[a-zA-Z]+")) throw new IllegalArgumentException();
			String capStaffRole = tempStaffRole.substring(0,1).toUpperCase() + tempStaffRole.substring(1);
			
			validInput = true;
			
			Staff newStaff = new Staff(capName, tempGender, tempID, capStaffRole);
			staffList.add(newStaff);
			
			System.out.println("Staff successfully created!");
			System.out.println("------------------------------------");
			System.out.println("Staff Name: " + newStaff.getStaffName());
			System.out.println("Staff ID: " + newStaff.getStaffID());
			System.out.println("Staff Gender: " + newStaff.getStaffGender());
			System.out.println("Job Title: " + newStaff.getStaffRole());
			System.out.println("------------------------------------");
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid staff ID.");
			} catch (IllegalArgumentException e) {
				System.out.println("Please enter a valid input.");
			}
		}
	}
	
	/**
	 * Updates the details of a staff worker in the restaurant based on the Staff ID keyed in
	 * @exception NumberFormatException if the input keyed in is not a number
	 * @exception IllegalArgumentException if the input keyed in contains invalid characters 
	 */
	public void updateStaff(){
		boolean validInput = false;
		System.out.println("Updating staff...\n");

		while(!validInput) {
			try {
				System.out.println("Enter ID of staff to update: \n");
				String input = RRPSS.sc.next();
				int tempID = Integer.parseInt(input);
				if(staffFound(tempID, staffList)!=null) {
					Staff s = staffFound(tempID, staffList);
					System.out.println("====================================");
					System.out.println("Updating Staff ID " + tempID + " " + s.getStaffName());			//maybe need more attributes of staff
					System.out.println("====================================");
					System.out.println("1. Job Title");
					System.out.println("0. Go back");
					int updateChoice = RRPSS.sc.nextInt();
					switch(updateChoice) {
						case 1:
							String tempJobTitle;
							System.out.println("Enter new Job Title: ");
							tempJobTitle = RRPSS.sc.next();
							if(!tempJobTitle.matches("[a-zA-Z]+")) throw new IllegalArgumentException();
							String capJobTitle = tempJobTitle.substring(0,1).toUpperCase() + tempJobTitle.substring(1);		// capitalise job title
							s.setStaffRole(capJobTitle);
							break;
						default:
							break;
					}
					validInput = true;
				}
				else System.out.println("Invalid Staff ID. Please enter again.");
				
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid staff ID.");
			}catch (IllegalArgumentException e) {
				System.out.println("User input is of invalid type.");
			}
		}
	}
	
	/**
	 * Removes a staff worker from the restaurant based on the Staff ID keyed in
	 * @exception NumberFormatException if the input keyed in is not a number
	 */
	public void deleteStaff(){
		boolean validInput = false;
		System.out.println("Deleting staff...\n");

		while(!validInput) {
			try {
				System.out.println("Enter ID of staff to delete: \n");
				String input = RRPSS.sc.next();
				int tempID = Integer.parseInt(input);
				if(staffFound(tempID, staffList)!=null) {
					System.out.println("====================================");
					System.out.println("Deleting Staff ID " + tempID + "...");
					staffList.remove(staffFound(tempID, staffList));
					System.out.println("Staff deleted successfully!");
					System.out.println("====================================");
					validInput = true;
				}
				else System.out.println("Invalid Staff ID. Please enter again.");
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid staff ID.");
			}
		}
	}
	
	/**
	 * Prints out the table to display all staff workers and their corresponding details
	 */
	public void displayStaffList() {
		sortStaffList(staffList);
		System.out.println("====================================");
		System.out.println("Displaying Staff List...");
		System.out.println("------------------------------------");
		
		System.out.println("Name\tGender\tStaffID\tStaffRole");
		System.out.println("------------------------------------");
		for(Staff s : staffList) {
			System.out.println(s.getStaffName() + "\t" + s.getStaffGender() + "\t" + s.getStaffID() + "\t" + s.getStaffRole());
		}
		System.out.println("====================================");
	}
	
	/**
	 * Sorts the staffList in ascending order of their Staff ID
	 * @param staffList list of all the staff workers in the restaurant
	 */
	public void sortStaffList(ArrayList<Staff> staffList) {
		Staff temp;
		for(int i=1;i<staffList.size();i++) {
			for(int j=i; j>0; j--) {
				if(staffList.get(j).getStaffID()<staffList.get(j-1).getStaffID()) {
					temp = staffList.get(j);
					staffList.set(j, staffList.get(j-1));
					staffList.set(j-1, temp);
				}
			}
		}
	}
}