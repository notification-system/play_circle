package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailTemplateMapper {
    EmailTemplateMapper INSTANCE = Mappers.getMapper( EmailTemplateMapper.class );

    EmailTemplateDto toEmailTemplateDto(EmailTemplate emailTemplate);
    EmailTemplate toEmailTemplate(EmailTemplateDto emailTemplateDto);
}
