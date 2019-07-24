package com.hello2pal.socialMediaApp.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperHelper {

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
