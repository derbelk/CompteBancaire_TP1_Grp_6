package com.atoudeft.banque;

public class OperationTransfer extends Operation{
    private double montantOperationTransfer;
    private double montantCompteBancaire;
    private CompteBancaire compteBancaire;
    private double montantFacture;
    private String numFacture;
    private String descriptFacture;

    public OperationTransfer(TypeOperation typeOperation, double montantOperationTransfer, double montantCompteBancaire, CompteBancaire compteBancaire, double montantFacture, String numFacture, String descriptFacture) {
        super(typeOperation);
        this.montantOperationTransfer = montantOperationTransfer;
        this.montantCompteBancaire = montantCompteBancaire;
        this.compteBancaire = compteBancaire;
        this.montantFacture = montantFacture;
        this.numFacture = numFacture;
        this.descriptFacture = descriptFacture;
    }

    @Override
    public String toString() {
        return  super.toString()+
                " OperationTransfer{" +
                "montantOperationTransfer=" + montantOperationTransfer +
                ", montantCompteBancaire=" + montantCompteBancaire +
                ", compteBancaire=" + compteBancaire +
                ", montantFacture=" + montantFacture +
                ", numFacture='" + numFacture + '\'' +
                ", descriptFacture='" + descriptFacture + '\'' +
                '}';
    }
}
