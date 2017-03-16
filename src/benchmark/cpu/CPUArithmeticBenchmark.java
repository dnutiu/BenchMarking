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

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

/**
 * Will stress the CPU by performing various arithmetic operations.
 */
public class CPUArithmeticBenchmark implements IBenchmark {
    private int size;

    @Override
    public void warmUp() {
        int oldSize = size;
        size = 10000000;
        this.run();
        size = oldSize;
    }

    @Override
    public void initialize(int value) {
        this.size = value;
    }

    @Override
    public void run() {
        float result = 0;
        for (int i = 0; i < size; ++i) {
            result += 1;
            result *= 2;
            result /= 3;
            result *= result;
        }
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Please use run() instead!");
    }

    public long getResults() {
        return this.size * 4; // nano Flops?
    }

    @Override
    public void clean() {

    }
}
