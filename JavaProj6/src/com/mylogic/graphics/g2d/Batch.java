package com.mylogic.graphics.g2d;

import com.mylogic.graphics.Color;
import com.mylogic.math.Matrix4;
import com.mylogic.utils.Disposable;

import java.awt.image.BufferedImage;

public interface Batch extends Disposable {
    /** Sets up the Batch for drawing. This will disable depth buffer writing. It enables blending and texturing. If you have more
     * texture units enabled than the first one you have to disable them before calling this. Uses a screen coordinate system by
     * default where everything is given in pixels. You can specify your own projection and modelview matrices via
     * {@link #setProjectionMatrix(Matrix4)} and {@link #setTransformMatrix(Matrix4)}. */
    public void begin();

    /** Finishes off rendering. Enables depth writes, disables blending and texturing. Must always be called after a call to
     * {@link #begin()} */
    public void end();

    /** Sets the color used to tint images when they are added to the Batch. Default is {@link Color#WHITE}. */
    public void setColor(Color tint);

    /** @see #setColor(Color) */
    public void setColor(float r, float g, float b, float a);

    /** @see #setColor(Color)
     * @see Color#toFloatBits() */
    public void setColor(float color);

    /** @return the rendering color of this Batch. Manipulating the returned instance has no effect. */
    public Color getColor();

    /** @return the rendering color of this Batch in vertex format
     * @see Color#toFloatBits() */
    public float getPackedColor();

    /** Draws a rectangle with the bottom left corner at x,y having the width and height of the texture.
     * @param x the x-coordinate in screen space
     * @param y the y-coordinate in screen space */
    public void draw(BufferedImage texture, float x, float y);

    /** Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height. */
    public void draw(BufferedImage texture, float x, float y, float width,
                     float height);
    
    /** Draws a rectangle using the given vertices. There must be 4 vertices, each made up of 5 elements in this order: x, y, color,
     * u, v. The {@link #getColor()} from the Batch is not applied. */
    public void draw (BufferedImage texture, float[] spriteVertices, int offset, int count);

    /** Causes any pending sprites to be rendered, without ending the Batch. */
    public void flush();

    /** @return true if blending for sprites is enabled */
    public boolean isBlendingEnabled();

    /** @return true if currently between begin and end. */
    public boolean isDrawing();
    
    public void setProjectionMatrix (Matrix4 projection);
    
    public void setTransformMatrix (Matrix4 transform);
    
    public void disableBlending();
    
    public void enableBlending();
    
    static public final int X1 = 0;
    static public final int Y1 = 1;
    static public final int C1 = 2;
    static public final int U1 = 3;
    static public final int V1 = 4;
    static public final int X2 = 5;
    static public final int Y2 = 6;
    static public final int C2 = 7;
    static public final int U2 = 8;
    static public final int V2 = 9;
    static public final int X3 = 10;
    static public final int Y3 = 11;
    static public final int C3 = 12;
    static public final int U3 = 13;
    static public final int V3 = 14;
    static public final int X4 = 15;
    static public final int Y4 = 16;
    static public final int C4 = 17;
    static public final int U4 = 18;
    static public final int V4 = 19;
}
