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

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michel Vollebregt
 */
public class ElementsRepository {

    private Connection connection;

    public ElementsRepository(Connection connection) {
        this.connection = connection;
    }

    public Set<Element> listBasicElements() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery("select name from elements where is_basic = 1");
        Set<Element> basicElements = new HashSet<Element>();
        while (results.next()) {
            basicElements.add(new Element(results.getString("name")));
        }
        results.close();
        stmt.close();
        return basicElements;
    }

    public Element getCombinedElement(Element first, Element second) throws SQLException {
        Element result = null;
        PreparedStatement stmt = connection.prepareStatement("select id from elements where name = ? or name = ?");
        stmt.setString(1, first.getName());
        stmt.setString(2, second.getName());
        ResultSet idSet = stmt.executeQuery();
        Integer firstId = idSet.next() ? idSet.getInt("id") : null;
        Integer secondId = idSet.next() ? idSet.getInt("id") : null;
        idSet.close();
        stmt.close();
        stmt = connection.prepareStatement("select name from combinations c inner join elements e on e.id = c.result where c.first = ? and c.second = ?");
        stmt.setInt(1, firstId);
        stmt.setInt(2, secondId);
        ResultSet nameSet = stmt.executeQuery();
        if (nameSet.next()) result = new Element(nameSet.getString("name"));
        nameSet.close();
        stmt.close();
        return result;
    }
}
