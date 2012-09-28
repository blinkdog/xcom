/*
 * Window.java
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

package com.rpgsheet.xcom.render;

import com.rpgsheet.xcom.slick.Palette;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Window implements Renderable
{
    public Window(float x1, float y1, float x2, float y2,
                  Palette palette, int borderColor, Image background)
    {
        this.background = background;
        this.borderColor = borderColor;
        this.palette = palette;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g)
    {
        // do a little math... make a little love... get down tonight!
        final float DISPLAY_WIDTH = gc.getWidth();
        final float DISPLAY_HEIGHT = gc.getHeight();
        
        float xScale = DISPLAY_WIDTH / XCOM_WIDTH;
        float yScale = DISPLAY_HEIGHT / XCOM_HEIGHT;

        float dx1 = x1*xScale;
        float dy1 = y1*yScale;
        float dx2 = x2*xScale;
        float dy2 = y2*yScale;
        float dw = ((x2-x1)+1)*xScale;
        float dh = ((y2-y1)+1)*yScale;
        
        // paint the whole background magic pink, just so we can be sure
        // that we filled it all in afterwards
        g.setColor(MAGIC_PINK);
        g.fillRect(dx1, dy1, dw, dh);

        // paint the border lines
        for(int i=0; i<5; i++) {
            int colorIndex = borderColor+Math.abs(2-i);
            g.setColor(palette.getColor(colorIndex));
            g.fillRect(dx1+(xScale*i), dy1+(yScale*i), dw-(xScale*i*2), dh-(yScale*i*2));
        }
        
        // draw the background image into place
        g.drawImage(
            background,
            dx1+(xScale*5), dy1+(yScale*5), dx2-(xScale*4), dy2-(yScale*4),
            x1+5, y1+5, x2-4, y2-4);
    }

    // TODO: Remove this MAGIC_PINK thing
    private static final Color MAGIC_PINK = new Color(255, 0, 255);

    private Image background;
    private int borderColor;
    private Palette palette;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
}
