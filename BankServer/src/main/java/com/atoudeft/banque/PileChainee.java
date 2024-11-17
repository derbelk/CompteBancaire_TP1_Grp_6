package com.atoudeft.banque;

import java.io.Serializable;

public class PileChainee implements Serializable {

    private Noeud sommet; 		//r�f�rence l'�l�ment au sommet de la file

    private int nbElement; 	//Nombre d'�l�ments dans la pile (utilis� pour
    //ne pas avoir � recalculer le nombre d'�l�ments dans
    //la m�thode taille()). Ne pas oublier de l'incr�menter
    //lorsqu'on empile et le d�cr�menter lorsqu'on d�pile.

    /**
     * Constructeur sans param�tre
     * Initialise la r�f�rence premier � null et nbElement � 0.
     */
    public PileChainee(){
        this.sommet = null;
        this.nbElement = 0;
    }

    /**
     * Cr�e une pile de la m�me fa�on que le premier constructeur.
     * Ce constructeur est laiss� l� juste pour que les programmes d�j� �crits
     * avec le version statique de la pile continuent de fonctionner.
     * @param taille La taille voulue pour la file.
     */
    public PileChainee(int taille){
        this.sommet = null;
        this.nbElement = 0;
    }

    /**
     * Ajoute un �l�ment au sommet de la pile.
     *
     * @param element l'�l�ment � empiler.
     * @return true si l'op�ration r�ussit et false sinon (ce qui n'arrive jamais)
     */
    public boolean empiler(Object element) {
        Noeud nouveau = new Noeud(sommet,element);
        this.sommet = nouveau;
        this.nbElement++;
        return true;
    }

    /**
     * Retire l'�l�ment au sommet de la pile.
     *
     * @return L'�l�ment au sommet de la pile s'il existe ou null sinon.
     */
    public Object depiler(){

        if (estVide())
            return null;

        Object objRetire = this.sommet.donnee;
        this.sommet = this.sommet.suivant;
        this.nbElement--;
        return objRetire;
    }

    /**
     * Indique si la pile est vide.
     *
     * @return true si la  pile est vide et false sinon.
     */
    public boolean estVide(){

        return nbElement==0; //ou: return this.sommet==null;
    }

    /**
     * Vide la pile.
     */
    public void vider(){
        while (!estVide())
            depiler();
    }

    /**
     * Permet de consulter l'�l�ment au sommet de la pile sans l'enlever.
     *
     * @return L'�l�ment au sommet si la pile n'est pas vide et null sinon.
     */
    public Object peek(){
        if (this.sommet==null) //ou : if (this.nbElement==0)
            return null;
        return this.sommet.donnee;
    }


    /**
     * Retourne le nombre d'�l�ments dans la pile.
     *
     * @return Le nombre d'�l�ments actuellement dans la pile.
     */
    public int taille(){

        return nbElement;
    }

    private class Noeud implements Serializable{
        public Object donnee;
        public Noeud suivant;

        public Noeud(Noeud next, Object data) {
            this.donnee = data;
            this.suivant = next;
        }
    }
}
