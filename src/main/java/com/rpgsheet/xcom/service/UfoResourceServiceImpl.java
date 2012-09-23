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
import com.rpgsheet.xcom.io.ScrInputStream;
import com.rpgsheet.xcom.slick.Palette;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rpgsheet.xcom.service.UfoGameFileService.*;

@Service("ufoResourceService")
public class UfoResourceServiceImpl implements UfoResourceService
{
    private static final String[] BACKGROUND_NAME = {
        BACK01_SCR, BACK02_SCR, BACK03_SCR, BACK04_SCR,
        BACK05_SCR, BACK06_SCR, BACK07_SCR, BACK08_SCR,
        BACK09_SCR, BACK10_SCR, BACK11_SCR, BACK12_SCR,
        BACK13_SCR, BACK14_SCR, BACK15_SCR, BACK16_SCR,
        BACK17_SCR
    };
    
    public Image getBackground(int index, Palette palette)
    {
        if(backgrounds == null) {
            backgrounds = new HashMap<Integer,Map<Palette,Image>>();
        }
        Map<Palette,Image> imageMap = backgrounds.get(index);
        if(imageMap == null) {
            imageMap = new HashMap<Palette,Image>();
            backgrounds.put(index, imageMap);
        }
        Image background = imageMap.get(palette);
        if(background == null) {
            background = readBackground(BACKGROUND_NAME[index], palette);
            imageMap.put(palette, background);
        }
        return background;
    }
    
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

    private Image readBackground(String fileName, Palette palette)
    {
        Image background = null;

        try {
            File backgroundFile = ufoGameFileService.getGameFile(fileName);
            FileInputStream fileInputStream = new FileInputStream(backgroundFile);
            ScrInputStream scrInputStream = new ScrInputStream(fileInputStream);
            background = scrInputStream.readImage(palette);
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }

        return background;
    }
    
    private Palette[] fullPalettes;
    private Palette[] microPalettes;
    private Map<Integer,Map<Palette,Image>> backgrounds;
    
    @Autowired private UfoGameFileService ufoGameFileService;
}
