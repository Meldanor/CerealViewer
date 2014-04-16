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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import de.meldanor.cerealviewer.data.Cereal;
import de.meldanor.cerealviewer.data.CerealReader;

public class CerealTest {

    @Test
    public void testCerealReader() throws IOException, URISyntaxException {
        CerealReader reader = new CerealReader();
        List<Cereal> cereals = reader.parse(Paths.get(getClass().getResource("/cereals.csv").toURI()).toFile());
        assertNotNull(cereals);
        assertFalse(cereals.isEmpty());

        Cereal cereal = cereals.get(0);
        assertNotNull(cereal);
        assertEquals("Apple Cinnamon Cheerios", cereal.getName());
        assertEquals("G", cereal.getManufacturer());
        assertEquals("C", cereal.getType());
        assertEquals(110, cereal.getCalories());
        assertEquals(2, cereal.getProtein());
        assertEquals(2, cereal.getFat());
        assertEquals(180, cereal.getSodium());
        assertEquals(1.5F, cereal.getFiber(), 0.0F);
        assertEquals(10.5F, cereal.getCarbohydrates(), 0.0F);
        assertEquals(10, cereal.getSugars());
        assertEquals(70, cereal.getPotassium());
        assertEquals(25, cereal.getVitamins());
        assertEquals(1.0F, cereal.getWeight(), 0.0F);
        assertEquals(0.75F, cereal.getCups(), 0.0F);
        assertEquals(1, cereal.getShelf());
    }

}
