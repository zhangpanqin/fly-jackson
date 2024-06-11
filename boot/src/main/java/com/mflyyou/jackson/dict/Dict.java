package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {
    DictMode mode() default DictMode.NORMAL;

    boolean isDict() default false;

    String type() default "";
}
