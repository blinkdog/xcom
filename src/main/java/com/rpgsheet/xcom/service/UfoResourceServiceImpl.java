/*
 * UfoResourceServiceImpl.java
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

package com.rpgsheet.xcom.service;

import com.rpgsheet.xcom.io.PaletteInputStream;
import com.rpgsheet.xcom.slick.Palette;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rpgsheet.xcom.service.UfoGameFileService.BACKPALS_DAT;
import static com.rpgsheet.xcom.service.UfoGameFileService.PALETTES_DAT;

@Service("ufoResourceService")
public class UfoResourceServiceImpl implements UfoResourceService
{
    @Override
    public Palette getPaletteFull(int index)
    {
        if(fullPalettes == null) {
            readPalettes();
        }
        return fullPalettes[index];
    }

    @Override
    public Palette getPaletteMicro(int index)
    {
        if(microPalettes == null) {
            readPalettes();
        }
        return microPalettes[index];
    }
    
    private void readPalettes()
    {
        fullPalettes = new Palette[NUM_PALETTE_FULL];
        microPalettes = new Palette[NUM_PALETTE_MICRO];
        
        // read the full palettes
        try {
            File palettesDat = ufoGameFileService.getGameFile(PALETTES_DAT);
            FileInputStream fileInputStream = new FileInputStream(palettesDat);
            PaletteInputStream paletteInputStream = new PaletteInputStream(fileInputStream);
            for(int i=0; i<NUM_PALETTE_FULL; i++) {
                fullPalettes[i] = paletteInputStream.readPaletteFull();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
        
        // read the micro palettes
        try {
            File palettesDat = ufoGameFileService.getGameFile(BACKPALS_DAT);
            FileInputStream fileInputStream = new FileInputStream(palettesDat);
            PaletteInputStream paletteInputStream = new PaletteInputStream(fileInputStream);
            for(int i=0; i<NUM_PALETTE_MICRO; i++) {
                microPalettes[i] = paletteInputStream.readPaletteMicro();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
        
    }
    
    private Palette[] fullPalettes;
    private Palette[] microPalettes;
    
    @Autowired private UfoGameFileService ufoGameFileService;
}
