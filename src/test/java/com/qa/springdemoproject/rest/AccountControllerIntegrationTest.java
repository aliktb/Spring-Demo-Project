package com.qa.springdemoproject.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springdemoproject.domain.Account;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:account-schema.sql", "classpath:account-data.sql"},
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AccountControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void testCreate() throws Exception {

    Account newAccount = new Account("123", "newName");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);
    RequestBuilder request =
        post("/account/create").contentType(MediaType.APPLICATION_JSON).content(newAccountAsJSON);


    ResultMatcher checkStatus = status().isCreated();


    Account newAccountSaved = new Account(2L, "123", "newName");
    String newAccountSavedAsJSON = this.mapper.writeValueAsString(newAccountSaved);

    ResultMatcher checkBody = content().json(newAccountSavedAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }


  @Test
  void testReadAll() throws Exception {


    List<Account> newList = new ArrayList<>();
    Account newAccount = new Account(1L, "5678", "h2Name");

    newList.add(newAccount);

    String newListAsJSON = this.mapper.writeValueAsString(newList);
    RequestBuilder request = get("/account/readAll");


    ResultMatcher checkStatus = status().isOk();



    ResultMatcher checkBody = content().json(newListAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testReadOne() throws Exception {

    Account newAccount = new Account(1L, "5678", "h2Name");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);
    RequestBuilder request = get("/account/readOne/1");


    ResultMatcher checkStatus = status().isOk();



    ResultMatcher checkBody = content().json(newAccountAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testUpdate() throws Exception {

    Account newAccount = new Account(1L, "5678", "h2Name");
    String newAccountAsJSON = this.mapper.writeValueAsString(newAccount);
    RequestBuilder request =
        put("/account/update/1").contentType(MediaType.APPLICATION_JSON).content(newAccountAsJSON);


    ResultMatcher checkStatus = status().isAccepted();


    ResultMatcher checkBody = content().json(newAccountAsJSON);

    this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
  }

  @Test
  void testDelete() throws Exception {


    RequestBuilder request = delete("/account/delete/1");


    ResultMatcher checkStatus = status().isNoContent();



    this.mvc.perform(request).andExpect(checkStatus);
  }



  // @Test
  // void testDeleteFail() throws Exception {
  //
  //
  // RequestBuilder request = delete("/account/delete/10");
  //
  // ResultMatcher checkStatus = status().isNotFound();
  //
  //
  //
  // this.mvc.perform(request).andExpect(checkStatus);
  //
  // }

}
