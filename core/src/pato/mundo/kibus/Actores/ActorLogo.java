package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by Omar Sanchez on 12/02/2015.
 */
public class ActorLogo extends Actor implements Disposable {
    Texture logo;
    TextureRegion region;

    public ActorLogo(String textura,float x,float y) {
        logo = new Texture(textura);
        region = new TextureRegion(logo);
        setSize(x,y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color=getColor();
        batch.setColor(color.r,color.g,color.b,color.a*parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        logo.dispose();
    }
}
