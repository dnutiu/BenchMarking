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

public class CPURecursionVsLooping implements IBenchmark {

    public enum Looping {
        RECURSIVE, ITERATIVE
    }

    private int size;
    private long result;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
        int oldsize = this.size;
        this.size = 1000;
        this.run(Looping.ITERATIVE);
        this.run(Looping.RECURSIVE);
        this.size = oldsize;
    }

    @Override
    @Deprecated
    public void run() {
        throw new UnsupportedOperationException(
                "Not implemented. Use run with options instead.");
    }

    @Override
    public void run(Object... options) {
        if (options.length == 1) {
            if (options[0] instanceof Looping) {
                switch ((Looping) options[0]) {
                    case ITERATIVE:
                        result = doSomethingIterative(this.size);
                        break;
                    case RECURSIVE:
                        result = doSomethingRecursive(this.size);
                        break;
                }
            } else {
                throw new IllegalArgumentException(
                        "Argument must be of type 'Looping'");
            }
        } else {
            throw new IllegalArgumentException(
                    "Benchmark needs only one argument of type 'Looping'");
        }
    }

    private long doSomethingIterative(long num) {
        long result = 1;
        for (int i = 1; i <= num; ++i) {
            result *= i;
        }
        return result;
    }

    private long doSomethingRecursive(long num) {
        if (num == 0) {
            return 1;
        } else if (num == 1) {
            return 1;
        } else {
            return num * doSomethingRecursive(num - 1);
        }
    }

    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }
}
