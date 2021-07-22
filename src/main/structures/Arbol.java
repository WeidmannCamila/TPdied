package main.structures;

import main.java.classes.Station;

import java.util.Collection;
import java.util.List;


public abstract class Arbol<E extends Comparable<E>> {
    protected E valor;

    public Arbol() {
    }

    public abstract List<E> rango(E var1, E var2);

    public abstract List<E> preOrden();

    public abstract List<E> inOrden();

    public abstract List<E> posOrden();

    public abstract boolean esVacio();

    public abstract E valor();

    public abstract Arbol<E> izquierdo();

    public abstract Arbol<E> derecho();

    public abstract boolean contiene(E var1);

    public abstract boolean equals(Arbol<E> var1);

    public abstract void agregar(E var1);

    public abstract int profundidad();

    public abstract int cuentaNodosDeNivel(int var1);

    public abstract boolean esCompleto();

    public abstract boolean esLleno();
}
