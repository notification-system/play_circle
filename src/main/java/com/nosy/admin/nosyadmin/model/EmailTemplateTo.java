package com.nosy.admin.nosyadmin.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
public class EmailTemplateTo {
    private int status;


    @NotNull
    private String address;

}
