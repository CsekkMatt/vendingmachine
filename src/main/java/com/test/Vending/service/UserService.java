package com.test.Vending.service;

import com.test.Vending.domain.Role;
import com.test.Vending.domain.User;
import com.test.Vending.payload.LoginDTO;
import com.test.Vending.payload.SignUpDTO;
import com.test.Vending.repository.RoleRepository;
import com.test.Vending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

  private UserRepository userRepository;

  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public void saveUser(SignUpDTO signUpDTO) {
    User user = new User();
    user.setName(signUpDTO.getName());
    user.setEmail(signUpDTO.getEmail());
    user.setUsername(signUpDTO.getUsername());
    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
    Role roles = roleRepository.findByName(signUpDTO.getRole()).get();
    user.setRoles(Collections.singleton(roles));
    userRepository.save(user);
  }

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public void authenticateUser(LoginDTO loginDTO) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
