package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.config.security.TokenCollection;
import com.nosy.admin.nosyadmin.dto.TokenCollectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TokenCollectionMapper {
    public static final TokenCollectionMapper INSTANCE = Mappers.getMapper( TokenCollectionMapper.class );
    public abstract TokenCollectionDto toTokenCollectionDto(TokenCollection tokenCollection);
}
