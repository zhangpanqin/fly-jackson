package com.mflyyou.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mflyyou.mask.Mask;
import lombok.Data;

/**
 * @author zhangpanqin
 */
public class Main {
    static ObjectMapper mapper;
    static User user;

    static {
        mapper = new ObjectMapper();
        user = new User();
        user.setUsername("zhangpanqin");
        user.setAge("1111");
        mapper.registerModule(new MyAnnotationIntrospectorModule());
        mapper.registerModule(new MyAnnotationIntrospectorModule2());
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(mapper.writeValueAsString(user));
        System.out.println(mapper.writeValueAsString(user));
    }

    @Data
    public static class User {
        @Mask
        private String username;

        private String age;
    }
}
