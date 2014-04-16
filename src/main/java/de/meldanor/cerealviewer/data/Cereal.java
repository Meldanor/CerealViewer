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

package de.meldanor.cerealviewer.data;

/**
 * Container class for the cereal information
 */
public class Cereal {

    private String name;
    private String manufacturer;
    private String type;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private float fiber;
    private float carbohydrates;
    private int sugars;
    private int potassium;
    private int vitamins;
    private float weight;
    private float cups;
    private int shelf;

    public Cereal(String name, String manufacturer, String type, int calories, int protein, int fat, int sodium, float fiber, float carbohydrates, int sugars, int potassium, int vitamins, float weight, float cups, int shelf) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.potassium = potassium;
        this.vitamins = vitamins;
        this.weight = weight;
        this.cups = cups;
        this.shelf = shelf;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public float getFiber() {
        return fiber;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public int getSugars() {
        return sugars;
    }

    public int getPotassium() {
        return potassium;
    }

    public int getVitamins() {
        return vitamins;
    }

    public float getWeight() {
        return weight;
    }

    public float getCups() {
        return cups;
    }

    public int getShelf() {
        return shelf;
    }

    @Override
    public String toString() {
        return "Cereal [name=" + name + ", manufacturer=" + manufacturer + ", type=" + type + ", calories=" + calories + ", protein=" + protein + ", fat=" + fat + ", sodium=" + sodium + ", fiber=" + fiber + ", carbohydrates=" + carbohydrates + ", sugars=" + sugars + ", potassium=" + potassium + ", vitamins=" + vitamins + ", weight=" + weight + ", cups=" + cups + ", shelf=" + shelf + "]";
    }
}
