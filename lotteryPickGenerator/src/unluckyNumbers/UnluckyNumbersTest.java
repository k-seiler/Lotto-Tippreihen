package unluckyNumbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class UnluckyNumbersTest {

    @Test
    void test() {
        UnluckyNumbers unluckyNumbers = new UnluckyNumbers();
        unluckyNumbers.clearNumbers();

        assertTrue(unluckyNumbers.isEmpty());

        try {
            unluckyNumbers.addNumber("13");
            unluckyNumbers.addNumber("7");
            unluckyNumbers.addNumber("");
            unluckyNumbers.addNumber("24");
            unluckyNumbers.addNumber("7");
            unluckyNumbers.addNumber("text");
            unluckyNumbers.addNumber("81");
            unluckyNumbers.addNumber("1");
            unluckyNumbers.addNumber("33");
            unluckyNumbers.addNumber("17");
            unluckyNumbers.addNumber("2");
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(unluckyNumbers.getNumbers().get(0), 1);
        assertEquals(unluckyNumbers.getNumbers().get(1), 7);
        assertEquals(unluckyNumbers.getNumbers().get(2), 13);
        assertEquals(unluckyNumbers.getNumbers().get(3), 17);
        assertEquals(unluckyNumbers.getNumbers().get(4), 24);
        assertEquals(unluckyNumbers.getNumbers().get(5), 33);

        assertEquals(unluckyNumbers.getNumbers().size(), 6);

        try {
            unluckyNumbers.remove("");
            unluckyNumbers.remove("7");
            unluckyNumbers.remove("55");
            unluckyNumbers.remove("33");
            unluckyNumbers.remove("test");
            unluckyNumbers.remove("1");
            unluckyNumbers.remove("17");
            unluckyNumbers.remove("33");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(unluckyNumbers.getNumbers().get(0), 13);
        assertEquals(unluckyNumbers.getNumbers().get(1), 24);
        assertEquals(unluckyNumbers.getNumbers().size(), 2);

        assertTrue(unluckyNumbers.isUnluckyNumber(13));
        assertFalse(unluckyNumbers.isUnluckyNumber(33));

    }

}