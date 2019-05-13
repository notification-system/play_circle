package com.nosy.admin.nosyadmin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Pusher {
  @Id private String username;
  @NotNull private String text;
}
