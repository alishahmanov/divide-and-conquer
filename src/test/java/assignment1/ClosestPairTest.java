package assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testSmallSet() {
        Point[] points = {
                new Point(2, 3),
                new Point(1, 4),
                new Point(4, 5),
                new Point(5, 6),
                new Point(9, 8),
                new Point(2, 5)
        };
        Counter counter = new Counter();
        double result = ClosestPair.findClosest(points, counter);

        double expected = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = Math.sqrt(
                        (points[i].x - points[j].x) * (points[i].x - points[j].x) +
                                (points[i].y - points[j].y) * (points[i].y - points[j].y)
                );
                expected = Math.min(expected, dist);
            }
        }

        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testTwoPoints() {
        Point[] points = { new Point(0, 0), new Point(3, 4) };
        Counter counter = new Counter();
        double result = ClosestPair.findClosest(points, counter);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    public void testIdenticalPoints() {
        Point[] points = { new Point(1, 1), new Point(1, 1), new Point(2, 2) };
        Counter counter = new Counter();
        double result = ClosestPair.findClosest(points, counter);
        assertEquals(0.0, result, 1e-9);
    }
}
