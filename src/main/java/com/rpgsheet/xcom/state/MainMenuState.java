/*
 * MainMenuState.java
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

package com.rpgsheet.xcom.state;

import com.rpgsheet.xcom.XcomEditor;
import com.rpgsheet.xcom.render.Button;
import com.rpgsheet.xcom.render.Label;
import com.rpgsheet.xcom.render.Renderable;
import com.rpgsheet.xcom.render.Window;
import com.rpgsheet.xcom.service.UfoResourceService;
import com.rpgsheet.xcom.slick.Font;
import com.rpgsheet.xcom.slick.Palette;
import com.rpgsheet.xcom.type.LabelStyle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.rpgsheet.xcom.service.TextResource.*;

@Component("mainMenuState")
public class MainMenuState extends BasicGameState
{
    /**
     * Generated by Random.Org
     * @see http://www.random.org/cgi-bin/randbyte?nbytes=4&format=h
     */
    public static final int ID = 0x4c12d540;
    
    @Override
    public int getID() {
        return ID;
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        // nothing to init
    }
    
    @Override
    public void enter(final GameContainer gc, final StateBasedGame sbg)
    {
        Font largeFont = ufoResourceService.getFontLarge();
        Font smallFont = ufoResourceService.getFontSmall();
        Palette imagePalette = ufoResourceService.getPaletteMicro(0);
        Palette mainPalette = ufoResourceService.getPaletteFull(0);
        Image background = ufoResourceService.getBackground(0, imagePalette);

        String[] appText = ufoResourceService.getTextApplication(xcomEditor.getLanguage());
        String[] geoText = ufoResourceService.getTextGeoscape(xcomEditor.getLanguage());
        String selectLanguage = appText[SELECT_LANGUAGE];
        String loadSavedGame = geoText[LOAD_SAVED_GAME];
        String quit = geoText[QUIT];
        String title = geoText[UFO_ENEMY_UNKNOWN];
        String[] titleSplit = title.split("\u0002");
        
        languageButton = new Button(64, 90, 255, 109, mainPalette, 134, smallFont, 134, selectLanguage,
            new Runnable() {
                public void run() {
                    xcomEditor.setLanguage(null);
                    sbg.enterState(SelectLanguageState.ID);
                }
            });
        loadGameButton = new Button(64, 118, 255, 137, mainPalette, 134, smallFont, 134, loadSavedGame,
            new Runnable() {
                public void run() {
                    sbg.enterState(GameLoadState.ID);
                }
            });
        quitButton = new Button(64, 146, 255, 165, mainPalette, 134, smallFont, 134, quit,
            new Runnable() {
                public void run() {
                    gc.exit();
                }
            });
        
        ufoTitle = new Label(largeFont, mainPalette, 139, 0, 145, 45, titleSplit[0]);
        enemyUnknownSubtitle = new Label(
            smallFont, mainPalette, 139, -1,
            LabelStyle.SINGLE_LINE_CENTER,
            0, 61, 319, 199, titleSplit[1]);
        
        mainMenuWindow = new Window(32, 20, 287, 179, mainPalette, 134, background);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        mainMenuWindow.render(gc,g);
        languageButton.render(gc, g);
        loadGameButton.render(gc, g);
        quitButton.render(gc, g);
        ufoTitle.render(gc, g);
        enemyUnknownSubtitle.render(gc, g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int timeDelta)
            throws SlickException
    {
        // there is nothing to update
    }

    @Override
    public void mousePressed(int button, int x, int y)
    {
        languageButton.mousePressed(button, x, y);
        loadGameButton.mousePressed(button, x, y);
        quitButton.mousePressed(button, x, y);
    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
        languageButton.mouseReleased(button, x, y);
        loadGameButton.mouseReleased(button, x, y);
        quitButton.mouseReleased(button, x, y);
    }
    
    private Renderable mainMenuWindow;
    private Button languageButton;
    private Button loadGameButton;
    private Button quitButton;
    private Renderable ufoTitle;
    private Renderable enemyUnknownSubtitle;

    @Autowired private UfoResourceService ufoResourceService;
    @Autowired private XcomEditor xcomEditor;
}
