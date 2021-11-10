package com.qa.springdemoproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Setter
// @ToString
// @EqualsAndHashCode


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String accountNumber;

  @Column
  private String name;


  public Account(String accountNumber, String name) {
    super();
    this.accountNumber = accountNumber;
    this.name = name;
  }



}
