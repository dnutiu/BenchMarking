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

package tests;

import benchmark.cpu.CPUDigitsOfPIBenchmark;
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
        benchmark.initialize(10000);
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