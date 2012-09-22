/*
 * PimpMyXcom.java
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

package com.rpgsheet.xcom;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PimpMyXcom
{
    public static final int DISPLAY_SCALE = 4;
    public static final int DISPLAY_WIDTH = 320 * DISPLAY_SCALE;
    public static final int DISPLAY_HEIGHT = 200 * DISPLAY_SCALE;
    public static final boolean USE_FULLSCREEN = false;
    
    public static void main(String[] args)
    {
        // create the Spring application context
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("classpath:xcom.xml");
        // create the XcomEditor application
        XcomEditor xcomEditor = appContext.getBean(XcomEditor.class);
        // run XcomEditor in Slick2D's AppGameContainer
        try {
            AppGameContainer app = new AppGameContainer(xcomEditor);
            app.setDisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT, USE_FULLSCREEN);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace(System.err);
        }
    }
}
