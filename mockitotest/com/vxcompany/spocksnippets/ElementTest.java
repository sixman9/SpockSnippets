package com.vxcompany.spocksnippets;

// This file is part of SpockSnippets.
//
// SpockSnippets is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// SpockSnippets is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with SpockSnippets.  If not, see <http://www.gnu.org/licenses/>.

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Michel Vollebregt
 */
public class ElementTest {

    @Test
    public void newElement_getName_nameIsCorrect() {
        Element element = new Element("name of element");
        assertEquals("Element name should equal name set in constructor", "name of element", element.getName());
    }
}
