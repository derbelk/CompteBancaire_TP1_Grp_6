package com.atoudeft.banque;

public class OperationRetrait extends Operation{
    private double montantRetrait;
    private double montantCompteBancaire;
    private CompteBancaire compteBancaire;
    private double montantFacture;
    private String numFacture;
    private String descriptFacture;

    public OperationRetrait(TypeOperation typeOperation, double montantRetrait, double montantCompteBancaire, CompteBancaire compteBancaire, double montantFacture, String numFacture, String descriptFacture) {
        super(typeOperation);
        this.montantRetrait = montantRetrait;
        this.montantCompteBancaire = montantCompteBancaire;
        this.compteBancaire = compteBancaire;
        this.montantFacture = montantFacture;
        this.numFacture = numFacture;
        this.descriptFacture = descriptFacture;
    }

    @Override
    public String toString() {
        return  super.toString()+
                " OperationRetrait{" +
                "montantRetrait=" + montantRetrait +
                ", montantCompteBancaire=" + montantCompteBancaire +
                ", compteBancaire=" + compteBancaire +
                ", montantFacture=" + montantFacture +
                ", numFacture='" + numFacture + '\'' +
                ", descriptFacture='" + descriptFacture + '\'' +
                '}';
    }
}
