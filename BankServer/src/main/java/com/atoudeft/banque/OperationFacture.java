package com.atoudeft.banque;

public class OperationFacture extends Operation{
    private double montantOperationFacture;
    private double montantCompteBancaire;
    private CompteBancaire compteBancaire;
    private double montantFacture;
    private String numFacture;
    private String descriptFacture;

    public OperationFacture(TypeOperation typeOperation, double montantOperationFacture, double montantCompteBancaire, CompteBancaire compteBancaire, double montantFacture, String numFacture, String descriptFacture) {
        super(typeOperation);
        this.montantOperationFacture = montantOperationFacture;
        this.montantCompteBancaire = montantCompteBancaire;
        this.compteBancaire = compteBancaire;
        this.montantFacture = montantFacture;
        this.numFacture = numFacture;
        this.descriptFacture = descriptFacture;
    }

    @Override
    public String toString() {
        return  super.toString()+
                " OperationFacture{" +
                "montantOperationFacture=" + montantOperationFacture +
                ", montantCompteBancaire=" + montantCompteBancaire +
                ", compteBancaire=" + compteBancaire +
                ", montantFacture=" + montantFacture +
                ", numFacture='" + numFacture + '\'' +
                ", descriptFacture='" + descriptFacture + '\'' +
                '}';
    }
}
