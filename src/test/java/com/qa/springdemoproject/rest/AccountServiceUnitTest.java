package com.qa.springdemoproject.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.springdemoproject.domain.Account;
import com.qa.springdemoproject.repository.AccountRepo;
import com.qa.springdemoproject.service.AccountService;

@SpringBootTest
public class AccountServiceUnitTest {

  @Autowired
  private AccountService service;

  @MockBean
  private AccountRepo repo;



  @Test
  void testCreate() {

    final Account NEW_ACCOUNT = new Account(null, "123", "name");
    final Account SAVED_NEW_ACCOUNT = new Account(2L, "123", "name");

    // WHEN
    Mockito.when(this.repo.saveAndFlush(NEW_ACCOUNT)).thenReturn(SAVED_NEW_ACCOUNT);

    // THEN
    Assertions.assertThat(this.service.create(NEW_ACCOUNT)).isEqualTo(SAVED_NEW_ACCOUNT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_ACCOUNT);

  }

  @Test
  void testReadOne() {


    final Account SAVED_ACCOUNT = new Account(1L, "5678", "h2Name");

    // WHEN
    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SAVED_ACCOUNT));

    // THEN
    Assertions.assertThat(this.service.readOne(SAVED_ACCOUNT.getId())).isEqualTo(SAVED_ACCOUNT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findById(SAVED_ACCOUNT.getId());

  }


  @Test
  void testReadAll() {

    List<Account> newList = new ArrayList<>();
    final Account SAVED_ACCOUNT = new Account(1L, "5678", "h2Name");
    newList.add(SAVED_ACCOUNT);

    // WHEN
    Mockito.when(this.repo.findAll()).thenReturn(newList);

    // THEN
    Assertions.assertThat(this.service.readAll()).isEqualTo(newList);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findAll();

  }

  @Test
  void testUpdate() {

    final Account OLD_ACCOUNT = new Account(1L, "5678", "h2Name");
    final Account NEW_ACCOUNT = new Account("12345", "newNameH2");
    final Account NEW_ACCOUNT_ID = new Account(1L, "12345", "newNameH2");
    // Optional<Account> existingOptional = this.repo.findById(NEW_ACCOUNT.getId());

    // WHEN

    Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(OLD_ACCOUNT));



    Mockito.when(this.repo.saveAndFlush(NEW_ACCOUNT)).thenReturn(NEW_ACCOUNT_ID);

    Account returnedAccount = service.update(1L, NEW_ACCOUNT);

    // THEN
    Assertions.assertThat(returnedAccount = NEW_ACCOUNT_ID);
    // Assertions.assertThat(this.service.update(1L, NEW_ACCOUNT)).isEqualTo(NEW_ACCOUNT);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_ACCOUNT_ID);
  }

  @Test
  void testDelete() {

    // WHEN

    Mockito.when(this.repo.existsById(1L)).thenReturn(false);



    // THEN
    Assertions.assertThat(this.service.deleteAccount(1L)).isEqualTo(true);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
    Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
  }

  @Test
  void testDeleteFail() {

    // WHEN

    Mockito.when(this.repo.existsById(2L)).thenReturn(true);



    // THEN
    Assertions.assertThat(this.service.deleteAccount(2L)).isEqualTo(false);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).deleteById(2L);
    Mockito.verify(this.repo, Mockito.times(1)).existsById(2L);
  }

  @Test
  void testFindNameLike() {

    final Account NEW_ACCOUNT = new Account(1L, "5678", "h2Name");
    List<Account> newList = new ArrayList<>();
    newList.add(NEW_ACCOUNT);

    // WHEN
    Mockito.when(this.repo.findLikeNameJPQL("h2")).thenReturn(newList);



    // THEN
    Assertions.assertThat(this.service.findNameLike("h2")).isEqualTo(newList);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findLikeNameJPQL("h2");

  }

  @Test
  void testIdUnder() {

    final Account NEW_ACCOUNT = new Account(1L, "5678", "h2Name");
    List<Account> newList = new ArrayList<>();
    newList.add(NEW_ACCOUNT);

    // WHEN
    Mockito.when(this.repo.findIdLessThan(5L)).thenReturn(newList);



    // THEN
    Assertions.assertThat(this.service.findIdUnder(5L)).isEqualTo(newList);

    // verify
    Mockito.verify(this.repo, Mockito.times(1)).findIdLessThan(5L);

  }
}
