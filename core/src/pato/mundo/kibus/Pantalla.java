package pato.mundo.kibus;

import com.badlogic.gdx.Screen;

/**
 * Created by Omar Sanchez on 11/02/2015.
 */
public abstract class Pantalla implements Screen {
    protected MainKibus game;
    public Pantalla(MainKibus game){
        this.game=game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
