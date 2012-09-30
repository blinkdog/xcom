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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Font
{
    public Font(Glyph[] glyphs, int glyphWidth, int glyphHeight)
    {
        this.glyphs = glyphs;
        this.glyphHeight = glyphHeight;
        this.glyphWidth = glyphWidth;
        this.glyphEncodings = new HashMap<String,Glyph[]>();
    }

    public int getGlyphHeight() {
        return glyphHeight;
    }
    
    public int getGlyphWidth() {
        return glyphWidth;
    }

    /**
     * Obtain the drawable glyphs that correspond to the provided String.
     * @see https://en.wikipedia.org/wiki/Code_page_437
     * @param s String to be converted into an array of Glyph objects
     * @return the drawable glyphs that correspond to the provided String
     */
    public Glyph[] toGlyphs(String s)
    {
        // if we've encoded this string before
        if(glyphEncodings.containsKey(s)) {
            return glyphEncodings.get(s);
        }
        // since we haven't encoded the string, let's do it...
        byte[] bytes = s.getBytes();
        Glyph[] glyphString = new Glyph[s.length()];
        // obtain the string bytes encoded in CP 437
        try {
            bytes = s.getBytes("CP437");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace(System.err);
        }
        // convert each byte into a drawable glyph in the array
        for(int i=0; i<glyphString.length; i++) {
            int index = (((int)bytes[i]) & 0xFF) - 32;
            if(index >= 0 && index < glyphs.length) {
                glyphString[i] = glyphs[index];
            } else {
                glyphString[i] = glyphs[0]; // unknown bytes become ' ' glyphs
            }
        }
        // since we did all that hard work, let's save it for later
        glyphEncodings.put(s, glyphString);
        // return the glyph array to the caller
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
    
    private Map<String,Glyph[]> glyphEncodings;
}
