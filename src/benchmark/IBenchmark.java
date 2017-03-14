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

package benchmark;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

/**
 * The IBenchmark interface contains the basic functions needed to
 * implement the benchmark functionality for a specified class.
 */
public interface IBenchmark {

    /**
     * The warmUp class will be used warmUp the system before the benchmark test so that the benchmark
     * does not measure the java orver-head in the benchmark.
     * @throws OperationNotSupportedException When the method is not available for use.
     * @throws IOException When there is a IO problem.
     */
    void warmUp() throws OperationNotSupportedException, IOException;

    /**
     * This will initialize the benchmark with the default values.
     * @throws OperationNotSupportedException When the method is not available for use.
     */
    void initialize(int value) throws OperationNotSupportedException;

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
