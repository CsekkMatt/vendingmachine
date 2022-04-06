package com.test.Vending.controller;

import com.test.Vending.domain.Deposit;
import com.test.Vending.payload.LoginDTO;
import com.test.Vending.payload.SignUpDTO;
import com.test.Vending.repository.DepositRepository;
import com.test.Vending.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private UserService userService;

  @Autowired private DepositRepository depositRepository;

  @PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
    System.out.println("Sign in " + loginDTO.getUsername());
    System.err.println(depositRepository.findByUserId(11L));
    userService.authenticateUser(loginDTO);
    return new ResponseEntity<>("User signed in ", HttpStatus.OK);
  }

  @PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
    System.out.println("Incoming request: " + signUpDTO.getUsername());
    if (userService.existsByUsername(signUpDTO.getUsername())) {
      return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }
    userService.saveUser(signUpDTO);
    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }
}
