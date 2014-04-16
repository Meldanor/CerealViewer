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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import de.meldanor.cerealviewer.data.Cereal;
import de.meldanor.cerealviewer.util.Counter;

public class ManufactorPie extends PieChart {

    private Map<String, Counter> manufactorFrequency;

    public ManufactorPie(Scene scene, List<Cereal> cereals) {
        this.manufactorFrequency = countManufactors(cereals);
        this.initGUI();
    }

    private Map<String, Counter> countManufactors(List<Cereal> cereals) {
        HashMap<String, Counter> frequency = new HashMap<>();
        System.out.println(cereals);
        // Create counter for every manufactor
        cereals.stream().map(Cereal::getManufacturer).distinct().forEach(s -> frequency.put(s, new Counter()));
        // Count the manufactor
        cereals.stream().forEach(c -> frequency.get(c.getManufacturer()).increment());

        return frequency;
    }

    private void initGUI() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        // Sort the map using the frequency
        Stream<Entry<String, Counter>> stream = manufactorFrequency.entrySet().stream().sorted((Entry<String, Counter> e1, Entry<String, Counter> e2) -> Long.compare(e1.getValue().get(), e2.getValue().get()));
        // Add sorted map to list
        stream.forEach((Entry<String, Counter> e) -> pieChartData.add(new Data(e.getKey(), e.getValue().get())));

        this.setData(pieChartData);
        this.setTitle("Cereal Manufactors");
    }

//    @Override
//    public void start(Stage stage) {
//
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//        Stream<Entry<String, Counter>> stream = manufactorFrequency.entrySet().stream().sorted((Entry<String, Counter> e1, Entry<String, Counter> e2) -> Long.compare(e1.getValue().count(), e2.getValue().count()));
//        stream.forEach((Entry<String, Counter> e) -> pieChartData.add(new Data(e.getKey(), e.getValue().count())));
//
//        final PieChart chart = new PieChart(pieChartData);
//
//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//
//        chart.setTitle("Cereal Manufactors");
//        chart.getData().forEach(d -> {
//            d.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(d.getPieValue()) + "%");
////                System.out.println(String.valueOf(d.getPieValue()) + "%");
//            });
//        });
//
//        ((Group) scene.getRoot()).getChildren().add(chart);
//        ((Group) scene.getRoot()).getChildren().add(caption);
//
//    }

}
