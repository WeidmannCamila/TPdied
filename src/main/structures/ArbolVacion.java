package main.structures;

import java.util.ArrayList;
import java.util.List;

class ArbolVacio<E extends Comparable<E>> extends Arbol<E> {
    public ArbolVacio() {
        this.valor = null;
    }

    public List<E> preOrden() {
        return new ArrayList();
    }

    public List<E> rango(E a, E b) {
        return new ArrayList();
    }

    public List<E> inOrden() {
        return new ArrayList();
    }

    public List<E> posOrden() {
        return new ArrayList();
    }

    public boolean esVacio() {
        return true;
    }

    public E valor() {
        return null;
    }

    public Arbol<E> izquierdo() {
        return null;
    }

    public Arbol<E> derecho() {
        return null;
    }

    public boolean contiene(E unValor) {
        return false;
    }

    public boolean equals(Arbol<E> unArbol) {
        return unArbol.esVacio();
    }

    public void agregar(E a) {
    }

    public int profundidad() {
        return 0;
    }

    public int cuentaNodosDeNivel(int nivel) {
        return 0;
    }

    public int cuentaNodosDeNivel2(int nivel, int contador) {
        return 0;
    }

    public boolean esCompleto() {
        return false;
    }

    public boolean esLleno() {
        return false;
    }
}
