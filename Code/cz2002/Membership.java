
package cz2002;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * customer csv file to check membership status
 * @author wongl
 *
 */
public class Membership {

	
	/** 
	 * By reading the csv file, gets membership status of customer with respective to contact number
	 * (contact number is unique for each customer)
	 * @param str contact number of customer
	 * @return boolean membership status of customer
	 */
	public boolean membershipStatus(String str) {

		String path = "src/CustomerList.csv"; // change path
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			//int i = 1;
			while ((line = br.readLine()) != null) {
			/*	if (i == 1) {
					i++;
					continue; // skip first line
				}*/

				String[] values = line.split(",");
				// System.out.println("name: " + values[0] + ", contact: " + values[2]);

				if (values[1].equals(str)) {
					if (values[2].equalsIgnoreCase("true")) {
						br.close();
						//System.out.println("FOUND, " + values[0] + " is a member");
						return true;
					}/*
					else {
						//System.out.println(values[0] + " is NOT a member");
						return false;
					}*/
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("Contact not found");
		return false;

	}
}