/*
 * UfoGameFileService.java
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

import java.io.File;

public interface UfoGameFileService
{
    public static final String BACK01_SCR = "GEOGRAPH/BACK01.SCR";
    public static final String BACK02_SCR = "GEOGRAPH/BACK02.SCR";
    public static final String BACK03_SCR = "GEOGRAPH/BACK03.SCR";
    public static final String BACK04_SCR = "GEOGRAPH/BACK04.SCR";
    public static final String BACK05_SCR = "GEOGRAPH/BACK05.SCR";
    public static final String BACK06_SCR = "GEOGRAPH/BACK06.SCR";
    public static final String BACK07_SCR = "GEOGRAPH/BACK07.SCR";
    public static final String BACK08_SCR = "GEOGRAPH/BACK08.SCR";
    public static final String BACK09_SCR = "GEOGRAPH/BACK09.SCR";
    public static final String BACK10_SCR = "GEOGRAPH/BACK10.SCR";
    public static final String BACK11_SCR = "GEOGRAPH/BACK11.SCR";
    public static final String BACK12_SCR = "GEOGRAPH/BACK12.SCR";
    public static final String BACK13_SCR = "GEOGRAPH/BACK13.SCR";
    public static final String BACK14_SCR = "GEOGRAPH/BACK14.SCR";
    public static final String BACK15_SCR = "GEOGRAPH/BACK15.SCR";
    public static final String BACK16_SCR = "GEOGRAPH/BACK16.SCR";
    public static final String BACK17_SCR = "GEOGRAPH/BACK17.SCR";
    public static final String BACKPALS_DAT = "GEODATA/BACKPALS.DAT";
    public static final String BIGLETS_DAT = "GEODATA/BIGLETS.DAT";
    public static final String ENGLISH_DAT = "GEODATA/ENGLISH.DAT";
    public static final String ENGLISH2_DAT = "GEODATA/ENGLISH2.DAT";
    public static final String FRENCH_DAT = "GEODATA/FRENCH.DAT";
    public static final String FRENCH2_DAT = "GEODATA/FRENCH2.DAT";
    public static final String GERMAN_DAT = "GEODATA/GERMAN.DAT";
    public static final String GERMAN2_DAT = "GEODATA/GERMAN2.DAT";
    public static final String PALETTES_DAT = "GEODATA/PALETTES.DAT";
    public static final String SMALLSET_DAT = "GEODATA/SMALLSET.DAT";
    public static final String UFO_BAT = "UFO.BAT";
    
    public static final String ACTS_DAT     = "ACTS.DAT";
    public static final String AKNOW_DAT    = "AKNOW.DAT";
    public static final String ALIEN_DAT    = "ALIEN.DAT";
    public static final String ASTORE_DAT   = "ASTORE.DAT";
    public static final String BASE_DAT     = "BASE.DAT";
    public static final String BPROD_DAT    = "BPROD.DAT";
    public static final String CRAFT_DAT    = "CRAFT.DAT";
    public static final String DIPLOM_DAT   = "DIPLOM.DAT";
    public static final String FACIL_DAT    = "FACIL.DAT";
    public static final String IGLOB_DAT    = "IGLOB.DAT";
    public static final String INTER_DAT    = "INTER.DAT";
    public static final String LEASE_DAT    = "LEASE.DAT";
    public static final String LIGLOB_DAT   = "LIGLOB.DAT";
    public static final String LOC_DAT      = "LOC.DAT";
    public static final String MISSIONS_DAT = "MISSIONS.DAT";
    public static final String PRODUCT_DAT  = "PRODUCT.DAT";
    public static final String PROJECT_DAT  = "PROJECT.DAT";
    public static final String PURCHASE_DAT = "PURCHASE.DAT";
    public static final String RESEARCH_DAT = "RESEARCH.DAT";
    public static final String SAVEINFO_DAT = "SAVEINFO.DAT";
    public static final String SOLDIER_DAT  = "SOLDIER.DAT";
    public static final String TRANSFER_DAT = "TRANSFER.DAT";
    public static final String UIGLOB_DAT   = "UIGLOB.DAT";
    public static final String UP_DAT       = "UP.DAT";
    public static final String XBASES_DAT   = "XBASES.DAT";
    public static final String XCOM_DAT     = "XCOM.DAT";
    public static final String ZONAL_DAT    = "ZONAL.DAT";
    
    public static final String[] GEOSCAPE_FILES = {
        ACTS_DAT,
        AKNOW_DAT,
        ALIEN_DAT,
        ASTORE_DAT,
        BASE_DAT,
        BPROD_DAT,
        CRAFT_DAT,
        DIPLOM_DAT,
        FACIL_DAT,
        IGLOB_DAT,
        INTER_DAT,
        LEASE_DAT,
        LIGLOB_DAT,
        LOC_DAT,
        MISSIONS_DAT,
        PRODUCT_DAT,
        PROJECT_DAT,
        PURCHASE_DAT,
        RESEARCH_DAT,
        SAVEINFO_DAT,
        SOLDIER_DAT,
        TRANSFER_DAT,
        UIGLOB_DAT,
        UP_DAT,
        XBASES_DAT,
        XCOM_DAT,
        ZONAL_DAT
    };
    
    public File getGameFile(String fileName);
    public File getSaveFile(int saveSlot, String fileName);
    public void setUfoPath(String ufoPath);
}
