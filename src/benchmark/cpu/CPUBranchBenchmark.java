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

/**
 * This class will stress the CPU by recursion.
 */
public class CPUBranchBenchmark implements IBenchmark {
    private int numberOfBranches;

    private void branch(int n) {
        if (n == 0) return;
        this.branch(n - 1);
    }

    @Override
    public void warmUp() {
        int old = numberOfBranches;
        numberOfBranches = 10000;
        run();
        numberOfBranches = old;
    }

    @Override
    public void initialize(int value) {
        if (value > 0) {
            this.numberOfBranches = value;
        }
    }

    @Override
    public void run() {
        this.branch(this.numberOfBranches);
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Please use run() instead");
    }

    @Override
    public String getResult() {
        return String.valueOf(this.numberOfBranches);
    }

    @Override
    public void clean() throws OperationNotSupportedException {

    }
}
