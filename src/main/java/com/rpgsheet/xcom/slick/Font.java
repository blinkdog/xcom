/*
 * Font.java
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

package com.rpgsheet.xcom.slick;

public class Font
{
    public Font(Glyph[] glyphs, int glyphWidth, int glyphHeight)
    {
        this.glyphs = glyphs;
        this.glyphHeight = glyphHeight;
        this.glyphWidth = glyphWidth;
    }

    public int getGlyphHeight() {
        return glyphHeight;
    }
    
    public int getGlyphWidth() {
        return glyphWidth;
    }
    
    public Glyph[] toGlyphs(String s)
    {
        Glyph[] glyphString = new Glyph[s.length()];
        
        for(int i=0; i<glyphString.length; i++) {
            char c = s.charAt(i);
            int index = ((c)-32); // ' ' is the first character
            if(index >= 0 && index < glyphs.length) {
                glyphString[i] = glyphs[index];
            } else {
                glyphString[i] = glyphs[0];
            }
        }
        
        return glyphString;
    }
    
    public int getTextWidth(String text, int spacing)
    {
        int width = 0;
        Glyph[] textGlyphs = toGlyphs(text);
        for(int i=0; i<textGlyphs.length; i++) {
            width += textGlyphs[i].getWidth();
            width += spacing;
        }
        return width;
    }
    
    private int glyphHeight;
    private int glyphWidth;
    
    private Glyph[] glyphs;
}
