package com.mygdx.game.states;

import com.mylogic.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.mygdx.game.PlatformerGame;

import com.mylogic.graphics.g2d.SpriteBatch;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javaproj4.Platformer;

import javax.imageio.ImageIO;

import src.states.AbstractState;
import src.states.GameStateManager;

/**
 * Game over game state
 */
public class GameOverState extends AbstractState {
    private BufferedImage backgroundImg;
    private BufferedImage gameOverImg;
    //private BitmapFont font;
    private int counter;

    /**
     * Constructor
     *
     * @param gsm Game State Manager for managed game states
     */
    public GameOverState(GameStateManager gsm, int counter) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Platformer.WIDTH, Platformer.HEIGHT);
        try {
            backgroundImg = ImageIO.read(new File("src/res/assets/sky.png"));
            gameOverImg = ImageIO.read(new File("src/res/assets/gameover.png"));
        } catch (IOException e) {
        }
        
        //font = new BitmapFont();
        this.counter = counter;
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Draw background image
        batch.draw(backgroundImg, 0, 0);
        // Draw game over image
        batch.draw(gameOverImg, Platformer.WIDTH / 2 - gameOverImg.getWidth() / 2, Platformer.HEIGHT / 2 - gameOverImg.getHeight() / 2);
        // Draw text
        //font.draw(batch,"Catch " + counter + " stars!", Platformer.WIDTH/2 - 30, Platformer.HEIGHT/2 - 50);
        batch.end();
    }

    @Override
    public void dispose() {
        //backgroundImg.dispose();
        //gameOverImg.dispose();
    }
}
