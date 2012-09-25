/*
 * ButtonRenderer.java
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

package com.rpgsheet.xcom.button;

import com.rpgsheet.xcom.slick.Palette;
import com.rpgsheet.xcom.slick.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ButtonRenderer implements Renderable
{
    public ButtonRenderer(Palette palette, int colorIndex,
                          float x1, float y1, float x2, float y2)
    {
        this.palette = palette;
        this.colorIndex = colorIndex;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void renderTo(GameContainer gc, Graphics g)
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
        float dw = dx2-dx1;
        float dh = dy2-dy1;
        
        // paint the brightest color in the upper left
        g.setColor(palette.getColor(colorIndex));
        g.fillRect(dx1, dy1, dw, dh);

        // paint the darkest color in the lower right
        g.setColor(palette.getColor(colorIndex+4));
        g.fillRect(dx1+xScale, dy1+yScale, dw-xScale, dh-yScale);
        // paint a strip to cleanup the far right side
        g.fillRect(dx2-xScale, dy1, xScale, dh);

        // paint the 2nd brightest color in the inner upper left
        g.setColor(palette.getColor(colorIndex+1));
        g.fillRect(dx1+xScale, dy1+yScale, dw-(2*xScale), dh-(2*yScale));

        // paint the 2nd darkest color in the inner lower right
        g.setColor(palette.getColor(colorIndex+3));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(3*xScale), dh-(3*yScale));
        // paint a strip to cleanup the inner far right side
        g.fillRect(dx2-(2*xScale), dy1+yScale, xScale, dh-(2*yScale));

        // paint the mid-shade over the center of the button
        g.setColor(palette.getColor(colorIndex+2));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(4*xScale), dh-(4*yScale));
    }
    
    private static final float XCOM_WIDTH = 320;
    private static final float XCOM_HEIGHT = 200;
    
    private int colorIndex;
    private Palette palette;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
}
