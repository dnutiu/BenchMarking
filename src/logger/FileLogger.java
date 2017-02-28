package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


/**
 * The logger is used to log items into a file.
 */
public class FileLogger implements ILogger {

    private File defaultFile;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    /**
     * The default constructor for the file logger, saves the log as log.log
     * @throws IOException throws this exception.
     */
    public FileLogger() throws IOException {
        this("log.log");
    }

    /**
     * Constructs the file logger and saves the log file to the specified path.
     * @param filepathname The path to the log file.
     * @throws IOException throws this exception.
     */
    public FileLogger(String filepathname) throws IOException {
        defaultFile = new File(filepathname);
        fileWriter = new FileWriter(defaultFile);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    @Override
    public void write(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(long number) {
        try {
            bufferedWriter.write(Long.toString(number));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(double number) {
        try {
            bufferedWriter.write(Double.toString(number));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeTime(long time, TimeUnit unit) {
        try {
            bufferedWriter.write("Total execution time: " + time);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function should be called after you're done logging.
     * It will close all the open streams and file descriptors.
     */
    public void close() {
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
