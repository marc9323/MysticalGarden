package com.youtube.tutorial;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
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

	private World world;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Box2D.init();
		// as opposed to Github make available to every screen
		world = new World(new Vector2(0, 9.81f), true);

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

	// use the cache so we don't create a new screen every time we switch
	public void setScreen(final ScreenType screenType) {
		final Screen screen = screenCache.get(screenType);
		if(screen == null) {
			// screen not created yet - create it
			try {
				Gdx.app.debug(TAG, "Creating new screen: " + screenType);
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), TutorialYouTube.class).newInstance(this);
				screenCache.put(screenType, (AbstractScreen)newScreen);
				System.out.println("ENUMMAP Contents: ");
				System.out.println("screenType: " + screenType + "    " + newScreen);
				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("Screen " + screenType + " could not be created", e);
			}
		} else {
			Gdx.app.debug(TAG,"Switching to screen: " + screenType);
			setScreen(screen);
		}
	}


}
