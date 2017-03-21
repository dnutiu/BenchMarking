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

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The FileBenchmark class will the the system's I/O capabilities
 * by writing a file and reading from it.
 */
public class FileBenchmark implements IBenchmark {
    private static int uniqueIndentifier;
    private File file;
    private long bytesToUse;
    private long fileSize;
    private int bufferSize;
    private long bytes;
    private byte[] buffer;

    /**
     * Returns the file size in MegaBytes.
     * @return the size of the file used in this benchmark.
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Gets the size of the buffer. The size of the buffer will be used when reading/writing to a file.
     * @return the size of the buffer.
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * Constructs a FileBenchmark class with the default size of 100 MB and 1024 buffer size.
     */
    public FileBenchmark() {
        this(100000000, 1024);
    }

    /**
     * Constructs a FileBenchmark class.
     * @param bytes The number bytes that should be used for the test.
     * @param bufferSize The size of the write/read buffer.
     */
    public FileBenchmark(long bytes, int bufferSize) {
        this.bufferSize = bufferSize;
        this.bytes = bytes;
    }

    /**
     * Sets the buffersize which will be used to read/write n bytes at a time.
     * You need to reinitialize this benchmark after calling this function.
     * @param bufferSize The buffersize.
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Sets the number of bytes of the file size.
     * You need to reinitialize this benchmark after calling this function.
     * @param bytes The filesize in bytes.
     */
    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public void warmUp() throws IOException {
        this.run();
    }

    @Override
    public void initialize(int value) {
        FileBenchmark.uniqueIndentifier += 1;
        file = new File("defaultTestFile" + FileBenchmark.uniqueIndentifier);

        this.bytes = value;
        this.bytesToUse = bytes / bufferSize;
        this.fileSize = bytes / 1000000;
        this.buffer = new byte[this.bufferSize];
    }

    @Override
    public void run() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file) ) {
            // Write buffer to file till bytes / bufferSize is reached.
            for (long i = 0; i < this.bytesToUse; ++i) {
                fileOutputStream.write(this.buffer);
                fileOutputStream.flush(); // we want to write down every iteration
            }
        } catch (IOException e) {
            throw new IOException("FileBenchmark READ/WRITE failed at write test.");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            //noinspection StatementWithEmptyBody
            while ( fileInputStream.read(this.buffer) != -1 );
        } catch (IOException e) {
            throw new IOException("FileBenchmark READ/WRITE failed at read test.");
        }
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Method not implemented!");
    }

    @Override
    public void clean() {
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }

    @Override
    public String getResult() {
        return null;
    }
}
