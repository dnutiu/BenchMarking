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

package benchmark.cpu;

import benchmark.IBenchmark;

public class CPUThreadedRoots implements IBenchmark {

    private double result;
    private int size;

    @Override
    public void initialize(int size) {
        this.size = size;
    }

    @Override
    public void warmUp() {
        int oldsize = this.size;
        this.size = 1000;
        this.run(1);
        this.size = oldsize;
        this.clean();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options) {
        int nThreads = (int) options[0];

        Thread[] threads = new Thread[nThreads];

        // create a thread for each runnable and start it
        int adder = this.size / nThreads;
        int portions = adder;

        int start = 1;
        for (int i = 0; i < nThreads; ++i) {
            threads[i] = new Thread(new SquareRootTask(start, portions));
            start = portions;
            portions += adder;

            threads[i].start();
        }

        // join threads
        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void clean() {
        this.result = 0;
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    private synchronized void add(double r) {

        this.result += r;

    }

    class SquareRootTask implements Runnable {

        private int from, to;
        private double sum = 0.0;

        SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for (int i = from; i < to; ++i) {
                sum += getNewtonian(i);
            }

            this.updateSum();
        }

        private double getNewtonian(double x) {
            if (x < 0) return Double.NaN;
            double EPSILON = 1E-15;
            double t = x;
            while (Math.abs(t - x / t) > EPSILON * t)
                t = (x / t + t) / 2.0;
            return t;
        }

        void updateSum() {
            CPUThreadedRoots.this.add(sum);
        }
    }

}
