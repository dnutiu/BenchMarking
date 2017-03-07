package benchmark;
import logger.ConsoleLogger;
import logger.ILogger;
import logger.TimeUnit;
import timer.Timer;

/**
 * Class used to test the power of the CPU by computing the digits of PI.
 */
public class CPUDigitsOfPIBenchmark implements IBenchmark {
    private ILogger logger;
    private Timer timer;
    private int numberOfDigits;

    /**
     * Returns the number of digits used to compute PI in the current becnhmark instance.
     * @return the number of digits.
     */
    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    /**
     * Sets the number of digits to use when computing PI.
     * The benchmark does not need to be re-initialized.
     * @param numberOfDigits The number of digits.
     */
    public void setNumberOfDigits(int numberOfDigits) {
        this.numberOfDigits = numberOfDigits;
    }

    /**
     * Computes PI to the n'th digit.
     * @param n The number of digits to compute PI.
     */
    private void computeDigitsOfPi(int n) {
        Pi piComputation = new Pi(n);
        piComputation.execute();
    }

    @Override
    public void initialize() {
        this.logger = new ConsoleLogger();
        this.timer  = new Timer();
        this.numberOfDigits = 10000;
    }

    @Override
    public void run() {
        logger.write("Starting CPU Benchmark: Computing PI");

        timer.start();
        this.computeDigitsOfPi(this.numberOfDigits);
        timer.stop();

        logger.write("The time it took to compute " + this.numberOfDigits + " digits of PI: "
                + TimeUnit.converNanosecondTo(timer.getTimePassed(), TimeUnit.MILISECOND) + " ms.");

        logger.write("CPU Benchmark finished.");
    }

    @Override
    public void run(Object... parameters) {

    }

    @Override
    public void clean() {

    }
}
