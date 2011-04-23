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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michel Vollebregt
 */
public class Game {

    private ElementsRepository repository;
    private Set<Element> elements;

    public Game(ElementsRepository repo) {
        this.repository = repo;
        elements = repository.listBasicElements();
    }

    public Set<Element> availableElements() {
        return elements;
    }

    public void setAvailableElements(Set<Element> elements) {
        this.elements = elements;
    }

    public void combine(Element first, Element second) {
        Element foundElement = repository.getCombinedElement(first, second);
        if (foundElement != null) {
            elements.add(repository.getCombinedElement(first, second));
        }
    }
}
