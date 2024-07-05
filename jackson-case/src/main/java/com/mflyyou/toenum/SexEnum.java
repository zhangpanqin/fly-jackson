package com.mflyyou.toenum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.DELEGATING;

@Getter
public enum SexEnum {
    BOY(1, "男"),
    GIRL(2, "女");

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator(mode = DELEGATING)
    public static SexEnum of(@JsonProperty("code") Integer code) {
        for (SexEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    private final Integer code;
    private final String desc;
}
