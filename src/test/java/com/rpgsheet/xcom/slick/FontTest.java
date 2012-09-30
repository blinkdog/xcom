/*
 * FontTest.java
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

package com.rpgsheet.xcom.slick;

import java.io.UnsupportedEncodingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FontTest
{
    public FontTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAlwaysSucceed() {
        assertTrue(true);
    }
    
    @Test
    public void testNoEncodingException() throws UnsupportedEncodingException
    {
        String selectLanguage = "Sprache auswählen";
        byte[] bytes = selectLanguage.getBytes("CP437");
        String newString = new String(bytes, "CP437");
        assertEquals(selectLanguage, newString);
    }
    
    @Test
    public void testDifferentBytes() throws UnsupportedEncodingException
    {
        String selectLanguage = "Sprache auswählen";
        byte[] utfBytes = selectLanguage.getBytes();
        byte[] cp437Bytes = selectLanguage.getBytes("CP437");
        assertFalse(utfBytes.length == cp437Bytes.length);
    }

    @Test
    public void testThroughEncoding() throws UnsupportedEncodingException
    {
        String selectLanguage = "Sprache auswählen";
        byte[] utfBytes = selectLanguage.getBytes();
        byte[] cp437Bytes = selectLanguage.getBytes("CP437");
        assertFalse(utfBytes.length == cp437Bytes.length);
        String cp437 = new String(cp437Bytes, "CP437");
        assertEquals(selectLanguage, cp437);
        byte[] utfBytes2 = cp437.getBytes();
        assertArrayEquals(utfBytes, utfBytes2);
        byte[] cp437Bytes2 = cp437.getBytes("CP437");
        assertArrayEquals(cp437Bytes, cp437Bytes2);
    }
}
