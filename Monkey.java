public class Monkey extends Animal {

    private String soundLevel;

    public Monkey(String name, int age, String colour, int weight, String soundLevel){
        super(name, age, colour, weight);
        setSoundLevel(soundLevel);
    }

    @Override
    public String makeSound() {
        return "oo-oo-aa-aa! I am " + getName() + ", a " + getAge() + " year old monkey.";
    }

    @Override
    public String toString(){
        return  "Name: " + getName() +
                "\n Age: " + getAge() +
                "\n Colour: " + getColour() +
                "\n Weight: " + getWeight() +
                "\n Sound Level: " + getSoundLevel();
    }

    @Override
    public String toFileString() {
        return "Monkey," + super.toFileString() + "," + getSoundLevel();
    }

    public void setSoundLevel(String soundLevel){
        if(soundLevel.equalsIgnoreCase("low") ||
                soundLevel.equalsIgnoreCase("medium") ||
                soundLevel.equalsIgnoreCase("high")){
            this.soundLevel = soundLevel.toLowerCase();
        }
        else{
            System.out.println("Invalid option entered (choose one of the three options!)");
        }
    }

    public String getSoundLevel(){
        return soundLevel;
    }
}
