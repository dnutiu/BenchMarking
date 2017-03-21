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

public class CPURecursionLoopUnrolling implements IBenchmark {

    private int size;
    private int calls;
    private long result;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
        recursive(1, 1000);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Not implemented. Use run with options instead.");
    }

    @Override
    public void run(Object... options) {
        int unrolled = (Integer) options[0];
        int unrollLevel = 0;
        if (unrolled == 1) {
            unrollLevel = (Integer) options[1];
            try {
                this.result = this.recursiveUnrolled(2, unrollLevel, this.size);
            } catch (StackOverflowError e) {
                System.out.println("Exception occured, number of calls: " + this.calls);
            }

        } else {
            this.result = this.recursive(2, this.size);
        }
    }

    private long recursive(long i, long n) {
        this.calls += 1;
        long prime = findNextPrime(i);

        if (prime >= n)
            return 0;

        try {
            return prime + recursive(prime + 1, n);
        } catch (StackOverflowError e) {
            System.out.println("Exception prime: " + prime);
            return 0;
        }
    }

    private long recursiveUnrolled(long i, int l, int n) throws StackOverflowError {
        this.calls += 1;
        long prime = i;
        this.result = 0;

        if (i >= n) {
            return 0;
        }

        for (int j = 0; j < l; ++j) {
            prime = this.findNextPrime(prime);
//            result += prime;
        }

        return prime + this.recursiveUnrolled(prime + 1, l, n);
    }

    private long findNextPrime(long n) {
        long result = n;
        while (!isPrime(result)) {
            ++result;
        }
        this.calls += 1;
        return result;
    }

    /**
     * Returns true of false if the number is prime
     */
    boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            return true;
        } else if ((n % 2 == 0) || (n % 3 == 0)) {
            return false;
        }
        long i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }

    @Override
    public void clean() {
        this.result = 0;
        this.calls = 0;
    }

    @Override
    public String getResult() {
        return String.valueOf(this.result);
    }

}
