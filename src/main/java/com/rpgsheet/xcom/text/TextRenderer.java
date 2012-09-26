/*
 * TextRenderer.java
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

package com.rpgsheet.xcom.text;

import com.rpgsheet.xcom.slick.Font;
import com.rpgsheet.xcom.slick.Glyph;
import com.rpgsheet.xcom.slick.Palette;
import com.rpgsheet.xcom.slick.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static com.rpgsheet.xcom.io.GlyphInputStream.*;

public class TextRenderer implements Renderable
{
    public TextRenderer(Font font,
                        Palette palette, int colorIndex,
                        int spacing,
                        float x1, float y1,
                        String text)
    {
        this.font = font;
        this.palette = palette;
        this.colorIndex = colorIndex;
        this.spacing = spacing;
        this.x1 = x1;
        this.y1 = y1;
        this.text = text;
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

        // determine what glyphs need to be drawn on the display
        Glyph[] textGlyphs = font.toGlyphs(text);
        for(int i=0; i<textGlyphs.length; i++)
        {
            Image glyphImage = textGlyphs[i].getImage(palette, colorIndex);
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
    
    private static final float XCOM_WIDTH = 320;
    private static final float XCOM_HEIGHT = 200;
    
    private Font font;
    private Palette palette;
    private int colorIndex;
    private int spacing;
    private float x1;
    private float y1;
    private String text;
}
