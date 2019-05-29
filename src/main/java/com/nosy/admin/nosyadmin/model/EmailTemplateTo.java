package com.nosy.admin.nosyadmin.model;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class EmailTemplateTo {
  private int status;

  @NotNull private String address;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
