/*
 * Button.java
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

import com.rpgsheet.xcom.slick.Font;
import com.rpgsheet.xcom.slick.Glyph;
import com.rpgsheet.xcom.slick.Palette;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Button implements Renderable
{
    public Button(float x1, float y1, float x2, float y2,
                  Palette palette, int buttonColor,
                  Font font, int labelColor, String labelText)
    {
        this.buttonColor = buttonColor;
        this.font = font;
        this.labelText = labelText;
        this.labelColor = labelColor;
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
        
        // paint the brightest color in the upper left
        g.setColor(palette.getColor(buttonColor));
        g.fillRect(dx1, dy1, dw, dh);

        // paint the darkest color in the lower right
        g.setColor(palette.getColor(buttonColor+4));
        g.fillRect(dx1+xScale, dy1+yScale, dw-xScale, dh-yScale);
        // paint a strip to cleanup the far right side
        g.fillRect(dx2, dy1, xScale, dh);

        // paint the 2nd brightest color in the inner upper left
        g.setColor(palette.getColor(buttonColor+1));
        g.fillRect(dx1+xScale, dy1+yScale, dw-(2*xScale), dh-(2*yScale));

        // paint the 2nd darkest color in the inner lower right
        g.setColor(palette.getColor(buttonColor+3));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(3*xScale), dh-(3*yScale));
        // paint a strip to cleanup the inner far right side
        g.fillRect(dx2-xScale, dy1+yScale, xScale, dh-(2*yScale));

        // paint the mid-shade over the center of the button
        g.setColor(palette.getColor(buttonColor+2));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(4*xScale), dh-(4*yScale));

        // if we haven't computed the label yet, compute it now
        if(label == null) {
            // determine the spacing between the characters
            int spacing = 0;
            if(font.getGlyphHeight() == Glyph.GLYPH_SMALL_HEIGHT) {
                spacing = -1;
            }
            // determine the dimensions and coordinates of the label
            int labelWidth = font.getTextWidth(labelText, spacing);
            int glyphHeight = font.getGlyphHeight();
            float lx1 = ((x1 + (((x2-x1)+1)/2)) - (labelWidth/2));
            float ly1 = ((y1 + (((y2-y1)+1)/2)) - (glyphHeight/2));
            // create the label
            label = new Label(font, palette, labelColor, spacing, lx1, ly1, labelText);
        }
        label.render(gc, g);
    }

    private int buttonColor;
    private Font font;
    private Label label;
    private int labelColor;
    private String labelText;
    private Palette palette;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
}
