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

import com.rpgsheet.xcom.io.PaletteInputStream;
import com.rpgsheet.xcom.service.UfoResourceService;
import com.rpgsheet.xcom.slick.Palette;
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
        renderUfoPalettes(gc,g);
    }
    
    private void renderUfoPalettes(GameContainer gc, Graphics g) throws SlickException
    {
        int DISPLAY_WIDTH = gc.getWidth();
        int DISPLAY_HEIGHT = gc.getHeight();
        int PAL_WIDTH = (DISPLAY_WIDTH / PaletteInputStream.NUM_PALETTE_FULL_COLORS);
        int topHeight = DISPLAY_HEIGHT >> 1;
        
        g.clear();
        
        for(int j=0; j<UfoResourceService.NUM_PALETTE_FULL; j++) {
            Palette palette = ufoResourceService.getPaletteFull(j);
            for(int i=0; i<palette.getNumColors(); i++) {
                g.setColor(palette.getColor(i));
                g.fillRect(i*PAL_WIDTH, j*(topHeight / UfoResourceService.NUM_PALETTE_FULL),
                           PAL_WIDTH, (topHeight / UfoResourceService.NUM_PALETTE_FULL));
            }
        }
        
        for(int j=0; j<UfoResourceService.NUM_PALETTE_MICRO; j++) {
            Palette palette = ufoResourceService.getPaletteMicro(j);
            for(int i=0; i<palette.getNumColors(); i++) {
                g.setColor(palette.getColor(i));
                g.fillRect(
                        ((j*palette.getNumColors())*PAL_WIDTH) + (i*PAL_WIDTH),
                        topHeight+1,
                        PAL_WIDTH,
                        (DISPLAY_HEIGHT-topHeight));
            }
        }
    }

    @Autowired private UfoResourceService ufoResourceService;
}
