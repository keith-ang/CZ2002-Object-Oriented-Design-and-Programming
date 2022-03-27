package cz2002;

/**
 * Stores MenuItem with its respective quantity
 * @author wongl
 *
 */
public class ItemQuantity{

	/**
	 * MenuItem(in Menu) ordered by customer
	 */
	private MenuItem menuItem;
	/**
	 * quantity of MenuItem ordered by customer
	 */
	private int quantity;

	/**
	 * Creates ItemQuantity to store each MenuItem ordered by customer together with its final quantity
	 * @param menuItem item from menu ordered
	 * @param quantity number of the item ordered
	 */
	public ItemQuantity(MenuItem menuItem,int quantity){
		this.menuItem = menuItem;
		this.quantity = quantity;

	}
	
	/** 
	 * gets name of menuItem
	 * @return String name of menuItem ordered
	 */
	public String getName() {
		return menuItem.getName();
	}
	
	
	/** 
	 * gets price of the menuItem
	 * @return double price of MenuItem
	 */
	public double getPrice() {
		return menuItem.getPrice();
	}
	

	
	/** 
	 * gets quantity of the menuItem ordered in total
	 * @return int number of the item ordered
	 */
	public int getQuantity() {
		return quantity;
	}
	
	

}