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
import benchmark.utilities.NumberRepresentation;

public class CPUFixedPointTest implements IBenchmark {

	private double result;
	private int size;

	@Override
	public void initialize() {

	}

    public void setSize(int size) {
        this.size = size;
    }

	@Override
	public void warmUp() {
		int oldsize = size;
		size = 100000;
		run(NumberRepresentation.FIXED);
		run(NumberRepresentation.FLOATING);
		size = oldsize;
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException("Use run(Object) instead");
	}

	@Override
	public void run(Object... options) {
		result = 0;
		
		switch ((NumberRepresentation) options[0]) {
		case FLOATING:
			for (int i = 0; i < size; ++i)
				result += 1 / 256.0;
			break;
		case FIXED:
			for (int i = 0; i < size; ++i)
				result += 1 >> 8;
			break;
		default:
			break;
		}
	}

	@Override
	public void clean() {
		throw new UnsupportedOperationException("This method is not implemented");
	}

	public String getResult() {
		return String.valueOf(result);
	}

}
