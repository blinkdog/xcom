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

import com.rpgsheet.xcom.io.GlyphInputStream;
import com.rpgsheet.xcom.io.PaletteInputStream;
import com.rpgsheet.xcom.io.ScrInputStream;
import com.rpgsheet.xcom.slick.Font;
import com.rpgsheet.xcom.slick.Glyph;
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
import static com.rpgsheet.xcom.slick.Glyph.*;

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

    @Override
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
    public Font getFontLarge()
    {
        if(fontLarge == null) {
            readFontLarge();
        }
        return fontLarge;
    }
    
    @Override
    public Font getFontSmall()
    {
        if(fontSmall == null) {
            readFontSmall();
        }
        return fontSmall;
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

    private void readFontLarge()
    {
        File bigLetsDat = ufoGameFileService.getGameFile(BIGLETS_DAT);
        int numGlyphs = 128;
        if(bigLetsDat.length() == 44288) {
            numGlyphs = 173;
        }
        Glyph[] glyphs = new Glyph[numGlyphs+1];
        glyphs[0] = new Glyph(GLYPH_LARGE_WIDTH, GLYPH_LARGE_HEIGHT);
        try {
            FileInputStream fileInputStream = new FileInputStream(bigLetsDat);
            GlyphInputStream glyphInputStream = new GlyphInputStream(fileInputStream);
            for(int i=1; i<=numGlyphs; i++) {
                glyphs[i] = glyphInputStream.readLargeGlyph();
            }
            fontLarge = new Font(glyphs, GLYPH_LARGE_WIDTH, GLYPH_LARGE_HEIGHT);
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
    }
    
    private void readFontSmall()
    {
        File smallSetDat = ufoGameFileService.getGameFile(SMALLSET_DAT);
        int numGlyphs = 128;
        if(smallSetDat.length() == 12456) {
            numGlyphs = 173;
        }
        Glyph[] glyphs = new Glyph[numGlyphs+1];
        glyphs[0] = new Glyph(GLYPH_SMALL_WIDTH, GLYPH_SMALL_HEIGHT);
        try {
            FileInputStream fileInputStream = new FileInputStream(smallSetDat);
            GlyphInputStream glyphInputStream = new GlyphInputStream(fileInputStream);
            for(int i=1; i<=numGlyphs; i++) {
                glyphs[i] = glyphInputStream.readSmallGlyph();
            }
            fontSmall = new Font(glyphs, GLYPH_SMALL_WIDTH, GLYPH_SMALL_HEIGHT);
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
    }
    
    private Font fontLarge;
    private Font fontSmall;
    private Palette[] fullPalettes;
    private Palette[] microPalettes;
    private Map<Integer,Map<Palette,Image>> backgrounds;
    
    @Autowired private UfoGameFileService ufoGameFileService;
}
