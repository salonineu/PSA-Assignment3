package edu.neu.coe.info6205.sort.elementary;
import com.google.common.collect.Lists;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class BenchmarkInsertion {
    private static final int REPETITION = 100;
    private static final int FROM = -1000;
    private static final int TO = 1000;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        final List<Integer[]> randNumList = Lists.newArrayList(
                randomNumGen(50),
                randomNumGen(100),
                randomNumGen(200),
                randomNumGen(400),
                randomNumGen(800),
                randomNumGen(1600),
                randomNumGen(3200),
                randomNumGen(6400),
                randomNumGen(12800),
                randomNumGen(25600)
        );
        for (Integer[] randomlyGenNo : randNumList) {
            System.out.println("-------START--------");
            System.out.println("No of element, N: " + randomlyGenNo.length);
            Arrays.sort(randomlyGenNo, Collections.reverseOrder());
            benchmarkCallTimerFunction("Reverse Order Array", randomlyGenNo);
            benchmarkCallTimerFunction("Random Order Array", randomlyGenNo);
            Arrays.sort(randomlyGenNo, 0, (randomlyGenNo.length) / 2);
            benchmarkCallTimerFunction("Partial Order Array", randomlyGenNo);
            Arrays.sort(randomlyGenNo);
            benchmarkCallTimerFunction("In-Order Array", randomlyGenNo);
            System.out.println("----END-----");
        }
    }

    private static Integer[] randomNumGen(int k) {
        Integer[] randomNumbers = new Integer[k];
        for (int i = 0; i < k; i++) {
            randomNumbers[i] = RANDOM.nextInt(TO + 1 - FROM) + FROM;
        }
        return randomNumbers;
    }

    public static void benchmarkCallTimerFunction(final String description, final Integer[] inArr) {
        final Consumer<Integer[]> sortUsingInsertion = InsertionSort::sort;
        final Supplier<Integer[]> supplier = () -> Arrays.copyOf(inArr, inArr.length);
        final Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(description, null, sortUsingInsertion, null);
        final double meanOfArraySortingTimeInMilliSeconds = benchmark_timer.runFromSupplier(supplier, REPETITION);
        System.out.println(description + " : mean sort time (in ms): " + meanOfArraySortingTimeInMilliSeconds);
    }
}
