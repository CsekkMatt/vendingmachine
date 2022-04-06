package com.test.Vending.domain;

import javax.persistence.*;

@Entity
@Table(name = "deposit")
public class Deposit {

  @Id @GeneratedValue private long id;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne
  @JoinColumn(name = "user_pid")
  private User user;

  private int fivecent;
  private int tencent;
  private int twentycent;
  private int fiftycent;
  private int hundredcent;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getFivecent() {
    return fivecent;
  }

  public void setFivecent(int fivecent) {
    this.fivecent = fivecent;
  }

  public int getTencent() {
    return tencent;
  }

  public void setTencent(int tencent) {
    this.tencent = tencent;
  }

  public int getTwentycent() {
    return twentycent;
  }

  public void setTwentycent(int twentycent) {
    this.twentycent = twentycent;
  }

  public int getFiftycent() {
    return fiftycent;
  }

  public void setFiftycent(int fiftycent) {
    this.fiftycent = fiftycent;
  }

  public int getHundredcent() {
    return hundredcent;
  }

  public void setHundredcent(int hundredcent) {
    this.hundredcent = hundredcent;
  }
}
