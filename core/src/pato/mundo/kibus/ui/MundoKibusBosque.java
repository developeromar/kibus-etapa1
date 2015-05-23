package pato.mundo.kibus.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pato.mundo.kibus.Actores.ActorCasa;
import pato.mundo.kibus.Actores.ActorFondo;
import pato.mundo.kibus.Actores.ActorKibus;
import pato.mundo.kibus.Actores.ActorRocas;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.Pantalla;
import pato.mundo.kibus.proseso.Matrix;
import pato.mundo.kibus.proseso.regreso;


/**
 * Created by Omar Sanchez on 12/02/2015.
 */
public class MundoKibusBosque extends Pantalla {
    public static final float SCALA = 1;
    private final float x = 800 * SCALA;
    private final float y = 480 * SCALA;
    private Skin skin;
    private final float YBUTTON = 140;
    private final int XBUTTON = 60;
    private final int SLIDERX = 720;
    private final int SLIDERY = 230;
    private boolean kibusSet = false;

    ActorFondo fondo;
    ActorKibus kibus;
    Stage stage;
    Texture pad, casat, updatet, regresat, audiot;
    ImageButton arriba, izq, der, abj;
    Slider slider;
    Sound click;
    Music bgSound;
    Matrix mapa;
    TextField porcentaje;
    TextButton set;
    ActorRocas obstaculo;
    ImageButton casa, regresar, update, audio;
    ActorCasa casaKibus;
    boolean sound = true;


    public MundoKibusBosque(MainKibus game) {
        super(game);
        //music
        bgSound = Gdx.audio.newMusic(Gdx.files.internal("bgsound.mp3"));
        bgSound.play();
        bgSound.setLooping(true);
        //fondo
        fondo = new ActorFondo();
        //sound
        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
        //generamos las skins
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        ///kibus actor
        kibus = new ActorKibus();
        kibus.setDireccion(ActorKibus.ABAJO);
        //Actor Casa
        casaKibus = new ActorCasa();
        //genereamos la matriz metodo de control totaly agregamos la posicion de kibus
        mapa = new Matrix();
        fondo.setPositioninit(mapa.getFondoPosition());
        //boton casa
        casat = new Texture("casa.png");
        TextureRegion ok = new TextureRegion(casat);
        TextureRegionDrawable dibujable = new TextureRegionDrawable(ok);
        ImageButton.ImageButtonStyle imgbutton = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton.imageUp = dibujable;
        casa = new ImageButton(imgbutton);
        casa.setSize(70, 70);
        casa.setPosition(XBUTTON - 10, YBUTTON + 80);
        casa.setColor(1, 1, 1, 1);
        casa.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                casa.setColor(new Color(1, 0, 0, 1));
                kibus.remove();
                mapa.setKibus(false);
                mapa.pila.clear();
                casaKibus.remove();
                kibusSet = true;
            }
        });
        //Boton audio
        audiot = new Texture("audioon.png");
        TextureRegion ok0 = new TextureRegion(audiot);
        TextureRegionDrawable dibujable0 = new TextureRegionDrawable(ok0);
        ImageButton.ImageButtonStyle imgbutton0 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton0.imageUp = dibujable0;
        audio = new ImageButton(imgbutton0);
        audio.setSize(60, 60);
        audio.setPosition(XBUTTON - 5, YBUTTON + 250);
        audio.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (bgSound.isPlaying()) {
                    bgSound.pause();
                    audio.setColor(1, 0, 0, 1);
                    sound = false;
                } else {
                    bgSound.play();
                    audio.setColor(1, 1, 1, 1);
                    sound = true;
                }
            }
        });
        //Boton update
        updatet = new Texture("reset.png");
        TextureRegion ok2 = new TextureRegion(updatet);
        TextureRegionDrawable dibujable2 = new TextureRegionDrawable(ok2);
        ImageButton.ImageButtonStyle imgbutton2 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton2.imageUp = dibujable2;
        update = new ImageButton(imgbutton2);
        update.setSize(70, 70);
        update.setPosition(XBUTTON - 10, YBUTTON + 160);
        update.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                obstaculo.remove();
                casaKibus.remove();
                mapa.setKibus(false);
                mapa.pila.clear();
                kibus.remove();
                mapa.update();
            }
        });
        //boton Regresar
        regresat = new Texture("regresa.png");
        TextureRegion ok3 = new TextureRegion(regresat);
        TextureRegionDrawable dibujable3 = new TextureRegionDrawable(ok3);
        ImageButton.ImageButtonStyle imgbutton3 = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
        imgbutton3.imageUp = dibujable3;
        regresar = new ImageButton(imgbutton3);
        regresar.setSize(80, 80);
        regresar.setPosition(SLIDERX - 30, SLIDERY - 150);
        regresar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new regreso(mapa, kibus).start();
            }
        });


        ///pad
        pad = new Texture("keys.png");
        TextureRegion trPad = new TextureRegion(pad, 32, 0, 32, 32);
        TextureRegionDrawable up = new TextureRegionDrawable(trPad);
        arriba = new ImageButton(up);
        arriba.setPosition(XBUTTON, YBUTTON);
        arriba.setSize(40, 40);
        arriba.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                kibus.setDireccion(kibus.ARRIBA);
                if (kibus.isSeguir())
                    if (mapa.mover(Matrix.ARRIBA)) {
                        kibus.camina(mapa.getKibusx(), mapa.getKibusy());
                    }
            }
        });
        trPad = new TextureRegion(pad, 0, 32, 32, 32);
        TextureRegionDrawable left = new TextureRegionDrawable(trPad);
        izq = new ImageButton(left);
        izq.setPosition(XBUTTON - 40, YBUTTON - 40);
        izq.setSize(40, 40);
        izq.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                kibus.setDireccion(kibus.IZQUIERDA);
                if (kibus.isSeguir())
                    if (mapa.mover(Matrix.IZQUIERDA)) {
                        kibus.camina(mapa.getKibusx(), mapa.getKibusy());
                    }
            }
        });
        trPad = new TextureRegion(pad, 32, 32, 32, 32);
        TextureRegionDrawable down = new TextureRegionDrawable(trPad);
        abj = new ImageButton(down);
        abj.setPosition(XBUTTON, YBUTTON - 80);
        abj.setSize(40, 40);
        abj.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                kibus.setDireccion(kibus.ABAJO);
                if (kibus.isSeguir())
                    if (mapa.mover(Matrix.ABAJO)) {
                        kibus.camina(mapa.getKibusx(), mapa.getKibusy());
                    }
            }
        });
        trPad = new TextureRegion(pad, 64, 32, 32, 32);
        TextureRegionDrawable rig = new TextureRegionDrawable(trPad);
        der = new ImageButton(rig);
        der.setPosition(XBUTTON + 40, YBUTTON - 40);
        der.setSize(40, 40);
        der.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sound)
                    click.play();
                kibus.setDireccion(kibus.DERECHA);
                if (kibus.isSeguir())
                    if (mapa.mover(Matrix.DERECHA)) {
                        kibus.camina(mapa.getKibusx(), mapa.getKibusy());
                    }

            }
        });
        ///Slider
        slider = new Slider(20, 80, 4, true, skin);
        slider.setPosition(SLIDERX, SLIDERY);
        porcentaje = new TextField("", skin);
        porcentaje.setPosition(SLIDERX - 15, SLIDERY + 150);
        porcentaje.setSize(50, porcentaje.getHeight());
        porcentaje.setText(String.valueOf(slider.getValue()));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                porcentaje.setText(String.valueOf(slider.getValue()));

            }
        });
        //rocas
        obstaculo = new ActorRocas();

        //boton set
        set = new TextButton("set", skin);
        set.setPosition(SLIDERX + 20, SLIDERY + 70);
        set.setSize(40, 20);
        set.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                obstaculo.remove();
                obstaculo.setArreglo(mapa.setObstaculos(slider.getValue()));
                stage.addActor(obstaculo);
                kibus.remove();
                casaKibus.remove();
                mapa.setKibus(false);
                mapa.pila.clear();


            }
        });

        //escene && actor add
        stage = new Stage(new StretchViewport(x, y));
        stage.addActor(fondo);
        stage.addActor(arriba);
        stage.addActor(izq);
        stage.addActor(abj);
        stage.addActor(der);
        stage.addActor(slider);
        stage.addActor(porcentaje);
        stage.addActor(set);
        stage.addActor(obstaculo);
        stage.addActor(casa);
        stage.addActor(update);
        stage.addActor(regresar);
        stage.addActor(audio);

        Gdx.input.setInputProcessor(stage);


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
        if (kibusSet)
            setKibus();
    }

    private void setKibus() {
        if (Gdx.input.isTouched()) {
            int x = (int) Math.round(Gdx.input.getX() / 2.4);
            int y = (int) Math.round(Gdx.input.getY() / 2.25);
            if (mapa.setKibus(x, 480 - y)) {
                kibus.initPosition(mapa.getKibusx(), mapa.getKibusy());
                casaKibus.setPosition(mapa.getKibusx(), mapa.getKibusy());
                stage.addActor(kibus);
                stage.addActor(casaKibus);
                casa.setColor(1, 1, 1, 1);
                kibusSet = false;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        pad.dispose();
        click.dispose();
        obstaculo.dispose();
        fondo.dispose();
        kibus.dispose();
        obstaculo.dispose();
        casat.dispose();
        stage.dispose();
        casaKibus.dispose();
        updatet.dispose();
        regresat.dispose();
        bgSound.dispose();
        audiot.dispose();
    }


}
