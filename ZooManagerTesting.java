import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZooManagerTesting {


    // test makeSound() outputs the correct individual details
    @Test
    public void testMakeSound() {

        Dog d = new Dog("Jim", 5, "Black", 18, 3);

        String expected = "Woof! I am Jim, a 5 year old dog.";
        String actual = d.makeSound();

        assertEquals(expected, actual);
    }


    // test sound level only accepts "low", "medium" and "high"
    @Test
    public void testMonkeyInvalidSoundLevel() {
        // Invalid sound level - should not be set
        Monkey m = new Monkey("Jack", 7, "Brown", 15, "ultra");
        assertNull(m.getSoundLevel());
    }


    // test toFileString() returns the correct format for saving to the file
    @Test
    public void testBirdToFileString() {
        Bird b = new Bird("tweety", 3, "blue", 1, 31);
        assertEquals("Bird,tweety,3,blue,1,31", b.toFileString());
    }

} //end class
