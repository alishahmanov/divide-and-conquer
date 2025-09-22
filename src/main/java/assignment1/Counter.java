package assignment1;

public class Counter {
    private long numberOfComparisons;
    private long allocation;
    private int maxDepth;
    private int currentDepth;

    public void increaseCompare() {
        numberOfComparisons++;
    }
    public void increaseMemory() {
        allocation++;
    }
    public void startOfRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }
    public void endOfRecursion() {
        currentDepth--;
    }

    // Getters
    public long getNumberOfComparisons() {
        return numberOfComparisons;
    }
    public long getAllocation() {
        return allocation;
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    public int getCurrentDepth() {
        return currentDepth;
    }
}
