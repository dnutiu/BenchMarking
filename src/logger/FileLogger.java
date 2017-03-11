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

package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


/**
 * The file logger class.
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
