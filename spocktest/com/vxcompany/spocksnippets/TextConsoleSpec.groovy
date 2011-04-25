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
class TextConsoleSpec extends Specification {

    def console
    def game

    def setup() {
        game = Mock(Game)
        console = new TextConsole(game)
    }

    def "on text console, command for combining two available elements should call Game.combine"() {
        when:
            game.availableElements() >> [new Element("first"), new Element("second")]
            console.eval("combine first with second")
        then:
            1 * game.combine(new Element("first"), new Element("second"))
    }



}
