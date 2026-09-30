import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // create instance of zooManager. Load data in txt files from previous session
        ZooManager zoo = new ZooManager();
        zoo.loadZooDetails();
        zoo.loadAnimalDetails();

        // menu repeats after every option until user selects "9. Exit Program"
        boolean online = true;

        System.out.println("Zoo system started...");
        int choice;
        while(online){

            System.out.println();
            System.out.println("=-- Menu --=");
            System.out.println("1. Add animal");
            System.out.println("2. Remove animal");
            System.out.println("3. Modify animal");
            System.out.println("4. View Animals");
            System.out.println("5. Perform Daily Care");
            System.out.println("6. Search animal (noise)");
            System.out.println("7. Zoo Report");
            System.out.println("8. Clear Zoo Data");
            System.out.println("9. Exit program");


            try{
                System.out.print("Enter a number: ");
                choice = scanner.nextInt();

                if(choice >= 1 && choice <=9){

                    if(choice == 1){
                        zoo.createChoice(scanner);
                    }
                    else if(choice == 2){
                        scanner.nextLine(); // clear buffer
                        System.out.print("Enter the name of the animal you want removed: ");
                        String name = scanner.nextLine();

                        zoo.removeAnimal(name);
                    }
                    else if(choice == 3){
                        scanner.nextLine(); // clear buffer
                        System.out.print("Enter the name of the animal you want to modify: ");
                        String name = scanner.nextLine();

                        zoo.modifyAnimal(scanner, name);
                    }
                    else if(choice == 4){
                        zoo.viewAnimals();
                    }
                    else if(choice == 5){
                        zoo.dailyCare();
                    }
                    else if(choice == 6){
                        zoo.searchAnimal(scanner);
                    }
                    else if(choice == 7){
                        zoo.zooReport();
                    }
                    else if(choice == 8){
                        zoo.clearZoo(scanner);
                    }
                    else if(choice == 9){
                        System.out.println("Exiting program...");
                        online = false;
                    } // end if 9

                } // end outer if-statement
                else{
                    System.out.println("Enter a number between 1 - 9");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Input, please enter a number!");
                scanner.next();
            }

        } // end while
        scanner.close();

        // save zoo details at end of session
        zoo.saveZooDetails();
        zoo.saveAnimalDetails();
    } // end method
} // end class
