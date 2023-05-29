package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long transactionId;
    private Double amount;
    private Long cbu;

    public Transaction(Long cbu, Double sum) {
        this.amount=0.0;
        this.cbu=cbu;
    }

    public Transaction() {

    }

    public Double getAmount() {

        return this.amount;
    }
    public void setAmount(Double amount) {

        this.amount = amount;
    }

    public void setCbu(Long cbu) {

        this.cbu = cbu;
    }
}
