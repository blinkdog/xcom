/*
 * Label.java
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
import com.rpgsheet.xcom.type.LabelStyle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static com.rpgsheet.xcom.type.LabelStyle.*;

public class Label implements Renderable
{
    public Label(Font font, Palette palette, int colorIndex, int spacing,
                 float x1, float y1, String text)
    {
        this.font = font;
        this.palette = palette;
        this.colorIndex = colorIndex;
        this.spacing = spacing;
        this.x1 = x1;
        this.y1 = y1;
        this.text = text;
        // ------------------------------------------------------------
        this.x2 = XCOM_WIDTH - 1;
        this.y2 = XCOM_HEIGHT - 1;
        this.labelStyle = SINGLE_LINE_LEFT;
        // ------------------------------------------------------------
        inverted = false;
    }

    public Label(Font font, Palette palette, int colorIndex,
                 int spacing, LabelStyle labelStyle,
                 float x1, float y1, float x2, float y2, String text)
    {
        this.font = font;
        this.palette = palette;
        this.colorIndex = colorIndex;
        this.spacing = spacing;
        this.labelStyle = labelStyle;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.text = text;
        // ------------------------------------------------------------
        inverted = false;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g)
    {
        // do a little math... make a little love... get down tonight!
        final float DISPLAY_WIDTH = gc.getWidth();
        final float DISPLAY_HEIGHT = gc.getHeight();

        float xScale = DISPLAY_WIDTH / XCOM_WIDTH;
        float yScale = DISPLAY_HEIGHT / XCOM_HEIGHT;

        float dx1 = 0;
        float dy1 = 0;
        
        // determine where the drawing should start
        if(labelStyle == SINGLE_LINE_LEFT)
        {
            dx1 = x1*xScale;
            dy1 = y1*yScale;
        }
        else if(labelStyle == SINGLE_LINE_CENTER)
        {
            // center the label horizontalled in the provided area
            // but use the y1 coordinate, as we're only drawing
            // a single line of text
            int labelWidth = font.getTextWidth(text, spacing);
            float lx1 = ((x1 + (((x2-x1)+1)/2)) - (labelWidth/2));
            
            dx1 = lx1*xScale;
            dy1 = y1*yScale;
        }

        // determine what glyphs need to be drawn on the display
        Glyph[] textGlyphs = font.toGlyphs(text);
        for(int i=0; i<textGlyphs.length; i++)
        {
            Image glyphImage;
            if(inverted) {
                glyphImage = textGlyphs[i].getInvertedImage(palette, colorIndex);
            } else {
                glyphImage = textGlyphs[i].getImage(palette, colorIndex);
            }
            g.drawImage(glyphImage,
                        dx1, dy1,
                        dx1+font.getGlyphWidth()*xScale,
                        dy1+font.getGlyphHeight()*yScale,
                        0, 0,
                        font.getGlyphWidth(),
                        font.getGlyphHeight());
            int width = textGlyphs[i].getWidth();
            width += spacing;
            dx1 += (width * xScale);
        }
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    
    private Font font;
    private Palette palette;
    private int colorIndex;
    private int spacing;
    private float x1;
    private float y1;
    private String text;

    private float x2;
    private float y2;
    private LabelStyle labelStyle;

    private boolean inverted;
}
