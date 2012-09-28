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

abstract public class Window implements Renderable
{
    /**
     * Prepare the resources to be used by the Window. Subclasses
     * are expected to implement this and fill in the protected fields
     * listed immediately below. Review other concrete subclasses for
     * an example of might is expected.
     */
    abstract protected void prepareResources();
    
    /**
     * The background image of the Window
     */
    protected Image background;
    
    /**
     * The index of a color in the palette. This should be the brightest
     * color of the border (the color of the bright middle line)
     */
    protected int borderIndex;
    
    /**
     * The palette to use when drawing the window border.
     */
    protected Palette palette;
    
    /**
     * The x-coordinate of the left side of the window in classic X-COM
     * resolution (0 to 320)
     */
    protected float x1;
    
    /**
     * The y-coordinate of the top of the window in classic X-COM
     * resolution (0 to 200)
     */
    protected float y1;
    
    /**
     * The x-coordinate of the right side of the window in classic X-COM
     * resolution (0 to 320)
     */
    protected float x2;
    
    /**
     * The y-coordinate of the bottom of the window in classic X-COM
     * resolution (0 to 200)
     */
    protected float y2;
    
    @Override
    public void render(GameContainer gc, Graphics g)
    {
        // prepare the resources if needed
        if(resourcesPrepared == false) {
            prepareResources();
            resourcesPrepared = true;
        }
        
        // do a little math... make a little love... get down tonight!
        final float DISPLAY_WIDTH = gc.getWidth();
        final float DISPLAY_HEIGHT = gc.getHeight();
        
        float xScale = DISPLAY_WIDTH / XCOM_WIDTH;
        float yScale = DISPLAY_HEIGHT / XCOM_HEIGHT;

        float dx1 = x1*xScale;
        float dy1 = y1*yScale;
        float dx2 = x2*xScale;
        float dy2 = y2*yScale;
        float dw = dx2-dx1;
        float dh = dy2-dy1;
        
        // paint the whole background magic pink, just so we can be sure
        // that we filled it all in afterwards
        g.setColor(MAGIC_PINK);
        g.fillRect(dx1, dy1, dw, dh);

        // paint the border lines
        for(int i=0; i<5; i++) {
            int colorIndex = borderIndex+Math.abs(2-i);
            g.setColor(palette.getColor(colorIndex));
            g.fillRect(dx1+(xScale*i), dy1+(yScale*i), dw-(xScale*i*2), dh-(yScale*i*2));
        }
        
        // draw the background image into place
        g.drawImage(
            background,
            dx1+(xScale*5), dy1+(yScale*5), dx2-(xScale*5), dy2-(yScale*5),
            x1+5, y1+5, x2-5, y2-5);
    }
    
    private static final Color MAGIC_PINK = new Color(255, 0, 255);
    
    private boolean resourcesPrepared;
}
