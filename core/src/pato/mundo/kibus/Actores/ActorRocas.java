package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 20/02/2015.
 */
public class ActorRocas extends Actor implements Disposable {
    Texture roca;
    TextureRegion rock;
    ArrayList<rockObj> arreglo;
    boolean change =false;
    boolean first=true;
    Batch batch;
    float parentAlpha;

    public void setArreglo(ArrayList<rockObj> arreglo) {
        this.arreglo = arreglo;
        this.change=true;
    }



    public ActorRocas(){
        roca=new Texture("rock.png");
        rock=new TextureRegion(roca);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
      //  batch.draw(rock, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
      if(first){
          this.batch=batch;
          this.parentAlpha=parentAlpha;
          first=false;
      }
      if(change){
          for(rockObj temp:arreglo){
              batch.draw(rock,temp.getX(),temp.getY());
          }
          //change=false;
      }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        roca.dispose();
    }
}
