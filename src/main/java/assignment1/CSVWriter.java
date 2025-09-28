package assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter implements AutoCloseable {
    private final FileWriter writer;

    public CSVWriter(String filename) throws IOException {
        File file = new File(filename);
        boolean append = file.exists() && file.length() > 0;
        writer = new FileWriter(file, true);

        if (!append) {
            writer.write("n,comparisons,allocations,maxDepth,timeMs\n");
            writer.flush();
        }
    }

    public void writeRow(int n, long comparisons, long allocations, int maxDepth, long timeMs) throws IOException {
        writer.write(n + "," + comparisons + "," + allocations + "," + maxDepth + "," + timeMs + "\n");
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
