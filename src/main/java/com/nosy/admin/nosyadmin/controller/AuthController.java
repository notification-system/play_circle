package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.config.security.ClientToken;
import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.service.UserService;
import com.nosy.admin.nosyadmin.utils.Conversion;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping("/api/v1/nosy")
public class AuthController {

  private UserService userService;
  private KeycloakClient keycloakClient;
  private Conversion conversion;

  @Autowired
  public AuthController(
      UserService userService, KeycloakClient keycloakClient, Conversion conversion) {
    this.userService = userService;
    this.keycloakClient = keycloakClient;
    this.conversion = conversion;
  }

  @GetMapping(path = "/auth/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    userService.logoutUser(request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(path = "/auth/status")
  public ResponseEntity<Boolean> isAuthenticated(@RequestBody String token) throws IOException {
    return new ResponseEntity<>(keycloakClient.isAuthenticated(token), HttpStatus.OK);
  }

  @PostMapping(value = "/auth/token")
  public ResponseEntity<ClientToken> getToken(@RequestBody @Valid UserDto userdto)
      throws IOException {
    return new ResponseEntity<>(
        keycloakClient.getTokens(conversion.convertToUser(userdto)), HttpStatus.OK);
  }

  @PostMapping(value = "/users")
  public ResponseEntity<User> newUser(@RequestBody @Valid UserDto userdto) {
    return new ResponseEntity<>(
        userService.addUser(conversion.convertToUser(userdto)), HttpStatus.OK);
  }

  @DeleteMapping(value = "/users")
  public ResponseEntity<String> deleteUsername(HttpServletRequest request) {
    userService.deleteUser(request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/users")
  public ResponseEntity<User> getUserProfile(HttpServletRequest request) {

    return new ResponseEntity<>(userService.getUserInfo(request), HttpStatus.OK);
  }
}
