package pato.mundo.kibus.proseso;

import java.util.ArrayList;

import pato.mundo.kibus.proseso.objetos.Position;
import pato.mundo.kibus.proseso.objetos.rockObj;

/**
 * Created by Omar Sanchez on 19/02/2015.
 */
public class Matrix {
    private static Position matriz[][];
    private final int x = 160;
    private final int y = 448;
    private int kibusx;
    private int kibusy;

    private boolean kibus;
    public static final int ARRIBA = 0, IZQUIERDA = 1, DERECHA = 2, ABAJO = 3;
    private int obsReales;
    private ArrayList<rockObj> rocas;
    public pilaPosiciones pila;

    public Matrix() {
        init();
        kibus = false;
        obsReales = 0;
        rocas = new ArrayList<rockObj>();
        pila = new pilaPosiciones();
    }

    public void init() {
        matriz = new Position[15][15];
        initMatriz();
    }

    public void initMatriz() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                matriz[i][j] = new Position(0, x + (j * 32), y - (i * 32));
                //  System.out.print(Integer.toString( matriz[i][j].getX())+" ");

            }
            // System.out.println();
        }
    }

    public boolean setKibus(int x, int y) {
        this.kibusx = damex(x);
        this.kibusy = damey(y);
        if (kibusx >= 0 && kibusy >= 0 && matriz[kibusy][kibusx].getEstate() == 0) {
            this.kibus = true;
            return true;
        }
        return false;
    }

    public int damex(int x) {
        int temp;
        for (int j = 0; j < 15; j++) {
            temp = matriz[0][j].getX();
            if (temp <= x && temp + 32 > x)
                return j;
        }
        return -1;
    }

    public int damey(int y) {
        int temp;
        for (int j = 0; j < 15; j++) {
            temp = matriz[j][0].getY();
            if (temp >= y && temp - 32 < y)
                return j;
        }
        return -1;
    }

    public boolean mover(int direccion) {
        if (!kibus)
            return false;
        switch (direccion) {
            case 0:
                if (kibusy == 0)
                    return false;
                if (matriz[kibusy - 1][kibusx].getEstate() != 0)
                    return false;
                else {
                    guardaPosicion(new Position(0, kibusx, kibusy));
                    kibusy -= 1;
                }

                break;
            case 1:
                if (kibusx == 0)
                    return false;
                if (matriz[kibusy][kibusx - 1].getEstate() != 0)
                    return false;
                else {
                    guardaPosicion(new Position(3, kibusx, kibusy));
                    kibusx -= 1;
                }
                break;
            case 2:
                if (kibusx == 14)
                    return false;
                if (matriz[kibusy][kibusx + 1].getEstate() != 0)
                    return false;
                else {
                    guardaPosicion(new Position(2, kibusx, kibusy));
                    kibusx += 1;
                }
                break;
            case 3:
                if (kibusy == 14)
                    return false;
                if (matriz[kibusy + 1][kibusx].getEstate() != 0)
                    return false;
                else {
                    guardaPosicion(new Position(1, kibusx, kibusy));
                    kibusy += 1;
                }
                break;

        }
        return true;
    }

    public boolean mover(Position obj) {
        kibusx = obj.getX();
        kibusy = obj.getY();

        return true;
    }

    public int getKibusy() {
        return matriz[kibusy][kibusx].getY();
    }


    public int getKibusx() {
        return matriz[kibusy][kibusx].getX();
    }

    public ArrayList<rockObj> setObstaculos(float numObstaculos) {
        numObstaculos = Math.round(numObstaculos / 100 * 225);
        if (numObstaculos == obsReales)
            return posicionreal();
        if (numObstaculos > obsReales) {
            int Diferencia = (int) numObstaculos - obsReales;
            agregar(Diferencia);
        } else {
            int diferencia = obsReales - (int) numObstaculos;
            int tamano = rocas.size() - 1;
            rockObj roca;
            for (int i = 0; i < diferencia; i++) {
                roca = rocas.get(tamano);
                matriz[roca.getX()][roca.getY()].setEstate(0);
                rocas.remove(tamano);
                tamano--;
            }


        }
        obsReales = (int) numObstaculos;
        return posicionreal();

    }

    private void agregar(int diferencia) {
        rockObj roca;
        while (diferencia > 0) {
            roca = new rockObj((int) (Math.random() * (14 + 1)), (int) (Math.random() * (14 + 1)));
            if (existRock(roca)) {
                rocas.add(roca);
                diferencia--;
            }
        }
    }

    private boolean existRock(rockObj roca) {
        if (matriz[roca.getX()][roca.getY()].getEstate() == 0) {
            matriz[roca.getX()][roca.getY()].setEstate(1);
            return true;
        } else
            return false;
    }

    public ArrayList<rockObj> getRocas() {
        return rocas;
    }

    public void update() {
        obsReales = 0;
        initMatriz();
        this.kibus = false;
        rocas.clear();
    }

    private ArrayList<rockObj> posicionreal() {
        ArrayList<rockObj> temp = new ArrayList<rockObj>();
        Position x;
        for (rockObj i : rocas) {
            x = matriz[i.getX()][i.getY()];
            temp.add(new rockObj(x.getX(), x.getY()));
        }
        return temp;
    }

    public rockObj getFondoPosition() {
        return new rockObj(matriz[0][0].getX(), matriz[14][0].getY());
    }

    public boolean isKibus() {
        return kibus;
    }

    public void setKibus(boolean kibus) {
        this.kibus = kibus;
    }

    public void guardaPosicion(Position pos) {
        pila.push(pos);
    }

}
