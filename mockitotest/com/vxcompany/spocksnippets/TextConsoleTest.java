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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.vxcompany.spocksnippets.TestUtil.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Michel Vollebregt
 */
@RunWith(MockitoJUnitRunner.class)
public class TextConsoleTest {

    private TextConsole console;

    @Mock private Game game;
    @Mock private PrintStream output;

    @Before
    public void setUp() {
        console = new TextConsole(game, output);
    }

    @Test
    public void textConsole_commandForCombiningTwoAvailableElements_callGameCombine() {
        when(game.availableElements()).thenReturn(createSet(new Element("first"), new Element("second")));
        console.eval("combine first with second");
        verify(game).combine(new Element("first"), new Element("second"));
    }

    @Test
    public void textConsole_commandForCombiningUnavailableElement_doNotCallGameCombine() {
        when(game.availableElements()).thenReturn(createSet(new Element("available")));
        console.eval("combine available with unavailable");
        verify(game, never()).combine(any(Element.class), any(Element.class));
    }

    @Test
    public void textConsole_listCommand_printAvailableElements() {
        when(game.availableElements()).thenReturn(createSet(new Element("first"), new Element("second"), new Element("third")));
        console.eval("list");
        // for easier test cases we could use argument matchers, but because we have to check
        // three boolean expressions on the argument, we have to use the more complicated ArgumentCaptor
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(output).println(captor.capture());
        String text = captor.getValue();
        assertTrue("list command should print available elements", text.contains("first") && text.contains("second") && text.contains("third"));
    }
}
