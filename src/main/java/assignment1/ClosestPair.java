package assignment1;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points, Counter counter) {
        Point[] px = points.clone();
        Point[] py = points.clone();

        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return closestUtil(px, py, points.length, counter);
    }

    private static double closestUtil(Point[] px, Point[] py, int n, Counter counter) {
        if (n <= 3) {
            return bruteForce(px, n, counter);
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid];
        Point[] pyr = new Point[n - mid];
        int li = 0, ri = 0;

        for (int i = 0; i < n; i++) {
            if (py[i].x <= midPoint.x && li < mid) {
                pyl[li++] = py[i];
            } else {
                pyr[ri++] = py[i];
            }
        }

        double dl = closestUtil(Arrays.copyOfRange(px, 0, mid), pyl, mid, counter);
        double dr = closestUtil(Arrays.copyOfRange(px, mid, n), pyr, n - mid, counter);

        double d = Math.min(dl, dr);

        return Math.min(d, stripClosest(py, n, midPoint.x, d, counter));
    }

    private static double stripClosest(Point[] py, int n, double midX, double d, Counter counter) {
        Point[] strip = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(py[i].x - midX) < d) {
                strip[j++] = py[i];
            }
        }

        double min = d;
        for (int i = 0; i < j; ++i) {
            for (int k = i + 1; k < j && (strip[k].y - strip[i].y) < min; ++k) {
                counter.increaseCompare();
                min = Math.min(min, dist(strip[i], strip[k]));
            }
        }
        return min;
    }

    private static double bruteForce(Point[] points, int n, Counter counter) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                counter.increaseCompare();
                min = Math.min(min, dist(points[i], points[j]));
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}
