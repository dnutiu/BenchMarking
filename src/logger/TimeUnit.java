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

import org.jetbrains.annotations.Contract;

/**
 * The TimeUnit enum provides an easy method of converting between time units.
 */
public enum TimeUnit {
    NANOSECOND(1),
    MICROSECOND(1000),
    MILISECOND(1000000),
    SECOND(1000000000);

    private long precision;

    /**
     * Constructs a TimeUnit object with the desired precision.
     * If we want to convert nanoseconds in microseconds then we must select a precision of
     * 1000 because nanoseconds / 1000 = microseconds.
     * @param precision The precision will be as divisor for the time.
     */
    TimeUnit(long precision) {
        this.precision = precision;
    }

    /**
     * @return The precision for the time unit
     */
    public long getPrecision() {
        return this.precision;
    }


    /**
     * Converts between time units.
     * @param time Takes the time.
     * @param unit Takes the precision.
     * @return The quotient of time / time unit.
     */
    @Contract(pure = true)
    public static long converNanosecondTo(long time, TimeUnit unit) {
        return (time / unit.precision);
    }
}
