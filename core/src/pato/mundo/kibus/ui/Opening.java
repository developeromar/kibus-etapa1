package pato.mundo.kibus.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pato.mundo.kibus.Actores.ActorLogo;
import pato.mundo.kibus.MainKibus;
import pato.mundo.kibus.Pantalla;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

/**
 * Created by Omar Sanchez on 11/02/2015.
 */
public class Opening extends Pantalla implements Disposable {
    private static final float DIFERENCIA = 30;
    public static final float SCALA = 0.7F;
    private Stage stage;
    public Skin skin;
    private ActorLogo logo,fondo;
    private final float x=800*SCALA;
    private final float y=480*SCALA;
    Music bgSound;

    public Opening(final MainKibus game) {
        super(game);
        bgSound = Gdx.audio.newMusic(Gdx.files.internal("intro.MP3"));
        bgSound.play();
        bgSound.setLooping(true);
        fondo=new ActorLogo("escudo.jpg",236,321);
        fondo.setPosition(x/2-fondo.getWidth()/2,y/2-fondo.getHeight()/2);
        Color col=fondo.getColor();
        fondo.setColor(col.r,col.g,col.b,col.a*.3f);
        logo=new ActorLogo("logo.png",501,136);
        stage = new Stage(new StretchViewport(x, 480 * SCALA));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final TextButton iniciar = new TextButton("Iniciar", skin);
        iniciar.setPosition(x/2-iniciar.getWidth(), y/2-iniciar.getHeight()*4);
        logo.setPosition(x/2-logo.getWidth()/2,y/2-DIFERENCIA);
        iniciar.setSize(iniciar.getWidth() * 2, iniciar.getHeight() * 1.5f);
        iniciar.setColor(0.2f, .2f, 1, 1);
        iniciar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                game.setScreen(new MundoKibusBosque(game));
                bgSound.stop();
            }
        });
        stage.addActor(fondo);
        stage.addActor(logo);
        stage.addActor(iniciar);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        bgSound.dispose();
        logo.dispose();
    }
}
