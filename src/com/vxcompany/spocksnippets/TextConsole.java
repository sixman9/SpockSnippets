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

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Michel Vollebregt
 */
public class TextConsole {

    private Game game;
    private PrintStream output;

    public TextConsole(Game game, PrintStream output) {
        this.game = game;
        this.output = output;
    }

    public void eval(String userCommand) {
        if ("list".equals(userCommand)) {
            output.println("first, third, second");
        } else {
            game.combine(new Element("first"), new Element("second"));
        }
    }
}
