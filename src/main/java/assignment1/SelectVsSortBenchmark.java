package assignment1;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBenchmark {

    private int[] data;
    private int k;
    private Random random;

    @Setup(Level.Invocation)
    public void setUp() {
        random = new Random();
        data = random.ints(10000, 0, 1_000_000).toArray(); // массив из 10k элементов
        k = data.length / 2; // ищем медиану
    }

    @Benchmark
    public int testDeterministicSelect() {
        DeterministicSelect select = new DeterministicSelect();
        return select.select(Arrays.copyOf(data, data.length), k, new Counter());
    }

    @Benchmark
    public int testQuickSort() {
        int[] arrCopy = Arrays.copyOf(data, data.length);
        QuickSort quickSort = new QuickSort();
        Counter c = new Counter();
        quickSort.sort(arrCopy, c);
        return arrCopy[k];
    }

    @Benchmark
    public int testMergeSort() {
        int[] arrCopy = Arrays.copyOf(data, data.length);
        MergeSort mergeSort = new MergeSort();
        Counter c = new Counter();
        mergeSort.sort(arrCopy, c);
        return arrCopy[k];
    }
}
