package pato.mundo.kibus.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by Omar Sanchez on 12/02/2015.
 */
public class ActorKibus extends Actor implements Disposable {
    Texture mono;
    TextureRegion monos;
    Animation[] kibus;
    int direccion = 0;
    float duracion = 0;
    int posNewx;
    int posNewY;
    int posActualX,posActualY;
    boolean seguir = false;
    public final static int ABAJO = 0, ARRIBA = 1, IZQUIERDA = 2, DERECHA = 3;
    private final int VELOCIDAD=1;

        public void setDireccion(int direccion) {
        this.direccion = direccion;
    }


    public ActorKibus() {
        mono = new Texture("mono.png");
        monos = new TextureRegion(mono);
        kibus = new Animation[4];
        TextureRegion[][] temp = monos.split(32, 32);
        setSize(32, 32);
        for (byte i = 0; i < temp.length; i++)
            kibus[i] = new Animation(0.2f, temp[i]);
}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion textureRegion = kibus[direccion].getKeyFrame(duracion, true);
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        if (seguir) {
            duracion += delta;
            mover();
        }

    }

    private void mover() {
        posActualX=(int)getX();
        posActualY=(int)getY();
        if(posActualX==this.posNewx&&posActualY==this.posNewY){
            seguir = false;
            return;
        }else if(posActualX==posNewx){
            if(posActualY<posNewY){
                posActualY+=VELOCIDAD;
            }else
                posActualY-=VELOCIDAD;
        }else if(posActualY==posNewY){
            if(posActualX<posNewx)
                posActualX+=VELOCIDAD;
            else
                posActualX-=VELOCIDAD;
        }
        setPosition(posActualX,posActualY);

    }

    @Override
    public void dispose() {
        mono.dispose();
    }

    public void camina(int newX,int newY){
        seguir=true;
        this.posNewY=newY;
        this.posNewx=newX;
    }
    public void initPosition(int x,int y){
        this.posNewx=x;
        this.posNewY=y;
        setPosition(x,y);
    }

    public boolean isSeguir() {
        return !seguir;
    }

}
