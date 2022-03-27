package cz2002;
import java.util.Comparator;
import java.util.ArrayList;


/**
 * Order management in Restaurant
 * @author wongl
 *
 */
public class OrderMgr {

	/**
	 * Menu manager of restaurant to get MenuItems
	 */
	private MenuMgr menuMgr;
	/**
	 * All existing orders in restaurant
	 */
	private ArrayList<Order> OrderedList = new ArrayList<Order>();

	/**
	 * Creates order manager
	 * @param menuMgr Menu manager of restaurant
	 */
	public OrderMgr(MenuMgr menuMgr) {
		this.menuMgr = menuMgr;
	}

	
	/** 
	 * Gets array list of all existing orders in restaurant
	 * @return ArrayList of Order objects list of Orders
	 */
	public ArrayList<Order> getOrderedList() {
		return OrderedList;
	}

	
	/** 
	 * Creates a new Order for customer with no existing order
	 * Add MenuItems in created/existing order
	 * @exception NumberFormatException if input keyed in is not a number
	 * @param tableNo table number to create Order/add items
	 */
	public void addOrder(int tableNo) {
		
		if(menuMgr.getMenuItemList().size()==0) {
			System.out.println("No items in menu! Add items in menu first");
			return;
		}
		
		Order order = getOrder(tableNo);
		if (order == null) {
			System.out.println("Creating new order for tableNo " + tableNo);
			order = new Order(tableNo);
			OrderedList.add(order);
		}

		boolean inputValid = false;
		int noOfItems=0;
		while (!inputValid) {

			try {
				System.out.println("How many items do you wish to add in your order :D (TableNo is " + tableNo + ")");
				String input = RRPSS.sc.next();
				noOfItems = Integer.parseInt(input);
				if (noOfItems > 0) {
					inputValid=true;
				}
				else
					System.out.println("Please enter a number > 0");

			} catch (NumberFormatException e) {
				System.out.println("Invalid number, please key in again");
			}
		}
		
		int number = 0;

		inputValid = false;
		int itemNo = 0;
		while (number < noOfItems) {

			while (!inputValid) {

				try {
					System.out.println("Which item do you want to add?");
					menuMgr.print(0);
					String input = RRPSS.sc.next();
					itemNo = Integer.parseInt(input);

					if (itemNo < 1 || itemNo > menuMgr.getMenuItemList().size()) 
						System.out.println("Item number has exceeded range, please re-enter");
					else
						inputValid = true;

				} catch (NumberFormatException e) {
					System.out.println("Invalid number, please key in again");
				}

			}
			order.getFoodList().add(menuMgr.getMenuItemList().get(itemNo - 1));
			order.getFoodList().sort(Comparator.comparing(MenuItem::getName));
			System.out.println("item successfully added!");
			System.out.println();
			number++;
			inputValid = false;
		}
	}

	
	/** 
	 * Prints the menuItems customer ordered for a particular table 
	 * @param tableNo The table number of that order
	 */
	public void viewOrder(int tableNo) {
		Order order = getOrder(tableNo);
		if (order == null) {
			System.out.println("Order for tableNo " + tableNo + " does not exist");
		} else {
			System.out.println("Order for tableNo " + tableNo);
			order.printFoodList();
		}
		System.out.println();
	}

	/**
	 * Prints the menuItems in all existing orders
	 */
	public void viewAllOrder() {

		if (OrderedList.size() == 0)
			System.out.println("No orders yet");
		else {
			System.out.println("All orders: ");
			for (int i = 0; i < OrderedList.size(); i++) {
				System.out.println("Order for tableNo: " + OrderedList.get(i).getTableNo());
				OrderedList.get(i).printFoodList();
				System.out.println();
			}
			System.out.println();
		}
	}

	
	/** 
	 * Remove entire Order if customer wants to change entire order
	 * Remove certain items in Order if customer no longer wants those item
	 * @exception NumberFormatException if input keyed in is not a number
	 * @param tableNo the table number for the order to be removed/remove items
	 */
	public void removeOrder(int tableNo) {

		Order order = getOrder(tableNo);
		if (order == null) {
			System.out.println("tableNo " + tableNo + " does not exist cannot remove");
		} else {

			int i = 0;
			boolean inputValid = false;
			while (!inputValid) {
				try {
					System.out.println("Enter (1) to remove whole order, (2) to remove item from order");
					String input = RRPSS.sc.next();
					i = Integer.parseInt(input);

					if (i < 1 || i > 2) {
						System.out.println("Invalid choice, please re-enter");

					} else
						inputValid = true;

				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number (1)/(2)");
				}

			}

			if (i == 1) {
				OrderedList.remove(order);
				System.out.println("order successfully removed for tableNo " + tableNo + "!");
			} else {

				inputValid = false;
				int k = 0;

				while (!inputValid) {

					try {
						System.out.println("How many items would you like to remove?");
						String input = RRPSS.sc.next();
						k = Integer.parseInt(input);
						if (k < 1 || k > order.getNumberOfItems())
							System.out.println("Invalid choice, please re-enter");
						else
							inputValid = true;

					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid number");
					}

				}

				int num = 0;

				while (k > 0) {

					order.printFoodList();

					inputValid = false;
					while (!inputValid) {

						try {
							System.out.println("Choose which item to remove from order");
							String input = RRPSS.sc.next();
							num = Integer.parseInt(input);
							if (num < 1 || num > order.getFoodList().size())
								System.out.println("Invalid item, please re-enter");
							else
								inputValid = true;
						} catch (NumberFormatException e) {
							System.out.println("Please enter a valid number");
						}
					}

					order.getFoodList().remove(num - 1);
					System.out.println("item successfully removed!");
					k--;
					inputValid = false;

				}
			}
		}

		if (order.getNumberOfItems() == 0) {
			OrderedList.remove(order);
			System.out.println("No more items in order - removing this order from tableNo " + tableNo);
		}
		System.out.println();
	}

	
	/** 
	 * Gets the Order for that particular table number
	 * @param tableNo table number of Order
	 * @return Order Order object for table number 
	 */
	public Order getOrder(int tableNo) {

		Order order = null;

		for (int i = 0; i < OrderedList.size(); i++) {
			if (OrderedList.get(i).getTableNo() == tableNo)
				order = OrderedList.get(i);
		}
		return order;

	}
	

}