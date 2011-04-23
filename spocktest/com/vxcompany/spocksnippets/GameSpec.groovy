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
class GameSpec extends Specification{

    def "on new game, available elements should equal basic elements in repo"() {
        setup:
            def BASIC_ELEMENTS = [new Element("first basic element"), new Element("second basic element")] as Set
            def repo = Mock(ElementsRepository)
            repo.listBasicElements() >> BASIC_ELEMENTS
        when:
            def game = new Game(repo)
        then:
            // note that == verifies equality (a.equals(b)) and NOT identity (a == b)
            game.availableElements() == BASIC_ELEMENTS;
    }

    def "on game with elements, combining two elements makes another element available"() {
        setup:
            def repo = Mock(ElementsRepository)
            def earth = new Element("earth");
            def fire = new Element("fire");
            def lava = new Element("lava");
        when:
            def game = new Game(repo)
            game.availableElements = [earth, fire]
        and:
            repo.getCombinedElement(earth, fire) >> lava
        and:
            game.combine(earth, fire)
        then:
            game.availableElements() == [earth, fire, lava] as Set
    }

    def "on game with elements, when combining two non-reacting elements then available elements are unchanged"() {
        setup:
            def repo = Mock(ElementsRepository)
            def earth = new Element("earth")
            def fire = new Element("fire")
            def lava = new Element("lava")
        when:
            def game = new Game(repo)
            game.availableElements = [earth, fire, lava]
        and:
            repo.getCombinedElement(lava, fire) >> null
        and:
            game.combine(lava, fire)
        then:
            game.availableElements() == [earth, fire, lava] as Set

    }
}
