package com.qa.springdemoproject.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.springdemoproject.domain.Account;
import com.qa.springdemoproject.repository.AccountRepo;

@Service
public class AccountService {


  private AccountRepo repo;

  public AccountService(AccountRepo repo) {
    super();
    this.repo = repo;
  }

  // private List<Account> accounts = new ArrayList<>();



  public Account create(Account account) {
    // this.accounts.add(account);
    // System.out.println(account.toString());
    // return this.accounts.get(this.accounts.size() - 1);
    return this.repo.saveAndFlush(account);
  }


  public List<Account> readAll() {

    // return this.accounts;
    return this.repo.findAll();

  }


  public Account readOne(Long id) {

    // id refers to list index
    // Account newAcc = accounts.get(id);
    // return newAcc;
    return this.repo.findById(id).get();

    // return existing;


  }


  public Account update(Long id, Account newAccount) {

    // this.accounts.remove(id);
    // this.accounts.add(id, account);
    // return this.accounts.get(id);

    Account existing = this.repo.findById(id).get();
    // Account existing = existingOptional.get();


    existing.setAccountNumber(newAccount.getAccountNumber());
    existing.setName(newAccount.getName());


    return this.repo.saveAndFlush(existing);
  }



  public Boolean deleteAccount(Long id) {


    this.repo.deleteById(id);

    boolean stillExists = this.repo.existsById(id);

    return !stillExists;

  }

  public List<Account> findNameLike(String startingName) {


    return this.repo.findLikeNameJPQL(startingName);
  }

  public List<Account> findIdUnder(Long maxVal) {


    return this.repo.findIdLessThan(maxVal);
  }



}
