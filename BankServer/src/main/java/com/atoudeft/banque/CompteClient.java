package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompteClient implements Serializable {
    private String numero;
    private String nip;
    private List<CompteBancaire> comptes;

    /**
     * Crée un compte-client avec un numéro et un nip.
     *
     * @param numero le numéro du compte-client
     * @param nip le nip
     */
    public CompteClient(String numero, String nip) {
        this.numero = numero;
        this.nip = nip;
        comptes = new ArrayList<>();
    }
    /**
     *Cette méthode est ajoutée pour accéder aux comptes bancaires du client (Compte chèque ou éventuellement Compte Epargne !)

     */
    public List<CompteBancaire> getComptesBancaire(){
        return  comptes;
    }

    // Méthode pour récupérer le numéro de compte client. elle nous sera utile afin de ne pas créé de numéro de compte similaire.


    public String getNumero() {
        return numero;
    }

    /**
     * Ajoute un compte bancaire au compte-client.
     *
     * @param compte le compte bancaire
     * @return true si l'ajout est réussi
     */
    public boolean ajouter(CompteBancaire compte) {
        return this.comptes.add(compte);
    }
}