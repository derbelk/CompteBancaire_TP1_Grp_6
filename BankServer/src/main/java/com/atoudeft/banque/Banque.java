package com.atoudeft.banque;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        CompteClient cpt = new CompteClient(numeroCompteClient, "");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient   numéro du compte-client
     * @return true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant      montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant      montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     *
     * @param montant             montant à transférer
     * @param numeroCompteInitial numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal   numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un paiement de facture.
     *
     * @param montant       montant de la facture
     * @param numeroCompte  numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description   texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        throw new NotImplementedException();
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip             nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        /*À compléter et modifier :
            - Vérifier que le numéro a entre 6 et 8 caractères et ne contient que des lettres majuscules et des chiffres.
              Sinon, retourner false.
            - Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres. Sinon,
              retourner false.
            - Vérifier s'il y a déjà un compte-client avec le numéro, retourner false.
            - Sinon :
                . Créer un compte-client avec le numéro et le nip;
                . Générer (avec CompteBancaire.genereNouveauNumero()) un nouveau numéro de compte bancaire qui n'est
                  pas déjà utilisé;
                . Créer un compte-chèque aver ce numéro et l'ajouter au compte-client;
                . Ajouter le compte-client à la liste des comptes et retourner true.
         */

        // Ici nous allons vérifier pour le numéro de compte fourni

        boolean NumCptOk = false;
        boolean PinCptOk = false;
        boolean SheckChiffre = false;
        boolean SheckMaj = false;
        boolean Sheckmin = false;
        boolean SheckTail = false;
        int taillech = numCompteClient.length();
        char carac;
        char carap;
        for (int i = 0; i < numCompteClient.length(); i++) {
            carac = numCompteClient.charAt(i);
            if (Character.isUpperCase(carac))
                SheckMaj = true;
            else if (!Character.isLowerCase(carac)) {
                Sheckmin = true;
            } else if (Character.isDigit(carac)) {
                SheckChiffre = true;
            } else if (taillech >= 6 && taillech <= 8) {
                SheckTail = true;
            }
        }
        if (Sheckmin && SheckMaj && SheckChiffre && SheckTail) {
            NumCptOk = true;
        }
        // Ici nous allons vérifier le pin fourni

        boolean Shecklettre = false;
        boolean Shecktailp = false;
        int tailp = nip.length();

        for (int j = 0; j < nip.length(); j++) {
            carap = nip.charAt(j);
            if (!Character.isLetter(carap))
                Shecklettre = true;
            else if (tailp <= 5 && tailp >= 4) {
                Shecktailp = true;
            }
        }
        if (Shecklettre && Shecktailp)
            PinCptOk = true;

        if (NumCptOk && PinCptOk)

            return true;


        return this.comptes.add(new CompteClient(numCompteClient, nip));//À modifier
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        String numCompteCheque="";
        CompteClient cheque;
        cheque = this.getCompteClient(numCompteClient);
        if(cheque == null){
            System.out.println("Ce client n'as pas de Compte chèque");
        }else numCompteCheque = cheque.toString();
        return numCompteCheque;
}

}