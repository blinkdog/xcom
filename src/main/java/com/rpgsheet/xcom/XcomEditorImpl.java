/*
 * XcomEditorImpl.java
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

package com.rpgsheet.xcom;

import com.rpgsheet.xcom.service.UfoGameFileService;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xcomEditor")
public class XcomEditorImpl extends BasicGame implements XcomEditor
{
    public XcomEditorImpl() {
        super("UFO: Enemy Unknown (Save Game Editor)");
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException
    {
    }

    /**
     * Update the logic of the game.
     * @param gc GameContainer in which the game is running
     * @param timeDelta milliseconds since the last call to update() finished
     * @throws SlickException if anything bad happens
     */
    @Override
    public void update(GameContainer gc, int timeDelta) throws SlickException
    {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.clear();
    }
    
    @Autowired private UfoGameFileService ufoGameFileService;
}
