package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
   @Autowired
    private TransactionRepository transactionsList;


   public Transaction newTransaction(Long cbu,Double sum){
     Transaction transaction=new Transaction(cbu,sum);


       transaction.setCbu(cbu);
       double promo = this.applyPromo(sum);
       transaction.setAmount(promo);
      return this.deposit(transaction);
       //return transaction;

   }
    public Transaction deposit(Transaction transaction) {
        transaction.setAmount(this.applyPromo(transaction.getAmount()));
        if (transaction.getAmount() <= 0){
            throw new DepositNegativeSumException("Cannot deposit negative sum");
        }
        Transaction unaTransaction=this.transactionsList.save(transaction);
        return unaTransaction;
    }

    Double applyPromo(Double amount) { //amount=2000
        if (amount>= 2000){
            Double promotional = amount* 0.1; //promotional=200



                   if (promotional > 500) {
                       promotional = 500.00;

                   }
            amount+= promotional;
        }
        return amount;
    }
    public Transaction withdraw(Transaction transaction) {
        transaction.setAmount(this.applyPromo(transaction.getAmount()));
        if (transaction.getAmount() <= 0 || transaction.getAmount()==null){
            throw new DepositNegativeSumException("Cannot deposit negative sum");
        }
        Transaction unaTransaction=this.transactionsList.save(transaction);
        return unaTransaction;
    }

    public Collection<Transaction> getTransactionsFrom(Long cbu) {
        return this.transactionsList.findAllByCbu(cbu);
    }

    public Optional<Transaction> getTransactionById(Long transactionId) {
       return this.transactionsList.findById(transactionId);
    }

    public void deleteTransactionById(Long transactionId) {
       this.transactionsList.deleteById(transactionId);
    }
}