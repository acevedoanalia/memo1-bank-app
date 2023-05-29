package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    TransactionService transactionService;


    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        transactionService.withdraw(transactionService.newTransaction(cbu,sum));
        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        Account account = accountRepository.findAccountByCbu(cbu);
        sum = transactionService.applyPromo(sum);
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);
        transactionService.deposit(transactionService.newTransaction(cbu,sum));
        return account;
    }

    public Collection<Transaction> getTransactionsFrom(Long cbu) {

        return transactionService.getTransactionsFrom(cbu);
    }

    public Optional<Transaction> findTransactionById(Long transactionId) {
        return transactionService.getTransactionById(transactionId);

    }

    public void deleteTransactionById(Long transactionId) {
        this.transactionService.deleteTransactionById(transactionId);
    }
}
