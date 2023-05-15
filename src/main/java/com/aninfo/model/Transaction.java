package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long cbu;
    private Double amount;
    private Long transactionId;

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

    public Long getCBU() {
        return cbu;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void transactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCbu() {
        return cbu;
    }
}