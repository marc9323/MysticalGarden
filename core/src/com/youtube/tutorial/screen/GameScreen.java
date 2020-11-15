package com.youtube.tutorial.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.youtube.tutorial.TutorialYouTube;

public class GameScreen implements Screen {

    private final TutorialYouTube context;

    public GameScreen(final TutorialYouTube context) {
        this.context = context;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
//            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            context.setScreen(ScreenType.LOADING);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
