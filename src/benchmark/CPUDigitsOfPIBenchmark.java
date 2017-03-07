package benchmark;
import javax.naming.OperationNotSupportedException;

/**
 * Class used to test the power of the CPU by computing the digits of PI.
 */
public class CPUDigitsOfPIBenchmark implements IBenchmark {
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
        this.numberOfDigits = 10000;
    }

    @Override
    public void run() {
        this.computeDigitsOfPi(this.numberOfDigits);
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {}

    @Override
    public void clean() throws OperationNotSupportedException {}
}
