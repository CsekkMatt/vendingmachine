package com.test.Vending.service;

import com.test.Vending.domain.Role;
import com.test.Vending.domain.User;
import com.test.Vending.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) {
    // TODO Logger....
    System.out.println("load by username " + userName);
    User user =
        userRepository
            .findByUsername(userName)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with username : " + userName));
    System.out.println("user found: " + user);
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), mapRolesToSpringAuth(user.getRoles()));
  }

  private List<? extends GrantedAuthority> mapRolesToSpringAuth(Set<Role> roles) {
    return roles.stream()
        .map(Role::getName)
        // TODO logger
        .peek(System.out::println)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}
