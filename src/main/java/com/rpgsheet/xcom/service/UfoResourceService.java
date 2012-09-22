/*
 * UfoResourceService.java
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

import com.rpgsheet.xcom.slick.Palette;

public interface UfoResourceService
{
    public static final int NUM_PALETTE_FULL = 5;
    public static final int NUM_PALETTE_MICRO = 8;
    
    public Palette getPaletteFull(int index);
    public Palette getPaletteMicro(int index);
}