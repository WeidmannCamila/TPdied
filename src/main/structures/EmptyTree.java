package main.structures;

import java.util.ArrayList;
import java.util.List;


public class EmptyTree <E extends Comparable<E>> extends Tree<E> {

    public EmptyTree() {
        this.valor = null;
    }

    @Override
    public List<E> preOrden() {
        return new ArrayList<E>();
    }

    @Override
    public List<E> inOrden() {
        return new ArrayList<E>();
    }

    @Override
    public List<E> posOrden() {
        return new ArrayList<E>();
    }

    @Override
    public boolean esVacio() {
        return true;
    }

    @Override
    public E valor() {
        return null;
    }

    @Override
    public Tree<E> izquierdo() {
        return null;
    }

    @Override
    public Tree<E> derecho() {
        return null;
    }

    @Override
    public boolean contiene(E unValor) {
        return false;
    }

    @Override
    public boolean equals(Tree<E> unArbol) {
        return unArbol.esVacio();
    }

    @Override
    public void agregar(E a) {

    }

    @Override
    public int profundidad() {
        return 0;
    }

    @Override
    public int cuentaNodosDeNivel(int nivel) {
        return 0;
    }

    @Override
    public boolean esCompleto() {
        return false;
    }

    @Override
    public boolean esLleno() {
        return false;
    }

    @Override
    public int cuentaNodosDeNivelAux(int nivel, int nivelActual) {
        return 0;
    }

    public List<E> buscar (E busqueda){
        List<E> list = new ArrayList<E>();
        return list;
    }

}
