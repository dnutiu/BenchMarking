package tests;

import benchmark.CPUDigitsOfPIBenchmark;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

/**
 * The test class for the CPU Benchmark: Digits of PI.
 */
public class CPUDigitsOfPIBenchmarkTest {
    private CPUDigitsOfPIBenchmark benchmark;

    @Before
    public void setUp() {
        benchmark = new CPUDigitsOfPIBenchmark();
        benchmark.initialize();
    }

    @Test
    public void getNumberOfDigits() throws Exception {
        assertEquals("Get number of digits should return the default number of digits.",
                benchmark.getNumberOfDigits(), 10000);

        benchmark.setNumberOfDigits(2);

        assertEquals("Get number of digits should return correct digits after the digits have been updated.",
                benchmark.getNumberOfDigits(), 2);
    }

    @Test
    public void setNumberOfDigits() throws Exception {
        benchmark.setNumberOfDigits(5);
        assertEquals("Get number of digits should return correct digits after the digits have been updated.",
                 5, benchmark.getNumberOfDigits());

        benchmark.setNumberOfDigits(-1);
        assertEquals("Get number of digits should return correct digits after the digits have been updated.",
                1, benchmark.getNumberOfDigits());
    }

    @Test
    public void initialize() throws Exception {
        assertEquals("Get number of digits should return the default number of digits.",
                benchmark.getNumberOfDigits(), 10000);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void clean() throws OperationNotSupportedException {
        benchmark.clean();
    }

    @Test
    public void run() throws OperationNotSupportedException {
        benchmark.setNumberOfDigits(1);
        benchmark.run();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void runWithParams() throws OperationNotSupportedException {
        benchmark.run(1);
    }

    @Test
    public void warmUp() {
        benchmark.warmUp();
    }
}