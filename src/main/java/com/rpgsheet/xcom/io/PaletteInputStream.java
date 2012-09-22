/*
 * PaletteInputStream.java
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

import com.rpgsheet.xcom.slick.Palette;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.Color;

public class PaletteInputStream
{
    public static final int NUM_BYTES_EXTRA_DATA = 6;
    public static final int NUM_PALETTE_FULL_COLORS = 256;
    public static final int NUM_PALETTE_MICRO_COLORS = 16;
    
    public PaletteInputStream(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    public Palette readPaletteFull() throws IOException
    {
        Palette palette = new Palette(NUM_PALETTE_FULL_COLORS);
        
        for(int i=0; i<NUM_PALETTE_FULL_COLORS; i++) {
            // UFO: Enemy Unknown palettes are on a 0-63 basis
            // but Slick2D is on a 0-255 basis. Multiplying by
            // 4 (or left-shifting by 2) is the proper conversion
            int r = dataInputStream.readUnsignedByte() << 2;
            int g = dataInputStream.readUnsignedByte() << 2;
            int b = dataInputStream.readUnsignedByte() << 2;
            palette.setColor(i, new Color(r,g,b));
        }
        
        // not sure what these extra bytes are used for (if anything)
        // but in case some psionic pulls it from the mind of an
        // alien commander, here it is, provided for you
        byte[] extraData = new byte[NUM_BYTES_EXTRA_DATA];
        dataInputStream.readFully(extraData);
        palette.setExtraData(extraData);
        
        return palette;
    }

    public Palette readPaletteMicro() throws IOException
    {
        Palette palette = new Palette(NUM_PALETTE_MICRO_COLORS);
        
        for(int i=0; i<NUM_PALETTE_MICRO_COLORS; i++) {
            // UFO: Enemy Unknown palettes are on a 0-63 basis
            // but Slick2D is on a 0-255 basis. Multiplying by
            // 4 (or left-shifting by 2) is the proper conversion
            int r = dataInputStream.readUnsignedByte() << 2;
            int g = dataInputStream.readUnsignedByte() << 2;
            int b = dataInputStream.readUnsignedByte() << 2;
            palette.setColor(i, new Color(r,g,b));
        }
        
        // micro palettes do not feature extra data
        palette.setExtraData(null);
        
        return palette;
    }
    
    private DataInputStream dataInputStream;
}
