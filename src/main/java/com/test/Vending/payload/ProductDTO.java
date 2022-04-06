package com.test.Vending.payload;

public class ProductDTO {
  private String productname;
  private int amountavailable;
  private int cost;

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public String getProductname() {

    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  public int getAmountavailable() {
    return amountavailable;
  }

  public void setAmountavailable(int amountavailable) {
    this.amountavailable = amountavailable;
  }
}
