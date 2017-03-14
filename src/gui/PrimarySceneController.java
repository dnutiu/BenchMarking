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

package gui;

import benchmark.cpu.CPUDigitsOfPIBenchmark;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import logger.TimeUnit;
import timer.Timer;

/**
 * Controls the events and actions for the primary view of the application.
 * The primary view represents the first root.
 */
public class PrimarySceneController {
    private Timer timer;
    public TextArea textConsole;

    @FXML
    public void initialize() {
        /* We'll do all the initialization work here. */
        timer = new Timer();
    }

    @FXML
    public void handleRunDigitsOfPi(ActionEvent actionEvent) {
        CPUDigitsOfPIBenchmark benchmark = new CPUDigitsOfPIBenchmark();
        benchmark.warmUp();
        benchmark.initialize();
        textConsole.appendText("Benchmark starting.\n");
        benchmark.setNumberOfDigits(5000);
        timer.start();
        benchmark.run();
        textConsole.appendText("Finished " +
                TimeUnit.converNanosecondTo(timer.stop(), TimeUnit.MILISECOND) + "ms\n");
    }
}
