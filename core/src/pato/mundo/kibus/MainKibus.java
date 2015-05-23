package pato.mundo.kibus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pato.mundo.kibus.ui.Opening;

public class MainKibus extends Game {
    public SpriteBatch batch;
    public Pantalla opening;
    public int x, y;



    @Override
    public void create() {
        batch = new SpriteBatch();
        opening = new Opening(this);
        x = Gdx.graphics.getWidth();
        y = Gdx.graphics.getHeight();
        setScreen(opening);


    }
}
