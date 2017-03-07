package benchmark;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

/**
 * The IBenchmark interface contains the basic functions needed to
 * implement the benchmark functionality for a specified class.
 */
public interface IBenchmark {

    /**
     * The warmup class will be used warmup the system before the benchmark test so that the benchmark
     * does not measure the java orver-head in the benchmark.
     * @throws OperationNotSupportedException When the method is not available for use.
     * @throws IOException When there is a IO problem.
     */
    void warmup() throws OperationNotSupportedException, IOException;

    /**
     * This will initialize the benchmark with the default values.
     * @throws OperationNotSupportedException When the method is not available for use.
     */
    void initialize() throws OperationNotSupportedException;

    /**
     * This will run the benchmark.
     * @throws OperationNotSupportedException When the method is not available for use.
     * @throws IOException When there is a IO problem.
     */
    void run() throws OperationNotSupportedException, IOException;

    /**
     * This will run the benchmark.
     * @param parameters it takes a variable number of parameters.
     * @throws OperationNotSupportedException When the method is not available for use.
     */
    void run(Object ... parameters) throws OperationNotSupportedException;

    /**
     * This will clean up after the benchmark was run.
     * @throws OperationNotSupportedException When the method is not available for use.
     */
    void clean() throws OperationNotSupportedException;
}
