package com.qa.springdemoproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springdemoproject.domain.Account;
import com.qa.springdemoproject.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerUnitTest {


  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private AccountService service;

  @Test
  public void createTest() throws Exception {
    Account newAccount = new Account("123", "newName");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);

    Mockito.when(this.service.create(newAccount)).thenReturn(newAccount);

    mvc.perform(
        post("/account/create").contentType(MediaType.APPLICATION_JSON).content(newAccountAsJSON))
        .andExpect(status().isCreated()).andExpect(content().json(newAccountAsJSON));
  }

  @Test
  public void readOneTest() throws Exception {
    Account newAccount = new Account(1L, "5678", "h2Name");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);

    Mockito.when(this.service.readOne(newAccount.getId())).thenReturn(newAccount);

    mvc.perform(get("/account/readOne/1")).andExpect(status().isOk())
        .andExpect(content().json(newAccountAsJSON));
  }

  @Test
  public void readAllTest() throws Exception {
    Account newAccount = new Account("5678", "h2Name");
    List<Account> newList = new ArrayList<>();
    newList.add(newAccount);
    String newListAsJSON = this.mapper.writeValueAsString(newList);


    Mockito.when(this.service.readAll()).thenReturn(newList);

    mvc.perform(get("/account/readAll")).andExpect(status().isOk())
        .andExpect(content().json(newListAsJSON));
  }

  @Test
  public void updateTest() throws Exception {
    Account newAccount = new Account("123", "newName");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);

    Mockito.when(this.service.update(1L, newAccount)).thenReturn(newAccount);

    mvc.perform(
        put("/account/update/1").contentType(MediaType.APPLICATION_JSON).content(newAccountAsJSON))
        .andExpect(status().isAccepted()).andExpect(content().json(newAccountAsJSON));
  }



  @Test
  public void deleteTest() throws Exception {

    Mockito.when(this.service.deleteAccount(1L)).thenReturn(true);

    mvc.perform(delete("/account/delete/1")).andExpect(status().isNoContent());
  }



  @Test
  public void deleteFailTest() throws Exception {

    Mockito.when(this.service.deleteAccount(2L)).thenReturn(false);

    mvc.perform(delete("/account/delete/2")).andExpect(status().isInternalServerError());
  }


}
