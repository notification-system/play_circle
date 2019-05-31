package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class EmailTemplateMapper {
    public static final EmailTemplateMapper INSTANCE = Mappers.getMapper( EmailTemplateMapper.class );

    public abstract EmailTemplateDto toEmailTemplateDto(EmailTemplate emailTemplate);
    public abstract EmailTemplate toEmailTemplate(EmailTemplateDto emailTemplateDto);
}
