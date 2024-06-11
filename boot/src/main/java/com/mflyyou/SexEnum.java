package com.mflyyou;

import com.mflyyou.convert.BaseEnum;
import lombok.Getter;

@Getter
public enum SexEnum implements BaseEnum<Integer> {
    BOY(1,"男"),
    GIRL(2,"女");

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;
    private final String desc;

    @Override
    public Integer code() {
        return this.code;
    }
}
