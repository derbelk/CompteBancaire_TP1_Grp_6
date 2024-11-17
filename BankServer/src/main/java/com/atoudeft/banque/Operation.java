package com.atoudeft.banque;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public abstract class Operation implements Serializable {
    private TypeOperation typeOperation;
    private Date date;

    public Operation(TypeOperation typeOperation) {
        //PRIS DE LA DOCUMENTATION ORACLE : https://docs.oracle.com/javase/8/docs/api/index.html?java/text/DateFormat.html
        DateFormat.getDateInstance().format(System.currentTimeMillis());
        this.typeOperation = typeOperation;
        //PRIS DE LA DOCUMENTATION ORACLE API : https://docs.oracle.com/javase/8/docs/api/?java/util/Date.html
        date = new Date(System.currentTimeMillis());

    }

    @Override
    public String toString() {
        return "OPERATION" +
                "DATE" + date.toString() +
                "TYPE OPERATION" + typeOperation +
                '}';
    }
}
