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

import org.junit.Before;
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

    private ElementsRepository repo;

    private Element earth = new Element("earth");
    private Element fire = new Element("fire");
    private Element lava = new Element("lava");

    @Before
    public void setUp() {
        repo = mock(ElementsRepository.class);
    }

    private <T> Set<T> createSet(T... elements) {
        Set<T> set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    @Test
    public void newGame_availableElements_equalToBasicElementsInRepo() {

        // setup
        Set<Element> BASIC_ELEMENTS = createSet(new Element("first basic element"), new Element("second basic element"));
        when(repo.listBasicElements()).thenReturn(BASIC_ELEMENTS);

        // call function
        Game game = new Game(repo);
        Set<Element> availableElements = game.availableElements();

        // assert
        assertEquals("new game should contain basic elements returned by repository", BASIC_ELEMENTS, availableElements);
    }

    @Test
    public void gameWithElements_combineTwoElements_newElementIsAdded() {

        // setup
        Game game = new Game(repo);
        game.setAvailableElements(createSet(earth, fire));
        when(repo.getCombinedElement(earth, fire)).thenReturn(lava);

        // call function
        game.combine(earth, fire);

        // assert
        Set<Element> resultSet = createSet(earth, fire, lava);
        assertEquals("combining two elements should make third element available", resultSet, game.availableElements());
    }

    @Test
    public void gameWithElements_combineNonReactingElements_availableElementsUnchanged() {

        // setup
        Game game = new Game(repo);
        game.setAvailableElements(createSet(earth, fire, lava));
        when(repo.getCombinedElement(lava, fire)).thenReturn(null);

        // call function
        game.combine(lava, fire);

        // assert
        Set<Element> resultSet = createSet(earth, fire, lava);
        assertEquals("if combining two non-reacting elements, then available elements are unchanged", resultSet, game.availableElements());
    }
}
