package cz2002;

import java.util.*;
/**
 * Main application for restaurant
 * @author wongl
 *
 */
public class RRPSS {

	static Scanner sc = new Scanner(System.in);
	/**
	 * main of application
	 * @param args args
	 */
	public static void main(String[] args) {
		
			int choice = 0;
			boolean inputInvalid = true;
			Menu menu = new Menu();
			MenuMgr menuMgr = new MenuMgr(menu);
			OrderMgr orderMgr = new OrderMgr(menuMgr);
			StaffMgr staffMgr = new StaffMgr();
			staffMgr.instantiateStaffList();
			TableMgr tableMgr = new TableMgr();
			tableMgr.instantiateTableList();
			ReservationMgr reservationMgr = new ReservationMgr(tableMgr);
			InvoiceMgr invoiceMgr = new InvoiceMgr(staffMgr);
			Membership membership = new Membership();
			WalkIn walkIn = new WalkIn(tableMgr, reservationMgr);
			SaleRevenueReport SRR = new SaleRevenueReport(invoiceMgr);
			
			
			/*
			Calendar openingTime = Calendar.getInstance();
			openingTime.set(Calendar.HOUR_OF_DAY, 9);
			Calendar closingTime = Calendar.getInstance();
			closingTime.set(Calendar.HOUR_OF_DAY, 21);
			Date now = new Date();
			 */
			
			do {
				try {
				System.out.println(	"=========================================================\n" +
									"|\t\t Welcome to Restaurant\t\t\t|\n" +
								   	"=========================================================\n" +
									"|(1) Open Order Manager\t\t\t\t\t|\n" +
									"---------------------------------------------------------\n" + 
									"|(2) Open Reservation Manager\t\t\t\t|\n" +
									"---------------------------------------------------------\n" +			
									"|(3) Open Menu Manager\t\t\t\t\t|\n" +
									"---------------------------------------------------------\n" +
									"|(4) Open Staff Manager\t\t\t\t\t|\n" + 
									"---------------------------------------------------------\n" +
									"|(5) Print Order Invoice\t\t\t\t|\n" +
									"---------------------------------------------------------\n" +
									"|(6) Print Sale Revenue Report\t\t\t\t|\n" +
									"---------------------------------------------------------\n" +
									"|(7) Refresh System\t\t\t\t\t|\n" + 
									"---------------------------------------------------------\n" +
									"|(0) Exit\t\t\t\t\t\t|\n" +
									"=========================================================");
				

				System.out.println();
				System.out.println("Enter your choice (0-7): ");
				choice = Integer.parseInt(sc.nextLine());
				
			
			switch(choice) {
				case 0 :
					break;					
				case 1:
					int orderChoice;
					do {
					System.out.println("==============================");
					System.out.println("\tOrder Manager");
					System.out.println("------------------------------");
					System.out.println("(1) Walk-in Customer");
					System.out.println("(2) Add Order/Item for a Table");
					System.out.println("(3) Remove Order/Item for a Table"); 
					System.out.println("(4) View Order for a Table");
					System.out.println("(5) View Orders for All Tables");
					
					System.out.println("(0) Go back");
					System.out.println("==============================");
					orderChoice = Integer.parseInt(sc.nextLine());
					if(orderChoice == 0)
						break;
					switch(orderChoice) {
					case 1:
						int tableNo;
							walkIn.occupyTable();
							tableNo = walkIn.getTableNo();
						break;

					case 2:
						boolean add = false;
						do {
							System.out.println("Please enter the table no. to add items"); // add order for a table
							System.out.println("OR Press '0' to back");
							tableNo = Integer.parseInt(sc.nextLine());
							if (tableNo == 0)
								break;
							for (Table t : tableMgr.getTableList()) {
								if (t.getTableNo() == tableNo) {
									if (t.getIsOccupied()) {
										orderMgr.addOrder(tableNo);
										orderMgr.viewOrder(tableNo);
										add = true;
										break; 
									} else {
										System.out
												.println("Cannot add order as table is unoccupied. Please try again.");
										break;
									}
								}
							}
						} while (!add);
						break;

					case 3:
						System.out.println("Please enter the table no. to remove order/item"); // remove order for a
																								// table
						System.out.println("OR Press '0' to back");
						tableNo = Integer.parseInt(sc.nextLine());
						if (tableNo == 0)
							break;
						orderMgr.removeOrder(tableNo);
						break;
					case 4:
						System.out.println("Please enter the table no. for order"); // view order for a table
						System.out.println("OR Press '0' to go back");
						tableNo = Integer.parseInt(sc.nextLine());
						if (tableNo == 0)
							break;
						orderMgr.viewOrder(tableNo);
						break;
					case 5:
						orderMgr.viewAllOrder();
						break;
					default:
						break;
					}
					sc.nextLine();
					}while(orderChoice!=0);
					break;
				case 2:
					int reservationChoice;
					do {
					System.out.println("=================================");
					System.out.println("\tReservation Manager");
					System.out.println("---------------------------------");
					System.out.println("(1) Add Reservation for a Table");
					System.out.println("(2) Remove Reservation for a Table"); 
					System.out.println("(3) View Reservation List");
					System.out.println("(4) Display Table Availability for Time Slot");
					System.out.println("(5) Display Table Availability by Table");
					System.out.println("(0) Go back");
					System.out.println("=================================");
					reservationChoice = Integer.parseInt(sc.nextLine());
					switch(reservationChoice) {
						case 1:
							reservationMgr.addReservation();
							break;
						case 2: 
							reservationMgr.removeReservation();
							break;
						case 3: 
							reservationMgr.showReservation();
							break;
						case 4:
							reservationMgr.displayTableAvail();
							break;
						case 5: 
							reservationMgr.displayTableAvailbyTable();
							break;
						default:
							break;
					}
					sc.nextLine();
					}while(reservationChoice!=0);
					break;
				case 3:
					int menuChoice;
					do {
						System.out.println("====================================");				// create/update/remove ala carte item
						System.out.println("\tMenu Manager");
						System.out.println("------------------------------------");
						System.out.println("(1) Create Ala Carte Item");
						System.out.println("(2) Create Set Package");
						System.out.println("(3) Update Menu Item");
						System.out.println("(4) Remove Menu Item");
						System.out.println("(5) Remove Menu Item from Set Package");
						System.out.println("(6) View All Menu Items");
						System.out.println("(0) Go back");
						System.out.println("====================================");
						System.out.println("Please enter your choice (0-6): ");
						menuChoice = Integer.parseInt(sc.nextLine());
						switch(menuChoice) {
							case 1:
								menuMgr.createAlaCarte();
								break;
							case 2:
								menuMgr.createSetPackage();
								break;
							case 3:
								menuMgr.update();
								break;
							case 4: 
								menuMgr.remove();
								break;
							case 5: 
								menuMgr.removeFromPackage();
								break;
							case 6:
								System.out.println("Which menu would you like to view?");
								System.out.println("------------------------------------");
								System.out.println("(1) All Menu Items");
								System.out.println("(2) Ala Carte Items only");
								System.out.println("(3) Set Packages only");
								System.out.println("(0) Go back");
								int viewMenuChoice = Integer.parseInt(sc.nextLine());
								if(viewMenuChoice == 0) break;
								menuMgr.print(viewMenuChoice -1);
								break;
							default: 
								break;
						}
						sc.nextLine();
					}while(menuChoice != 0);
					break;
				
				case 4:
					int staffChoice;
					do {
						System.out.println( "===========================\n" +
											"\tStaff Menu\n"+ 
											"===========================\n" +
											"1. Create Staff\n"+
											"2. Update Staff\n" + 
											"3. Delete Staff\n" + 
											"4. View Staff List\n" +
											"0. Go back\n");
						staffChoice = Integer.parseInt(sc.nextLine());
						switch(staffChoice) {
						case 1:
							staffMgr.addStaff();
							break;
						case 2:
							staffMgr.updateStaff();
							break;
						case 3:
							staffMgr.deleteStaff();
							break;
						case 4:
							staffMgr.displayStaffList();
							break;
						default:
							break;
						}
						sc.nextLine();
					}while(staffChoice != 0);
					break;
					
					
				case 5: 
					System.out.println("Enter Table No: ");
			          int tableNo = Integer.parseInt(sc.nextLine());
			          if (orderMgr.getOrder(tableNo) != null) {
			            invoiceMgr.addInvoice(orderMgr, tableMgr, tableNo, membership);
			            invoiceMgr.printInvoice(tableMgr, tableNo);
			            Order order = orderMgr.getOrder(tableNo);
			            orderMgr.getOrderedList().remove(order);
			            tableMgr.clearTable(tableNo);
			          }
			          else
			            System.out.println("No order for this table");
			          
			          sc.nextLine();
			          //invoiceMgr.printAllInvoice();
					break;
				case 6: 
					int saleChoice;
					do {
						System.out.println( "===========================\n" +
								"\tSale Revenue Report\n"+ 
								"===========================\n" +
								"1. Revenue by Day\n"+
								"2. Revenue by Month\n" + 
								"3. Qty of each item by day\n" + 
								"4. Qty of each item by month\n" +
								"0. Go back\n");
						saleChoice = Integer.parseInt(sc.nextLine());
						switch(saleChoice) {
						case 1:
							SRR.getTotalRevenueDay();
							break;
						case 2:
							SRR.getTotalRevenueMonth();
							break;
						case 3:
							SRR.getTotalQtyDay();
							break;
						case 4:
							SRR.getTotalQtyMonth();
							break;
						default:
							break;
						}
						sc.nextLine();
					}while(saleChoice != 0);
					
					break;
				case 7:
					reservationMgr.refresh();
					break;
				default:
					System.out.println("Invalid number. Please enter again.\n");
					break;
				}
			
				inputInvalid = false;	//this correct??
				
				}catch (NumberFormatException e){
					System.out.println("Input must be a number.");

				}
			
			}while(choice!=0 || inputInvalid); //this too
			
			
			menu.closeMenu();
			sc.close();
			System.out.println("Program terminating...");
			
	
	}
	
}