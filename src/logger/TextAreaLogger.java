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

import javafx.scene.control.TextArea;

/**
 * It will append text to the linked TextArea.
 */
public class TextAreaLogger implements ILogger {
    private TextArea stream;

    public void setTextArea(TextArea textArea) {
        if (textArea != null) {
            this.stream = textArea;
        }
    }

    @Override
    public void write(String message) {
        stream.appendText(message + "\n");
    }

    @Override
    public void write(long number) {
        stream.appendText(Long.toString(number));
    }

    @Override
    public void write(double number) {
        stream.appendText(Double.toString(number));
    }

    @Override
    public void writeTime(long time, TimeUnit unit) {
        String timeUnit = TimeUnit.getTimeUnit(unit);
        stream.appendText("Total execution time: " + TimeUnit.converNanosecondTo(time, unit));
        stream.appendText(timeUnit);
    }
}
