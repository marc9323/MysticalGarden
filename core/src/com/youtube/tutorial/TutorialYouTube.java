package com.youtube.tutorial;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.prism.image.ViewPort;
import com.youtube.tutorial.screen.LoadingScreen;
import com.youtube.tutorial.screen.ScreenType;

import java.util.EnumMap;

public class TutorialYouTube extends Game {

	private static final String TAG = TutorialYouTube.class.getSimpleName();

	private EnumMap<ScreenType, Screen> screenCache;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		screenCache = new EnumMap<ScreenType, Screen>(ScreenType.class);
		setScreen(ScreenType.LOADING);
	}

	// use the cache so we don't create a new screen every time we switch
	public void setScreen(final ScreenType screenType) {
		final Screen screen = screenCache.get(screenType);
		if(screen == null) {
			// screen not created yet - create it
			try {
				Gdx.app.debug(TAG, "Creating new screen: " + screenType);
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), TutorialYouTube.class).newInstance(this);
				screenCache.put(screenType, (Screen)newScreen);
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
