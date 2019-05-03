package com.nosy.admin.nosyadmin.dto;

import com.nosy.admin.nosyadmin.model.EmailFromProvider;
import com.nosy.admin.nosyadmin.model.InputSystem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class EmailTemplateDto {
    @NotNull
    private String emailTemplateId;

    @NotNull
    private String emailTemplateName;
    @NotNull
    @Email
    private String fromAddress;

    @NotNull
    private EmailFromProvider emailFromProvider;

    @NotNull
    private Set<@NotEmpty @Email String> emailTemplateTo;

    private Set<@NotEmpty @Email String> emailTemplateCc;

    @NotNull
    private String text;

    @NotNull
    private int retryTimes;

    private int retryPeriod;

    private int priority;
    @NotNull
    private InputSystem inputSystem;


}
