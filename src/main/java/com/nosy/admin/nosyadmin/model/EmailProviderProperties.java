package com.nosy.admin.nosyadmin.model;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmailProviderProperties {
  private String username;
  private String password;
  private List<PlaceHolder> placeholders;

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

  public List<PlaceHolder> getPlaceholders() {
    return placeholders;
  }

  public void setPlaceholders(List<PlaceHolder> placeholders) {
    this.placeholders = placeholders;
  }
}
