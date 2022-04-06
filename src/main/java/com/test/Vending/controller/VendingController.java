package com.test.Vending.controller;

import com.test.Vending.domain.Product;
import com.test.Vending.model.Coin;
import com.test.Vending.payload.ProductDTO;
import com.test.Vending.service.CoinValidatorService;
import com.test.Vending.service.DepositService;
import com.test.Vending.service.UserService;
import com.test.Vending.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vending")
public class VendingController {

  @Autowired private VendingService vendingService;
  @Autowired private UserService userService;
  @Autowired private CoinValidatorService coinValidatorService;
  @Autowired private DepositService depositService;

  @GetMapping(path = "products")
  public ResponseEntity<?> allProducts() {
    return new ResponseEntity<>(vendingService.findAllProduct(), HttpStatus.OK);
  }

  @GetMapping(path = "products/{sellerId}")
  public ResponseEntity<?> getProductById(@PathVariable(value = "sellerId") Long id) {
    Product product = vendingService.findProductBySellerId(id).orElse(null);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_SELLER')")
  @PutMapping(path = "products/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
    System.out.println("add product  " + productDTO.getProductname() + "  " + productDTO.getCost());
    if (vendingService.multipleOfFive.test(productDTO.getCost())) {
      vendingService.saveProduct(productDTO);
      return new ResponseEntity<>("Product added", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("cost should be multiple of 5", HttpStatus.BAD_REQUEST);
    }
  }

  @PreAuthorize("hasRole('ROLE_BUYER')")
  @PostMapping(path = "deposit")
  public ResponseEntity<?> deposit(@RequestBody int coin) {
    Coin c = coinValidatorService.validateCoin(coin);
    if (c == null) {
      return new ResponseEntity<>("Invalid coin" + coin, HttpStatus.BAD_REQUEST);
    }
    depositService.createOrUpdateDeposit(c);
    return new ResponseEntity<>("Deposit updated with coin" + coin, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_BUYER')")
  @PostMapping(path = "buy/{productId}/{amount}")
  public ResponseEntity<?> buyProduct(
      @PathVariable(value = "productId") int productId,
      @PathVariable(value = "amount") int amount) {
    Product product = vendingService.findProductById(productId).orElse(null);
    System.out.println(product);
    // TODO implement buy .. decrease product nr availability etc
    // TODO implement change return & stuff...
    return new ResponseEntity<>(
        "Spent: " + amount * product.getCost() + " Bought: " + product.getProductname(),
        HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_SELLER')")
  @DeleteMapping(path = "delete/{productId}")
  public ResponseEntity<?> deleteProduct(@PathVariable(value = "productId") long productId) {
    if (vendingService.canDeleteProduct(productId)) {
      vendingService.deleteProduct(productId);
      return new ResponseEntity<>("Deleted product: " + productId, HttpStatus.OK);
    }
    return new ResponseEntity<>("Cannot delete product... " + productId, HttpStatus.BAD_REQUEST);
  }
}
