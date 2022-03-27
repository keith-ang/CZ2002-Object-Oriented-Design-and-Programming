package cz2002;
/**
 * Class containing common functionalities and attributes of all items in the menu.
 * 
 * @author      Symus
 */

public abstract class MenuItem implements Printable {
    private String name;
    private String desc;
    private double price;
    private FoodType foodType;

    /** 
     * MenuItem constructor
     * @param name String name of item
     * @param desc String description of item
     * @param price Double price of item
     * @param foodType FoodType type of item
     */
    public MenuItem (String name, String desc, double price, FoodType foodType) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.foodType = foodType;
    }

    // getters and setters
    /**
     * get Name of MenuItem (String)
     * @return name of MenuItem
     */
    public String getName() {return this.name;}
    /**
     * get Desc of MenuItem (String)
     * @return description of MenuItem
     */
    public String getDesc() {return this.desc;}
    /**
     * get Price of MenuItem (double)
     * @return price of MenuItem
     */
    public double getPrice() {return this.price;}
    /**
     * get foodtype of MenuItem
     * @return foodtype
     */
    public FoodType getFoodType() {return this.foodType;}
    /**
     * set price of MenuItem
     * @param price of the menuItem
     */
    public void setPrice(double price) {this.price = price;}

    /** 
     * Defines all subclasses as having the print functionality
     */
    public abstract void print();
}