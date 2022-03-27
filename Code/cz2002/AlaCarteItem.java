package cz2002;
/**
 * Represents an AlaCarteItem Object.
 * 
 * @author      Symus
 */

public class AlaCarteItem extends MenuItem {    
    /** 
     * AlaCarteItem constructor
     * @param name String name of item
     * @param desc String description of item
     * @param price Double price of item
     * @param foodType FoodType type of item
     */
    // constructor
    public AlaCarteItem(String name, String desc, double price, FoodType foodType) {
        super(name,desc,price,foodType);
    }

    
    /** 
     * Prints out the name and price of the AlaCarteItem object.
     */
    // public methods
    public void print() {System.out.printf("%-20s | $%5.2f\n", this.getName(), this.getPrice());}
}
