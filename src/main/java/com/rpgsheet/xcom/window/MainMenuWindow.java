/*
 * MainMenuWindow.java
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

package com.rpgsheet.xcom.window;

import com.rpgsheet.xcom.service.UfoResourceService;
import com.rpgsheet.xcom.slick.Palette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainMenuWindow")
public class MainMenuWindow extends WindowRenderer
{
    @Override
    protected void prepareResources()
    {
        palette = ufoResourceService.getPaletteFull(0);
        Palette imagePalette = ufoResourceService.getPaletteMicro(0);
        background = ufoResourceService.getBackground(0, imagePalette);
        borderIndex = 134;
        x1 = 32;
        y1 = 20;
        x2 = 288;
        y2 = 180;
    }
    
    @Autowired private UfoResourceService ufoResourceService;
}
