package com.vxcompany.spocksnippets

import java.sql.DriverManager
import groovy.sql.Sql
import spock.lang.Specification
import spock.lang.Shared

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
class ElementsRepositorySpec extends Specification {

    @Shared database
    def repo

    def "on repository, listBasicElements() returns elements marked as basic"() {
        setup:
            def insert = "insert into elements (name, is_basic) values (?, ?)"
            database.execute insert, ['basic element 1', 1]
            database.execute insert, ['basic element 2', 1]
            database.execute insert, ['non basic element', 0]
        expect:
            repo.listBasicElements() == [new Element("basic element 1"), new Element("basic element 2")] as Set
    }

    def "on repository, combining two elements returns combined element"() {
        setup:
            def insert = "insert into elements (id, name) values (?, ?)"
            database.execute insert, [1, "first element"]
            database.execute insert, [2, "second element"]
            database.execute insert, [3, "combined element"]
            database.execute "insert into combinations (first, second, result) values (1, 2, 3)"
        expect:
            repo.getCombinedElement(new Element("first element"), new Element("second element")) == new Element("combined element")
    }

    def setupSpec() {
        database = Sql.newInstance("jdbc:hsqldb:mem:testdb", "org.hsqldb.jdbcDriver")
        database.connection.autoCommit = false
        database.execute("create table elements (id int, name nvarchar(40), is_basic bit)")
        database.execute("create table combinations (first int, second int, result int)")
    }

    def cleanupSpec() {
        database.close();
    }

    def setup() {
        repo = new ElementsRepository(database.connection)
    }

    def cleanup() {
        database.rollback()
    }


}
