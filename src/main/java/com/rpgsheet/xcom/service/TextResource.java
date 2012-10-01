/*
 * TextResource.java
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

public interface TextResource
{
    // Application Text Indexes
    public static final int SELECT_LANGUAGE = 0;
    
    // Geoscape Text Indexes
    public static final int CANCEL = 49;
    public static final int LOAD_SAVED_GAME = 781;
    public static final int QUIT = 801;
    public static final int SELECT_GAME_TO_LOAD = 790;
    public static final int UFO_ENEMY_UNKNOWN = 779;
    
    // ----------------------------------------------------------------
    
    public static final String[] TEXT_APPLICATION_ENGLISH = {
        "Select Language"               // 0: Select Language
    };
    
    /**
     * Translations provided by Google Translate.
     * @see http://translate.google.com/#en/fr/
     */
    public static final String[] TEXT_APPLICATION_FRENCH = {
        "Sélectionnez la langue"        // 0: Select Language
    };

    /**
     * Translations provided by Google Translate.
     * @see http://translate.google.com/#en/de/
     */
    public static final String[] TEXT_APPLICATION_GERMAN = {
        "Sprache auswählen"             // 0: Select Language
    };
}
