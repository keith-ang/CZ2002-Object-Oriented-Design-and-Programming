package cz2002;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.nio.charset.Charset;

/**
 * Represents a Menu Object, storing a collection of MenuItems.
 * 
 * @author      Symus
 */
public class Menu {
    /**
     * Creates a new ArrayList of MenuItem.
     */
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    /**
     * Index where the first SetPackage in ArrayList<MenuItem> occurs.
     */
    private int diffItemIndex = 0;

    /** 
     * Constructs the menu.
     * Reads in the csv file "FoodList.csv", creating AlaCarteItem and SetPackage objects as defined by the first value in each line. 
     * Sorts the menu using the order inherent from the FoodType enum.
     * @exception NullPointerException if there is no data in the file.
     * @exception NumberFormatException if the entries in the CSV file are not as expected (e.g. String parsed to Integer).
     * @exception InputMismatchException if the entries in the CSV file are not as expected (e.g. String parsed to Integer).
     * @exception IndexOutOfBoundsException if the index referenced by SetPackage is not an AlaCarteItem.
     */
    // constructor
    public Menu() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/FoodList.csv"))) { // read in csv
            String[] splitLine;
            String currLine;
            while ((currLine = br.readLine()) != null) { // iterate through lines, stop when line is empty
                splitLine = currLine.split(","); // split line by comma
                if (splitLine[0].equals("AlaCarte")) {
                    menuItems.add(new AlaCarteItem(splitLine[1], splitLine[2], Double.parseDouble(splitLine[3]), FoodType.valueOf(splitLine[4])));
                    diffItemIndex++;
                } else if (splitLine[0].equals("Package")) {
                    ArrayList<AlaCarteItem> packageItems = new ArrayList<>(); // temp array for items in package
                    String[] itemID = splitLine[4].split("-"); // temp array for item ID identifiers, delimited by '-'
                    for (int i=0;i<itemID.length;i++) { // add items to temp package array
                        if (menuItems.get(Integer.parseInt(itemID[i])-1) instanceof AlaCarteItem) {
                            packageItems.add((AlaCarteItem)menuItems.get(Integer.parseInt(itemID[i])-1));
                        } else {
                            System.out.println("Not an AlaCarte item!");
                        }
                    }
                    menuItems.add(new SetPackage(splitLine[1], splitLine[2], Double.parseDouble(splitLine[3]), packageItems, FoodType.valueOf(splitLine[5]))); // create package
                } else {
                    System.out.println("Line ignored when importing FoodList.csv.");
                }
            }
            menuItems.sort(Comparator.comparing(MenuItem::getFoodType));
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please place \"FoodList.csv\" into the root folder of this Java executable.");
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
        } catch (NullPointerException|NumberFormatException e) {
            System.out.println("Error reading CSV file: Invalid format encountered.");
        } catch (InputMismatchException e) {
            System.out.println("Error reading CSV file: Invalid input type encountered.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error reading CSV file: Please check items in Set Package.");
        }
    }

    
    /** 
     * Gets the ArrayList of MenuItem.
     * @return ArrayList of MenuItem Objects
     */
    // methods
    public ArrayList<MenuItem> getList() {return this.menuItems;}
    
    /** 
     * Gets MenuItem, located at index, in ArrayList of MenuItem objects.
     * @param i Index of target MenuItem
     * @return MenuItem
     */
    public MenuItem getItem(int i) {return this.menuItems.get(i);}
    
    /** 
     * Adds a MenuItem object to the menu.
     * Sorts the Menu after adding, and checks if the object is an AlaCarteItem.
     * If it is an AlaCarteItem, diffItemIndex will be updated.
     * @param menuItem MenuItem object
     */
    public void add(MenuItem menuItem) {
        this.menuItems.add(menuItem);
        menuItems.sort(Comparator.comparing(MenuItem::getFoodType));
        if (menuItem instanceof AlaCarteItem) {
            diffItemIndex++;
        }
    }
    
    /** 
     * Removes MenuItem, located at index i, from ArrayList of MenuItem objects
     * @param i Index of target MenuItem
     */
    public void remove(int i) {this.menuItems.remove(i);}
    
    /** 
     * Gets the index of the first SetPackage item in the ArrayList of MenuItem objects.
     * @return int index 
     */
    public int getDiffItemIndex() {return this.diffItemIndex;}

    /**
     * Overwrites all existing data in "FoodList.csv" with current Menu.
     * Converts AlaCarteItem and SetPackage to String before storing.
     * SetPackage relies on relative index of AlaCarteItem.
     */
    public void closeMenu() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/FoodList.csv",Charset.forName("UTF-8")))) {
            for (int i=0;i<diffItemIndex;i++) { // AlaCarte
                String item = String.join(",","AlaCarte",menuItems.get(i).getName(),menuItems.get(i).getDesc(),String.valueOf(menuItems.get(i).getPrice()),String.valueOf(menuItems.get(i).getFoodType()));
                bw.write(item+"\n");
            }
            for (int i=diffItemIndex;i<menuItems.size();i++) { // Set Packages
                String alaCarteList="";
                if (menuItems.get(i) instanceof SetPackage) { // downcast
                    SetPackage setPackage = (SetPackage)menuItems.get(i);
                    for (int a=0;a<diffItemIndex;a++) { // check within array of alacarte items
                        for (int b=0;b<setPackage.getItems().size();b++) { // check each alacarte item in setpackage
                            if (setPackage.getItems().get(b).getName().equals(menuItems.get(a).getName())) {
                                alaCarteList += (String.valueOf(a+1)+"-");
                            }
                        }
                    }
                }
                String item = String.join(",","Package",menuItems.get(i).getName(),menuItems.get(i).getDesc(),String.valueOf(menuItems.get(i).getPrice()),alaCarteList.substring(0, alaCarteList.length()-1),String.valueOf(menuItems.get(i).getFoodType()));
                bw.write(item+"\n");
            }
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
        }

        System.out.println("Menu successfully written to FoodList.csv!");
    }
}