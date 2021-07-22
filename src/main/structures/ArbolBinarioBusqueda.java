package main.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArbolBinarioBusqueda<E extends Comparable<E>> extends Arbol<E> {
    protected Arbol<E> izquierdo;
    protected Arbol<E> derecho;

    public ArbolBinarioBusqueda() {
        this.valor = null;
        this.izquierdo = new ArbolVacio();
        this.derecho = new ArbolVacio();
    }

    public ArbolBinarioBusqueda(E e) {
        this.valor = e;
        this.izquierdo = new ArbolVacio();
        this.derecho = new ArbolVacio();
    }

    public ArbolBinarioBusqueda(E e, Arbol<E> i, Arbol<E> d) {
        this.valor = e;
        this.izquierdo = i;
        this.derecho = d;
    }

    public List<E> buscar(E busqueda) {
        List<E> lista = new ArrayList();
        new ArrayList();
        List<E> inorden = this.inOrden();
        Iterator var5 = inorden.iterator();

        while(var5.hasNext()) {
            E e = (E) var5.next();
            if (e.compareTo(busqueda) == 0) {
                lista.add(e);
            }
        }

        return lista;
    }

    public List<E> buscarMin(E busqueda) {
        List<E> lista = new ArrayList();
        new ArrayList();
        List<E> inorden = this.inOrden();
        Iterator var5 = inorden.iterator();

        while(true) {
            Comparable e;
            do {
                if (!var5.hasNext()) {
                    return lista;
                }

                e = (Comparable)var5.next();
            } while(e.compareTo(busqueda) != 0 && e.compareTo(busqueda) >= 0);

            lista.add((E) e);
        }
    }

    public List<E> buscarMax(E busqueda) {
        List<E> lista = new ArrayList();
        new ArrayList();
        List<E> inorden = this.inOrden();
        Iterator var5 = inorden.iterator();

        while(true) {
            Comparable e;
            do {
                if (!var5.hasNext()) {
                    return lista;
                }

                e = (Comparable)var5.next();
            } while(e.compareTo(busqueda) != 0 && e.compareTo(busqueda) <= 0);

            lista.add((E) e);
        }
    }

    public List<E> preOrden() {
        List<E> lista = new ArrayList();
        lista.add(this.valor);
        lista.addAll(this.izquierdo.preOrden());
        lista.addAll(this.derecho.preOrden());
        return lista;
    }

    public List<E> inOrden() {
        List<E> lista = new ArrayList();
        lista.addAll(this.izquierdo.inOrden());
        lista.add(this.valor);
        lista.addAll(this.derecho.inOrden());
        return lista;
    }

    public List<E> posOrden() {
        List<E> lista = new ArrayList();
        lista.addAll(this.izquierdo.posOrden());
        lista.addAll(this.derecho.posOrden());
        lista.add(this.valor);
        return lista;
    }

    public boolean esVacio() {
        return false;
    }

    public E valor() {
        return this.valor;
    }

    public Arbol<E> izquierdo() {
        return this.izquierdo;
    }

    public Arbol<E> derecho() {
        return this.derecho;
    }

    public void agregar(E a) {
        if (this.valor == null) {
            this.valor = a;
        } else if (this.valor.compareTo(a) < 1) {
            if (this.derecho.esVacio()) {
                this.derecho = new ArbolBinarioBusqueda(a);
            } else {
                this.derecho.agregar(a);
            }
        } else if (this.izquierdo.esVacio()) {
            this.izquierdo = new ArbolBinarioBusqueda(a);
        } else {
            this.izquierdo.agregar(a);
        }

    }

    public boolean equals(Arbol<E> unArbol) {
        return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
    }

    public boolean contiene(E unValor) {
        if (this.valor.compareTo(unValor) == 0) {
            return true;
        } else {
            return this.valor.compareTo(unValor) > 0 ? this.izquierdo.contiene(unValor) : this.derecho.contiene(unValor);
        }
    }

    public int profundidad() {
        return this.izquierdo.esVacio() && this.derecho.esVacio() ? 0 : Math.max(this.izquierdo.profundidad(), this.derecho.profundidad()) + 1;
    }

    public int cuentaNodosDeNivel(int nivel) {
        if (nivel > this.profundidad()) {
            return 0;
        } else if (this.esVacio()) {
            return 0;
        } else {
            return nivel == 0 ? 1 : this.izquierdo.cuentaNodosDeNivel(nivel - 1) + this.derecho.cuentaNodosDeNivel(nivel - 1);
        }
    }

    public boolean esCompleto() {
        if (this.profundidad() == 0) {
            return true;
        } else if (this.izquierdo.profundidad() - this.derecho.profundidad() == 1) {
            return this.derecho.esLleno() && this.izquierdo.esCompleto();
        } else if (this.izquierdo.profundidad() - this.derecho.profundidad() == 0) {
            return this.izquierdo.esLleno() && this.derecho.esCompleto();
        } else {
            return false;
        }
    }

    public boolean esLleno() {
        return (double)this.cuentaNodosDeNivel(this.profundidad()) == Math.pow(2.0D, (double)this.profundidad());
    }

    public List<E> rango(E a, E b) {
        List<E> lista = new ArrayList();
        lista.addAll(this.izquierdo.rango(a, b));
        if (this.valor.compareTo(a) >= 0 && this.valor.compareTo(b) <= 0) {
            lista.add(this.valor);
        }

        lista.addAll(this.derecho.rango(a, b));
        return lista;
    }
}