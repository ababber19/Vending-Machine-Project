package GoodiesCo;/*
 * Aryaman Babber

 * Phase 2:
 * Consumer experience – consumer interaction:
    1.	Customer buys an item
    2.	Customer pays
    3.	Change is return to customer if needed
 */

import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

class Interactions{
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

    private void getDataForConsumer() {
        System.out.printf("%7s%25s%15s\n\n", "", "Items", "Price");
        for (int i = 0; i < dataName.size(); i++) {
            System.out.printf("%7s%25s%15s\n", (i + 1) + ")", dataName.get(i), dataPrice.get(i));
        }
    }

    private void returnChange(String paymentMethod, int indexOfItem) {
        double cost = Double.parseDouble(dataPrice.get(indexOfItem));

        double amountPayed = Double.parseDouble(paymentMethod);

        System.out.println("Your change is: $" + (amountPayed - cost));

        try{TimeUnit.SECONDS.sleep(1);}
        catch( java.lang.InterruptedException error) {System.out.println("Error");}
    }

    private void changeQuantity(int indexOfItem) {
        double currentQuantity = Double.parseDouble(dataQuantity.get(indexOfItem));

        String newQuantity = Double.toString(currentQuantity - 1);

        dataQuantity.set(indexOfItem, newQuantity);
    }

    private void changeProfits(int indexOfItem) {
        double currentProfits = Double.parseDouble(dataProfits.get(indexOfItem));

        double newProfits = (Double.parseDouble(dataPrice.get(indexOfItem))
                - Double.parseDouble(dataCost.get(indexOfItem))) + currentProfits;

        dataProfits.set(indexOfItem, Double.toString(newProfits));
    }

    void interactWithUser() {
        System.out.println("Welcome to the vending machine!\n");

        try{TimeUnit.SECONDS.sleep(1);}
        catch( java.lang.InterruptedException error) {System.out.println("Error");}


        Scanner input = new Scanner(System.in);

        int itemToBuy;
        String paymentMethod;

        boolean run = true;
        while(run) {
            getDataForConsumer();

            System.out.print("\nEnter the number of the item you would like to buy: ");
            itemToBuy = input.nextInt() - 1;

            if(itemToBuy > dataName.size()) {
                System.out.println("\nThis item does not exist.\n");

                try{TimeUnit.SECONDS.sleep(1);}
                catch( java.lang.InterruptedException error) {System.out.println("Error");}
            }
            else if(Double.parseDouble(dataQuantity.get(itemToBuy)) < 1) {
                System.out.println("\nI'm sorry. This item is out of stock.");
            }

            else {
                System.out.println("Cost of item: $" + dataPrice.get(itemToBuy));

                System.out.print("\nEnter the amount of money you are placing in the vending machine " +
                        "(in the format 1.10): ");
                paymentMethod = input.nextLine();

                returnChange(paymentMethod, itemToBuy);

                changeQuantity(itemToBuy);
                changeProfits(itemToBuy);
            }

            System.out.print("\nDo you want to buy another item? (y/n): ");
            if(input.nextLine().equals("n")) run = false;
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

public class ConsumerExperience_AB {
    public static void main(String[] args) {

        Interactions object1 = new Interactions();

        object1.rawDataSetup();

        object1.interactWithUser();

        object1.printToFile();
    }
}

/* input/output:

Welcome to the vending machine!

                    Items          Price

                  sunkist           1.25
                coca cola           1.25
                  ice tea           1.25
                   yoohoo           0.75
                   sprite           0.75
               ginger ale           0.75
               dr. pepper           0.75
                    water           0.50
                    chips           0.50
              pop corners           0.50
            veggie sticks           1.25
            rice krispies           1.25
             granola bars           1.25
             orange juice           1.50

Enter the item you would like to buy: please give me an A ms elia

This item does not exist.


Do you want to buy another item? (y/n): y
                    Items          Price

                  sunkist           1.25
                coca cola           1.25
                  ice tea           1.25
                   yoohoo           0.75
                   sprite           0.75
               ginger ale           0.75
               dr. pepper           0.75
                    water           0.50
                    chips           0.50
              pop corners           0.50
            veggie sticks           1.25
            rice krispies           1.25
             granola bars           1.25
             orange juice           1.50

Enter the item you would like to buy: orange juice
Cost of item: $1.50

Enter the amount of money you are placing in the vending machine (in the format 1.10): 1.75
Your change is: $0.25

Do you want to buy another item? (y/n): y
                    Items          Price

                  sunkist           1.25
                coca cola           1.25
                  ice tea           1.25
                   yoohoo           0.75
                   sprite           0.75
               ginger ale           0.75
               dr. pepper           0.75
                    water           0.50
                    chips           0.50
              pop corners           0.50
            veggie sticks           1.25
            rice krispies           1.25
             granola bars           1.25
             orange juice           1.50

Enter the item you would like to buy: sunkist

I'm sorry. This item is out of stock.

Do you want to buy another item? (y/n): n

Goodbye

Process finished with exit code 0
 */