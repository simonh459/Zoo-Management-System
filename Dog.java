public class Dog extends Animal {

    private int friendliness;

    public Dog(String name, int age, String colour, int weight, int friendliness){
        super(name, age, colour, weight); // calls variables from parent class
        this.friendliness = friendliness;
    }

    @Override
    public String makeSound() {
        return "Woof! I am " + getName() + ", a " + getAge() + " year old dog.";
    }

    @Override
    public String toString(){
        return   "Name: " + getName() +
                "\n Age: " + getAge() +
                "\n Colour: " + getColour() +
                "\n Weight: " + getWeight() +
                "\n Friendliness: " + getFriendliness();
    }

    @Override
    public String toFileString() {
        return "Dog," + super.toFileString() + "," + getFriendliness();
    }

    //setter
    public void setFriendliness(int friendliness){
        this.friendliness = friendliness;
    }

    //getter
    public int getFriendliness(){
        return friendliness;
    }
}
