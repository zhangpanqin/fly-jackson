package com.mflyyou.mask;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
        public static void main(String[] args) throws Exception {
        ObjectMapper mapper = CustomObjectMapper.createObjectMapper();
        Address address = new Address("12345");
        User user = new User("John Doe", "123-45-6789", 987654321, address);
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        Address address2 = new Address("12345");
        User user2 = new User("John Doe2", "2123-45-6789", 987654321, address2);
        String json2 = mapper.writeValueAsString(user2);
        System.out.println(json2);
    }
}