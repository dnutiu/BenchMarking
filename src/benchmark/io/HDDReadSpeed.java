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

import benchmark.IBenchmark;

import java.io.IOException;

public class HDDReadSpeed implements IBenchmark {
	private int bufferSize;

	@Override
	public void initialize(int size) {
		this.bufferSize = size;
	}

	@Override
	public void warmUp() {
	}

	@Override
	public void run() {
	}

	@Override
	public void run(Object... options) {

		try {
			ReadOptions option = (ReadOptions) options[0];
			FileReader reader = new FileReader();

			int minBufferSize = 1024; // 1KB
			int maxBufferSize = 4 * 1024 * 1024; // 4MB
			String sourcePath = "uselessFiles/bigFile";

//
			switch (option) {
			case STREAM:
				reader.streamReadFixedSize(sourcePath, minBufferSize, maxBufferSize);
				break;
			case NIO:
				reader.nIOReadFixedSize(sourcePath, minBufferSize, maxBufferSize);
				break;
			case COMPARE:
			    reader.compareNIO("uselessFiles/bigFkingFile", "uselessFiles/bigFkingFile2", this.bufferSize * 1024);
			    break;
			case COMPARESTREAM:
				reader.compareWithBufferSize("uselessFiles/bigFkingFile", "uselessFiles/bigFkingFile2", this.bufferSize * 1024);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clean() {
	}

	@Override
	public String getResult() {
		return null;
	}

	public enum ReadOptions {
		STREAM,
		NIO,
        COMPARE,
		COMPARESTREAM
	}

}
