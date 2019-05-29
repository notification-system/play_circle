package com.nosy.admin.nosyadmin.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmailTemplateCc {
  private int status;
  private String address;

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
