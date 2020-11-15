package com.youtube.tutorial.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.youtube.tutorial.TutorialYouTube;

public abstract class AbstractScreen implements Screen {
    protected final TutorialYouTube context;
    protected final FitViewport viewport;

    public AbstractScreen(final TutorialYouTube context) {
        this.context = context;
        viewport = context.getScreenViewport();
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height);
    }
}
