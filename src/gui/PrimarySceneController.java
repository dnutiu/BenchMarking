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

import benchmark.cpu.CPUFixedPointTest;
import benchmark.utilities.NumberRepresentation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import logger.TextAreaLogger;
import logger.TimeUnit;
import timer.Timer;
import java.net.URL;
import java.util.*;

/**
 * Controls the events and actions for the primary view of the application.
 * The primary view represents the first root.
 */
public class PrimarySceneController implements Initializable {
    private Timer timer;
    private TextAreaLogger logger;
    private HashMap benchmarks;
    @FXML
    private TextArea textConsole;
    @FXML
    private ChoiceBox choiceBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* We'll do all the initialization work here. */
        timer = new Timer();
        logger = new TextAreaLogger();
        benchmarks = new HashMap<String, Runnable>();
        logger.setTextArea(this.textConsole);

        this.loadBenchMarks();
    }

    private void loadBenchMarks() {
        benchmarks.put("Hello", new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        });

        benchmarks.put("DigitsOfPi", new Runnable() {
            @Override
            public void run() {
                CPUFixedPointTest fpt = new CPUFixedPointTest();
                fpt.initialize(100000000);
                fpt.warmUp();

                logger.write("Benchmark starting for fixed");
                timer.start();
                fpt.run(NumberRepresentation.FIXED);
                logger.writeTime(timer.stop(), TimeUnit.MILISECOND);
                logger.write("Sum is " + fpt.getResult());

                logger.write("Benchmark starting for float");
                timer.start();
                fpt.run(NumberRepresentation.FLOATING);
                logger.writeTime(timer.stop(), TimeUnit.MILISECOND);
                logger.write("Sum is " + fpt.getResult());
            }
        });

        // add the keys to the list
        for (Object str : benchmarks.keySet()) {
            ObservableList<String> add = choiceBox.getItems();
            if (add != null) {
                add.add((String) str);
            }
        }
    }

    @FXML
    private void runBenchmark(ActionEvent actionEvent) {
        String key = (String) choiceBox.getValue();
        if (key != null) {
            Runnable methodToRun = (Runnable) benchmarks.get(key);
            if (methodToRun != null) {
                methodToRun.run();
            }
        }
    }
}
