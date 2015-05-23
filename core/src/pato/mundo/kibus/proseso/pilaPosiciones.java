package pato.mundo.kibus.proseso;

import java.util.ArrayList;

import pato.mundo.kibus.proseso.objetos.Position;


/**
 * Created by Omar Sanchez on 24/02/2015.
 */
public class pilaPosiciones {
    ArrayList<Position> pila;
    int count;
    boolean hasNext;

    public pilaPosiciones() {
        pila = new ArrayList<Position>();
        count = -1;
        hasNext = false;
    }

    public void push(Position pos) {
        pila.add(pos);
        count++;
        hasNext = true;
    }

    public Position pop() {
        Position tem = pila.get(count);
        pila.remove(count);
        count--;
        if (count == -1)
            hasNext = false;
        return tem;

    }

    public void clear() {
        pila.clear();
        count=-1;
    }
}
