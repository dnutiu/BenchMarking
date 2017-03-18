/*
 * Copyright (c) 2017 Nutiu Denis-Cosmin <denis.nutiu@gmail.com>
 *
 * This file is part of BenchMarking.
 *
 * BenchMarking is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BenchMarking is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package benchmark.cpu;
import benchmark.IBenchmark;
import benchmark.utilities.Pi;

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
        if (numberOfDigits > 0) {
            this.numberOfDigits = numberOfDigits;
        } else {
            this.numberOfDigits = 1;
        }
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
    public void warmUp(){
        this.computeDigitsOfPi(10000);
    }

    @Override
    public void initialize(int value) {
        this.numberOfDigits = value;
    }

    @Override
    public void run() {
        this.computeDigitsOfPi(this.numberOfDigits);
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Method not implemented!");
    }

    @Override
    public String getResult() {
        return String.valueOf(this.numberOfDigits);
    }

    @Override
    public void clean() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Method not implemented!");
    }
}
