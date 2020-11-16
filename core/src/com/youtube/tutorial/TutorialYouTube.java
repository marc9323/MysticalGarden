package com.youtube.tutorial;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.youtube.tutorial.screen.AbstractScreen;
import com.youtube.tutorial.screen.ScreenType;

import java.util.EnumMap;

public class TutorialYouTube extends Game {

	private static final String TAG = TutorialYouTube.class.getSimpleName();

	private EnumMap<ScreenType, AbstractScreen> screenCache;
	private FitViewport screenViewport;

	public static final short BIT_CIRCLE = 1 << 0;
	public static final short BIT_BOX = 1 << 1;
	public static final short BIT_GROUND = 1 << 2;
	public static final short BIT_PLAYER = 1 << 3;

	private World world;
	private WorldContactListener worldContactListener;
	private Box2DDebugRenderer box2DDebugRenderer;

	private static final float FIXED_TIME_STEP = 1 / 60f;
	private float accumulator;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		accumulator = 0;

		Box2D.init();
		// as opposed to Github make available to every screen
		world = new World(new Vector2(0, 0), true);
		worldContactListener = new WorldContactListener();
		world.setContactListener(worldContactListener);

		box2DDebugRenderer = new Box2DDebugRenderer();

		// 16 x 9 aspect ratio
		screenViewport = new FitViewport(9, 16);
		screenCache = new EnumMap<ScreenType, AbstractScreen>(ScreenType.class);
		setScreen(ScreenType.GAME);
	}

	public FitViewport getScreenViewport(){
		return screenViewport;
	}

	public World getWorld() {
		return this.world;
	}

	public Box2DDebugRenderer getBox2DDebugRenderer() {
		return this.box2DDebugRenderer;
	}

	// use the cache so we don't create a new screen every time we switch
	public void setScreen(final ScreenType screenType) {
		final Screen screen = screenCache.get(screenType);
		if(screen == null) {
			// screen not created yet - create it
			try {
				Gdx.app.debug(TAG, "Creating new screen: " + screenType);
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), TutorialYouTube.class).newInstance(this);
				screenCache.put(screenType, (AbstractScreen)newScreen);

				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("Screen " + screenType + " could not be created", e);
			}
		} else {
			Gdx.app.debug(TAG,"Switching to screen: " + screenType);
			setScreen(screen);
		}
	}

	@Override
	public void render() {
		super.render();

		//Gdx.app.debug(TAG, "raw delta time: " + Gdx.graphics.getRawDeltaTime());
		accumulator += Math.min(0.25f, Gdx.graphics.getRawDeltaTime());
		while(accumulator >= FIXED_TIME_STEP) {
			world.step(FIXED_TIME_STEP, 6, 2);
			accumulator -= FIXED_TIME_STEP;
		}

		// final float alpha = accumulator / FIXED_TIME_STEP;
	}

	@Override
	public void dispose(){
		super.dispose();
		box2DDebugRenderer.dispose();
		world.dispose();
	}

}
