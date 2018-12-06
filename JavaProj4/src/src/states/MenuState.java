package src.states;

import com.mylogic.Gdx;
import com.mylogic.graphics.OrthographicCamera;
//import com.mylogic.graphics.Texture;
//import com.mylogic.graphics.g2d.BitmapFont;
import com.mylogic.graphics.g2d.SpriteBatch;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javaproj4.Platformer;

import javax.imageio.ImageIO;

/**
 * DiplomaProj menu game state
 */
public class MenuState extends AbstractState {
    private BufferedImage backgroundImg;
    private BufferedImage btnPlay;
    //private BitmapFont font;

    /**
     * Constructor
     *
     * @param gsm Game State Manager for managed game states
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Platformer.WIDTH, Platformer.HEIGHT);
        try {
            backgroundImg = ImageIO.read(new File("src/res/assets/sky.png"));
            btnPlay = ImageIO.read(new File("src/res/assets/playbtn.png"));
        } catch (IOException e) {
        }
        //font = new BitmapFont(); // by default use Arial without support cyrillic symbols
    }

    /**
     * Handle touch for run game
     */
    private void handleInput(){
        if (Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Draw background image
        batch.draw(backgroundImg, 0, 0);
        // Draw text
        //font.draw(batch,"Touch for play", Platformer.WIDTH/2 - 50, Platformer.HEIGHT/2 + 50);
        // Draw button for run game
        batch.draw(btnPlay, Platformer.WIDTH / 2 - btnPlay.getWidth() / 2, Platformer.HEIGHT / 2 - btnPlay.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        //backgroundImg.dispose();
        //btnPlay.dispose();
        //font.dispose();
    }
}
