package com.atoudeft.banque.serveur;

import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.TypeCompte;

public class CompteEpargne extends CompteBancaire {
    private static int limiteSolde = 1000;
    private int fraisOperation = 2;
    private double tauxInteret;

    public CompteEpargne(String numero, TypeCompte type, double tauxInteret) {
        super(numero, type);
        tauxInteret = tauxInteret;
    }

    public boolean crediter(double montant) {
        if(montant > 0){
            solde += montant;
            return true;
        }
        return false;
    }

    public boolean debiter(double montant) {
        if((montant> 0) && (solde>= montant)) {
            solde -= montant;
        }else{
        if(solde < limiteSolde){
                solde -= fraisOperation;
            }
            return true;
        }
        return false;
    }

    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }
    public void ajouterInterets(){
        solde += solde*tauxInteret;
    }
}
//