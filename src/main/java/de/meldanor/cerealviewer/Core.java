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

package de.meldanor.cerealviewer;

import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import de.meldanor.cerealviewer.data.Cereal;
import de.meldanor.cerealviewer.data.CerealReader;
import de.meldanor.cerealviewer.gui.MainGUI;

public class Core {

    public static List<Cereal> cereals;

    public static void main(String[] args) {
        CerealReader reader = new CerealReader();
        try {
            cereals = reader.parse(Paths.get(Core.class.getResource("/cereals.csv").toURI()).toFile());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        cereals.removeIf((c) -> c.getName().charAt(0) > 'D');

        Application.launch(MainGUI.class, args);
    }
}
