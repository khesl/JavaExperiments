package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Implementation simple state machine for managed game states
 */
public class GameStateManager {
    private final Stack<AbstractState> states;

    /**
     * Constructor. Initialized stack for save game states.
     */
    public GameStateManager() {
        this.states = new Stack<AbstractState>();
    }

    /**
     * Add new game state in stack
     * @param item New game state
     */
    public void push(AbstractState item) {
        states.push(item);
    }

    /**
     * Remove current game state from stack and unloaded resources
     */
    public void pop() {
        states.pop().dispose();
    }

    /**
     * Change current game state
     * @param element New game state
     */
    public void set(AbstractState element) {
        this.pop();
        states.push(element);
    }

    /**
     * Run update logic for current state
     * @param delta time after last call method render
     */
    public void update(float delta){
        states.peek().update(delta);
    }

    /**
     * Run render logic for current state
     * @param batch sprites collections for render
     */
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
