package com.nosy.admin.nosyadmin.model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailProviderProperties {
  private String username;
  private String password;
  private List<PlaceHolders> placeholders;

  public List<PlaceHolders> getPlaceholders() {
    return placeholders;
  }

  public void setPlaceholders(List<PlaceHolders> placeholders) {
    this.placeholders = placeholders;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
