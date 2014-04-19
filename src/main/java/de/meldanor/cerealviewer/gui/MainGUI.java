/** 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2014 Kilian Gaertner
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package de.meldanor.cerealviewer.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import de.meldanor.cerealviewer.Core;

public class MainGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Cereals");

        BorderPane pane = new BorderPane();

        ContentBarChart chart = new ContentBarChart(Core.cereals);
        pane.setCenter(chart);

        VBox controllPane = new VBox(20.0);
        controllPane.setPadding(new Insets(100, 20, 10, 10));

        ToggleGroup controllGroup = new ToggleGroup();
        controllGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
                if (toggle != null)
                    ((ToggleButton) toggle).setDisable(false);
                if (new_toggle != null)
                    ((ToggleButton) new_toggle).setDisable(true);
            }
        });

        ToggleButton showMaxButton = new ToggleButton("Maximum");
        showMaxButton.setToggleGroup(controllGroup);
        showMaxButton.setOnAction(e -> chart.showMaximum());

        ToggleButton showMinButton = new ToggleButton("Minimum");
        showMinButton.setToggleGroup(controllGroup);
        showMinButton.setOnAction(e -> chart.showMinimum());

        ToggleButton showMedianButton = new ToggleButton("Median");
        showMedianButton.setToggleGroup(controllGroup);
        showMedianButton.setOnAction(e -> chart.showMedian());

        ToggleButton showAverageButton = new ToggleButton("Average");
        showAverageButton.setToggleGroup(controllGroup);
        showAverageButton.setOnAction(e -> chart.showAverage());

        controllPane.getChildren().addAll(showMinButton, showMaxButton, showMedianButton, showAverageButton);

        pane.setRight(controllPane);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }
}
