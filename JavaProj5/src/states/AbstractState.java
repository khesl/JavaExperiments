package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Abstract game state
 */
public abstract class AbstractState {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    /**
     * Constructor
     * @param gsm Game State Manager for managed game states
     */
    public AbstractState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    /**
     * Update logic
     * @param delta time after last call method render
     */
    public abstract void update(float delta);

    /**
     * Render game_objects
     * @param batch game_objects collections for render
     */
    public abstract void render(SpriteBatch batch);


    /**
     * Unload resources
     */
    public abstract void dispose();
}