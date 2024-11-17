package com.atoudeft.banque;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;
    private int index = 0;

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
    //CETTE MÉTHODE A ÉTÉ REDÉFINIE POUR VÉRIFIER L'EXISTENCE D'UN COMPTE BANCAIRE
    public  CompteClient getCompteClient(String numeroCompteClient) {
        boolean trouve = false;
        Iterator<CompteClient>iterator = comptes.iterator();
        while (iterator.hasNext()){
            CompteClient compteClient = iterator.next();
            if (numeroCompteClient.equals(compteClient.getNumero())){
                return compteClient;
            }
        }
        return null;
        }
       /* CompteClient cpt = new CompteClient(numeroCompteClient, "");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }
    */


    /*MÉTHODE POUR RETOURNER TOUS LES CLIENTS DE LA BANQUE */
    public List<CompteClient> getComptes() {
        return comptes;
    }
    //TU VÉRIFIE PAR LA BANQUE LA VALIDITÉ D'UN COMPTE ÉPARGNE!

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
        //throw new NotImplementedException();

        boolean depot = false;
        CompteClient compteClient = getCompteClient(numeroCompte);
        ArrayList<CompteBancaire>compteBancaires = (ArrayList<CompteBancaire>) compteClient.getComptesBancaire();
        Iterator<CompteBancaire>iterator = compteBancaires.iterator();
        while (iterator.hasNext()){
            CompteBancaire compteBancaire = iterator.next();
            compteBancaire.solde += montant;
            depot = true;

        }

        return depot;
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant      montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        //throw new NotImplementedException(); rebv

        boolean retrait = false;
        CompteClient compteClient = getCompteClient(numeroCompte);
        ArrayList<CompteBancaire>compteBancaires = (ArrayList<CompteBancaire>) compteClient.getComptesBancaire();
        Iterator<CompteBancaire>iterator = compteBancaires.iterator();
        while (iterator.hasNext()){
            CompteBancaire compteBancaire = iterator.next();
            if (compteBancaire.solde<0)
                retrait=false;

            compteBancaire.solde += montant;
            retrait = true;

        }

        return retrait;
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
        //throw new NotImplementedException();
        boolean tranfert = false;

        retirer(montant,numeroCompteInitial);
        deposer(montant,numeroCompteFinal);
        tranfert=true;

        return tranfert;
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
              Sinon, retourner false. OK
            - Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres. Sinon,
              retourner false. OK
            - Vérifier s'il y a déjà un compte-client avec le numéro, retourner false. OK
            - Sinon :
                . Créer un compte-client avec le numéro et le nip; OK
                . Générer (avec CompteBancaire.genereNouveauNumero()) un nouveau numéro de compte bancaire qui n'est
                  pas déjà utilisé; OK
                . Créer un compte-chèque aver ce numéro et l'ajouter au compte-client; OK
                . Ajouter le compte-client à la liste des comptes et retourner true. OK
         */

        int taille = numCompteClient.length();
        int tailleNip = nip.length();
        char lettre;
        char carac;
        int nbMaj = 0, nbChiffre = 0;
        CompteClient compteClient;
        CompteCheque compteCheque;
        String numcOmpteBancaire;

        // ------------------------------------- Vérrification du numéro client ----------------------------------------------------------

        if (taille < 6 || taille > 8) {
            return false;
        } else {
            for (int i = 0; i < taille; i++) {
                lettre = numCompteClient.charAt(i);
                if (Character.isUpperCase(lettre)){
                    nbMaj++;
                }
                if (Character.isDigit(lettre)){
                    nbChiffre++;
                }
            }
            if (nbMaj == 0 || nbChiffre == 0){
                return false;
            }
        }
        // ---------------------------------------------------------------------------------------------------------------------------------

        //------------------------------------- Vérification du nip ------------------------------------------------------------------------

        if (tailleNip < 4 || tailleNip > 5) {
            return false;
        } else {
            for (int j = 0; j < tailleNip; j++) {
                carac = nip.charAt(j);
                if (!Character.isDigit(carac)) {
                    return false;
                }
            }
        }

        //----------------------------------------------------------------------------------------------------------------------------------
        Iterator<CompteClient> iterator = comptes.iterator();
        // LA BOUCLE WHILE ME PERMET DE VERIFIER SI LE NUMÉRO DU COMPTE CLIENT EST DÉJÀ UTILISÉ PAR LES AUTRES COMPTES.
        while(iterator.hasNext())
        {
            CompteClient compteClientActuel = iterator.next();

            if(compteClientActuel != null){
                if(numCompteClient.equals(compteClientActuel.getNumero())){
                    return false;
                }
            }
        }
        //JE CRÉE UN NOUVEAU COMPTE CLIENT APRÈS AVOIR FAIT LA VÉRIFICATION
        compteClient = new CompteClient(numCompteClient,nip);

        //CRÉATION D'UN NOUVEAU NUMÉRO DE COMPTE BANCAIRE ET VÉRIFICATION DE S'IL EST UNIQUE OU PAS. DANS LE CAS OÙ IL NE L'EST PAS ON LE RECRÉÉ

        String numCompteBancaire = CompteBancaire.genereNouveauNumero();
        Iterator<CompteClient>iterator1 = comptes.iterator();
        CompteClient compteClient1;
        while (iterator1.hasNext()) {
            compteClient1 = iterator1.next();
            ArrayList<CompteBancaire> compteBancaires = (ArrayList<CompteBancaire>) compteClient1.getComptesBancaire();
            Iterator<CompteBancaire>iterator2 = compteBancaires.iterator();
            while (iterator2.hasNext()){
                CompteBancaire compteBancaire = iterator2.next();
                while (numCompteBancaire.equals(compteBancaire.getNumero())) {
                    numCompteBancaire = CompteBancaire.genereNouveauNumero();
                }
            }
        }
        //------------------------------------------------------------------------------------------------------------------------------------------

        //JE CRÉE LE NOUVEAU COMPTE CHÈQUE
        compteCheque = new CompteCheque(numCompteBancaire,TypeCompte.CHEQUE);
        compteClient.ajouter(compteCheque);
        this.comptes.add(compteClient);
        return true;
    }
    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.123
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        CompteClient compteClient = getCompteClient(numCompteClient);

        if (compteClient == null) {
            return null;
        }

        Iterator<CompteBancaire> iterator = compteClient.getComptesBancaire().iterator();

        while (iterator.hasNext()) {
            CompteBancaire compte = iterator.next();
            if (compte != null && compte.getType().equals(TypeCompte.CHEQUE)) {
                return compte.getNumero();
            }
        }
        return null;
    }
        /**
         *CETTE MÉTHODE EST UTILISÉE POUR VÉRIFIER SI LE CLIENT POSSÈDE UN COMPTE ÉPARGNE !
         */

    public String getNumeroCompteEpargne (String numCompteClient){
        CompteClient compteClient = getCompteClient(numCompteClient);
        if(compteClient == null){
            return null;
        }

        Iterator<CompteBancaire> iterator = compteClient.getComptesBancaire().iterator();

        while (iterator.hasNext())
        {
            CompteBancaire compte = iterator.next();
            if(compte!=null && compte.getType().equals(TypeCompte.EPARGNE)){
                return compte.getNumero();
            }
        }
        return null;
    }
}