package com.test.Vending.service;

import com.test.Vending.domain.Product;
import com.test.Vending.domain.User;
import com.test.Vending.payload.ProductDTO;
import com.test.Vending.repository.ProductRepository;
import com.test.Vending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class VendingService {

  public final Predicate<Integer> multipleOfFive = number -> number % 5 == 0;

  private ProductRepository productRepository;

  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Optional<Product> findProductById(long id) {
    return productRepository.findById(id);
  }

  public List<Product> findAllProduct() {
    return productRepository.findAll();
  }

  public Optional<Product> findProductBySellerId(long sellerId) {
    return productRepository.findBysellerid(sellerId);
  }

  public void saveProduct(ProductDTO productDTO) {
    Product product = new Product();
    product.setProductname(productDTO.getProductname());
    product.setAmountavailable(productDTO.getAmountavailable());
    product.setSellerid(getUserId());
    productRepository.save(product);
  }

  public boolean canDeleteProduct(long productId) {
    return productRepository
        .findById(productId)
        .filter(product -> product.getSellerid() == getUserId())
        .isPresent();
  }

  public void deleteProduct(long productId) {
    productRepository.deleteById(productId);
  }

  public long getUserId() {
    return Optional.of(findByUserName()).map(User::getId).orElse(null);
  }

  public User findByUserName() {
    return userRepository
        .findByUsername(currentUserName())
        .orElseThrow(() -> new UsernameNotFoundException("User not found: "));
  }

  private String currentUserName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
