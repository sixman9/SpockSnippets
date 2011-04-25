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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Michel Vollebregt
 */
@RunWith(Parameterized.class)
public class GameCombiningUnavailableElementsTest extends AbstractGameTest {

    private Element available;

    public GameCombiningUnavailableElementsTest(Element available) {
        this.available = available;
    }

    @Test(expected = Exception.class)
    public void game_combineUnavailableElement_exceptionThrown() {
        Game game = new Game(repo);
        game.setAvailableElements(createSet(available));
        // call function
        game.combine(earth, fire);
    }

    @Parameters
    public static Collection<Object[]> parameters() {
        Object[][] parameters = new Object[][] {{earth}, {fire}};
        return Arrays.asList(parameters);
    }
}
