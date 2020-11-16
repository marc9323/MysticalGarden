package com.youtube.tutorial.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.youtube.tutorial.TutorialYouTube;
import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractScreen implements Screen {
    protected final TutorialYouTube context;
    protected final FitViewport viewport;
    protected final Box2DDebugRenderer box2DDebugRenderer;

    // now every Screen has access to the world through context.getWorld();
    protected final World world;

    public AbstractScreen(final TutorialYouTube context) {
        this.context = context;
        viewport = context.getScreenViewport();
        this.world = context.getWorld();
        this.box2DDebugRenderer = context.getBox2DDebugRenderer();
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height);
    }
}
