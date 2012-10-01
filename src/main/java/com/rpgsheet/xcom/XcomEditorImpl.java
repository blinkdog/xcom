/*
 * XcomEditorImpl.java
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

import com.rpgsheet.xcom.game.SaveGame;
import com.rpgsheet.xcom.type.Language;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("xcomEditor")
public class XcomEditorImpl extends StateBasedGame implements XcomEditor
{
    public XcomEditorImpl() {
        super("Pimp My X-COM");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        // it seems the first state is the default state
        this.addState(selectLanguageState);
        this.addState(mainMenuState);
        this.addState(displayUfoBackgroundState);
        this.addState(displayUfoPalettesState);
        this.addState(gameLoadState);
        this.addState(geoscapeState);
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public SaveGame getSaveGame() {
        return saveGame;
    }

    @Override
    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }
    
    @Autowired private GameState displayUfoBackgroundState;
    @Autowired private GameState displayUfoPalettesState;
    @Autowired private GameState gameLoadState;
    @Autowired private GameState geoscapeState;
    @Autowired private GameState mainMenuState;
    @Autowired private GameState selectLanguageState;
    
    private Language language;
    private SaveGame saveGame;
}
