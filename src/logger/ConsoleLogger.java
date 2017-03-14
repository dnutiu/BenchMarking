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
 * The console logger class.
 */
public class ConsoleLogger implements ILogger {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(long number) {
        System.out.println(Long.toString(number));
    }

    @Override
    public void write(double number) {
        System.out.println(Double.toString(number));
    }

    @Override
    public void writeTime(long time, TimeUnit unit) {
        System.out.println("Total execution time: " + TimeUnit.converNanosecondTo(time, unit)
                + TimeUnit.getTimeUnit(unit));
    }
}
