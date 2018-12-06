package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;

import base.Platformer;
import sprites.Ground;
import sprites.Platform;
import sprites.Player;
//import src.sprites.Star;

import java.util.HashSet;
import java.util.Set;

import static com.badlogic.gdx.Input.*;

/**
 * DiplomaProj game logic
 */
public class PlayState extends AbstractState {
    private final Texture backgroundImg;
    private final BitmapFont font;
    private final Ground ground;
    private final Set<Platform> platforms;
    //private final Set<Star> stars;
    private final Player player;
    private final Sound catchSound;
    private int counter;

    /**
     * Constructor
     *
     * @param gsm Game State Manager for managed game states
     */
    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Platformer.WIDTH, Platformer.HEIGHT);
        backgroundImg = new Texture("sky.png");
        counter = 0;
        font = new BitmapFont();
        ground = new Ground();
        platforms = new HashSet<Platform>();
        platforms.add(new Platform(new Vector2(0, 300)));
        platforms.add(new Platform(new Vector2(Platformer.WIDTH/2 + 20, 150)));
        /*stars = new HashSet<Star>();
        for (int i = 0; i < 10; i++) {
            stars.add(new Star(new Vector2(i * 70,Platformer.HEIGHT)));
        }*/
        catchSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

        player = new Player();
    }

    /**
     * Check that player press control key
     */
    private void hadleInput(){
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            player.left();
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            player.right();
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            player.jump();
        }
    }

    /**
     * Check that player catch star
     */
    /*private void catchStar(){
        for (Star star : stars) {
            if (star.isVisible() && star.collides(player.getBounds())) {
                counter++;
                catchSound.play();
                star.setVisible(false);
            }
        }
    }*/

    /**
     * Check that all star is fly over game area
     */
    private void checkGameOver(){
        boolean gameover = true;
        /*for (Star star : stars) {
            if (star.getPosition().y > ground.getImage().getHeight()) {
                gameover = false;
            }
        }
        if (gameover) {
            gsm.set(new GameOverState(gsm, counter));
        }*/
    }

    @Override
    public void update(float delta) {
        // Check input
        hadleInput();
        // Update game entity
        player.update(delta);
        /*for (Star star : stars) {
            star.update(delta);
        }*/
        // Check catch star
        /*catchStar();*/
        // Check collide with sky platform and ground
        boolean isCollide = false;
        for (Platform platform : platforms) {
            if (platform.collides(player.getBounds())) {
                isCollide = true;
            }
        }
        if (ground.collides(player.getBounds())) {
            isCollide = true;
        }
        player.setStayOnObject(isCollide);
        // Check game over
        checkGameOver();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Draw background image
        batch.draw(backgroundImg, 0, 0);
        // Draw text for catch stars
        font.draw(batch,String.format("Catch: %s", counter), 10, Platformer.HEIGHT - 20);
        // Draw ground ( serial drawing two textures)
        batch.draw(ground.getImage(), ground.getPosition().x, ground.getPosition().y);
        batch.draw(ground.getImage(), ground.getPosition().x + ground.getImage().getWidth(), ground.getPosition().y);
        // Draw sky platforms
        for (Platform platform : platforms) {
            batch.draw(platform.getImage(), platform.getPosition().x, platform.getPosition().y);
        }
        // Draw stars
        /*for (Star star : stars) {
            if (star.isVisible()) {
                batch.draw(star.getImage(), star.getPosition().x, star.getPosition().y);
            }
        }*/
        // Draw player
        batch.draw(player.getImage(), player.getPosition().x, player.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        font.dispose();
        ground.dispose();
        for (Platform platform : platforms) {
            platform.dispose();
        }
        player.dispose();
        /*for (Star star : stars) {
            star.dispose();
        }*/
    }
}
