package cz2002;

import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 * Manages the Menu object.
 * 
 * @author      Symus
 */

public class MenuMgr {
    /**
     * Stores a Menu object.
     */
    // attributes
    private Menu menu;

    /**
     * Constructs a MenuMgr object with a Menu.
     * @param menu Menu Object
     */
    // constructor
    public MenuMgr(Menu menu) {this.menu = menu;}

    /** 
     * Checks if FoodType entered by user is a valid FoodType.
     * @param rawInput String of FoodType as entered by the user.
     * @return FoodType
     * @throws IllegalArgumentException if not a valid FoodType.
     */
    // private methods
    private FoodType checkEnum(String rawInput) throws IllegalArgumentException { // check if String entered is valid
        if ((rawInput.compareToIgnoreCase("main_course")==0) || (rawInput.compareToIgnoreCase("main course")==0)) {
            return FoodType.MAIN_COURSE;
        } else if (rawInput.compareToIgnoreCase("sides")==0) {
            return FoodType.SIDES;
        } else if (rawInput.compareToIgnoreCase("drinks")==0) {
            return FoodType.DRINKS;
        } else if (rawInput.compareToIgnoreCase("dessert")==0) {
            return FoodType.DESSERT;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /** 
     * Checks if item currently exists in menu.
     * @param name String of item as entered by the user.
     * @return true if exists, false if does not exist
     */
    private boolean itemExists(String name){
        for (int i=0;i<menu.getList().size();i++) {
            if (name.equalsIgnoreCase(menu.getItem(i).getName())) {return true;}
        }
        return false;
    }

    /** 
     * Get ArrayList of AlaCarteItem in the Menu.
     * @return ArrayList of AlaCarteItem objects
     */
    // getters
    public ArrayList<AlaCarteItem> getAlaCarteList() {
        ArrayList<AlaCarteItem> alaCarteItemSubArrayList = new ArrayList<AlaCarteItem>();
        for (int i=0;i<menu.getDiffItemIndex();i++) {
            if (menu.getItem(i) instanceof AlaCarteItem) {
                alaCarteItemSubArrayList.add((AlaCarteItem)menu.getItem(i));
            }
        }
        return alaCarteItemSubArrayList;
    }
    
    /** 
     * Get ArrayList of SetPackage in the Menu.
     * @return ArrayList of SetPackage objects
     */
    public ArrayList<SetPackage> getSetPackageList() {
        ArrayList<SetPackage> setPackageSubArrayList = new ArrayList<SetPackage>();
        for (int i=menu.getDiffItemIndex();i<menu.getList().size();i++) {
            if (menu.getItem(i) instanceof SetPackage) {
                setPackageSubArrayList.add((SetPackage)menu.getItem(i));
            }
        }
        return setPackageSubArrayList;
    }
    
    /** 
     * Get ArrayList of all MenuItem.
     * @return ArrayList of MenuItem objects
     */
    // public methods
    public ArrayList<MenuItem> getMenuItemList() {return menu.getList();}
    
    /** 
     * Prints the items in the list based on the flag passed to it.
     * @param flag Integer indicating which data is to be printed.
     * @exception NullPointerException if no items in menu.
     */
    public void print(int flag) {
        try {
            switch (flag) {
                case 0:
                    for (int i=0;i<menu.getList().size();i++) {
                        if (i==0) {
                            System.out.println("\n"+menu.getItem(i).getFoodType().toString());
                        } else if (menu.getItem(i).getFoodType() != menu.getItem(i-1).getFoodType()) {
                            System.out.println("\n"+menu.getItem(i).getFoodType().toString());
                        }
                        System.out.printf(("%-2d | "),(i+1));
                        menu.getItem(i).print();
                    }
                    break;
                case 1:
                    for (int i=0;i<menu.getDiffItemIndex();i++) {
                        if (i==0) {
                            System.out.println("\n"+menu.getItem(i).getFoodType().toString());
                        } else if (menu.getItem(i).getFoodType() != menu.getItem(i-1).getFoodType()) {
                            System.out.println("\n"+menu.getItem(i).getFoodType().toString());
                        }
                        System.out.printf(("%-2d | "),(i+1));
                        menu.getItem(i).print();
                    }
                    break;
                case 2:
                    System.out.println("\nSET_PACKAGE");
                    for (int i=menu.getDiffItemIndex();i<menu.getList().size();i++) {
                        System.out.printf(("%-2d | "),(i-menu.getDiffItemIndex()+1));
                        menu.getItem(i).print();
                    }
                    break;
                default:
                    System.out.println("Invalid flag!");
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("No items in menu!");
        }
    }

    /**
     * Update the price of a MenuItem.
     * @exception NumberFormatException if an invalid index or price is entered.
     * @exception ArrayIndexOutOfBoundsException if the index of the object to update is out of range. 
     */
    public void update() {
        try {
            this.print(0);
            System.out.print("Please enter the index of the item to edit: ");
            int i = Integer.parseInt(RRPSS.sc.nextLine());
            System.out.println("Item to update: "+menu.getItem(i-1).getName());
            System.out.println("Description: "+menu.getItem(i-1).getDesc());
            System.out.println("Current Price: "+menu.getItem(i-1).getPrice());
            System.out.print("Please enter new price: ");
            double price = Double.parseDouble(RRPSS.sc.nextLine());
            menu.getItem(i-1).setPrice(price);
            System.out.println("Price of "+menu.getItem(i-1).getName() + " is updated to $" + menu.getItem(i-1).getPrice() +".\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please ensure a valid price has been entered.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Item not found in array!");
        }
    }

    /**
     * Remove a MenuItem.
     * @exception NumberFormatException if an invalid index is entered.
     * @exception ArrayIndexOutOfBoundsException if the index of the object to remove is out of range. 
     */
    public void remove() {
        try {
            this.print(0);
            System.out.print("Please enter the index of the item to remove: ");
            int i = Integer.parseInt(RRPSS.sc.nextLine());
            menu.remove(i-1);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter an integer.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input! Index entered is out of bounds.");
        }
    }

    /**
     * Remove an AlaCarteItem from a SetPackage.
     * @exception NumberFormatException if an invalid index is entered.
     * @exception ArrayIndexOutOfBoundsException if the index of the object to remove is out of range. 
     */
    public void removeFromPackage() {
        try {
            this.print(2);
            System.out.print("Please enter the index of the Set Package: ");
            int i = Integer.parseInt(RRPSS.sc.nextLine());
            ArrayList<SetPackage> setPackageList = this.getSetPackageList();
            setPackageList.get(i-1).print();
            System.out.print("Please enter the index of the item in "+setPackageList.get(i-1).getName()+" to remove: ");
            int j = Integer.parseInt(RRPSS.sc.nextLine());
            System.out.println(setPackageList.get(i-1).removeAlaCarteItem(j-1).getName()+" removed.");
            setPackageList.get(i-1).print();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index entered!");
        }
    }

    /**
     * Create an AlaCarteItem.
     * @exception NumberFormatException if an invalid index is entered.
     * @exception ArrayIndexOutOfBoundsException if the index of the object to remove is out of range. 
     */
    public void createAlaCarte() {
        try {
            System.out.print("Name of item to create: ");
            String name = RRPSS.sc.nextLine();
            if (!itemExists(name)) {
                System.out.print("Description of item: ");
                String desc = RRPSS.sc.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(RRPSS.sc.nextLine());
                System.out.print("Food Type: ");
                FoodType foodType = checkEnum(RRPSS.sc.nextLine());
                menu.add(new AlaCarteItem(name, desc, price, foodType));    
            } else {
                System.out.println("Item of that name exists in Menu!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please ensure a valid price has been entered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input! Please ensure a valid food type has been entered.");
        }
    }

    /**
     * Create a SetPackage
     * @exception NumberFormatException if an invalid index or price is entered.
     * @exception ArrayIndexOutOfBoundsException if the index of the AlaCarteItem to add is out of range. 
     */
    public void createSetPackage() {
        try {
            System.out.print("Name of package to create: ");
            String name = RRPSS.sc.nextLine();
            if (!itemExists(name)) {
                System.out.print("Description of package: ");
                String desc = RRPSS.sc.nextLine();
                System.out.print("Price: ");
                Double price = Double.parseDouble(RRPSS.sc.nextLine());
    
                int choice = -1;
                ArrayList<AlaCarteItem> packageItems = new ArrayList<AlaCarteItem>();
    
                while (true) {
                    this.print(1);
                    System.out.println("Enter 0 to stop adding.");
                    System.out.print("Please specify which AlaCarte Item you would like to add to the package: ");
                    choice = Integer.parseInt(RRPSS.sc.nextLine());
                    if (choice-1>=this.menu.getDiffItemIndex()) {throw new IndexOutOfBoundsException();}
                    else if (choice==0) {break;}
                    packageItems.add(getAlaCarteList().get(choice-1));
                }
                menu.add(new SetPackage(name, desc, price, packageItems,FoodType.SET_PACKAGE));    
            } else {
                System.out.println("Item of that name exists in Menu!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please ensure a valid price has been entered.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input! Index entered is out of range.");
        }
    }
}