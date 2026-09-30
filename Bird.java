public class Bird extends Animal implements Flyable {

    private int wingspan;

    public Bird(String name, int age, String colour, int weight, int wingspan){
        super(name, age, colour, weight);
        this.wingspan = wingspan;
    }

    @Override
    public String makeSound() {
        return "chirp! I am " + getName() + ", a " + getAge() + " year old bird.";
    }

    @Override
    public String toString(){
        return "Name: " + getName() +
                "\n Age: " + getAge() +
                "\n Colour: " + getColour() +
                "\n Weight: " + getWeight() +
                "\n Wingspan: " + getWingspan();
    }

    @Override
    public String toFileString() {
        return "Bird," + super.toFileString() + "," + getWingspan();
    }

    public void setWingspan(int wingspan){
        this.wingspan = wingspan;
    }

    public int getWingspan(){
        return wingspan;
    }

    @Override
    public void fly(){
        System.out.println(getName() + " is flying!");
    }

    @Override
    public void checkWings(){
        System.out.println(getName() + "'s wings are healthy.");
    }

}
