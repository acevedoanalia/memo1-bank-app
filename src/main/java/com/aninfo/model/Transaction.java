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
        this.amount=sum;
        this.cbu=cbu;
    }

    public Transaction() {

    }

    public Long getTransactionIdByCBU(Long cbu) {

        return transactionId;
    }

    public Double getAmount() {

        return amount;
    }
    public void setAmount(Double amount) {

        this.amount = amount;
    }
    public void transactionId(Long transactionId) {

        this.transactionId = transactionId;
    }

    public void getTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCbu() {

        return this.cbu;
    }
}
