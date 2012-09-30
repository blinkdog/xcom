/*
 * Button.java
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

package com.rpgsheet.xcom.render;

import com.rpgsheet.xcom.slick.Font;
import com.rpgsheet.xcom.slick.Glyph;
import com.rpgsheet.xcom.slick.Palette;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static org.newdawn.slick.Input.MOUSE_LEFT_BUTTON;

public class Button implements Clickable, Renderable
{
    public Button(float x1, float y1, float x2, float y2,
                  Palette palette, int buttonColor,
                  Font font, int labelColor,
                  String labelText, Runnable clickHandler)
    {
        this.buttonColor = buttonColor;
        this.clickHandler = clickHandler;
        this.font = font;
        this.labelText = labelText;
        this.labelColor = labelColor;
        this.palette = palette;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void render(GameContainer gc, Graphics g)
    {
        // do a little math... make a little love... get down tonight!
        final float DISPLAY_WIDTH = gc.getWidth();
        final float DISPLAY_HEIGHT = gc.getHeight();
        
        float xScale = DISPLAY_WIDTH / XCOM_WIDTH;
        float yScale = DISPLAY_HEIGHT / XCOM_HEIGHT;

        dx1 = x1*xScale;
        dy1 = y1*yScale;
        dx2 = x2*xScale;
        dy2 = y2*yScale;
        float dw = ((x2-x1)+1)*xScale;
        float dh = ((y2-y1)+1)*yScale;

        // invert the colors if we are pressed
        int color0 = buttonColor;
        int color1 = buttonColor+1;
        int color2 = buttonColor+2;
        int color3 = buttonColor+3;
        int color4 = buttonColor+4;
        if(pressed) {
            color4 = buttonColor;
            color3 = buttonColor+1;
            color2 = buttonColor+2;
            color1 = buttonColor+3;
            color0 = buttonColor+4;
        }
        
        // paint the brightest color in the upper left
        g.setColor(palette.getColor(color0));
        g.fillRect(dx1, dy1, dw, dh);

        // paint the darkest color in the lower right
        g.setColor(palette.getColor(color4));
        g.fillRect(dx1+xScale, dy1+yScale, dw-xScale, dh-yScale);
        // paint a strip to cleanup the far right side
        g.fillRect(dx2, dy1, xScale, dh);

        // paint the 2nd brightest color in the inner upper left
        g.setColor(palette.getColor(color1));
        g.fillRect(dx1+xScale, dy1+yScale, dw-(2*xScale), dh-(2*yScale));

        // paint the 2nd darkest color in the inner lower right
        g.setColor(palette.getColor(color3));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(3*xScale), dh-(3*yScale));
        // paint a strip to cleanup the inner far right side
        g.fillRect(dx2-xScale, dy1+yScale, xScale, dh-(2*yScale));

        // paint the mid-shade over the center of the button
        g.setColor(palette.getColor(color2));
        g.fillRect(dx1+(2*xScale), dy1+(2*yScale), dw-(4*xScale), dh-(4*yScale));

        // if we haven't computed the label yet, compute it now
        if(label == null) {
            // determine the spacing between the characters
            int spacing = 0;
            if(font.getGlyphHeight() == Glyph.GLYPH_SMALL_HEIGHT) {
                spacing = -1;
            }
            // determine the dimensions and coordinates of the label
            int labelWidth = font.getTextWidth(labelText, spacing);
            int glyphHeight = font.getGlyphHeight();
            float lx1 = ((x1 + (((x2-x1)+1)/2)) - (labelWidth/2));
            float ly1 = ((y1 + (((y2-y1)+1)/2)) - (glyphHeight/2));
            // create the label
            label = new Label(font, palette, labelColor, spacing, lx1, ly1, labelText);
        }
        label.render(gc, g);
    }

    @Override
    public void mousePressed(int button, int x, int y)
    {
        // if the left mouse button was pressed in our region
        if(   (button == MOUSE_LEFT_BUTTON)
           && (x >= dx1)
           && (x <= dx2)
           && (y >= dy1)
           && (y <= dy2))
        {
            // mark the button as pressed
            pressed=true;
            label.setInverted(true);
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y)
    {
        // if we're talking about the left mouse button
        if(button == MOUSE_LEFT_BUTTON) {
            // and if it was released in our region
            if(   (x >= dx1)
               && (x <= dx2)
               && (y >= dy1)
               && (y <= dy2))
            {
                // and it was previously pressed in our region
                if(pressed == true) {
                    // then this qualifies as a click
                    clickHandler.run();
                }
            }
            // no matter what, if the left button was released
            // we can no longer be pressed
            pressed=false;
            label.setInverted(false);
        }
    }
    
    private int buttonColor;
    private Font font;
    private Label label;
    private int labelColor;
    private String labelText;
    private Palette palette;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    
    private float dx1;
    private float dy1;
    private float dx2;
    private float dy2;
    private boolean pressed;
    private Runnable clickHandler;
}
