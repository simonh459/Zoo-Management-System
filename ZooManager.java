import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ZooManager {

    private ArrayList<Animal> animals = new ArrayList<>();


    // method to run daily care of animals
    public void dailyCare(){

        System.out.println(); // clean menu spacing
        if(animals.isEmpty()){
            System.out.println("There are no animals in this zoo!");
            return;
        }

        for(Animal a : animals){
            System.out.println("General caring for " + a.getName());

            //general care
            System.out.println("Feeding and Cleaning animal...");

            //special care
            if(a instanceof Flyable){
                Flyable f = (Flyable) a;
                f.fly();
                System.out.println("Performing wing check...");
                f.checkWings();
            }
            System.out.println("General caring for " + a.getName() + " is complete!");
            System.out.println(); // newline
        }
        System.out.println("All animals in the zoo have been cared for.");
    }


    // user chooses what subclass of animal they want to create
    public void createChoice(Scanner input){
        System.out.println("WARNING: any data left blank WILL result in the animal not being added...");
        System.out.println("Which animal would you like to add: 1. Dog  2. Bird  3. Monkey");

        int choice = 0;
        while(true){
            try{
                System.out.print("Pick a number: ");
                choice = input.nextInt();

                if(choice >= 1 && choice <=3){
                    break;
                }
                else{
                    System.out.println("Please enter a number between 1-3");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Enter in a number.");
                input.next(); // clear input
            }
        }

        Animal animal = null;

        if(choice == 1){
            animal = createDog(input);
        }
        else if(choice == 2){
            animal = createBird(input);
        }
        else if(choice == 3){
            animal = createMonkey(input);
        }
        if(animal != null){
            animals.add(animal);
            System.out.println("Animal added to zoo successfully");
        }
    }

    // method used to validate the data entered by user FOR STRINGS
    public static String checkValidString(Scanner input, String prompt){
        String value;
        while(true){
            System.out.print(prompt);
            value = input.nextLine();

            if(!value.trim().isEmpty()){
                return value;
            }
            else{
                System.out.println("This field cannot be empty");
            }
        }
    }

    // method used to validate user date from input FOR INTEGERS
    public static int checkValidInt(Scanner input, String prompt, int minValue, int maxValue){
        while(true){
            System.out.print(prompt); // retells the prompt entered in create_"Animal"_() method
            try{
                int value = Integer.parseInt(input.nextLine());

                if(value >= minValue && value <= maxValue){
                    return value;
                }
                else{
                    System.out.println("Value must be between " + minValue + " - " + maxValue);
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input. You must enter a number");
            }
        }
    }


    // user input for dog class
    public static Dog createDog(Scanner input) {
        input.nextLine();

        String name = checkValidString(input, "Enter name of the dog: " );
        int age = checkValidInt(input, "Enter age of the dog: ", 0, 50);
        String colour = checkValidString(input, "Enter the colour of the dog: ");
        int weight = checkValidInt(input, "Enter the weight of the dog (kg): ", 1, 100);
        int friendliness = checkValidInt(input, "Enter the friendliness (1 min - 5 max) of the dog: ", 1, 5);

        return new Dog(name, age, colour, weight, friendliness);
    }


    // user input for bird class
    public static Bird createBird(Scanner input) {
        input.nextLine(); // Ensure input displayed correctly

        String name = checkValidString(input, "Enter name of the bird: " );
        int age = checkValidInt(input, "Enter age of the bird: ", 0, 100);
        String colour = checkValidString(input, "Enter the colour of the bird: ");
        int weight = checkValidInt(input, "Enter the weight of the bird (kg): ", 1, 100);
        int wingspan = checkValidInt(input, "Enter the wingspan of the bird (cm): ", 1, 100);

        return new Bird(name, age, colour, weight, wingspan);
    }


    // user input for class monkey
    public static Monkey createMonkey(Scanner input) {
        input.nextLine();

        String name = checkValidString(input, "Enter name of the monkey: " );
        int age = checkValidInt(input, "Enter age of the monkey: ", 0, 80);
        String colour = checkValidString(input, "Enter the colour of the monkey: ");
        int weight = checkValidInt(input, "Enter the weight of the monkey (kg): ", 1, 100);

        String soundLevel;
        while(true){
            System.out.print("Enter how noisy the monkey is (low, medium, high): ");
            soundLevel = input.nextLine();

            if(soundLevel.equalsIgnoreCase("low") ||
                    soundLevel.equalsIgnoreCase("medium") ||
                    soundLevel.equalsIgnoreCase("high")){
                break;
            }
            else{
                System.out.println("Invalid input. Enter one of three choices");
            }
        } // end while

        return new Monkey(name, age, colour, weight, soundLevel);
    } // end method


    // remove animal if target variable matches animal name
    public void removeAnimal(String name){
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i).getName().equalsIgnoreCase(name)){
                animals.remove(i);
                System.out.println("Animal Removed!");
                return;
            }
        }
        System.out.println("Animal not found in zoo");

    }


    // if user-inputted variable matches animal name / colour, they can modify selected animal details
    public void modifyAnimal(Scanner scanner, String name){
        boolean found = false;

        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i).getName().equalsIgnoreCase(name)){

                Animal a = animals.get(i);
                System.out.println(); // spacing for console interface
                System.out.println(a); // calls toString() ; showing current details

                // Modify or Re-enter names and set them into appropriate subclass
                String newName = checkValidString(scanner, "Modify name of animal: ");
                a.setName(newName);

                int newAge = checkValidInt(scanner, "Modify age of animal: ", 0, 100);
                a.setAge(newAge);

                String newColour = checkValidString(scanner, "Modify the colour of the animal: ");
                a.setColour(newColour);

                int newWeight = checkValidInt(scanner, "Modify weight of animal (kg): ", 1, 100);
                a.setWeight(newWeight);

                // for instance variables, if animal is `subclass`, modify its instance variable

                if(a instanceof Dog){
                    Dog d = (Dog) a; // typecast "a" to dog

                    int newFriendliness = checkValidInt(scanner, "Modify friendliness of animal (1 min - 5 max): ", 1, 5);
                    d.setFriendliness(newFriendliness);
                }

                else if(a instanceof Bird){
                    Bird b = (Bird) a;

                    int newWingSpan = checkValidInt(scanner, "Modify wing span of animal (cm): ", 1, 100);
                    b.setWingspan(newWingSpan);
                }

                else if(a instanceof Monkey){
                    Monkey m = (Monkey) a;

                    String soundLevel;
                    while(true) {
                        System.out.print("Modify sound level (low/medium/high): ");
                        soundLevel = scanner.nextLine();

                        if (soundLevel.equalsIgnoreCase("low") ||
                                soundLevel.equalsIgnoreCase("medium") ||
                                soundLevel.equalsIgnoreCase("high")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Enter low, medium, or high.");
                        }
                    }

                    m.setSoundLevel(soundLevel);
                }

                System.out.println("Animal updated successfully!");
                found = true;
                break;
            }
        }

        // if user inputted animal name not the same as ones in arrayList:
        if(!found){
            System.out.println("Animal not found in zoo");
        }
    }


    // view animals in ArrayList animals
    public void viewAnimals(){
        System.out.println(); // menu spacing cleanliness

        if(animals.isEmpty()){
            System.out.println("There are no animals in this zoo!");
        }

        for(Animal a : animals){
            System.out.println(a);
            System.out.println(a.makeSound());
            System.out.println("-------------------------");
        }
    }

    // if NAME or COLOUR of animal match an existing animal's, show their report. If 2 brown, shows both.
    public void searchAnimal(Scanner scanner){
        scanner.nextLine(); // menu spacing

        if(animals.isEmpty()){
            System.out.println("There are no animals in this zoo!");
            return;
        }

        System.out.print("Enter the animal's name OR the animal's colour: ");
        String animalChoice = scanner.nextLine();
        boolean found = false;

        for(Animal a : animals){
            if(a.getName().equalsIgnoreCase(animalChoice) || a.getColour().equalsIgnoreCase(animalChoice)){
                System.out.println();
                System.out.println("---- Animal's details ----");
                System.out.println(a);
                System.out.println(a.makeSound());
                found = true;
            }
        }
        if(!found){
            System.out.println("Animal not found in zoo");
        }
    }


    // shows report containing number of animals and most dominant colour.
    public void zooReport(){
        System.out.println("Belfast Zoo Report");

        // check how many of each animal is in zoo
        int dogCount = 0;
        int birdCount = 0;
        int monkeyCount = 0;

        for(Animal a: animals){
            if(a instanceof Dog){
                dogCount++;
            }
            else if(a instanceof Bird){
                birdCount++;
            }
            else if(a instanceof Monkey){
                monkeyCount++;
            }
        }
        System.out.println("Total number of Animals: " + animals.size());
        System.out.println("Total number of dogs: " + dogCount);
        System.out.println("Total number of birds: " + birdCount);

        System.out.println("Total number of monkeys: " + monkeyCount);

        if(animals.isEmpty()){
            System.out.println("There are no animals in this zoo!");
            return;
        }

        // get most dominant colour using a HashMap (similar to data dictionary python)
        HashMap<String, Integer> colourCount = new HashMap<>();
        for(Animal a : animals){
            String colour = a.getColour();
            colourCount.put(colour, colourCount.getOrDefault(colour, 0)+1 ); // +1 since it starts at 0
        }

        // counts all colours in zoo, most common is displayed
        String dominantColour = "";
        int max = 0;
        for(String colour : colourCount.keySet()){
            int count = colourCount.get(colour); // gets value from hashmap

            if(count > max){
                max = count;
                dominantColour = colour;
            }
        } // end for
        System.out.println("The most dominant colour in the zoo is: " + dominantColour);
    } // end method


    // ran at end of session, saves all valid animals entered and data modified
    public void saveAnimalDetails(){
        try{
            FileWriter fWriter = new FileWriter("AnimalDetails.txt");
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for(Animal a : animals){
                bWriter.write(a.toFileString()); // method in Animal class to make code neater
                bWriter.newLine(); // new space (\n)
            }

            System.out.println("Animals saved successfully!");
            bWriter.close();
        }
        catch(IOException e){
            System.out.println("An error occurred saving details");
        }
    }

    // runs at start of program, loading previous sessions' details
    public void loadAnimalDetails(){

        try{
            File file = new File("AnimalDetails.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] pieces = line.split(",");
                Animal animal = null; // create the animal here instead of each separate if statement - polymorphism

                String type = pieces[0];
                String name = pieces[1];
                int age = Integer.parseInt(pieces[2]);
                String colour = pieces[3];
                int weight = Integer.parseInt(pieces[4]);

                if(type.equalsIgnoreCase("Dog")){
                    int friendliness = Integer.parseInt(pieces[5]);
                    animal = new Dog(name, age, colour, weight, friendliness);
                }

                else if(type.equalsIgnoreCase("Bird")){
                    int wingSpan = Integer.parseInt(pieces[5]);
                    animal = new Bird(name, age, colour, weight, wingSpan);
                }

                else if(type.equalsIgnoreCase("Monkey")){
                    String soundLevel = pieces[5];
                    animal = new Monkey(name, age, colour, weight, soundLevel);
                }

                if(animal != null){
                    animals.add(animal);
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("No file found...");
        }
    }

    // ran at end of session, saves zoo report to txt file
    public void saveZooDetails(){
        try{
            FileWriter fWriter = new FileWriter("ZooDetails.txt");
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            bWriter.write("Belfast Zoo Report");
            bWriter.newLine();

            int dogCount = 0;
            int birdCount = 0;
            int monkeyCount = 0;

            for(Animal a: animals){
                if(a instanceof Dog){
                    dogCount++;
                }
                else if(a instanceof Bird){
                    birdCount++;
                }
                else if(a instanceof Monkey){
                    monkeyCount++;
                }
            }
            bWriter.write("Total Number of Animal(s): " + animals.size());
            bWriter.newLine();
            bWriter.write("Total number of dog(s): " + dogCount);
            bWriter.newLine();
            bWriter.write("Total number of bird(s): " + birdCount);
            bWriter.newLine();
            bWriter.write("Total number of monkey(s): " + monkeyCount);
            bWriter.newLine();

            HashMap<String, Integer> colourCount = new HashMap<>();
            for(Animal a : animals){
                String colour = a.getColour();
                colourCount.put(colour, colourCount.getOrDefault(colour, 0)+1 ); // +1 since it starts at 0
            }

            String dominantColour = "";
            int max = 0;
            for(String colour : colourCount.keySet()){
                int count = colourCount.get(colour); // gets value from hashmap

                if(count > max){
                    max = count;
                    dominantColour = colour;
                }
            } // end for

            bWriter.write("Zoo's most dominant colour: " + dominantColour);
            bWriter.close();

            System.out.println("Zoo details saved successfully!");

        }
        catch(IOException e){
            System.out.println("Error saving zoo details...");
        }
    }


    // runs at start of program, loading previous sessions' details
    public void loadZooDetails(){
        try{
            File file = new File("ZooDetails.txt");
            Scanner scanner = new Scanner(file);

            System.out.println("Loading zoo details...");

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
            }
            scanner.close();
        }
        catch(IOException e){
            System.out.println("No zoo details found...");
        }
    } // end method


    // assure user they want to delete ALL animal data before doing so.
    // overwrites both txt files with a singular "" blank string.
    public void clearZoo(Scanner input){
        System.out.println("----------------------------------------------------------------------"); // design
        input.nextLine(); // clear input

        System.out.print("Are you sure you want to clear all animals from the zoo? (d to delete): ");
        String choice = input.nextLine();

        if(choice.equalsIgnoreCase("d")){
            animals.clear();

            try{
                FileWriter fWriter = new FileWriter("AnimalDetails.txt");
                fWriter.write(""); // replaces all data in the file with a blank string
                fWriter.close();

                FileWriter zooWriter = new FileWriter("ZooDetails.txt");
                zooWriter.write("");
                zooWriter.close();

                System.out.println("Zoo cleared successfully!");
            }
            catch(IOException e){
                System.out.println("An error occurred clearing zoo data...");
            }
        }
        else{
            System.out.println("Zoo clearance cancelled...");
        }
    }
}