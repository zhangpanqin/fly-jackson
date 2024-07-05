package com.mflyyou.toenum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangpanqin
 */
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue("{\"name\":\"name_50157356baff\",\"age\":0,\"sex\":2}", User.class);
        System.out.println(user);
    }
}
