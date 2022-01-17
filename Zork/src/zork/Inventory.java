package zork;

import java.util.ArrayList;

public class Inventory {
  public static final String yellow = "\u001B[33m"; // for displayInventory
  public static final String white = "\u001B[37m"; // for displayInventory
  public static final String blue = "\u001B[34m"; // for another functionpublic static final String TEXT_RED = "\u001B[31m";
  public static final String red = "\u001B[31m";  //for "keep searching"


  private ArrayList<Item> items;
  private int maxWeight;

  public Inventory(int maxWeight) {
    this.items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
    
  }

  public int getMaxWeight() {
    return maxWeight;
  }

  public int getCurrentWeight() {
    int currentWeight = 0;
    for(Item item: items){
      currentWeight+=item.getWeight();
    }

    return currentWeight;
  }

  public boolean add(Item item) {

    if (item.getWeight() + getCurrentWeight() <= maxWeight) {

      return items.add(item);

    } else {
      System.out.println();
      System.out.println("There is no room to add this item");
      System.out.println();
    }
    return false;
  }
  public void displayInventory() throws InterruptedException {

    String message = blue + "Displaying Inventory" + white;
    System.out.println();
    System.out.println();

    if(items.size() == 0){
      System.out.println("Search for items around the map, and find them here");
      System.out.println();
    }else{


    for (int i = 0; i < message.length(); i++) {

      System.out.printf("%c", message.charAt(i));
      Thread.sleep(50);
    }
    System.out.println();
    System.out.println();

    for (Item i : items) {
      
      System.out.println(blue + "- " + white + i.getName() + " ");
    }
  }
}

public void inspectRoomItem(String itemName){

for(Item i:items){

  if(i.getName().equals(itemName)){

    System.out.println();
    System.out.println(itemName + ": " + i.getDescription());
    System.out.println();
    
  }else{
  System.out.println();
   System.out.println("item not found");
   System.out.println();
  }    
  }
  }

 public void searchRoom() throws InterruptedException {

    String noItems = red + "Keep Searching..." + white; //printable message for when no items are found
    String youFound = blue + "You Found: " + white;     //printable message for when items are found

    if (items.size() == 0) {
      System.out.println();
      for (int i = 0; i < noItems.length(); i++) {    //prints "keep searching..." message when the rooms inventory.size() returns 0
        System.out.printf("%c", noItems.charAt(i));
        Thread.sleep(20);
      }
      System.out.println();
      System.out.println();

    } else {
      System.out.println();
      for (int i = 0; i < youFound.length(); i++) {   //Prints "you found" message, followed by the items
        System.out.printf("%c", youFound.charAt(i));
        Thread.sleep(20);
      }

      for (int i = 0; i < items.size(); i++) {    //uses to determine where the final item is to insert "and" text
        if (i == items.size() - 2) {
          System.out.print(items.get(i).getName() + " and ");
        } else if (i == items.size() - 1) { //if the items.size() returns 1, then no "and message occurs, and a single item is printed"
          System.out.print(items.get(i).getName());
        } else {
          System.out.print(items.get(i).getName() + ", ");  //"used to display commas after every item name, until the last item"
        }
      }

      System.out.println();
      System.out.println();

    }
  }
  /**Key Method For ZORK, which allows for items to removed from a specific inventory
   */
  public Item remove(String itemName) {

    /** Each room has an arraylist<> of items
      * This method iterates through that list to find the item name that matches the variable passed in
      * In this case, the method is looking for a item with the same name as itemName  */
    
    for (int i = items.size() - 1; i >= 0; i--) {

      Item item = items.get(i);

      if (item.getName().equals(itemName)) {
        return items.remove(i);
      }
    }
    return null;
  }
  /**Allows the user to read the descriptions of notes */
  public String readItem(String itemName){
    for (Item item : items) {
      if (item.isNote() && item.getName().equals(itemName)) { //Has a checker to see if a item is a note using isNote(), while also checking to see if the name of that item is the same as itemname
        return item.getDescription();
      }
    }
    return("Unable to read this item");     //is the item is not a note, print a message
  }

  /**hasAllKeys is used to determine whether the lock on the trapdoor is now unlocked
   * Using the inInventory method, a check is made to see if the user has key 1, key 2 & key 3
   */
  public boolean hasAllKeys(){  
    if(inInventory("key 1") && inInventory("key 2") && inInventory("key 3")){
      return true; 
    }else{
      return false; 
    }
  }
  /**By Passing in the variable name, this method iterates,
   * through a list of items, to check if any i.getName() returns the same name */

  public boolean inInventory(String name){
    for (Item i : items) {
      if(i.getName().equals(name)){
        return true; 
      }
    }
    return false; 
  }

  public Item findItem(String itemName) {
    for(Item i : items){
      if(i.getName().equals(itemName)){
        return i; 
      }
    }
    return null; 
  }

  public void inventorySpace(){
    
 /* * * * * * * * * * * * * * * * * * * * *
  * Calculates the total inventory space  *
  * The user has LEFT not how much space  *
  * They have USED                        *
  * * * * * * * * * * * * * * * * * * * * */ 
    
    int sum = 0;  //sum will be used to determine the total weight of all the items in your inventory
    int part = 0; //part will be used to determine the percentage of space left in playerInventory
    int total = 4317; //total inventory space passed in with constructor in game class

    if(items.size() == 0){  //If items.size() returns zero, you have 100% capacity left

      System.out.println();
      System.out.println(blue + "100% Carrying Capacity Left" + white);

    }else{

    for(Item i: items){    //If not, iterate through list of items and add each 

     int value = i.getWeight(); //value is each items weight

     sum += value;    //add the value of "value" sum
    }   
    part = total - sum;   //part = difference of whole to sum
    part = ((part * 100)) / total;    //final calculation to find percentage

    System.out.println();
    System.out.println("Total Inventory Space Left: " + blue + part +"%" + white);

  }
}
/**Basic toString method that every iventory has */

  public String toString() {
    String msg = "";

    for (Item item : items) {
      msg += item + ", ";
    }

    if (msg.length() > 0) {
      msg = msg.substring(msg.length() - 2);
    }
    return msg;
  }

}