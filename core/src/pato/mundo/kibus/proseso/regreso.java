package pato.mundo.kibus.proseso;

import pato.mundo.kibus.Actores.ActorKibus;
import pato.mundo.kibus.proseso.objetos.Position;

/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class regreso extends Thread {

    Matrix matrix;
    ActorKibus kibus;
    Position pos;

    public regreso(Matrix matrix, ActorKibus kibus) {

        this.matrix = matrix;
        this.kibus = kibus;
    }

    @Override
    public void run() {
        while (matrix.pila.hasNext) {
            if (kibus.isSeguir()) {
                pos = matrix.pila.pop();
                if (matrix.mover(pos)) {
                    kibus.setDireccion(pos.getEstate());
                    kibus.camina(matrix.getKibusx(), matrix.getKibusy());
                }
            }
        }

    }
}
