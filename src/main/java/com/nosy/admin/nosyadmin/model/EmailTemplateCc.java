package com.nosy.admin.nosyadmin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class EmailTemplateCc {
  private int status;

  private String address;
}
