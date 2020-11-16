package com.youtube.tutorial.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.GL20;
import com.youtube.tutorial.TutorialYouTube;

import static com.youtube.tutorial.TutorialYouTube.*;

public class GameScreen extends AbstractScreen {

    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    private Body player;

    public GameScreen(final TutorialYouTube context) {
        super(context);

        // body is a container for many different fixtures

        // create a circle
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        // create player
        bodyDef.position.set(4.5f, 3);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        player = world.createBody(bodyDef);
        player.setUserData("PLAYER");
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;
        final PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = pShape;
        player.createFixture(fixtureDef);
        pShape.dispose();

        // create room
        bodyDef.position.set(0, 0);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        final Body body = world.createBody(bodyDef);
        body.setUserData("GROUND");

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        final ChainShape chainShape = new ChainShape();
        chainShape.createLoop(new float[]{1, 1, 1, 15, 8, 15, 8, 1}); // ????
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        chainShape.dispose();

//        bodyDef.position.set(4.5f, 15);
//        bodyDef.gravityScale = 1;
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        Body body = world.createBody(bodyDef);
//        body.setUserData("CIRCLE");
//
//        // can move through static bodies but will still notify on collision
//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.5f;  // elasticity of the body, bounciness
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = BIT_CIRCLE;
//        fixtureDef.filter.maskBits = BIT_GROUND;
//        CircleShape cShape = new CircleShape();
//        cShape.setRadius(0.5f);
//        fixtureDef.shape = cShape;
//        body.createFixture(fixtureDef);
//        cShape.dispose();
//        // category bit defines category of fixture player, gameobj, platform, etc
//        // mask bit defines what kind of other categories it can collide with platform but not box etc.
//
//        fixtureDef.isSensor = true;
//        fixtureDef.restitution = 0;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = BIT_CIRCLE;
//        fixtureDef.filter.maskBits = BIT_BOX;
//        ChainShape chainShape = new ChainShape();
//        chainShape.createChain(new float[]{-0.5f, -0.7f, 0.5f, -0.7f});
//        fixtureDef.shape = chainShape;
//        body.createFixture(fixtureDef);
//        chainShape.dispose();
//
//        // create a  BOX
//        bodyDef.position.set(5.3f, 6);
//        bodyDef.gravityScale = 1;
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        body = world.createBody(bodyDef);
//        body.setUserData("BOX");
//
//        // can move through static bodies but will still notify on collision
//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.5f;  // elasticity of the body, bounciness
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = BIT_BOX;
//        fixtureDef.filter.maskBits = BIT_GROUND | BIT_CIRCLE;
//
//        PolygonShape pShape = new PolygonShape();
//        pShape.setAsBox(.5f, .5f);
//        //pShape.setRadius(0.5f);
//        fixtureDef.shape = pShape;
//        body.createFixture(fixtureDef);
//        pShape.dispose();
//
//        //  PLATFORM
//        bodyDef.position.set(4.5f, 2);
//        bodyDef.gravityScale = 1;
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        body = world.createBody(bodyDef);
//        body.setUserData("PLATFORM");
//
//        // can move through static bodies but will still notify on collision
//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.5f;  // elasticity of the body, bounciness
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = BIT_GROUND;
//        fixtureDef.filter.maskBits = -1; // collides with everything
//
//        pShape = new PolygonShape();
//        pShape.setAsBox(4f, .5f);
//        fixtureDef.shape = pShape;
//        //pShape.setRadius(0.5f);
//        body.createFixture(fixtureDef);
//        pShape.dispose();



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
//            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            context.setScreen(ScreenType.LOADING);
        }

        viewport.apply(true);
       // world.step(delta, 6, 2);

        box2DDebugRenderer.render(world, viewport.getCamera().combined);
    }

//    @Override
//    public void resize(int width, int height) {
//
//    }

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
