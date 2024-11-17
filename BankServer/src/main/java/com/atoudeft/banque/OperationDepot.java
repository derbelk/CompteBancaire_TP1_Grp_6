package com.atoudeft.banque;

public class OperationDepot extends Operation{
    private double montantDepot;
    private double montantCompteBancaire;
    private CompteBancaire compteBancaire;
    private double montantFacture;
    private String numFacture;
    private String descriptFacture;

    public OperationDepot(TypeOperation typeOperation, double montantDepot, double montantCompteBancaire, CompteBancaire compteBancaire, double montantFacture, String numFacture, String descriptFacture) {
        super(typeOperation);
        this.montantDepot = montantDepot;
        this.montantCompteBancaire = montantCompteBancaire;
        this.compteBancaire = compteBancaire;
        this.montantFacture = montantFacture;
        this.numFacture = numFacture;
        this.descriptFacture = descriptFacture;
    }

    @Override
    public String toString() {
        return  super.toString()+
                " OperationDepot{" +
                "montantDepot=" + montantDepot +
                ", montantCompteBancaire=" + montantCompteBancaire +
                ", compteBancaire=" + compteBancaire +
                ", montantFacture=" + montantFacture +
                ", numFacture='" + numFacture + '\'' +
                ", descriptFacture='" + descriptFacture + '\'' +
                '}';
    }
}
