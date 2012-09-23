/*
 * ScrInputStream.java
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
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;

public class ScrInputStream
{
    public ScrInputStream(InputStream inputStream)
    {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    public Image readImage(Palette palette) throws IOException
    {
        ImageBuffer imageBuffer = new ImageBuffer(320, 200);

        for(int y=0; y<200; y++) {
            for(int x=0; x<320; x++) {
                int color = dataInputStream.readUnsignedByte();
                Color c = palette.getColor(color & (palette.getNumColors()-1));
                imageBuffer.setRGBA(x, y, c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
            }
        }
        
        return imageBuffer.getImage(Image.FILTER_NEAREST);
    }

    private DataInputStream dataInputStream;
}
