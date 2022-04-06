package com.test.Vending.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
  @Id @GeneratedValue private long id;

  private int amountavailable;

  private int cost;

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  private String productname;

  private long sellerid;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getAmountavailable() {
    return amountavailable;
  }

  public void setAmountavailable(int amountavailable) {
    this.amountavailable = amountavailable;
  }

  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  public long getSellerid() {
    return sellerid;
  }

  public void setSellerid(long sellerid) {
    this.sellerid = sellerid;
  }
}
