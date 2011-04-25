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
import java.util.Set;

import static com.vxcompany.spocksnippets.GameTest.createSet;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Michel Vollebregt
 */
@RunWith(Parameterized.class)
public class GameCombiningElementsTest extends AbstractGameTest {

    private Set<Element> initial;
    private Element first;
    private Element second;
    private Element combined;
    private Set<Element> result;

    public GameCombiningElementsTest(Set<Element> initial, Element first, Element second, Element combined, Set<Element> result) {
        this.initial = initial;
        this.first = first;
        this.second = second;
        this.combined = combined;
        this.result = result;
    }

    @Test
    public void game_combineElements_correctResult() {

        // setup
        Game game = new Game(repo);
        game.setAvailableElements(initial);
        when(repo.getCombinedElement(first, second)).thenReturn(combined);

        // call function
        try {
            game.combine(first, second);
        } catch (Exception e) {}

        // assert
        assertEquals("game must combine elements correctly", result, game.availableElements());
    }

    @Parameters
    public static Collection<Object[]> parameters() {
         Object[][] parameters = new Object[][] {
                 {createSet(earth, fire)       , earth, fire, lava, createSet(earth, fire, lava)},
                 {createSet(earth, fire, lava) , lava , fire, null, createSet(earth, fire, lava)},
                 {createSet(earth)             , earth, fire, lava, createSet(earth)},
                 {createSet(fire)              , earth, fire, lava, createSet(fire)}};
        return Arrays.asList(parameters);
    }
}
