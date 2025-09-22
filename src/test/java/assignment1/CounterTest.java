package assignment1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CounterTest {
    @Test
    public void testComparisons() {
        Counter counter = new Counter();
        counter.increaseCompare();
        counter.increaseCompare();
        assertEquals(2, counter.getNumberOfComparisons(), "Должно быть два сравнения");
    }

    @Test
    public void testMemory() {
        Counter counter = new Counter();
        counter.increaseMemory();
        assertEquals(1,counter.getAllocation(), "Должна быть 1 аллокация");

    }
    @Test
    public void testRecursionDepth() {
        Counter counter = new Counter();
        counter.startOfRecursion();
        counter.startOfRecursion();
        counter.endOfRecursion();
        counter.endOfRecursion();
        assertEquals(2, counter.getMaxDepth(), "Максимальная глубина должна быть 2");
    }
}