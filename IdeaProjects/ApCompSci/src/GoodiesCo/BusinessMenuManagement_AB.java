package GoodiesCo;/*
 * Aryaman Babber

 * 1. Warning: 11/29 is a hard due date.

 * 2. If you submit incomplete work, make sure you add a line at the top of the post so I don't grade it.

 * 3. Attach all needed files including the data file.


 * Implementation Phase #1:
 * Using your Design from last class, implement the Business Operations application for the business
 * owner/manager/book keeper/accountant to operate the business.

 * Menu:
 * 1. Start the company’s initial state. That includes inventory with the cost associated for the
 * purchases of the products. This feature enables the user to print the table below with the current inventory.
 * NOTE: display all information in well aligned columns. Use "printf".

 * 2. Updates to the products, quantities,  cost and the sale price.

 * 3. Closing of the business cycle by producing a report with all needed information.

 * 11/29/17
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

class Inventory {
    private ArrayList<String> dataName = new ArrayList<>();
    private ArrayList<String> dataQuantity = new ArrayList<>();
    private ArrayList<String> dataCost = new ArrayList<>();
    private ArrayList<String> dataPrice = new ArrayList<>();
    private ArrayList<String> dataProfits = new ArrayList<>();

    void rawDataSetup() {
        try {
            File rawData = new File("BusinessRawData");
            Scanner inFile = new Scanner(rawData);

            while (inFile.hasNext()) {
                String i = inFile.nextLine().toLowerCase();
                String[] j = i.split(",");

                if (j.length != 1) {
                    dataName.add(j[0]);
                    dataQuantity.add(j[1]);
                    dataCost.add(j[2]);
                    dataPrice.add(j[3]);
                    dataProfits.add(j[4]);
                }
            }
            inFile.close();
        } catch (FileNotFoundException error) {
            System.out.println("\nError: the file with this pathname does not exist");
        }
    }

    void getData() {
        System.out.printf("%25s%15s%15s%15s%15s\n\n", "Items", "Quantity", "Cost", "Price", "Profits");
        for (int i = 0; i < dataName.size(); i++) {
            System.out.printf("%25s%15s%15s%15s%15s\n", dataName.get(i), dataQuantity.get(i), dataCost.get(i),
                    dataPrice.get(i), dataProfits.get(i));
        }
    }

    void addProduct() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nWhat's the name of the item you want to add: ");
        String newProductName = (input.next() + input.nextLine()).toLowerCase();

        if (dataName.contains(newProductName)) System.out.println("\nThis item already exists");

        else {
            System.out.print("What's the quantity of the item: ");
            String newProductQuantity = input.nextLine();

            System.out.print("What's the cost for you to buy the item: ");
            String newProductCost = input.nextLine();

            System.out.print("What's the price you sell your item for: ");
            String newProductPrice = input.nextLine();

            dataName.add(newProductName);
            dataQuantity.add(newProductQuantity);
            dataCost.add(newProductCost);
            dataPrice.add(newProductPrice);
            dataProfits.add("0.00");

            System.out.println("\nItem added!");

            try{TimeUnit.SECONDS.sleep(1);}
            catch( java.lang.InterruptedException error) {System.out.println("Error");}
        }

    }

    void deleteProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("Items:");

        for (int i = 0; i < dataName.size(); i++) System.out.println(dataName.get(i));

        System.out.print("\nWhich product do u want to delete? (enter the item name the way it is shown): ");

        String itemDeleted = input.nextLine().toLowerCase();

        if (dataName.contains(itemDeleted)) {
            int i = dataName.indexOf(itemDeleted);

            dataName.remove(dataName.get(i));
            dataQuantity.remove(dataQuantity.get(i));
            dataCost.remove(dataCost.get(i));
            dataPrice.remove(dataPrice.get(i));

            System.out.println("\nItem deleted!");
        }
        else System.out.println("\nItem does not exist");

        try{TimeUnit.SECONDS.sleep(1);}
        catch( java.lang.InterruptedException error) {System.out.println("Error");}
    }

    void editData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Which item would you like to edit: ");

        System.out.println("Items:");

        for (int i = 0; i < dataName.size(); i++) System.out.println(dataName.get(i));

        System.out.print("\nWhich product would you like to edit? (enter the item name the way it is shown): ");

        String itemToEdit = input.nextLine().toLowerCase();

        if (!dataName.contains(itemToEdit)) System.out.println("\n This item does not exist");

        else {
            int indexOfItem = dataName.indexOf(itemToEdit);

            System.out.print("What would you like to edit? quantity (q), cost (c), or price (p): ");
            String variableToEdit = input.nextLine();

            switch (variableToEdit) {
                case "q": {
                    System.out.print("What would you like to change the quantity to: ");
                    String newQuantity = input.nextLine();
                    dataQuantity.set(indexOfItem, newQuantity);
                    System.out.println("\nQuantity changed");

                    try{TimeUnit.SECONDS.sleep(1);}
                    catch( java.lang.InterruptedException error) {System.out.println("Error");}

                    break;
                }

                case "c": {
                    System.out.print("What would you like to change the cost to: ");
                    String newCost = input.nextLine();
                    dataCost.set(indexOfItem, newCost);
                    System.out.println("\nCost changed");

                    try{TimeUnit.SECONDS.sleep(1);}
                    catch( java.lang.InterruptedException error) {System.out.println("Error");}

                    break;
                }

                case "p": {
                    System.out.print("What would you like to change the price to: ");
                    String newPrice = input.nextLine();
                    dataPrice.set(indexOfItem, newPrice);
                    System.out.println("\nQPrice changed");

                    try{TimeUnit.SECONDS.sleep(1);}
                    catch( java.lang.InterruptedException error) {System.out.println("Error");}

                    break;
                }
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    void printToFile() {
        try{
            PrintWriter fileOut = new PrintWriter("BusinessRawData");

            for(int i = 0; i < dataName.size(); i++) {
                fileOut.println(dataName.get(i) + "," + dataQuantity.get(i)
                        + "," + dataCost.get(i) + "," + dataPrice.get(i) + "," + dataProfits.get(i));
            }

            fileOut.flush();
            fileOut.close();

            System.out.println("\nGoodbye");

        }
        catch(FileNotFoundException error) {
            System.out.println("File does not exist");
        }
    }
}

public class BusinessMenuManagement_AB {
    public static void main(String[] args) {
        System.out.println("Welcome to the Business Menu Management file.");

        try{TimeUnit.SECONDS.sleep(1);}
        catch( java.lang.InterruptedException error) {System.out.println("Error");}

        Scanner input = new Scanner(System.in);

        Inventory object1 = new Inventory();
        object1.rawDataSetup();

        System.out.println("\nCurrent Data:");
        object1.getData();

        boolean run = true;

        try{TimeUnit.SECONDS.sleep(1);}
        catch( java.lang.InterruptedException error) {System.out.println("Error");}

        while(run) {
            System.out.print("\nWhat would you like to do? (get the data (g), add an item(a), delete an item (d)," +
                    " edit an item's information (e), or exit the program (x)): ");

            String userChoice = input.nextLine().toLowerCase();

            switch(userChoice) {
                case "g": {
                    object1.getData();
                    break;
                }

                case "a": {
                    object1.addProduct();
                    break;
                }

                case "d": {
                    object1.deleteProduct();
                    break;
                }

                case "e": {
                    object1.editData();
                    break;
                }

                case "x": {
                    object1.printToFile();
                    run = false;
                    break;
                }

                default : System.out.println("Incorrect input");
            }
        }
    }
}

/* input/output:

Welcome to the Business Menu Management file.

Current Data:
                    Items       Quantity           Cost          Price        Profits

                  sunkist             45           0.85           1.25           0.00
                coca cola             45           0.89           1.25           0.00
                  ice tea             45           0.78           1.25           0.00
                   yoohoo             45           0.45           0.75           0.00
                   sprite             45           0.85           0.75           0.00
               ginger ale             45           0.90           0.75           0.00
               dr. pepper             44           0.88           0.75           0.00
                    water             45           0.60           0.50           0.00
                    chips             50           0.55           0.50           0.00
              pop corners             45           0.59           0.50           0.00
            veggie sticks             75           0.20           1.25           0.00
            rice krispies             45           0.38           1.25           0.00
             granola bars             45           0.30           1.25           0.00
              apple juice             45           0.50           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): a

What's the name of the item you want to add: Orange Juice
What's the quantity of the item: 45
What's the cost for you to buy the item: 0.70
What's the price you sell your item for: 1.50

Item added!

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): g
                    Items       Quantity           Cost          Price        Profits

                  sunkist             45           0.85           1.25           0.00
                coca cola             45           0.89           1.25           0.00
                  ice tea             45           0.78           1.25           0.00
                   yoohoo             45           0.45           0.75           0.00
                   sprite             45           0.85           0.75           0.00
               ginger ale             45           0.90           0.75           0.00
               dr. pepper             44           0.88           0.75           0.00
                    water             45           0.60           0.50           0.00
                    chips             50           0.55           0.50           0.00
              pop corners             45           0.59           0.50           0.00
            veggie sticks             75           0.20           1.25           0.00
            rice krispies             45           0.38           1.25           0.00
             granola bars             45           0.30           1.25           0.00
              apple juice             45           0.50           1.50           0.00
             orange juice             45           0.70           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): d
Items:
sunkist
coca cola
ice tea
yoohoo
sprite
ginger ale
dr. pepper
water
chips
pop corners
veggie sticks
rice krispies
granola bars
apple juice
orange juice

Which product do u want to delete? (enter the item name the way it is shown): apple juice

Item deleted!

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): g
                    Items       Quantity           Cost          Price        Profits

                  sunkist             45           0.85           1.25           0.00
                coca cola             45           0.89           1.25           0.00
                  ice tea             45           0.78           1.25           0.00
                   yoohoo             45           0.45           0.75           0.00
                   sprite             45           0.85           0.75           0.00
               ginger ale             44           0.90           0.75           0.00
               dr. pepper             45           0.88           0.75           0.00
                    water             50           0.60           0.50           0.00
                    chips             45           0.55           0.50           0.00
              pop corners             75           0.59           0.50           0.00
            veggie sticks             45           0.20           1.25           0.00
            rice krispies             45           0.38           1.25           0.00
             granola bars             45           0.30           1.25           0.00
             orange juice             45           0.70           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): e
Which item would you like to edit: Items:
sunkist
coca cola
ice tea
yoohoo
sprite
ginger ale
dr. pepper
water
chips
pop corners
veggie sticks
rice krispies
granola bars
orange juice

Which product would you like to edit? (enter the item name the way it is shown): veggie sticks
What would you like to edit? quantity (q), cost (c), or price (p): q
What would you like to change the quantity to: 50

Quantity changed

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): g
                    Items       Quantity           Cost          Price        Profits

                  sunkist             45           0.85           1.25           0.00
                coca cola             45           0.89           1.25           0.00
                  ice tea             45           0.78           1.25           0.00
                   yoohoo             45           0.45           0.75           0.00
                   sprite             45           0.85           0.75           0.00
               ginger ale             44           0.90           0.75           0.00
               dr. pepper             45           0.88           0.75           0.00
                    water             50           0.60           0.50           0.00
                    chips             45           0.55           0.50           0.00
              pop corners             75           0.59           0.50           0.00
            veggie sticks             50           0.20           1.25           0.00
            rice krispies             45           0.38           1.25           0.00
             granola bars             45           0.30           1.25           0.00
             orange juice             45           0.70           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): x

Goodbye

Process finished with exit code 0

 */
