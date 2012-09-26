/*
 * GlyphInputStream.java
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

package com.rpgsheet.xcom.io;

import com.rpgsheet.xcom.slick.Glyph;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.rpgsheet.xcom.slick.Glyph.*;

public class GlyphInputStream
{
    public GlyphInputStream(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    public Glyph readLargeGlyph() throws IOException
    {
        byte[][] glyphData = new byte[GLYPH_LARGE_HEIGHT][GLYPH_LARGE_WIDTH];
        for(int i=0; i<GLYPH_LARGE_HEIGHT; i++) {
            dataInputStream.readFully(glyphData[i]);
        }
        return new Glyph(glyphData);
    }
    
    public Glyph readSmallGlyph() throws IOException
    {
        byte[][] glyphData = new byte[GLYPH_SMALL_HEIGHT][GLYPH_SMALL_WIDTH];
        for(int i=0; i<GLYPH_SMALL_HEIGHT; i++) {
            dataInputStream.readFully(glyphData[i]);
        }
        return new Glyph(glyphData);
    }
    
    private DataInputStream dataInputStream;
}
