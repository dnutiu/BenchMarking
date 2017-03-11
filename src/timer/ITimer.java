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

package timer;

/**
 * The ITimer interface contains the basic functions that need to be used when implementing a timer.
 */
public interface ITimer {
    /**
     * This function starts the timer.
     */
    void start();

    /**
     * This function stops the timer.
     * @return The passed time.
     */
    long stop();

    /**
     * This function pauses the timer.
     * @return The passed time.
     */
    long pause();

    /**
     * This function resumes the time.
     */
    void resume();
}
