package cz2002;
import java.util.ArrayList;
/**
 * Represents an Order created for a table in the Restaurant
 * 
 * Each table has one Order for a particular customer
 * 
 * Order for this customer is removed once invoice is printed
 * @author wongl
 */
public class Order {

	/**
	 * The MenuItems of an Order
	 */
	private ArrayList<MenuItem> foodList = new ArrayList<MenuItem>();
	
	/**
	 * The table number of this Order
	 */
	private int tableNo;
	
	
	/**
	 * Creates a new Order for that particular table
	 * @param tableNo The table number of this Order
	 */
	public Order(int tableNo) {
		this.tableNo = tableNo;
	}
	
	
	/** 
	 * Gets the list of MenuItems that customer ordered
	 * @return ArrayList of MenuItem objects list of MenuItems
	 */
	public ArrayList<MenuItem> getFoodList(){
		return foodList;
	}
	
	
	/** 
	 * Gets the name of a particular MenuItem 
	 * @param i index of item in foodList
	 * @return String Name of MenuItem
	 */
	public String getItemName(int i) {
		return this.foodList.get(i).getName(); 
	}

	
	/** 
	 * Gets the Price of a particular MenuItem
	 * @param i index of item in foodList
	 * @return double Price of MenuItem
	 */
	public double getPrice(int i) {
		return foodList.get(i).getPrice();
	}

	/**
	 * Prints the MenuItems customer ordered 
	 */
	public void printFoodList() {
		if(foodList.size()==0)
			System.out.println("No item in order");
		for(int i = 0;i<foodList.size();i++)
			System.out.println(foodList.get(i).getName());
	}
	
	
	/** 
	 * Gets the total sum of an Order
	 * @return double Total sum of Order
	 */
	public double getSum() {
		double j=0;
		for(int i=0;i<foodList.size();i++) {
			j+=foodList.get(i).getPrice();
		}
		return j;
	}
	
	/** 
	 * Gets the number of items customer ordered
	 * @return int number of items in Order
	 */
	public int getNumberOfItems() {
		return foodList.size();
	}
	
	/** 
	 * Gets table number of Order
	 * @return int table number of Order
	 */
	public int getTableNo() {
		return this.tableNo;
	}
	
	
}
	
	