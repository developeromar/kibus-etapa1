package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class ActorFondo extends Actor implements Disposable {
    Texture fondo;
    TextureRegion fondito;

    public ActorFondo() {
        fondo = new Texture("mapa.png");
        fondito = new TextureRegion(fondo);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(fondito, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void dispose() {
        fondo.dispose();
    }

    public void setPositioninit(rockObj fondoPosition) {
        setPosition(fondoPosition.getX(), fondoPosition.getY());
    }
}
