package org.example.challenge.product.model;

import java.sql.Date;

public class Product {

  private int id;
  private String name;
  private Date registrationDate;

  public Product(int id, String name, Date registrationDate) {
    this.id = id;
    this.name = name;
    this.registrationDate = registrationDate;
  }

  public Product() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
}
