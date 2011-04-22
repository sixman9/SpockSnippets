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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Michel Vollebregt
 */
public class GameTest {

    @Test
    public void newGame_availableElements_equalToBasicElementsInRepo() {

        // setup
        Set<Element> BASIC_ELEMENTS = new HashSet<Element>();
        Collections.addAll(BASIC_ELEMENTS, new Element("first basic element"), new Element("second basic element"));

        ElementsRepository repo = mock(ElementsRepository.class);
        when(repo.listBasicElements()).thenReturn(BASIC_ELEMENTS);

        // call function
        Game game = new Game(repo);
        Set<Element> availableElements = game.availableElements();

        // assert
        assertEquals("new game should contain basic elements returned by repository", BASIC_ELEMENTS, availableElements);
    }
}
