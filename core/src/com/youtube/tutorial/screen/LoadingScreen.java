package com.youtube.tutorial.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.youtube.tutorial.TutorialYouTube;

public class LoadingScreen extends AbstractScreen {

    public LoadingScreen(final TutorialYouTube context) {
        super(context);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            System.out.println("KEY A PRESSED");
//            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            context.setScreen(ScreenType.GAME);
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
