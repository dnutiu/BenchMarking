package benchmark;

/**
 * The IBenchmark interface contains the basic functions needed to
 * implement the benchmark functionality for a specified class.
 */
public interface IBenchmark {
    /**
     * This will initialize the benchmark with the default values.
     */
    void initialize();

    /**
     * This will run the benchmark.
     */
    void run();

    /**
     * This will run the benchmark.
     * @param parameters it takes a variable number of parameters.
     */
    void run(Object ... parameters);

    /**
     * This will clean up after the benchmark was run.
     */
    void clean();
}
