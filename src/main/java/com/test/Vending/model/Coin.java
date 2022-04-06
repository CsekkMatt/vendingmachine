package com.test.Vending.model;

import java.util.Map;

public enum Coin {
  FIVE_CENT,
  TEN_CENT,
  TWENTY_CENT,
  FIFTY_CENT,
  HUNDRED_CENT;

  public static final Map<Integer, Coin> coinValueMap =
      Map.of(5, FIVE_CENT, 10, TEN_CENT, 20, TWENTY_CENT, 50, FIFTY_CENT, 100, HUNDRED_CENT);
}
