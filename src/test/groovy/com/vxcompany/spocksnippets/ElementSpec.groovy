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
class ElementSpec extends Specification {

    def "on new element, calling getName should return correct name"() {
        when:
            def element = new Element("name of element")
        then:
            element.getName() == "name of element"
    }


    def "creating new element with name null should throw exception"() {
        when:
            new Element(null)
        then:
            thrown(NullPointerException)
    }

    def "on element, toString should return name"() {
        expect:
            new Element("name of element").toString() == "name of element"
    }

    // example of a helper method
    // adding the asserts is optional, but generates much more readable output on failure
    void areEqual(object, otherObject) {
        assert object.equals(otherObject)
        assert object.hashCode() == otherObject.hashCode()
    }

    def "two elements with same name must equal"() {
        expect:
            areEqual(new Element("same name"), new Element("same name"))
    }

    def "two elements with different name must not equal"() {
        expect:
            new Element("name") != new Element("different name")
    }

    def "element should not equal other object"() {
        expect:
            new Element("name of element") != new Object()
    }

}
