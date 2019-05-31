package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {
  private UserRepository userRepository;
  private KeycloakClient keycloakClient;

  @Autowired
  public UserService(UserRepository userRepository, KeycloakClient keycloakClient) {
    this.userRepository = userRepository;
    this.keycloakClient = keycloakClient;
  }

  public User getUserInfo(HttpServletRequest request) {

    return keycloakClient.getUserInfo(request.getUserPrincipal().getName());
  }

  public void deleteUser(HttpServletRequest request) {


    String obtainedUser = request.getUserPrincipal().getName();

    keycloakClient.deleteUsername(obtainedUser);
    userRepository.deleteById(obtainedUser);
  }

  public void logoutUser(HttpServletRequest request) {

    keycloakClient.logoutUser(request.getUserPrincipal().getName());
  }

  public User addUser(User user) {
    if (!isValidPassword(user.getPassword())) {
      throw new GeneralException("Password is not valid");
    }

    if (!keycloakClient.registerNewUser(user)) {
      throw new GeneralException("User already exists in database please try another email");
    }

    return userRepository.saveAndFlush(user);
  }

  private boolean isValidPassword(String password) {
    return null != password && password.length() > 1;
  }
}
