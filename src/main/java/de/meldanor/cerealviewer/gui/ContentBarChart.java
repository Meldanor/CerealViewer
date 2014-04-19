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
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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

        setVerticalGridLinesVisible(false);
        setCategoryGap(100);
        setBarGap(0);

//        cereal.

        addData("Calories", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getCalories()));
        addData("Protein", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getProtein()));
        addData("Fat", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getFat()));
        addData("Sodium", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getSodium()));
        addData("Fiber", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getFiber()));
        addData("Carbohydrates", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getCarbohydrates()));
        addData("Sugars", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getSugars()));
        addData("Potassium", (Cereal c) -> new Data<>(Integer.toString(c.getShelf()), c.getPotassium()));
    }

    private void addData(String name, DataCreator creator) {
        Series<String, Number> series = new Series<>();
        series.setName(name);

        final List<Data<String, Number>> list = series.getData();
        cereals.stream().sorted((c1, c2) -> c1.getShelf() - c2.getShelf()).forEach(c -> list.add(creator.construct(c)));
        this.getData().add(series);
    }
    @FunctionalInterface
    private interface DataCreator {
        public Data<String, Number> construct(Cereal c);
    }

    private void showData(DoubleBinaryOperator op) {
        this.getData().clear();

        showData(c -> c.getShelf() == 1, 1, op);
        showData(c -> c.getShelf() == 2, 2, op);
        showData(c -> c.getShelf() == 3, 3, op);
    }

    private void showData(Predicate<? super Cereal> p, int shelf, DoubleBinaryOperator op) {
        showData(cereals.stream().filter(p), shelf, "Calories", Cereal::getCalories, op);
        showData(cereals.stream().filter(p), shelf,"Protein", Cereal::getProtein, op);
        showData(cereals.stream().filter(p), shelf,"Fat", Cereal::getFat, op);
        showData(cereals.stream().filter(p), shelf,"Sodium", Cereal::getSodium, op);
        showData(cereals.stream().filter(p), shelf,"Fiber", Cereal::getFiber, op);
        showData(cereals.stream().filter(p), shelf,"Carbohydrates", Cereal::getCarbohydrates, op);
        showData(cereals.stream().filter(p), shelf,"Sugars", Cereal::getSugars, op);
        showData(cereals.stream().filter(p), shelf,"Potassium", Cereal::getPotassium, op);
    }

    private void showData(Stream<Cereal> stream, int shelf, String name, ToDoubleFunction<? super Cereal> function, DoubleBinaryOperator op) {
        Series<String, Number> series = new Series<>();
        series.setName(name);
        
//        cereals.stream().col
//        System.out.println(function);
        DoubleStream dStream = stream.mapToDouble(function);
        OptionalDouble val = dStream.reduce(op);
        
        series.getData().add(new Data<String, Number>(Integer.toString(shelf), val.getAsDouble()));
        this.getData().add(series);
    }

    public void showMaximum() {
        showData(Double::max);
    }

    public void showMinimum() {
        showData(Double::min);
    }

    public void showAverage() {
        System.out.println("Show average!");
    }

    public void showMedian() {
        System.out.println("Show median!");
    }
}
