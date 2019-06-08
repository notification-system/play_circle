package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.config.security.TokenCollection;
import com.nosy.admin.nosyadmin.dto.TokenCollectionDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenCollectionMapperTest {

    private TokenCollection tokenCollection=new TokenCollection();
    @Test
    public void testToTokenCollection(){
        tokenCollection.setAccessToken("token");
        tokenCollection.setExpiresIn(1);
        tokenCollection.setNotBeforePolicy(2);
        tokenCollection.setRefreshExpiresIn(2);
        tokenCollection.setRefreshToken("refreshtoken");
        tokenCollection.setSessionState("dasdas");
        tokenCollection.setTokenType("type");
        TokenCollectionDto tokenCollectionDto=TokenCollectionMapper.INSTANCE.toTokenCollectionDto(tokenCollection);
        assertEquals(tokenCollectionDto.getAccessToken(), tokenCollection.getAccessToken());
        assertEquals(tokenCollectionDto.getExpiresIn(), tokenCollection.getExpiresIn());
        assertEquals(tokenCollectionDto.getRefreshToken(), tokenCollection.getRefreshToken());


    }

}
