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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

import timer.Timer;

public class FileReader {

	private Timer timer = new Timer();
	private double benchScore;

	/**
	 * Read file benchmark using java.io
	 */
	public void streamReadFixedSize(String fileName, int minBufSize,
			int maxBufSize) throws IOException {

		System.out.println("Stream read benchmark");
		int currentBufferSize = minBufSize;

		int counter = 0;

		while (currentBufferSize <= maxBufSize) {
			readWithBufferSize(fileName, currentBufferSize);
			currentBufferSize *= 2;
			counter++;
		}

		benchScore /= counter;
		System.out.println("File read score : "
				+ String.format("%.2f", benchScore) + " MB/sec");
	}

	/**
	 * Read file benchmark using java.nio	
	 */
	public void nIOReadFixedSize(String fileName, int minBufSize, int maxBufSize)
			throws IOException {

		System.out.println("NIO read");
		int myBufferSize = minBufSize;
		while (myBufferSize <= maxBufSize) {
			timer.start();
			long totalBytes = readNio(fileName, myBufferSize);

			if (totalBytes != -1) {
				printStats(fileName, totalBytes, myBufferSize);
			} else {
				System.out.println("File are not equal");
			}

			myBufferSize *= 2;
		}
	}

	/**
	 * Read given file with a buffered stream (io)	
	 */
	private void readWithBufferSize(String fileName, int myBufferSize)
			throws IOException {

		if (!(new File(fileName).exists()))
			throw new IOException("Benchmark file " + fileName
					+ " was not found");

		final BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File(fileName)), myBufferSize);
		byte[] buffer = new byte[myBufferSize];
		int read;

		timer.start();
		long totalbytes = 0;
		while ((read = inputStream.read(buffer)) != -1) {
			totalbytes += read;
		}
		if (read == -1) {
			printStats(fileName, totalbytes, myBufferSize);
		} else {
			System.out.println("Error reading " + fileName);
		}
		inputStream.close();
	}

	public void compareWithBufferSize(String fileName1, String fileName2,
			int myBufferSize) throws IOException {
		final BufferedInputStream inputStream1 = new BufferedInputStream(
				new FileInputStream(new File(fileName1)), myBufferSize);
		byte[] buff1 = new byte[myBufferSize];
		final BufferedInputStream inputStream2 = new BufferedInputStream(
				new FileInputStream(new File(fileName2)), myBufferSize);
		byte[] buff2 = new byte[myBufferSize];
		int read1;

		System.out.println("File compare benchmark");

		timer.start();
		long totalBytes = 0;
		while ((read1 = inputStream1.read(buff1)) != -1) {
			totalBytes += 2 * read1;
			int read2 = inputStream2.read(buff2);
			if (read1 != read2) {
				break;
			}
			if (!Arrays.equals(buff1, buff2)) {
				break;
			}
		}
		if (read1 == -1) {
			printStats(fileName1, totalBytes, myBufferSize);
		} else {
			System.out.println("Files are not equal");
		}
		inputStream1.close();
		inputStream2.close();
	}
	
	/**
	 * Read given file using a file channel (nio)	
	 */
	private long readNio(String fileName, int myBufferSize) throws IOException {
		FileChannel fChannel = null;
		long totalBytes = 0;

		try {
			fChannel = new FileInputStream(fileName).getChannel();
			ByteBuffer buffer = ByteBuffer.allocateDirect(myBufferSize);
			int read;

			while ((read = fChannel.read(buffer)) != -1) {
				buffer.clear();
				totalBytes += read;
			}
			return totalBytes;
		} finally {
			if (fChannel != null) {
				fChannel.close();
			}
		}
	}
	
	public void compareNIO(String fileName1, String fileName2, int myBufferSize)
			throws IOException {
        FileChannel fChannel1 = new FileInputStream(fileName1).getChannel();
        FileChannel fChannel2 = new FileInputStream(fileName2).getChannel();
        long totalBytes = 0;

        System.out.println("File compare benchmark using NIO with buffersize " + myBufferSize);

        ByteBuffer buffer = ByteBuffer.allocate(myBufferSize);
        ByteBuffer buffer2 = ByteBuffer.allocate(myBufferSize);

        timer.start();
        while (true) {
            int c1 = fChannel1.read(buffer);
            int c2 = fChannel2.read(buffer2);
            totalBytes += c1;
            totalBytes += c2;

            buffer.flip();
            buffer2.flip();
            try {
                while (true) {
                    byte b1 = buffer.get();
                    byte b2 = buffer2.get();

                    if (b1 != b2) {
                        System.out.println("Buffers are not equal! b1: " + b1 + " b2: " + b2);
                        throw new IOException("Buffers are not equal");
                    }
                }
            } catch (BufferUnderflowException e) {
                // ok
            } catch (IOException e) {
                break;
            }
            if (c1 == -1 || c2 == -1) {
                break;
            }
            buffer.clear();
            buffer2.clear();
        }

        printStats("NIO COMPARE", totalBytes, myBufferSize);
	}

	private void printStats(String fileName, long totalBytes, int myBufferSize) {
		NumberFormat nf = new DecimalFormat("#.00");
		final long time = timer.stop();
		double mseconds = time / 1000000d;
		double megabytes = totalBytes / 1024 / 1024;
		double rate = (megabytes) / mseconds * 1000;
		System.out.println("Done reading " + totalBytes + " bytes from file: "
				+ fileName + " in " + nf.format(mseconds) + " ms ("
				+ nf.format(rate) + "MB/sec)" + " with a buffer size of "
				+ myBufferSize / 1024 + " kB");

		// actual score (MBps)
		benchScore += rate;
	}
}