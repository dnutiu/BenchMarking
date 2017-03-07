package benchmark;

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
        this.buffer = new byte[bufferSize];
    }

    /**
     * Sets the buffersize which will be used to read/write n bytes at a time.
     * @param bufferSize The buffersize.
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Sets the number of bytes of the file size.
     * @param bytes The filesize in bytes.
     */
    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public void warmup() throws IOException {
        this.run();
    }

    @Override
    public void initialize() {
        FileBenchmark.uniqueIndentifier += 1;
        file = new File("defaultTestFile" + FileBenchmark.uniqueIndentifier);

        this.bytesToUse = bytes / bufferSize;
        this.fileSize = bytes / 1000000;
    }

    @Override
    public void run() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file) ) {
            // Write buffer to file till bytesToUse / bufferSyze is reached.
            for (long i = 0; i < this.bytesToUse; ++i) {
                fileOutputStream.write(this.buffer);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new IOException("FileBenchmark READ/WRITE failed at write test.");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while ( fileInputStream.read(this.buffer) != -1 );
        } catch (IOException e) {
            throw new IOException("FileBenchmark READ/WRITE failed at read test.");
        }
    }

    @Override
    public void run(Object... parameters) throws OperationNotSupportedException {}

    @Override
    public void clean() {
        file.delete();
    }
}
