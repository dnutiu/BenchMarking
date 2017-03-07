package benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import timer.Timer;
import logger.ILogger;
import logger.ConsoleLogger;
import logger.TimeUnit;

/**
 * The FileBenchmark class will the the system's I/O capabilities
 * by writing a file and reading from it.
 */
public class FileBenchmark implements IBenchmark {


    private ILogger logger;
    private File file;
    private Timer timer;
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
    public void initialize(long see) {
        // TODO: Make logger settings.
        timer = new Timer();
        file = new File("defaultTestFile");
        logger = new ConsoleLogger();

        this.bytesToUse = bytes / bufferSize;
        this.fileSize = bytes / 1000000;
    }

    @Override
    public void run() {
        // TODO: Perhaps add exception if class is not initialised.
        try (FileOutputStream fileOutputStream = new FileOutputStream(file) ) {

            logger.write("FileBenchmark test started.");
            logger.write("Testing write speed with file of " + this.fileSize + " MB");

            timer.start();
            // Write buffer to file till bytesToUse / bufferSyze is reached.
            for (long i = 0; i < this.bytesToUse; ++i) {
                fileOutputStream.write(this.buffer);
            }
            fileOutputStream.flush();
            timer.stop();

            logger.write("Time passed to write file on disk: "
                    + TimeUnit.converNanosecondTo(timer.getTimePassed(), TimeUnit.MILISECOND) + " ms");

        } catch (java.io.IOException e) {
            logger.write("FileBenchmark write failed.");
            logger.write(e.getMessage());
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            logger.write("Testing read speed with file of " + this.fileSize + " MB");

            timer.start();
            while ( fileInputStream.read(this.buffer) != -1 );
            timer.stop();

            logger.write("Time passed to read file from disk: "
                    + TimeUnit.converNanosecondTo(timer.getTimePassed(), TimeUnit.MILISECOND) + " ms");

        } catch (java.io.IOException e) {
            logger.write("FileBenchmark read failed");
            logger.write(e.getMessage());
        }

    }

    @Override
    public void run(Object... parameters) {
        System.out.println("Operation not implemented!");
    }

    @Override
    public void clean() {
        file.delete();
    }
}
