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
    public static final String PALETTES_DAT = "GEODATA/PALETTES.DAT";
    public static final String UFO_BAT = "UFO.BAT";
    
    public File getGameFile(String fileName);
}
