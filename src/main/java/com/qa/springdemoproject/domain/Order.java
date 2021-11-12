// package com.qa.springdemoproject.domain;
//
// import java.time.LocalDate;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.ManyToOne;
// import org.springframework.data.annotation.Id;
// import org.springframework.format.annotation.DateTimeFormat;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
//
// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Order {
//
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;
//
// @DateTimeFormat(pattern = "dd-MM-yyyy")
// private LocalDate date;
//
// private Long cost;
//
// @ManyToOne
// private Account account;
//
// public Order(LocalDate date, Long cost, Account account) {
// super();
// this.date = date;
// this.cost = cost;
// this.account = account;
// }
//
//
//
// }
