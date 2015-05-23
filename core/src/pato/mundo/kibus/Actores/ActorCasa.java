package pato.mundo.kibus.Actores;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class ActorCasa extends Actor implements Disposable {
    Texture casa;
    TextureRegion rCasa;

    public ActorCasa() {
        casa = new Texture("casa.png");
        rCasa = new TextureRegion(casa);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(rCasa, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        casa.dispose();
    }


}
