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
 * This is a basic timer.
 */
public class Timer implements ITimer{
    private long startTime;
    private long endTime;
    private long timePassed;
    private long totalTime;

    @Override
    public void start() {
        timePassed = 0;
        startTime = System.nanoTime();
    }

    @Override
    public long stop() {
        endTime = System.nanoTime();
        timePassed += endTime - startTime;
        totalTime = timePassed;
        return totalTime;
    }

    @Override
    public long pause() {
        endTime = System.nanoTime();
        timePassed += endTime - startTime;
        return timePassed;
    }

    @Override
    public void resume() {
        startTime = System.nanoTime();
    }

    /**
     * Function used to get the time that has passed.
     * @return Returns the total time that has passed.
     */
    public long getTimePassed() {
        return this.timePassed;
    }
}
