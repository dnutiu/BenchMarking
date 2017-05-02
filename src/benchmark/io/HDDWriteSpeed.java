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

package benchmark.io;

import java.io.IOException;

import benchmark.IBenchmark;

public class HDDWriteSpeed implements IBenchmark {

    @Override
    public void initialize(int size) {
    }

    @Override
    public void warmUp() {
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Object) instead");
    }

    @Override
    public void run(Object... options) {
        FileWriter writer = new FileWriter();
        /*
            Fixed size
		 */
        // either "fs" - fixed size, or "fb" - fixed buffer
        String option = (String) options[0];
        // true/false whether the written files should be deleted at the end
        Boolean clean = (Boolean) options[1];

        int bufferSize = 1024;
        if (options.length >= 3) {
            bufferSize = (Integer) options[2];
        }

        int dasFileSize = 128;
        if (options.length >= 4) {
            bufferSize = (Integer) options[3];
        }

        String prefix = "uselessFiles/testFile";
        String suffix = ".dat";
        int startIndex = 0;
        int endIndex = 8;

        try {
            if (option.equals("fs"))
                writer.streamWriteFixedSize(prefix, suffix, startIndex,
                        endIndex, 512 * 1024 * 1024, clean);
            else if (option.equals("fb"))
                writer.streamWriteFixedBuffer(prefix, suffix, startIndex,
                        endIndex, 128 * 1024 * 1024, bufferSize, clean);
            else
                throw new IllegalArgumentException("Argument "
                        + options[0].toString() + " is undefined");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
        // Delete files automatically.

    }

    @Override
    public String getResult() {
        return null;
    }
}
