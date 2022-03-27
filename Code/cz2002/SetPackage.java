package cz2002;


import java.util.ArrayList;
/**
 * Represents a SetPackage Object, containing a collection of AlaCarteItems.
 * 
 * @author      Symus
 */
public class SetPackage extends MenuItem {
    /** 
     * Stores a collection of AlaCarteItems. 
     */
    // attributes
    private ArrayList<AlaCarteItem> packageItems;
    
    /** 
     * @param name String containing name of SetPackage. 
     * @param desc String containing short description of SetPackage. 
     * @param price Double containing price of SetPackage.
     * @param packageItems ArrayList of AlaCarte items. 
     * @param foodType FoodType - Always a SET_PACKAGE.
     */
    // constructor
    public SetPackage(String name, String desc, double price, ArrayList<AlaCarteItem> packageItems, FoodType foodType) {
        super(name,desc,price, foodType);
        this.packageItems = packageItems;
    }
    
    /** 
     * Gets the ArrayList of AlaCarteItems in the SetPackage.
     * @return ArrayList of AlaCarteItem objects
     */
    // getters and setters
    public ArrayList<AlaCarteItem> getItems() {return this.packageItems;}
    
    /** 
     * Sets the ArrayList of AlaCarteItems in SetPackage. 
     * @param packageItems ArrayList of AlaCarteItems.
     */
    public void setPackageItems(ArrayList<AlaCarteItem> packageItems) {this.packageItems = packageItems;}
    
    /** 
     * Removes an AlaCarteItem, located at index, from the SetPackage.
     * @param index Index in the ArrayList of AlaCarteItems objects of the AlaCarteItem to be removed. 
     * @return AlaCarteItem
     */
    public AlaCarteItem removeAlaCarteItem(int index) {return this.packageItems.remove(index);}
    
    /** 
     * Prints out the items in the SetPackage.
     */
    public void print() {
        System.out.printf("%-20s | $%5.2f\n", this.getName(), this.getPrice());
        for (int i=0;i<this.getItems().size();i++) {
            System.out.printf(("\t%2d | %s\n"),(i+1),this.packageItems.get(i).getName());
        }
    }
}