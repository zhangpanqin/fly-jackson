package com.mflyyou.mask;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Mask {
    String maskWith() default "****";
    int unmaskedPrefixLength() default 0;
}
