package com.mflyyou.mask;

public class Address {
    @Mask(maskWith = "##-####")
    public String zipCode;

    public Address(String zipCode) {
        this.zipCode = zipCode;
    }
}