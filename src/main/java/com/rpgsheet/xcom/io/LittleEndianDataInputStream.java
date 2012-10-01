/*
 * LittleEndianDataInputStream.java
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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LittleEndianDataInputStream
{
    public LittleEndianDataInputStream(InputStream inputStream)
    {
        this.dataInputStream = new DataInputStream(inputStream);
    }
    
    public int readUnsignedShort() throws IOException {
        int lb = dataInputStream.readUnsignedByte();
        int hb = dataInputStream.readUnsignedByte();
        return ((hb << 8) | (lb));
    }

    public void read(byte[] bytes) throws IOException {
        dataInputStream.readFully(bytes);
    }
    
    private DataInputStream dataInputStream;
}
