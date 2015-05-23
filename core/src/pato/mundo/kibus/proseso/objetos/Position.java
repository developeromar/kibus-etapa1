package pato.mundo.kibus.proseso.objetos;

/**
 * Created by Omar Sanchez on 19/02/2015.
 */
public class Position {
    private int estate;
    private int x;
    private int y;

    public Position(int estate, int x, int y) {
        this.estate = estate;
        this.x = x;
        this.y = y;
    }
    public int getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
