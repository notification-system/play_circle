package com.nosy.admin.nosyadmin.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nosy.admin.nosyadmin.model.InputSystem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Set;


@Getter
@Setter
public class UserDto {
    private String email;
    @JsonManagedReference

    private Set<InputSystem> inputSystem;

    @Transient
    private String password;

    private String firstName;
    private String lastName;
}
