/*
 * Glyph.java
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

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;

public class Glyph
{
    public static final int GLYPH_LARGE_HEIGHT = 16;
    public static final int GLYPH_LARGE_WIDTH = 16;
    public static final int GLYPH_SMALL_HEIGHT = 9;
    public static final int GLYPH_SMALL_WIDTH = 8;

    /**
     * Create a Glyph based on data from a file.
     * @param glyphData 
     */
    public Glyph(byte[][] glyphData) {
        this.glyphData = glyphData;
        this.space = false;
    }

    /**
     * Create a Glyph based on the nominal width of a glyph. This constructor
     * is used exclusively for generating a ' ' (space) character glyph.
     * @param width
     * @param height 
     */
    public Glyph(int width, int height) {
        // the data will all be 0 (transparent)
        this.glyphData = new byte[height][width];
        this.space = true;
    }
    
    public int getWidth() {
        // the ' ' (space) character has a defined width
        if(space == true) {
            if(glyphData[0].length == GLYPH_LARGE_WIDTH) {
                return 10;
            }
            if(glyphData[0].length == GLYPH_SMALL_WIDTH) {
                return 6;
            }
        }
        // otherwise, calculate the width based upon the glyph image
        int widest = 0;
        for(int i=0; i<glyphData.length; i++) {
            for(int j=0; j<glyphData[i].length; j++) {
                if(glyphData[i][j] != 0) {
                    widest = Math.max(widest, j+1);
                }
            }
        }
        return widest;
    }
    
    public Image getImage(Palette palette, int colorIndex) {
        if(imageMap == null) {
            imageMap = new HashMap<Integer,Map<Palette,Image>>();
        }
        Map<Palette,Image> imageMap2 = imageMap.get(colorIndex);
        if(imageMap2 == null) {
            imageMap2 = new HashMap<Palette,Image>();
            imageMap.put(colorIndex, imageMap2);
        }
        Image image = imageMap2.get(palette);
        if(image == null) {
            image = renderGlyph(palette, colorIndex, NORMAL);
            imageMap2.put(palette, image);
        }
        return image;
    }

    public Image getInvertedImage(Palette palette, int colorIndex) {
        if(invertMap == null) {
            invertMap = new HashMap<Integer,Map<Palette,Image>>();
        }
        Map<Palette,Image> invertMap2 = invertMap.get(colorIndex);
        if(invertMap2 == null) {
            invertMap2 = new HashMap<Palette,Image>();
            invertMap.put(colorIndex, invertMap2);
        }
        Image image = invertMap2.get(palette);
        if(image == null) {
            image = renderGlyph(palette, colorIndex, INVERTED);
            invertMap2.put(palette, image);
        }
        return image;
    }
    
    private Image renderGlyph(Palette palette, int colorIndex, boolean inverted)
    {
        int height = glyphData.length;
        int width = glyphData[0].length;
        
        ImageBuffer imageBuffer = new ImageBuffer(width, height);
        
        if(inverted) {
            colorIndex += 5;
        } else {
            colorIndex--; // data is 1-based (1 to 5)
        }
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                if(glyphData[y][x] == 0) {
                    imageBuffer.setRGBA(x,y,255,0,255,0);
                } else {
                    Color c;
                    if(inverted) {
                        c = palette.getColor(colorIndex - glyphData[y][x]);
                    } else {
                        c = palette.getColor(colorIndex + glyphData[y][x]);
                    }
                    imageBuffer.setRGBA(x, y, c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
                }
            }
        }
        
        return imageBuffer.getImage(Image.FILTER_NEAREST);
    }

    private static final boolean NORMAL = false;
    private static final boolean INVERTED = true;
    
    private boolean space;
    private byte[][] glyphData;
    private Map<Integer,Map<Palette,Image>> imageMap;
    private Map<Integer,Map<Palette,Image>> invertMap;
}
