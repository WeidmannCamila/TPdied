package main.structures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> extends Tree<E> {

        protected Tree<E> izquierdo;
        protected Tree<E> derecho;

        public BinarySearchTree() {
            this.valor = null;
            this.izquierdo = new EmptyTree<E>();
            this.derecho = new EmptyTree<E>();
        }

        public BinarySearchTree(E e) {
            this.valor = e;
            this.izquierdo = new EmptyTree<E>();
            this.derecho = new EmptyTree<E>();
        }

        public BinarySearchTree(E e, Tree<E> i, Tree<E> d) {
            this.valor = e;
            this.izquierdo = i;
            this.derecho = d;
        }

        @Override
        public List<E> preOrden() {
            List<E> lista = new ArrayList<E>();
            lista.add(this.valor);
            lista.addAll(this.izquierdo.preOrden());
            lista.addAll(this.derecho.preOrden());
            return lista;
        }

        @Override
        public List<E> inOrden() {
            List<E> lista = new ArrayList<E>();
            lista.addAll(this.izquierdo.preOrden());
            lista.add(this.valor);
            lista.addAll(this.derecho.preOrden());
            return lista;
        }

        @Override
        public List<E> posOrden() {
            List<E> lista = new ArrayList<E>();
            lista.addAll(this.izquierdo.preOrden());
            lista.addAll(this.derecho.preOrden());
            lista.add(this.valor);
            return lista;

        }

        @Override
        public boolean esVacio() {
            return false;
        }

        @Override
        public E valor() {
            return this.valor;
        }

        @Override
        public Tree<E> izquierdo() {
            return this.izquierdo;
        }

        @Override
        public Tree<E> derecho() {
            return this.derecho;
        }

        @Override
        public void agregar(E a) {
            if (this.valor.compareTo(a) < 1) {
                if (this.derecho.esVacio())
                    this.derecho = new BinarySearchTree<E>(a);
                else
                    this.derecho.agregar(a);
            } else {
                if (this.izquierdo.esVacio())
                    this.izquierdo = new BinarySearchTree<E>(a);
                else
                    this.izquierdo.agregar(a);
            }
        }

        @Override
        public boolean equals(Tree<E> unArbol) {
            return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo())
                    && this.derecho.equals(unArbol.derecho());
        }

        @Override
        public boolean contiene(E unValor) {
            if (this.valor().compareTo(unValor) == 0) {
                return true;

            } else if (this.valor().compareTo(unValor) > 0) {
                return this.derecho.contiene(unValor);
            } else {
                return this.izquierdo.contiene(unValor);
            }
        }

        @Override
        public int profundidad() {
            return 1 + Math.max(this.izquierdo.profundidad(), this.derecho.profundidad());
        }

        @Override
        public int cuentaNodosDeNivel(int nivel) {
            return this.cuentaNodosDeNivelAux(nivel, 0);
        }

        @Override
        public int cuentaNodosDeNivelAux(int nivel, int nivelActual) {
            if (nivel == nivelActual) {
                return 1;
            } else if (nivelActual == nivel - 1) {
                return this.izquierdo.cuentaNodosDeNivelAux(nivel, nivelActual + 1)
                        + this.derecho.cuentaNodosDeNivelAux(nivel, nivelActual + 1);
            } else {
                return this.cuentaNodosDeNivelAux(nivel, nivelActual++);
            }
        }



    @Override
        public boolean esCompleto() {

            Integer profDer = this.derecho.profundidad();
            Integer profIzq = this.izquierdo.profundidad();

            if (this.derecho.esVacio() && this.izquierdo.esVacio())
                return true;

            else if (profDer == profIzq) {
                return (this.izquierdo.esLleno() && this.derecho.esCompleto());
            } else if (profDer == profIzq - 1) {
                return (this.derecho.esLleno() && this.izquierdo.esCompleto());
            } else
                return false;
        }

        @Override
        public boolean esLleno() {

            if (this.cuentaNodosDeNivel(this.profundidad()) == Math.pow(2, this.profundidad()))
                return true;

            return false;
        }

        public List<E> rango(E inicio, E fin) {
            List<E> lista = new ArrayList<E>();
            List<E> inorden = new ArrayList<E>();

            inorden = this.inOrden();

            for (E e : inorden) {
                if (e.compareTo(inicio) >= 0 && e.compareTo(fin) <= 0) {
                    lista.add(e);
                }
            }
            return lista;
        }



}
