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

import java.util.List;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import de.meldanor.cerealviewer.data.Cereal;

public class ContentBarChart extends BarChart<String, Number> {

    private List<Cereal> cereals;

    public ContentBarChart(List<Cereal> cereals) {
        super(new CategoryAxis(), new NumberAxis());

        this.cereals = cereals;

        this.initGUI();
    }

    private void initGUI() {
        // Type Calories Protein Fat Sodium Fiber Carbohydrates Sugars Potassium

        Series<String, Number> series = new Series<>();

        // Calories
        series.setName("Calories");
        List<Data<String, Number>> list = series.getData();
        cereals.forEach(c -> {
            list.add(new Data<>(c.getName(), c.getCalories()));
        });
        this.getData().add(series);

//        // Calories
//        series = new Series<>();
//        series.setName("Calories");
//        list = series.getData();
//        cereals.forEach(c -> list.add(new Data<>(c.getName(), c.getCalories())));
//        this.getData().add(series);

    }
}
