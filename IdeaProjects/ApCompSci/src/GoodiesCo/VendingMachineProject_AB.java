package GoodiesCo;
/*
 * Aryaman Babber

 * Integrate phases 1 and 2 into this final implementation.
 * Your Goodies Co. Business Software should run a seamless and smooth
 * application that allows either the consumer of the business owner
 * to perform their task without much effort.
 * It should be a good experience for both of them.
 * All feature should be thoroughly tested. That means no errors.

 * 12/18/17
 */

import GoodiesCo.BusinessMenuManagement_AB;
import GoodiesCo.ConsumerExperience_AB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VendingMachineProject_AB{
    public static void main(String[] args) {
        System.out.println("Welcome to the vending machine.\n");

        try {
            File rawData = new File("EncryptedInfoForVendingMachine");
            Scanner inFile = new Scanner(rawData);

            Scanner input = new Scanner(System.in);

            System.out.print("Are you a customer or the business manager? (enter 'c' for customer, or 'b' for manager): ");
            String typeOfPerson = input.nextLine().toLowerCase();

            switch(typeOfPerson) {
                case "c": {

                    ConsumerExperience_AB.main(null);
                    break;
                }

                case "b": {
                    System.out.print("\nEnter your username: ");
                    String username = input.nextLine();

                    System.out.print("Enter your password: ");
                    String password = input.nextLine();

                    String actualUsername = inFile.nextLine();
                    String actualPassword = inFile.nextLine();

                    if(username.equals(actualUsername) && password.equals(actualPassword)) {
                        System.out.println("\nEntry successful\n\n\n\n");

                        BusinessMenuManagement_AB.main(null);
                    }

                    else System.out.println("\nIncorrect username or password. Please try again later");
                    break;
                }
                default: {
                    System.out.println("Incorrect input. Please try again later.");
                    break;
                }
            }

        }
        catch(FileNotFoundException error) {
            try {
                Scanner input = new Scanner(System.in);
                PrintWriter fileOut = new PrintWriter("EncryptedInfoForVendingMachine");

                System.out.print("To begin, please enter a username: ");
                String username = input.nextLine();

                System.out.print("\nPlease enter a password: ");
                String password = input.nextLine();

                fileOut.println(username);
                fileOut.println(password);

                fileOut.flush();
                fileOut.close();

                System.out.println("\nYour information has been saved for next time.\n\n\n");

                BusinessMenuManagement_AB.main(null);
            }
            catch(FileNotFoundException e) {
                System.out.println("Not working");
            }
        }

    }
}

/* input/output:
(First time program ever runs):

Welcome to the vending machine.

To begin, please enter a username: ababber

Please enter a password: PleaseGiveMeAnA

Your information has been saved for next time.



Welcome to the Business Menu Management file.

Current Data:
                    Items       Quantity           Cost          Price        Profits

                  sunkist              0           0.85           1.25           0.00
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
             orange juice           45.0           0.70           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): x

Goodbye

Process finished with exit code 0







(If a customer):

Welcome to the vending machine.

Are you a customer or the business manager? (enter 'c' for customer, or 'b' for manager): c
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

Enter the item you would like to buy: sprite
Cost of item: $0.75

Enter the amount of money you are placing in the vending machine (in the format 1.10): 1.00
Your change is: $0.25

Do you want to buy another item? (y/n): n

Goodbye

Process finished with exit code 0







(If a business owner with incorrect username/password):

Welcome to the vending machine.

Are you a customer or the business manager? (enter 'c' for customer, or 'b' for manager): b

Enter your username: thisIs
Enter your password: Wrong

Incorrect username or password. Please try again later

Process finished with exit code 0

(If a business owner with correct information):

Welcome to the vending machine.

Are you a customer or the business manager? (enter 'c' for customer, or 'b' for manager): b

Enter your username: ababber
Enter your password: PleaseGiveMeAnA

Entry successful




Welcome to the Business Menu Management file.

Current Data:
                    Items       Quantity           Cost          Price        Profits

                  sunkist              0           0.85           1.25           0.00
                coca cola             45           0.89           1.25           0.00
                  ice tea             45           0.78           1.25           0.00
                   yoohoo             45           0.45           0.75           0.00
                   sprite           44.0           0.85           0.95           0.00
               ginger ale             44           0.90           0.95           0.00
               dr. pepper             45           0.88           0.95           0.00
                    water             50           0.60           0.90           0.00
                    chips             45           0.55           0.90           0.00
              pop corners             75           0.59           0.80           0.00
            veggie sticks             50           0.20           1.25           0.00
            rice krispies             45           0.38           1.25           0.00
             granola bars             45           0.30           1.25           0.00
             orange juice           44.0           0.70           1.50           0.00

What would you like to do? (get the data (g), add an item(a), delete an item (d), edit an item's information (e), or exit the program (x)): x

Goodbye

Process finished with exit code 0
 */
