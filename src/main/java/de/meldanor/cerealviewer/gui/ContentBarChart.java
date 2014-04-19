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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.function.ToDoubleFunction;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import de.meldanor.cerealviewer.data.Cereal;

public class ContentBarChart extends BarChart<String, Number> {

    private Map<Integer, List<Cereal>> mapByShelf;

    public ContentBarChart(List<Cereal> cereals) {
        super(new CategoryAxis(), new NumberAxis(0,350,50));

        this.prepareData(cereals);

        this.initGUI();
    }

    private void prepareData(List<Cereal> cereals) {
        mapByShelf = new HashMap<>();

        cereals.forEach((Cereal c) -> {
            List<Cereal> list = mapByShelf.get(c.getShelf());
            if (list == null) {
                list = new ArrayList<Cereal>();
                mapByShelf.put(c.getShelf(), list);
            }
            list.add(c);
        });
    }

    private void initGUI() {

        setVerticalGridLinesVisible(false);
        setCategoryGap(100);
        setBarGap(0);
        setAnimated(false);

        showAverage();
    }

    private void showData(DoubleBinaryOperator op) {
        this.getData().clear();

        showData("Calories", Cereal::getCalories, op);
        showData("Protein", Cereal::getProtein, op);
        showData("Fat", Cereal::getFat, op);
        showData("Sodium", Cereal::getSodium, op);
        showData("Fiber", Cereal::getFiber, op);
        showData("Carbohydrates", Cereal::getCarbohydrates, op);
        showData("Sugars", Cereal::getSugars, op);
        showData("Potassium", Cereal::getPotassium, op);
    }

    private void showData(String name, ToDoubleFunction<? super Cereal> function, DoubleBinaryOperator op) {

        Series<String, Number> series = new Series<>();
        series.setName(name);

        mapByShelf.forEach((Integer shelf, List<Cereal> list) -> {

            OptionalDouble val = list.stream().mapToDouble(function).filter(v -> v != -1).reduce(op);
            series.getData().add(new Data<>(Integer.toString(shelf), val.getAsDouble()));

        });
        this.getData().add(series);
    }

    public void showMaximum() {
        showData(Double::max);
    }

    public void showMinimum() {
        showData(Double::min);
    }

    public void showAverage() {
        this.getData().clear();

        showDataAverage("Calories", Cereal::getCalories);
        showDataAverage("Protein", Cereal::getProtein);
        showDataAverage("Fat", Cereal::getFat);
        showDataAverage("Sodium", Cereal::getSodium);
        showDataAverage("Fiber", Cereal::getFiber);
        showDataAverage("Carbohydrates", Cereal::getCarbohydrates);
        showDataAverage("Sugars", Cereal::getSugars);
        showDataAverage("Potassium", Cereal::getPotassium);
    }

    private void showDataAverage(String name, ToDoubleFunction<? super Cereal> function) {

        Series<String, Number> series = new Series<>();
        series.setName(name);

        mapByShelf.forEach((Integer shelf, List<Cereal> list) -> {

            OptionalDouble val = list.stream().mapToDouble(function).filter(v -> v != -1).average();
            series.getData().add(new Data<>(Integer.toString(shelf), val.getAsDouble()));

        });
        this.getData().add(series);
    }

    public void showMedian() {
        System.out.println("Show median!");
    }
}
