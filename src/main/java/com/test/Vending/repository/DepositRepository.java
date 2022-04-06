package com.test.Vending.repository;

import com.test.Vending.domain.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
  Optional<Deposit> findByUserId(Long userId);
}
