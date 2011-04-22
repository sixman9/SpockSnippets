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

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

/**
 * @author Michel Vollebregt
 */
public class ElementTest {

    @Test
    public void newElement_getName_nameIsCorrect() {
        Element element = new Element("name of element");
        assertEquals("Element name should equal name set in constructor", "name of element", element.getName());
    }

    @Test(expected = NullPointerException.class)
    public void newElementWithNameNull_exception() {
        new Element(null);
    }

    @Test
    public void element_toString_equalToName() {
        assertEquals("toString must should element name", "name of element", new Element("name of element").toString());
    }

    @Test
    public void element_equalsSameName_true() {
        assertEquals("two elements with same name must be equal", new Element("same name"), new Element("same name"));
    }

    @Test
    public void element_equalsDifferentName_false() {
        assertThat("two elements with different name must not equal", new Element("name"), not(equalTo(new Element("different name"))));
    }

    @Test
    public void element_equalsOtherObject_false() {
        assertFalse("element should not equal other object", new Element("name of element").equals(new Object()));
    }
}
