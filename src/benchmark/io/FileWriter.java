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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.StringJoiner;

import timer.Timer;
import logger.TimeUnit;

public class FileWriter {

    private static final int MIN_BUFFER_SIZE = 1024 * 1; // KB
    private static final int MAX_BUFFER_SIZE = 1024 * 1024 * 32; // MB
    private static final int MIN_FILE_SIZE = 1024 * 1024 * 1; // MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 512; // MB
    private Timer timer = new Timer();
    private double benchScore;

    /**
     * Writes files on disk using a variable write buffer and fixed file size.
     *
     * @param filePrefix - Path and file name
     * @param fileSuffix - file extension
     * @param minIndex   - start buffer size index
     * @param maxIndex   - end buffer size index
     * @param fileSize   - size of the benchmark file to be written in the disk
     * @throws IOException
     */
    public void streamWriteFixedSize(String filePrefix, String fileSuffix,
                                     int minIndex, int maxIndex, int fileSize, boolean clean)
            throws IOException {

        System.out.println("Stream write benchmark with fixed file size");
        int currentBufferSize = MIN_BUFFER_SIZE;
        String fileName;
        int counter = 0;
        benchScore = 0;

        while (currentBufferSize <= MAX_BUFFER_SIZE
                && counter <= maxIndex - minIndex) {
            fileName = filePrefix + counter + fileSuffix;
            writeWithBufferSize(fileName, currentBufferSize, fileSize, clean);
            // update buffer size
            currentBufferSize += currentBufferSize;
            counter++;
        }

        benchScore /= (maxIndex - minIndex + 1);
//		String partition = filePrefix.substring(0, filePrefix.indexOf(":/"));
        System.out.println("File write score on partition " + ": "
                + String.format("%.2f", benchScore) + " MB/sec");
    }

    /**
     * Writes files on disk using a variable file size and fixed buffer size.
     *
     * @param filePrefix - Path and file name
     * @param fileSuffix - file extension
     * @param minIndex   - start file size index
     * @param maxIndex   - end file size index
     * @param fileSize   - size of the benchmark file to be written in the disk
     */
    public void streamWriteFixedBuffer(String filePrefix, String fileSuffix,
                                       int minIndex, int maxIndex, int fileSize, int bufferSize, boolean clean)
            throws IOException {

        System.out.println("Stream write benchmark with fixed buffer size");
        int currentFileSize = MIN_FILE_SIZE;
        int counter = 0;
        String fileName = filePrefix + counter + fileSuffix;
        while (currentFileSize <= MAX_FILE_SIZE
                && counter <= maxIndex - minIndex) {
            counter++;
//			writeWithBufferSize();
            // update fileSize instead of bufferSize
            currentFileSize += currentFileSize;
            writeWithBufferSize(fileName, bufferSize, currentFileSize, clean);
        }

        benchScore /= (maxIndex - minIndex + 1);
//		String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
        System.out.println("File write score on partition " + ": "
                + String.format("%.2f", benchScore) + " MB/sec");
    }

    /**
     * Writes a file with random binary content on the disk, using a given file
     * path and buffer size.
     */
    private void writeWithBufferSize(String fileName, int myBufferSize,
                                     int fileSize, boolean clean) throws IOException {

        File folderPath = new File(fileName.substring(0,
                fileName.lastIndexOf(File.separator)));

        // create folder path to benchmark output
        if (!folderPath.isDirectory())
            folderPath.mkdirs();

        final File file = new File(fileName);
        // create stream writer with given buffer size
        final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file), myBufferSize);

        byte[] buffer = new byte[myBufferSize];
        int i = 0, toWrite = fileSize / myBufferSize;
        Random rand = new Random();

        timer.start();
        while (i < toWrite) {
            // generate random content to write
            rand.nextBytes(buffer);

            outputStream.write(buffer);
            i++;
        }
        printStats(fileName, fileSize, myBufferSize);

        outputStream.close();
        if (clean)
            file.delete();
    }

    private void printStats(String fileName, int totalBytes, int myBufferSize) {
        NumberFormat nf = new DecimalFormat("#.00");
        final long time = timer.stop();
        double mseconds = TimeUnit.converNanosecondTo(time, TimeUnit.MILISECOND);
        double megabytes = totalBytes / (1024 * 1024);
        double rate = megabytes / mseconds * 1000;
        System.out.println("Done writing " + totalBytes + " bytes to file: "
                + fileName + " in " + nf.format(mseconds) + " ms ("
                + nf.format(rate) + "MB/sec)" + " with a buffer size of "
                + myBufferSize / 1024 + " kB");

        benchScore += rate;
    }
}