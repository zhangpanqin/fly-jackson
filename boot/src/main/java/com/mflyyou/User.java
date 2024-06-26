package com.mflyyou;

import com.mflyyou.jackson.dict.Dict;
import com.mflyyou.jackson.dict.DictMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    @Dict(mode = DictMode.NORMAL)
    private String name;
    @Dict(mode = DictMode.OBJECT)
    private Integer age;
    @Dict(mode = DictMode.OBJECT)
    private SexEnum sex;

    private Address address = new Address("测试地限制", SexEnum.GIRL);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String address;
        @Dict(mode = DictMode.OBJECT)
        private SexEnum sexa;
    }
}




