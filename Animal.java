public abstract class Animal {

    private String name;
    private int age;
    private String colour;
    private int weight;

    public Animal(String name, int age, String colour, int weight){
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    // ensures all subclasses use this method
    public abstract String makeSound();

    // used when reporting animal data
    public String toString(){
        return  "Name: " + name +
                "\nAge: " + age +
                "\nColour: " + colour +
                "\nWeight: " + weight;
    }

    // used to format data before saving to animal txt file
    public String toFileString() {
        return name + "," + age + "," + colour + "," + weight;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }


    //getters
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getColour(){
        return colour;
    }

    public int getWeight(){
        return weight;
    }
}
