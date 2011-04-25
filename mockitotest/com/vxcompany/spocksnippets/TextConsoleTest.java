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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Michel Vollebregt
 */
public class TextConsoleTest {

    private TextConsole console;
    private Game game;

    @Before
    public void setUp() {
        game = mock(Game.class);
        console = new TextConsole(game);
    }

    @Test
    public void textConsole_commandForCombiningTwoAvailableElements_callGameCombine() {
        Set<Element> available = new HashSet<Element>();
        Collections.addAll(available, new Element("first"), new Element("second"));
        game.setAvailableElements(available);
        console.eval("combine first with second");
        verify(game).combine(new Element("first"), new Element("second"));
    }
}
