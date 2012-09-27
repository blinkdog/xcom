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

import com.rpgsheet.xcom.service.UfoGameFileService;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFileChooser;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.rpgsheet.xcom.service.UfoGameFileService.*;

public class PimpMyXcom implements Runnable
{
    public static final int DISPLAY_SCALE = 4;
    public static final int DISPLAY_WIDTH = 320 * DISPLAY_SCALE;
    public static final int DISPLAY_HEIGHT = 200 * DISPLAY_SCALE;
    public static final boolean USE_FULLSCREEN = false;

    private static Logger log = LoggerFactory.getLogger(PimpMyXcom.class);
    private static ApplicationContext appContext;
    
    public static void main(String[] args)
    {
        // create the Spring application context
        appContext = new ClassPathXmlApplicationContext("classpath:xcom.xml");
        // load the saved properties for the application
        Properties appProperties = loadApplicationProperties();
        // if we can't load application properties, that's bad
        if(appProperties == null) {
            log.error("Unable to determine location of X-COM.");
            return;
        }
        // otherwise we'll check to see that X-COM exists
        String xcomPath = appProperties.getProperty("xcom.path");
        File xcomDir = new File(xcomPath);
        if(containsXcom(xcomDir) == false) {
            openXcomPathDialog(appProperties);
        }
        // if don't know where X-COM is
        xcomPath = appProperties.getProperty("xcom.path");
        xcomDir = new File(xcomPath);
        if(containsXcom(xcomDir) == false) {
            log.error("Unable to locate X-COM");
            return;
        }
        // we know where X-COM is; so we tell the appropriate beans
        UfoGameFileService ufoGameFileService = appContext.getBean(UfoGameFileService.class);
        ufoGameFileService.setUfoPath(xcomPath);
        // create and run the PimpMyXcom application
        PimpMyXcom pimpMyXcom = new PimpMyXcom();
        pimpMyXcom.run();
    }

    @Override
    public void run()
    {
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
    
    private static Properties loadApplicationProperties()
    {
        String homePath = System.getProperty(SYSTEM_PROPERTY_USER_HOME);
        // verify that the home directory exists and can be read
        File homeDir = new File(homePath);
        if(homeDir.exists() == false) {
            log.error("Home directory '{}' does not exist.", homeDir.getAbsolutePath());
            return null;
        }
        if(homeDir.canRead() == false) {
            log.error("Unable to read home directory '{}'.", homeDir.getAbsolutePath());
            return null;
        }
        // verify that the application properties file exists and can be read
        File appPropertiesFile = new File(homeDir, APPLICATION_PROPERTIES_FILE);
        try {
            createApplicationProperties(appPropertiesFile);
        } catch(IOException e) {
            log.error("Exception while creating application properties file.", e);
            return null;
        }
        // read the application properties from the disk
        Properties appProperties = new Properties();
        try {
            FileReader fileReader = new FileReader(appPropertiesFile);
            appProperties.load(fileReader);
        } catch(FileNotFoundException e) {
            log.error("Exception while trying to read application properties file.", e);
        } catch(IOException e) {
            log.error("Exception while trying to read application properties file.", e);
        }
        // return the read properties to the caller
        return appProperties;
    }
    
    private static void createApplicationProperties(File appPropertiesFile)
            throws IOException
    {
        // if we've already got one, skip this creation step
        if(appPropertiesFile.exists()) return;
        // otherwise, create a default set of properties
        Properties appProperties = new Properties();
        appProperties.setProperty("xcom.lang", APPLICATION_DEFAULT_LANG);
        appProperties.setProperty("xcom.path", APPLICATION_DEFAULT_PATH);
        // and write them to the disk
        saveApplicationProperties(appPropertiesFile, appProperties);
    }

    private static void openXcomPathDialog(Properties appProperties)
    {
        // display the box to the user
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("PimpMyXcom - Where is X-COM?");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retCode = fileChooser.showOpenDialog(null);
        // if the user cancelled the dialog, there is nothing we can do
        if(retCode == JFileChooser.CANCEL_OPTION) {
            log.error("User cancel while attempting to locate X-COM.");
            return;
        }
        // if there was an error, there is nothing we can do
        if(retCode == JFileChooser.ERROR_OPTION) {
            log.error("Error while attempting to locate X-COM: {}",
                      JFileChooser.ERROR_OPTION);
            return;
        }
        // if the user chose a directory, then we have some work to do
        String xcomPath = fileChooser.getSelectedFile().getAbsolutePath();
        File xcomDir = new File(xcomPath);
        if(containsXcom(xcomDir)) {
            // store the path to xcom in the application properties
            appProperties.setProperty("xcom.path", xcomPath);
            // write the updated application properties to disk
            String homePath = System.getProperty(SYSTEM_PROPERTY_USER_HOME);
            File homeDir = new File(homePath);
            File appPropertiesFile = new File(homeDir, APPLICATION_PROPERTIES_FILE);
            try {
                saveApplicationProperties(appPropertiesFile, appProperties);
            } catch(IOException e) {
                log.error("Unable to store X-COM path in application properties.", e);
                return;
            }
        }
        // if we were unable to find X-COM
        else {
            log.error("User provided location did not contain X-COM.");
            return;
        }
    }
    
    private static boolean containsXcom(File xcomDir)
    {
        UfoGameFileService ufoGameFileService = appContext.getBean(UfoGameFileService.class);
        ufoGameFileService.setUfoPath(xcomDir.getAbsolutePath());

        File ufoBat = ufoGameFileService.getGameFile(UFO_BAT);
        if(ufoBat.exists() == false) { return false; }
        
        for(int i=1; i<=10; i++) {
            File saveGameDir = ufoGameFileService.getGameFile("GAME_" + i);
            if(saveGameDir.exists() == false) { return false; }
        }

        // okay, so maybe it isn't as thorough a test as one might hope
        // but hey, if you want to trick the editor, then be my guest
        // if you break it, you still get to keep all the pieces
        return true;
    }

    private static void saveApplicationProperties(
            File appPropertiesFile, Properties appProperties)
        throws IOException
    {
        FileWriter fileWriter = new FileWriter(appPropertiesFile);
        appProperties.store(fileWriter, APPLICATION_COMMENT);
        fileWriter.close();
    }
    
    private static final String APPLICATION_COMMENT = "PimpMyXcom - Copyright 2012 Patrick Meade";
    private static final String APPLICATION_DEFAULT_LANG = "English";
    private static final String APPLICATION_DEFAULT_PATH = "/path/to/xcom";
    private static final String APPLICATION_PROPERTIES_FILE = ".pimpMyXcom";
    private static final String SYSTEM_PROPERTY_USER_HOME = "user.home";
}
