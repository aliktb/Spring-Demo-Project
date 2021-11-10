package com.qa.springdemoproject.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.springdemoproject.domain.Account;
import com.qa.springdemoproject.service.AccountService;


@RestController
@RequestMapping("/account") // http://localhost:8080/account/...
public class AccountController {

  private AccountService service;


  // Constructor Injection
  public AccountController(AccountService service) {

    this.service = service;
  }



  @PostMapping("/create")
  public ResponseEntity<Account> create(@RequestBody Account account) {

    return new ResponseEntity<Account>(this.service.create(account), HttpStatus.CREATED);
  }

  @GetMapping("/readAll")
  public ResponseEntity<List<Account>> readAll() {

    return new ResponseEntity<List<Account>>(this.service.readAll(), HttpStatus.OK);

  }

  @GetMapping("/readOne/{id}")
  public ResponseEntity<Account> readOne(@PathVariable Long id) {

    return new ResponseEntity<Account>(this.service.readOne(id), HttpStatus.ACCEPTED);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {

    return new ResponseEntity<Account>(this.service.update(id, account), HttpStatus.ACCEPTED);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id) {

    // return new ResponseEntity<Boolean>(this.service.deleteAccount(id), HttpStatus.NO_CONTENT);
    return this.service.deleteAccount(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


  }

  @GetMapping("/getLike/{startingName}")
  public ResponseEntity<List<Account>> getLike(@PathVariable String startingName) {

    return new ResponseEntity<List<Account>>(this.service.findNameLike(startingName),
        HttpStatus.OK);
  }

  @GetMapping("/getLessThan/{maxVal}")
  public ResponseEntity<List<Account>> getLike(@PathVariable Long maxVal) {

    return new ResponseEntity<List<Account>>(this.service.findIdUnder(maxVal), HttpStatus.OK);
  }


}
