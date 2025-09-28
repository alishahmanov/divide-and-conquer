package assignment1;

import java.io.IOException;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        if (args.length < 2) {
            printUsageAndExit();
        }

        String algo = args[0].toLowerCase();
        int n = parseInt(args[1], "n должно быть целым положительным");
        if (n < 0) die("n должно быть неотрицательным");

        int runs = 1;
        int k = Math.max(0, n / 2);
        String out = "results.csv";
        long seed = System.currentTimeMillis();

        for (int i = 2; i < args.length; i++) {
            switch (args[i]) {
                case "--runs":
                    i++; runs = parseIntOrDie(args, i, "--runs требует число");
                    break;
                case "--k":
                    i++; k = parseIntOrDie(args, i, "--k требует число (0-based)");
                    break;
                case "--out":
                    i++; if (i >= args.length) die("--out требует путь к файлу");
                    out = args[i];
                    break;
                case "--seed":
                    i++; seed = parseLongOrDie(args, i, "--seed требует число (long)");
                    break;
                default:
                    printUsageAndExit("Неизвестный параметр: " + args[i]);
            }
        }

        if (algo.equals("select")) {
            if (n == 0) die("Для select требуется n > 0");
            if (k < 0 || k >= n) die("--k должен быть в диапазоне [0, n-1]");
        }

        System.out.printf("Алгоритм: %s | n=%d | runs=%d | out=%s | seed=%d%n", algo, n, runs, out, seed);

        try {
            CSVWriter csv = new CSVWriter(out);
            try {
                switch (algo) {
                    case "mergesort":
                        runMergeSort(n, runs, seed, csv);
                        break;
                    case "quicksort":
                        runQuickSort(n, runs, seed, csv);
                        break;
                    case "select":
                        runDeterministicSelect(n, k, runs, seed, csv);
                        break;
                    case "closest":
                        runClosestPair(n, runs, seed, csv);
                        break;
                    default:
                        csv.close();
                        printUsageAndExit("Неизвестный алгоритм: " + algo);
                }
            } finally {
                csv.close();
            }
            System.out.println("Готово. Результаты записаны в: " + out);
        } catch (IOException e) {
            e.printStackTrace();
            die("Ошибка записи CSV: " + e.getMessage());
        }
    }

    private static void runMergeSort(int n, int runs, long seed, CSVWriter csv) throws IOException {
        Random rnd = new Random(seed);
        SortInterface sorter = new MergeSort();

        for (int r = 0; r < runs; r++) {
            int[] a = rnd.ints(n, 0, Math.max(1, n * 10)).toArray();
            Counter counter = new Counter();

            long t0 = System.nanoTime();
            sorter.sort(a, counter);
            long t1 = System.nanoTime();

            long ms = (t1 - t0) / 1_000_000;
            csv.writeRow(n, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth(), ms);
            System.out.printf("[run %d] mergesort: time=%d ms, cmp=%d, alloc=%d, depth=%d%n",
                    r + 1, ms, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth());
        }
    }

    private static void runQuickSort(int n, int runs, long seed, CSVWriter csv) throws IOException {
        Random rnd = new Random(seed);
        SortInterface sorter = new QuickSort();

        for (int r = 0; r < runs; r++) {
            int[] a = rnd.ints(n, 0, Math.max(1, n * 10)).toArray();
            Counter counter = new Counter();

            long t0 = System.nanoTime();
            sorter.sort(a, counter);
            long t1 = System.nanoTime();

            long ms = (t1 - t0) / 1_000_000;
            csv.writeRow(n, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth(), ms);
            System.out.printf("[run %d] quicksort: time=%d ms, cmp=%d, alloc=%d, depth=%d%n",
                    r + 1, ms, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth());
        }
    }

    private static void runDeterministicSelect(int n, int k, int runs, long seed, CSVWriter csv) throws IOException {
        Random rnd = new Random(seed);
        DeterministicSelect ds = new DeterministicSelect();

        for (int r = 0; r < runs; r++) {
            int[] a = rnd.ints(n, 0, Math.max(1, n * 10)).toArray();
            Counter counter = new Counter();

            long t0 = System.nanoTime();
            int value = ds.select(a, k, counter);
            long t1 = System.nanoTime();

            long ms = (t1 - t0) / 1_000_000;
            csv.writeRow(n, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth(), ms);
            System.out.printf("[run %d] select(k=%d): val=%d, time=%d ms, cmp=%d, alloc=%d, depth=%d%n",
                    r + 1, k, value, ms, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth());
        }
    }

    private static void runClosestPair(int n, int runs, long seed, CSVWriter csv) throws IOException {
        if (n < 2) die("Для closest требуется как минимум 2 точки (n >= 2)");
        Random rnd = new Random(seed);

        for (int r = 0; r < runs; r++) {
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(rnd.nextDouble() * 1_000_000.0, rnd.nextDouble() * 1_000_000.0);
            }

            Counter counter = new Counter();
            long t0 = System.nanoTime();
            double d = ClosestPair.findClosest(pts, counter);
            long t1 = System.nanoTime();

            long ms = (t1 - t0) / 1_000_000;
            csv.writeRow(n, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth(), ms);
            System.out.printf("[run %d] closest: d=%.6f, time=%d ms, cmp=%d, alloc=%d, depth=%d%n",
                    r + 1, d, ms, counter.getNumberOfComparisons(), counter.getAllocation(), counter.getMaxDepth());
        }
    }

    private static void printUsageAndExit() {
        printUsageAndExit(null);
    }

    private static void printUsageAndExit(String msg) {
        if (msg != null) System.err.println(msg);
        System.err.println("Usage:");
        System.err.println("  java -jar target/divide-and-conquer-1.0-SNAPSHOT.jar <algo> <n> [--runs R] [--k K] [--out file.csv] [--seed S]");
        System.err.println("  <algo> ∈ { mergesort | quicksort | select | closest }");
        System.err.println("  <n>    размер массива / число точек");
        System.err.println("  --runs  сколько прогонов (default 1)");
        System.err.println("  --k     для select: порядковая статистика (0..n-1), default n/2");
        System.err.println("  --out   путь к csv (default results.csv)");
        System.err.println("  --seed  зерно ГПСЧ (default: текущее время)");
        System.exit(1);
    }

    private static void die(String msg) {
        System.err.println("Ошибка: " + msg);
        System.exit(1);
    }

    private static int parseInt(String s, String err) {
        try { return Integer.parseInt(s); } catch (Exception e) { die(err); return -1; }
    }

    private static int parseIntOrDie(String[] args, int i, String err) {
        if (i >= args.length) die(err);
        return parseInt(args[i], err);
    }

    private static long parseLongOrDie(String[] args, int i, String err) {
        if (i >= args.length) die(err);
        try { return Long.parseLong(args[i]); } catch (Exception e) { die(err); return -1L; }
    }
}
