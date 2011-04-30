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
class IntegrationSpec extends Specification {

    def console
    def output

    def setup() {
        console = TextConsole.create()
        output = Mock(PrintStream)
        console.output = output
    }

    def "on console, combining JavaMagazine with VXCompany should return Alchemy"() {
        when: "the user types the following commands"
            console.eval("combine JavaMagazine with VXCompany")
            console.eval("list")
        then: "the output must contain the word Alchemy"
            1 * output.println({ it.contains("Alchemy") })
    }

}
