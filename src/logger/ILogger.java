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

/**
 * This interface is used as a basis for the logging classes.
 */
public interface ILogger {
    /**
     *  This function outputs the string message.
     * @param message The initial message
     */
    void write(String message);

    /**
     *  This function takes a number and loggs it to the stream
     * @param number The number
     */
    void write(long number);

    /**
     *  This function takes a double and loggs it to the stream.
     * @param number The number to write to the stream.
     */
    void write(double number);

    /**
     * This functions converts time to the specified time unit
     * @param time  The time in nanoseconds.
     * @param unit A time unit to convert to.
     */
    void writeTime(long time, TimeUnit unit);
}
