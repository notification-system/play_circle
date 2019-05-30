package com.nosy.admin.nosyadmin.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nosy.admin.nosyadmin.model.InputSystem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Set;


public class UserDto {
  private String email;
  @JsonManagedReference private Set<InputSystem> inputSystem;

  @Transient private String password;

  private String firstName;
  private String lastName;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<InputSystem> getInputSystem() {
    return inputSystem;
  }

  public void setInputSystem(Set<InputSystem> inputSystem) {
    this.inputSystem = inputSystem;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
