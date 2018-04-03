package com.mylogic.graphics.g2d;

import com.mylogic.graphics.Color;
//import com.mylogic.graphics.glutils.ShaderProgram;

import com.mylogic.math.Matrix4;
import com.mylogic.utils.NumberUtils;

import java.awt.image.BufferedImage;

public class SpriteBatch implements Batch {
    float [] vertices;
    int idx = 0;
    BufferedImage lastTexture = null;
    float invTexWidth = 0, invTexHeight = 0;

    boolean drawing = false;
    
    private final Matrix4 transformMatrix = new Matrix4();
    private final Matrix4 projectionMatrix = new Matrix4();
    private final Matrix4 combinedMatrix = new Matrix4();

    private boolean blendingDisabled = false;

    float color = Color.WHITE.toFloatBits();
    private Color tempColor = new Color(1, 1, 1, 1);

    /** Number of render calls since the last {@link #begin()}. **/
    public int renderCalls = 0;

    /** Number of rendering calls, ever. Will not be reset unless set manually. **/
    public int totalRenderCalls = 0;

    /** The maximum number of sprites rendered in one batch so far. **/
    public int maxSpritesInBatch = 0;

    /** Constructs a new SpriteBatch with a size of 1000, one buffer, and the default shader.
     * @see SpriteBatch#SpriteBatch(int, ShaderProgram) */
    public SpriteBatch() {
       //this(1000, null);
    }




    @Override
    public void begin() {
        if (drawing)
            throw new IllegalStateException("SpriteBatch.end must be called before begin.");
        renderCalls = 0;

        //

        drawing = true;
    }

    @Override
    public void end() {
        if (!drawing)
            throw new IllegalStateException("SpriteBatch.begin must be called before end.");
        if (idx > 0)
            flush();
        lastTexture = null;
        drawing = false;

        //
    }

    @Override
    public void setColor(Color tint) {
        color = tint.toFloatBits();
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        int intBits =
            (int)(255 * a) << 24 | (int)(255 * b) << 16 | (int)(255 * g) << 8 |
            (int)(255 * r);
        color = NumberUtils.intToFloatColor(intBits);
    }

    @Override
    public void setColor(float color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        int intBits = NumberUtils.floatToIntColor(color);
        Color color = tempColor;
        color.r = (intBits & 0xff) / 255f;
        color.g = ((intBits >>> 8) & 0xff) / 255f;
        color.b = ((intBits >>> 16) & 0xff) / 255f;
        color.a = ((intBits >>> 24) & 0xff) / 255f;
        return color;
    }

    @Override
    public float getPackedColor() {
        return color;
    }

    @Override
    public boolean isBlendingEnabled() {
        return !blendingDisabled;
    }

    @Override
    public void draw(BufferedImage texture, float x, float y) {
        draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw(BufferedImage texture, float x, float y, float width,
                     float height) {
        if (!drawing)
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        final float fx2 = x + width;
        final float fy2 = y + height;
        final float u = 0;
        final float v = 1;
        final float u2 = 1;
        final float v2 = 0;

        float color = this.color;
        int idx = this.idx;
        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        this.idx = idx;
    }
    
    @Override
    public void draw (BufferedImage texture, float[] spriteVertices, int offset, int count) {
            if (!drawing) throw new IllegalStateException("SpriteBatch.begin must be called before draw.");

            int verticesLength = vertices.length;
            int remainingVertices = verticesLength;
            if (texture != lastTexture)
                    switchTexture(texture);
            else {
                    remainingVertices -= idx;
                    if (remainingVertices == 0) {
                            flush();
                            remainingVertices = verticesLength;
                    }
            }
            int copyCount = Math.min(remainingVertices, count);

            System.arraycopy(spriteVertices, offset, vertices, idx, copyCount);
            idx += copyCount;
            count -= copyCount;
            while (count > 0) {
                    offset += copyCount;
                    flush();
                    copyCount = Math.min(verticesLength, count);
                    System.arraycopy(spriteVertices, offset, vertices, 0, copyCount);
                    idx += copyCount;
                    count -= copyCount;
            }
    }

    @Override
    public void flush() {
        if (idx == 0)
            return;
        // очистка?

        renderCalls++;
        totalRenderCalls++;
        int spritesInBatch = idx / 20;
        if (spritesInBatch > maxSpritesInBatch)
            maxSpritesInBatch = spritesInBatch;
        int count = spritesInBatch * 6;

        /*lastTexture.bind();
            Mesh mesh = this.mesh;
            mesh.setVertices(vertices, 0, idx);
            mesh.getIndicesBuffer().position(0);
            mesh.getIndicesBuffer().limit(count);
            // да ну к чёрту
            */
        // похоже загружаем текстуру для дальнейшей работы

        /*if (blendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            if (blendSrcFunc != -1)
                Gdx.gl.glBlendFunc(blendSrcFunc, blendDstFunc);
        }*/

        //mesh.render(customShader != null ? customShader : shader, GL20.GL_TRIANGLES, 0, count);
        // ну а тут рендерим

        idx = 0;
    }

    @Override
    public void disableBlending() {
        if (blendingDisabled)
            return;
        flush();
        blendingDisabled = true;
    }

    @Override
    public void enableBlending() {
        if (!blendingDisabled)
            return;
        flush();
        blendingDisabled = false;
    }
    
    @Override
    public void setProjectionMatrix (Matrix4 projection) {
            if (drawing) flush();
            projectionMatrix.set(projection);
            //if (drawing) setupMatrices();
    }

    @Override
    public void setTransformMatrix (Matrix4 transform) {
            if (drawing) flush();
            transformMatrix.set(transform);
            //if (drawing) setupMatrices();
    }

    @Override
    public void dispose() {
        // метод очистки
    }

    public boolean isDrawing() {
        return false;
    }

    protected void switchTexture(BufferedImage texture) {
        flush();
        lastTexture = texture;
        invTexWidth = 1.0f / texture.getWidth();
        invTexHeight = 1.0f / texture.getHeight();
    }

}
