package com.test.Vending.service;

import com.test.Vending.model.Coin;
import org.springframework.stereotype.Service;

@Service
public class CoinValidatorService {

  public Coin validateCoin(int coin) {
    //TODO
    Coin res = Coin.coinValueMap.get(coin);
    return res;
  }
}
