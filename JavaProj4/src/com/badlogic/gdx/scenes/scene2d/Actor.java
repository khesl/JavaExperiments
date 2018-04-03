/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.scenes.scene2d;

import static com.badlogic.gdx.utils.Align.*;

import com.badlogic.gdx.Gdx;
/*
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;*/
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
//import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import java.security.acl.Group;

/** 2D scene graph node. An actor has a position, rectangular size, origin, scale, rotation, Z index, and color. The position
 * corresponds to the unrotated, unscaled bottom left corner of the actor. The position is relative to the actor's parent. The
 * origin is relative to the position and is used for scale and rotation.
 * <p>
 * An actor has a list of in progress {@link Action actions} that are applied to the actor (often over time). These are generally
 * used to change the presentation of the actor (moving it, resizing it, etc). See {@link #act(float)}, {@link Action} and its
 * many subclasses.
 * <p>
 * An actor has two kinds of listeners associated with it: "capture" and regular. The listeners are notified of events the actor
 * or its children receive. The regular listeners are designed to allow an actor to respond to events that have been delivered.
 * The capture listeners are designed to allow a parent or container actor to handle events before child actors. See {@link #fire}
 * for more details.
 * <p>
 * An {@link InputListener} can receive all the basic input events. More complex listeners (like {@link ClickListener} and
 * {@link ActorGestureListener}) can listen for and combine primitive events and recognize complex interactions like multi-touch
 * or pinch.
 * @author mzechner
 * @author Nathan Sweet */
public class Actor {
	//private Stage stage;
	//Group parent;
	private final DelayedRemovalArray<EventListener> listeners = new DelayedRemovalArray(0);
	private final DelayedRemovalArray<EventListener> captureListeners = new DelayedRemovalArray(0);
	//private final Array<Action> actions = new Array(0);

	private String name;
	private Touchable touchable = Touchable.enabled;
	private boolean visible = true, debug;
	float x, y;
	float width, height;
	float originX, originY;
	float scaleX = 1, scaleY = 1;
	float rotation;
	//final Color color = new Color(1, 1, 1, 1);
	private Object userObject;

	/** Draws the actor. The batch is configured to draw in the parent's coordinate system.
	 * {@link Batch#draw(com.badlogic.gdx.graphics.g2d.TextureRegion, float, float, float, float, float, float, float, float, float)
	 * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link Batch#begin()} has already been called on
	 * the batch. If {@link Batch#end()} is called to draw without the batch then {@link Batch#begin()} must be called before the
	 * method returns.
	 * <p>
	 * The default implementation does nothing.
	 * @param parentAlpha Should be multiplied with the actor's alpha, allowing a parent's alpha to affect all children. */
	//public void draw (Batch batch, float parentAlpha) {
	//}

	/** Returns the deepest actor that contains the specified point and is {@link #getTouchable() touchable} and
	 * {@link #isVisible() visible}, or null if no actor was hit. The point is specified in the actor's local coordinate system
	 * (0,0 is the bottom left of the actor and width,height is the upper right).
	 * <p>
	 * This method is used to delegate touchDown, mouse, and enter/exit events. If this method returns null, those events will not
	 * occur on this Actor.
	 * <p>
	 * The default implementation returns this actor if the point is within this actor's bounds.
	 * @param touchable If true, the hit detection will respect the {@link #setTouchable(Touchable) touchability}.
	 * @see Touchable */
	public Actor hit (float x, float y, boolean touchable) {
		if (touchable && this.touchable != Touchable.enabled) return null;
		return x >= 0 && x < width && y >= 0 && y < height ? this : null;
	}


	/** Add a listener to receive events that {@link #hit(float, float, boolean) hit} this actor. See {@link #fire(Event)}.
	 * @see InputListener
	 * @see ClickListener */
	public boolean addListener (EventListener listener) {
		if (listener == null) throw new IllegalArgumentException("listener cannot be null.");
		if (!listeners.contains(listener, true)) {
			listeners.add(listener);
			return true;
		}
		return false;
	}

	public boolean removeListener (EventListener listener) {
		if (listener == null) throw new IllegalArgumentException("listener cannot be null.");
		return listeners.removeValue(listener, true);
	}

	public Array<EventListener> getListeners () {
		return listeners;
	}

	/** Adds a listener that is only notified during the capture phase.
	 * @see #fire(Event) */
	public boolean addCaptureListener (EventListener listener) {
		if (listener == null) throw new IllegalArgumentException("listener cannot be null.");
		if (!captureListeners.contains(listener, true)) captureListeners.add(listener);
		return true;
	}

	public boolean removeCaptureListener (EventListener listener) {
		if (listener == null) throw new IllegalArgumentException("listener cannot be null.");
		return captureListeners.removeValue(listener, true);
	}

	public Array<EventListener> getCaptureListeners () {
		return captureListeners;
	}

	

	/** Removes all listeners on this actor. */
	public void clearListeners () {
		listeners.clear();
		captureListeners.clear();
	}

	

	/** Returns true if input events are processed by this actor. */
	public boolean isTouchable () {
		return touchable == Touchable.enabled;
	}

	public Touchable getTouchable () {
		return touchable;
	}

	/** Determines how touch events are distributed to this actor. Default is {@link Touchable#enabled}. */
	public void setTouchable (Touchable touchable) {
		this.touchable = touchable;
	}

	public boolean isVisible () {
		return visible;
	}

	/** If false, the actor will not be drawn and will not receive touch events. Default is true. */
	public void setVisible (boolean visible) {
		this.visible = visible;
	}

	/** Returns an application specific object for convenience, or null. */
	public Object getUserObject () {
		return userObject;
	}

	/** Sets an application specific object for convenience. */
	public void setUserObject (Object userObject) {
		this.userObject = userObject;
	}

	/** Returns the X position of the actor's left edge. */
	public float getX () {
		return x;
	}

	/** Returns the X position of the specified {@link Align alignment}. */
	public float getX (int alignment) {
		float x = this.x;
		if ((alignment & right) != 0)
			x += width;
		else if ((alignment & left) == 0) //
			x += width / 2;
		return x;
	}

	public void setX (float x) {
		if (this.x != x) {
			this.x = x;
			positionChanged();
		}
	}

	/** Returns the Y position of the actor's bottom edge. */
	public float getY () {
		return y;
	}

	public void setY (float y) {
		if (this.y != y) {
			this.y = y;
			positionChanged();
		}
	}

	/** Returns the Y position of the specified {@link Align alignment}. */
	public float getY (int alignment) {
		float y = this.y;
		if ((alignment & top) != 0)
			y += height;
		else if ((alignment & bottom) == 0) //
			y += height / 2;
		return y;
	}

	/** Sets the position of the actor's bottom left corner. */
	public void setPosition (float x, float y) {
		if (this.x != x || this.y != y) {
			this.x = x;
			this.y = y;
			positionChanged();
		}
	}

	/** Sets the position using the specified {@link Align alignment}. Note this may set the position to non-integer
	 * coordinates. */
	public void setPosition (float x, float y, int alignment) {
		if ((alignment & right) != 0)
			x -= width;
		else if ((alignment & left) == 0) //
			x -= width / 2;

		if ((alignment & top) != 0)
			y -= height;
		else if ((alignment & bottom) == 0) //
			y -= height / 2;

		if (this.x != x || this.y != y) {
			this.x = x;
			this.y = y;
			positionChanged();
		}
	}

	/** Add x and y to current position */
	public void moveBy (float x, float y) {
		if (x != 0 || y != 0) {
			this.x += x;
			this.y += y;
			positionChanged();
		}
	}

	public float getWidth () {
		return width;
	}

	public void setWidth (float width) {
		if (this.width != width) {
			this.width = width;
			sizeChanged();
		}
	}

	public float getHeight () {
		return height;
	}

	public void setHeight (float height) {
		if (this.height != height) {
			this.height = height;
			sizeChanged();
		}
	}

	/** Returns y plus height. */
	public float getTop () {
		return y + height;
	}

	/** Returns x plus width. */
	public float getRight () {
		return x + width;
	}

	/** Called when the actor's position has been changed. */
	protected void positionChanged () {
	}

	/** Called when the actor's size has been changed. */
	protected void sizeChanged () {
	}

	/** Called when the actor's rotation has been changed. */
	protected void rotationChanged () {
	}

	/** Sets the width and height. */
	public void setSize (float width, float height) {
		if (this.width != width || this.height != height) {
			this.width = width;
			this.height = height;
			sizeChanged();
		}
	}

	/** Adds the specified size to the current size. */
	public void sizeBy (float size) {
		if (size != 0) {
			width += size;
			height += size;
			sizeChanged();
		}
	}

	/** Adds the specified size to the current size. */
	public void sizeBy (float width, float height) {
		if (width != 0 || height != 0) {
			this.width += width;
			this.height += height;
			sizeChanged();
		}
	}

	/** Set bounds the x, y, width, and height. */
	public void setBounds (float x, float y, float width, float height) {
		if (this.x != x || this.y != y) {
			this.x = x;
			this.y = y;
			positionChanged();
		}
		if (this.width != width || this.height != height) {
			this.width = width;
			this.height = height;
			sizeChanged();
		}
	}

	public float getOriginX () {
		return originX;
	}

	public void setOriginX (float originX) {
		this.originX = originX;
	}

	public float getOriginY () {
		return originY;
	}

	public void setOriginY (float originY) {
		this.originY = originY;
	}

	/** Sets the origin position which is relative to the actor's bottom left corner. */
	public void setOrigin (float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
	}

	/** Sets the origin position to the specified {@link Align alignment}. */
	public void setOrigin (int alignment) {
		if ((alignment & left) != 0)
			originX = 0;
		else if ((alignment & right) != 0)
			originX = width;
		else
			originX = width / 2;

		if ((alignment & bottom) != 0)
			originY = 0;
		else if ((alignment & top) != 0)
			originY = height;
		else
			originY = height / 2;
	}

	public float getScaleX () {
		return scaleX;
	}

	public void setScaleX (float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY () {
		return scaleY;
	}

	public void setScaleY (float scaleY) {
		this.scaleY = scaleY;
	}

	/** Sets the scale for both X and Y */
	public void setScale (float scaleXY) {
		this.scaleX = scaleXY;
		this.scaleY = scaleXY;
	}

	/** Sets the scale X and scale Y. */
	public void setScale (float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	/** Adds the specified scale to the current scale. */
	public void scaleBy (float scale) {
		scaleX += scale;
		scaleY += scale;
	}

	/** Adds the specified scale to the current scale. */
	public void scaleBy (float scaleX, float scaleY) {
		this.scaleX += scaleX;
		this.scaleY += scaleY;
	}

	public float getRotation () {
		return rotation;
	}

	public void setRotation (float degrees) {
		if (this.rotation != degrees) {
			this.rotation = degrees;
			rotationChanged();
		}
	}

	/** Adds the specified rotation to the current rotation. */
	public void rotateBy (float amountInDegrees) {
		if (amountInDegrees != 0) {
			rotation += amountInDegrees;
			rotationChanged();
		}
	}

	
	/** @see #setName(String)
	 * @return May be null. */
	public String getName () {
		return name;
	}

	/** Set the actor's name, which is used for identification convenience and by {@link #toString()}.
	 * @param name May be null.
	 * @see Group#findActor(String) */
	public void setName (String name) {
		this.name = name;
	}

	


	/** Ends clipping begun by {@link #clipBegin(float, float, float, float)}. */
	public void clipEnd () {
		//Pools.free(ScissorStack.popScissors());
	}

	

	

	/** Transforms the specified point in the actor's coordinates to be in the parent's coordinates. */
	public Vector2 localToParentCoordinates (Vector2 localCoords) {
		final float rotation = -this.rotation;
		final float scaleX = this.scaleX;
		final float scaleY = this.scaleY;
		final float x = this.x;
		final float y = this.y;
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				localCoords.x += x;
				localCoords.y += y;
			} else {
				final float originX = this.originX;
				final float originY = this.originY;
				localCoords.x = (localCoords.x - originX) * scaleX + originX + x;
				localCoords.y = (localCoords.y - originY) * scaleY + originY + y;
			}
		} else {
			final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
			final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
			final float originX = this.originX;
			final float originY = this.originY;
			final float tox = (localCoords.x - originX) * scaleX;
			final float toy = (localCoords.y - originY) * scaleY;
			localCoords.x = (tox * cos + toy * sin) + originX + x;
			localCoords.y = (tox * -sin + toy * cos) + originY + y;
		}
		return localCoords;
	}

	

	/** Converts the coordinates given in the parent's coordinate system to this actor's coordinate system. */
	public Vector2 parentToLocalCoordinates (Vector2 parentCoords) {
		final float rotation = this.rotation;
		final float scaleX = this.scaleX;
		final float scaleY = this.scaleY;
		final float childX = x;
		final float childY = y;
		if (rotation == 0) {
			if (scaleX == 1 && scaleY == 1) {
				parentCoords.x -= childX;
				parentCoords.y -= childY;
			} else {
				final float originX = this.originX;
				final float originY = this.originY;
				parentCoords.x = (parentCoords.x - childX - originX) / scaleX + originX;
				parentCoords.y = (parentCoords.y - childY - originY) / scaleY + originY;
			}
		} else {
			final float cos = (float)Math.cos(rotation * MathUtils.degreesToRadians);
			final float sin = (float)Math.sin(rotation * MathUtils.degreesToRadians);
			final float originX = this.originX;
			final float originY = this.originY;
			final float tox = parentCoords.x - childX - originX;
			final float toy = parentCoords.y - childY - originY;
			parentCoords.x = (tox * cos + toy * sin) / scaleX + originX;
			parentCoords.y = (tox * -sin + toy * cos) / scaleY + originY;
		}
		return parentCoords;
	}

	

	public boolean getDebug () {
		return debug;
	}

	public String toString () {
		String name = this.name;
		if (name == null) {
			name = getClass().getName();
			int dotIndex = name.lastIndexOf('.');
			if (dotIndex != -1) name = name.substring(dotIndex + 1);
		}
		return name;
	}
}
