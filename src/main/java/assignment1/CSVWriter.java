package assignment1;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final FileWriter writer;

    public CSVWriter(String filename) throws IOException {
        writer = new FileWriter(filename);
        writer.write("n,comparisons,allocations,maxDepth,timeMs\n");
    }

    public void writeRow(int n, long comparisons, long allocations, int maxDepth, long timeMs) throws IOException {
        writer.write(n + "," + comparisons + "," + allocations + "," + maxDepth + "," + timeMs + "\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
