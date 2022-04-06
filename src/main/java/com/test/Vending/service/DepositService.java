package com.test.Vending.service;

import com.test.Vending.domain.Deposit;
import com.test.Vending.model.Coin;
import com.test.Vending.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
  @Autowired private DepositRepository depositRepository;

  @Autowired private VendingService vendingService;

  public Deposit findDepositByUser() {
    return depositRepository.findByUserId(vendingService.getUserId()).orElse(null);
  }

  public Deposit saveDeposit(Deposit deposit) {
    return depositRepository.save(deposit);
  }

  public void createOrUpdateDeposit(Coin coin) {
    Deposit deposit = findDepositByUser();
    System.out.println("Deposit exists" + deposit);
    if (deposit != null && coin != null) {
      updateDeposit(deposit, coin);
    }
    if (deposit == null && coin != null) {
      Deposit newDeposit = new Deposit();
      newDeposit.setUser(vendingService.findByUserName());
      updateDeposit(newDeposit, coin);
    }
    // TODO throw some exceptions
  }

  public void updateDeposit(Deposit deposit, Coin coin) {
    switch (coin) {
      case FIVE_CENT:
        deposit.setFivecent(deposit.getFivecent() + 1);
        break;
      case TEN_CENT:
        deposit.setTencent(deposit.getTencent() + 1);
        break;
      case FIFTY_CENT:
        deposit.setFiftycent(deposit.getFiftycent() + 1);
        break;
      case TWENTY_CENT:
        deposit.setTwentycent(deposit.getTwentycent() + 1);
        break;
      case HUNDRED_CENT:
        deposit.setHundredcent(deposit.getHundredcent() + 1);
        break;
    }
    saveDeposit(deposit);
  }
}
