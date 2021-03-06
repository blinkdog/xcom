/*
 * GeoscapeState.java
 * Copyright 2012 Patrick Meade
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rpgsheet.xcom.state;

import com.rpgsheet.xcom.XcomEditor;
import com.rpgsheet.xcom.game.SaveInfo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("geoscapeState")
public class GeoscapeState extends BasicGameState
{
    /**
     * Generated by Random.Org
     * @see http://www.random.org/cgi-bin/randbyte?nbytes=4&format=h
     */
    public static final int ID = 0x1d8bacde;
    
    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        // nothing to init
    }
    
    @Override
    public void enter(final GameContainer gc, final StateBasedGame sbg)
    {
        // nothing to enter
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        // nothing to render
    }

    public void update(GameContainer gc, StateBasedGame sbg, int timeDelta)
            throws SlickException
    {
        // just to show that we loaded the game correctly
        SaveInfo saveInfo = new SaveInfo(xcomEditor.getSaveGame().saveinfo);
        System.out.println(
            "Date: " + saveInfo.getYear() + "-" + (saveInfo.getMonth()+1) + "-" + saveInfo.getDay() +
            "  Time: " + saveInfo.getHour() + ":" + saveInfo.getMinute() +
            "  Game: '" + saveInfo.getSaveName() + "'");
        // now exit this editor until we can come up with something good
        // to do with all this cool save game data
        gc.exit();
    }

    @Override
    public void mousePressed(int button, int x, int y)
    {
        // there is nothing for mouse input
    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
        // there is nothing for mouse input
    }
    
    @Autowired private XcomEditor xcomEditor;
}
