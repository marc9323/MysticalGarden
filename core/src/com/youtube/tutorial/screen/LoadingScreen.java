package com.youtube.tutorial.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.youtube.tutorial.TutorialYouTube;

public class LoadingScreen extends AbstractScreen {

    private final AssetManager assetManager;

    public LoadingScreen(final TutorialYouTube context) {
        super(context);

        this.assetManager = context.getAssetManager();
        assetManager.load("map/map.tmx", TiledMap.class); // non blocking, asynchronous

      //  assetManager.finishLoading(); // blocking
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // assetManager.getProgress()  returns 0... 1

        if(assetManager.update()) {
            context.setScreen(ScreenType.GAME);
        }; // returns true when loading complete

        if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
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
