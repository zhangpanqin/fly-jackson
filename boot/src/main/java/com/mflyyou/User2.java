package com.mflyyou;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User2 {

    private String name;

    private Integer age;

    private SexEnum sex;

    private Address address = new Address("测试地限制", SexEnum.GIRL);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String address;

        private SexEnum sexa;
    }
}




