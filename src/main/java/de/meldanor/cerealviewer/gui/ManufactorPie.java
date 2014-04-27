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
import javafx.scene.chart.PieChart;
import de.meldanor.cerealviewer.data.Cereal;
import de.meldanor.cerealviewer.util.Counter;

public class ManufactorPie extends PieChart {

    private Map<String, Counter> manufactorFrequency;

    public ManufactorPie(List<Cereal> cereals, int shelf) {
        this.manufactorFrequency = countManufactors(cereals, shelf);
        this.initGUI();
    }

    private Map<String, Counter> countManufactors(List<Cereal> cereals, int shelf) {
        HashMap<String, Counter> frequency = new HashMap<>();
        // Create counter for every manufactor
        cereals.stream().map(Cereal::getManufacturer).distinct().forEach(s -> frequency.put(s, new Counter()));
        // Count the manufactor
        cereals.stream().filter(c -> c.getShelf() == shelf).forEach(c -> frequency.get(c.getManufacturer()).increment());

        return frequency;
    }

    private void initGUI() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Sort the map using the frequency
        Stream<Entry<String, Counter>> stream = manufactorFrequency.entrySet().stream().sorted((Entry<String, Counter> e1, Entry<String, Counter> e2) -> e1.getKey().compareTo(e2.getKey()));
        // Add sorted map to list
        stream.forEach((Entry<String, Counter> e) -> pieChartData.add(new Data(e.getKey(), e.getValue().get())));

        this.setData(pieChartData);
    }

}
