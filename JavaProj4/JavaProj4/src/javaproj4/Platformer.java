package javaproj4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import src.states.GameStateManager;
import src.states.MenuState;

/**
 * DiplomaProj class initialization all game components and listen game event.
 * Send game events to other components.
 */
public class Platformer extends ApplicationAdapter {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final String TITLE = "Sky platform";

    private GameStateManager gsm;
    private SpriteBatch batch;
    private Music backgroundMusic;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        /*backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundMusic.dispose();

    }
}
