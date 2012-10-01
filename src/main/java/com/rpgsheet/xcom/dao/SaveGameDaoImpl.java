/*
 * SaveGameDaoImpl.java
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

package com.rpgsheet.xcom.dao;

import com.rpgsheet.xcom.game.SaveGame;
import com.rpgsheet.xcom.service.UfoGameFileService;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rpgsheet.xcom.service.UfoGameFileService.*;

@Service("saveGameDao")
public class SaveGameDaoImpl implements SaveGameDao
{
    @Override
    public SaveGame read(int saveSlot)
    {
        SaveGame saveGame = new SaveGame();
        saveGame.acts = readIt(saveSlot, ACTS_DAT);
        saveGame.aknow = readIt(saveSlot, AKNOW_DAT);
        saveGame.alien = readIt(saveSlot, ALIEN_DAT);
        saveGame.astore = readIt(saveSlot, ASTORE_DAT);
        saveGame.base = readIt(saveSlot, BASE_DAT);
        saveGame.bprod = readIt(saveSlot, BPROD_DAT);
        saveGame.craft = readIt(saveSlot, CRAFT_DAT);
        saveGame.diplom = readIt(saveSlot, DIPLOM_DAT);
        saveGame.facil = readIt(saveSlot, FACIL_DAT);
        saveGame.iglob = readIt(saveSlot, IGLOB_DAT);
        saveGame.inter = readIt(saveSlot, INTER_DAT);
        saveGame.lease = readIt(saveSlot, LEASE_DAT);
        saveGame.liglob = readIt(saveSlot, LIGLOB_DAT);
        saveGame.loc = readIt(saveSlot, LOC_DAT);
        saveGame.missions = readIt(saveSlot, MISSIONS_DAT);
        saveGame.product = readIt(saveSlot, PRODUCT_DAT);
        saveGame.project = readIt(saveSlot, PROJECT_DAT);
        saveGame.purchase = readIt(saveSlot, PURCHASE_DAT);
        saveGame.research = readIt(saveSlot, RESEARCH_DAT);
        saveGame.saveinfo = readIt(saveSlot, SAVEINFO_DAT);
        saveGame.soldier = readIt(saveSlot, SOLDIER_DAT);
        saveGame.transfer = readIt(saveSlot, TRANSFER_DAT);
        saveGame.uiglob = readIt(saveSlot, UIGLOB_DAT);
        saveGame.up = readIt(saveSlot, UP_DAT);
        saveGame.xbases = readIt(saveSlot, XBASES_DAT);
        saveGame.xcom = readIt(saveSlot, XCOM_DAT);
        saveGame.zonal = readIt(saveSlot, ZONAL_DAT);
        return saveGame;
    }

    @Override
    public void write(int saveSlot, SaveGame saveGame)
    {
        writeIt(saveSlot, ACTS_DAT, saveGame.acts);
        writeIt(saveSlot, AKNOW_DAT, saveGame.aknow);
        writeIt(saveSlot, ALIEN_DAT, saveGame.alien);
        writeIt(saveSlot, ASTORE_DAT, saveGame.astore);
        writeIt(saveSlot, BASE_DAT, saveGame.base);
        writeIt(saveSlot, BPROD_DAT, saveGame.bprod);
        writeIt(saveSlot, CRAFT_DAT, saveGame.craft);
        writeIt(saveSlot, DIPLOM_DAT, saveGame.diplom);
        writeIt(saveSlot, FACIL_DAT, saveGame.facil);
        writeIt(saveSlot, IGLOB_DAT, saveGame.iglob);
        writeIt(saveSlot, INTER_DAT, saveGame.inter);
        writeIt(saveSlot, LEASE_DAT, saveGame.lease);
        writeIt(saveSlot, LIGLOB_DAT, saveGame.liglob);
        writeIt(saveSlot, LOC_DAT, saveGame.loc);
        writeIt(saveSlot, MISSIONS_DAT, saveGame.missions);
        writeIt(saveSlot, PRODUCT_DAT, saveGame.product);
        writeIt(saveSlot, PROJECT_DAT, saveGame.project);
        writeIt(saveSlot, PURCHASE_DAT, saveGame.purchase);
        writeIt(saveSlot, RESEARCH_DAT, saveGame.research);
        writeIt(saveSlot, SAVEINFO_DAT, saveGame.saveinfo);
        writeIt(saveSlot, SOLDIER_DAT, saveGame.soldier);
        writeIt(saveSlot, TRANSFER_DAT, saveGame.transfer);
        writeIt(saveSlot, UIGLOB_DAT, saveGame.uiglob);
        writeIt(saveSlot, UP_DAT, saveGame.up);
        writeIt(saveSlot, XBASES_DAT, saveGame.xbases);
        writeIt(saveSlot, XCOM_DAT, saveGame.xcom);
        writeIt(saveSlot, ZONAL_DAT, saveGame.zonal);
    }
    
    private byte[] readIt(int saveSlot, String fileName)
    {
        File saveFile = ufoGameFileService.getSaveFile(saveSlot, fileName);
        byte[] data = new byte[(int)saveFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            dataInputStream.readFully(data);
        } catch(FileNotFoundException e) {
            // if we don't have it, we don't have it
            data = null;
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
        return data;
    }
    
    private void writeIt(int saveSlot, String fileName, byte[] data)
    {
        File saveFile = ufoGameFileService.getSaveFile(saveSlot, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.write(data);
        } catch(FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
    }
    
    @Autowired private UfoGameFileService ufoGameFileService;
}
