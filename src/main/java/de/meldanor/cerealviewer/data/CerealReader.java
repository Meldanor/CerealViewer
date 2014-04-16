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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CerealReader {

    public CerealReader() {

    }

    public Cereal parse(String line) {
        String[] attributes = line.split(";");
        int pos = 0;
        String name = attributes[pos++];
        String manufacturer = attributes[pos++];
        String type = attributes[pos++];
        int calories = Integer.valueOf(attributes[pos++]);
        int protein = Integer.valueOf(attributes[pos++]);
        int fat = Integer.valueOf(attributes[pos++]);
        int sodium = Integer.valueOf(attributes[pos++]);
        float fiber = Float.valueOf(attributes[pos++].replaceAll(",", "."));
        float carbohydrates = Float.valueOf(attributes[pos++].replaceAll(",", "."));
        int sugars = Integer.valueOf(attributes[pos++]);
        int potassium = Integer.valueOf(attributes[pos++]);
        int vitamins = Integer.valueOf(attributes[pos++]);
        float weight = Float.valueOf(attributes[pos++].replaceAll(",", "."));
        float cups = Float.valueOf(attributes[pos++].replaceAll(",", "."));
        int shelf = Integer.valueOf(attributes[pos++]);
        return new Cereal(name, manufacturer, type, calories, protein, fat, sodium, fiber, carbohydrates, sugars, potassium, vitamins, weight, cups, shelf);
    }

    public List<Cereal> parse(List<String> lines) {
        List<Cereal> cereals = new ArrayList<Cereal>(lines.size());
        lines.forEach(s -> cereals.add(parse(s)));
        return cereals;
    }

    public List<Cereal> parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        return parse(lines);
    }
}
