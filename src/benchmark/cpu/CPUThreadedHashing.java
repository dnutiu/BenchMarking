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

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import benchmark.IBenchmark;

public class CPUThreadedHashing implements IBenchmark {

    private Random rand;
    private int size;
    private String result;
    private volatile boolean running = true;

    @Override
    public void initialize(int size) {
        rand = new Random();
        this.size = size;
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Object) instead");
    }

    @Override
    public void run(Object... options) {
        // maximum text length
        int maxTextLength = (Integer) options[0];
        // thread pool size
        int nThreads = (Integer) options[1];
        // hash code
        int hashCode = (Integer) options[2];

        // try to break these hash codes (in ascending order of difficulty):
        // 524381996
        // 276111076
        // 904300281

        int length = 2;

        ExecutorService executor = Executors.newFixedThreadPool(nThreads); // .newCachedThreadPool();
        HashManager hasher = new HashManager();
        String text = "aa";

        while (running) {
            HashBreakerTask worker = new HashBreakerTask(hasher, text, hashCode);
            executor.execute(worker);
            text = hasher.getNextString(text);
//			text = hasher.getRandomString(5);

            // stop searching
            if (length > maxTextLength) {
                running = false;
            }

            // reset string length
            if (text == null) {
                length++;
                text = "aa";
                for (int i = 2; i < length; ++i)
                    text += "a";
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

    }

    @Override
    public void clean() {
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    class HashBreakerTask implements Runnable {

        // used to compute hashes from strings
        private final HashManager hasher;
        // the string to be hashed
        private final String text;
        // the expected hash output
        private final int expected;

        private HashBreakerTask(HashManager hasher, String text, int expected) {
            this.hasher = hasher;
            this.text = text;
            this.expected = expected;
        }

        @Override
        public void run() {
            // if we found the hash
            if (expected == hasher.hash(text)) {
                running = false;
                result = text;
            }
        }
    }

    /**
     * Used to compute hashes from strings
     */
    class HashManager {

        private final String charSet = "abcdefghijklmnopqrstuvwxyz";

        private int hash(String text) {
            int a = 0;
            int b = 0;
            for (char c : text.toCharArray()) {
                int index = charSet.indexOf(c);
                if (index == -1)
                    index = charSet.length() + 1;
                for (int i = 0; i < 17; i++) {
                    a = a * -6 + b + 0x74FA - index;
                    b = b / 3 + a + 0x81BE - index;
                }
            }

            return (a ^ b) % Integer.MAX_VALUE;
        }

        private String getNextString(String text) {
            int[] index = new int[text.length()];
            int end = charSet.length() - 1;

            // convert string to table of indices
            for (int i = 0; i < text.length(); ++i) {
                try {
                    index[i] = (int) text.charAt(i);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            boolean incremented = false;
            int toIncrement = text.length() - 1;
            while (!incremented) {
                if (toIncrement < 0) {
                    return null;
                }
                if (index[toIncrement] + 1 == 123) {
                    index[toIncrement] += 1;
                    if (index[toIncrement] > 122) {
                        index[toIncrement] -= 26;
                    }
                    toIncrement -= 1;
                } else {
                    index[toIncrement] += 1;
                    incremented = true;
                    if (index[toIncrement] > 122) {
                        index[toIncrement] -= 26;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length(); ++i) {
                sb.append((char) index[i]);
            }
            return sb.toString();
        }

        public String getRandomString(int length) {
            String text = "";

            for (int i = 0; i < length; i++) {
                char c = charSet.charAt(rand.nextInt(charSet.length()));
                text += c;
            }

            return text;
        }
    }

}
