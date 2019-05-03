package com.nosy.admin.nosyadmin.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;


@Getter
@Setter
public class InputSystemDto {
    @NotNull
    private String inputSystemId;
    @NotNull
    private String inputSystemName;
    @JsonBackReference
    private User user;


    @JsonManagedReference
    private Set<EmailTemplate> emailTemplate;


}
