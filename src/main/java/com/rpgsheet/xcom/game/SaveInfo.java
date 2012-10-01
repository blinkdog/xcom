/*
 * SaveInfo.java
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

package com.rpgsheet.xcom.game;

import com.rpgsheet.xcom.io.LittleEndianDataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SaveInfo
{
    public SaveInfo(byte[] saveinfo)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(saveinfo);
        LittleEndianDataInputStream ledis = new LittleEndianDataInputStream(bais);
        try {
            missdatFlag = ledis.readUnsignedShort();
            byte[] saveNameBytes = new byte[26];
            ledis.read(saveNameBytes);
            saveName = new String(saveNameBytes, "CP437");
            String[] saveNameSplit = saveName.split("\u0000");
            saveName = saveNameSplit[0];
            year = ledis.readUnsignedShort();
            month = ledis.readUnsignedShort();
            day = ledis.readUnsignedShort();
            hour = ledis.readUnsignedShort();
            minute = ledis.readUnsignedShort();
            modeFlag = ledis.readUnsignedShort();
        } catch(IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public int getMissdatFlag() {
        return missdatFlag;
    }

    public void setMissdatFlag(int missdatFlag) {
        this.missdatFlag = missdatFlag;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getModeFlag() {
        return modeFlag;
    }

    public void setModeFlag(int modeFlag) {
        this.modeFlag = modeFlag;
    }
    
    private int missdatFlag;
    private String saveName;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int modeFlag;
}
