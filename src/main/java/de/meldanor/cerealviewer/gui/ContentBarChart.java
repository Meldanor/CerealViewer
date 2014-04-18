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
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import com.sun.javafx.charts.Legend;

import de.meldanor.cerealviewer.data.Cereal;

public class ContentBarChart extends BarChart<String, Number> {

    private List<Cereal> cereals;

    public ContentBarChart(List<Cereal> cereals) {
        super(new CategoryAxis(), new NumberAxis());

        this.cereals = cereals;

        this.initGUI();
    }

    private void initGUI() {

        this.getLegend().addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            Region reg = (Region) e.getTarget();
            reg.
        });

//        addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
//            if (e.getTarget() instanceof Legend) {
//                System.out.println("lol");
//            }
//            System.out.println(e.getTarget());
//        });

        setVerticalGridLinesVisible(false);

        addData("Calories", (Cereal c) -> new Data<>(c.getName(), c.getCalories()));
        addData("Protein", (Cereal c) -> new Data<>(c.getName(), c.getProtein()));
        addData("Fat", (Cereal c) -> new Data<>(c.getName(), c.getFat()));
        addData("Sodium", (Cereal c) -> new Data<>(c.getName(), c.getSodium()));
        addData("Fiber", (Cereal c) -> new Data<>(c.getName(), c.getFiber()));
        addData("Carbohydrates", (Cereal c) -> new Data<>(c.getName(), c.getCarbohydrates()));
        addData("Sugars", (Cereal c) -> new Data<>(c.getName(), c.getSugars()));
        addData("Potassium", (Cereal c) -> new Data<>(c.getName(), c.getPotassium()));
    }

    private void addData(String name, DataCreator creator) {
        Series<String, Number> series = new Series<>();
        series.setName(name);

        final List<Data<String, Number>> list = series.getData();
        cereals.forEach(c -> list.add(creator.construct(c)));
        this.getData().add(series);
    }

    @FunctionalInterface
    private interface DataCreator {
        public Data<String, Number> construct(Cereal c);
    }
}
