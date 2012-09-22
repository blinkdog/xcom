/*
 * Palette.java
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

import org.newdawn.slick.Color;

public class Palette
{
    public Palette(int numColors) {
        colors = new Color[numColors];
        extraData = null;
    }

    public Color getColor(int index) {
        return colors[index];
    }
    
    public byte[] getExtraData() {
        return extraData;
    }
    
    public int getNumColors() {
        return colors.length;
    }

    public void setColor(int index, Color color) {
        colors[index] = color;
    }

    public void setExtraData(byte[] extraData) {
        this.extraData = extraData;
    }
    
    private Color[] colors;
    private byte[] extraData;
}
