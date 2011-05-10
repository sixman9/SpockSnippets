package com.vxcompany.spocksnippets

import spock.lang.Specification

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

/**
 *
 *
 * @author Michel Vollebregt
 */
class GameSpec extends Specification {

    def repo;

    static earth = new Element("earth");
    static fire = new Element("fire");
    static lava = new Element("lava");

    def setup() {
        repo = Mock(ElementsRepository)
    }

    def "on new game, available elements should equal basic elements in repo"() {
        setup:
            def BASIC_ELEMENTS = [new Element("first basic element"), new Element("second basic element")] as Set
            repo.listBasicElements() >> BASIC_ELEMENTS
        when:
            def game = new Game(repo)
        then:
            // note that == verifies equality (a.equals(b)) and NOT identity (a == b)
            game.availableElements() == BASIC_ELEMENTS;
    }

    def "game must combine elements correctly"() {
        when:
            def game = new Game(repo)
            game.availableElements = initial
        and:
            repo.getCombinedElement(first, second) >> combined
        and:
            try {
                game.combine(first, second)
            } catch (Exception e) {}
        then:
            game.availableElements() == result as Set
        where:
            // note: we may not use instance variables earth, fire and lava within a where block
            // we must make them static. in the version of spock I used I did not get a proper error message, though
            // they claim the error does occur in the latest version of Spock
            initial             | first | second | combined | result
            [earth, fire]       | earth | fire   | lava     | [earth, fire, lava]
            [earth, fire, lava] | lava  | fire   | null     | [earth, fire, lava]
            [earth]             | earth | fire   | lava     | [earth]
            [fire]              | earth | fire   | lava     | [fire]
    }

    def "on game, when combining unavailable element then exception is thrown"() {
        when:
            def game = new Game(repo)
            game.availableElements = [available]
        and:
            game.combine(earth, fire)
        then:
            thrown(Exception)
        where:
            // syntax with << means: run test once with available == earth and once with available == fire
            available << [earth, fire]
    }
}
