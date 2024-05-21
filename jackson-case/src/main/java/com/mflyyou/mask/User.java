package com.mflyyou.mask;

public class User {
    public String name;

    @Mask(maskWith = "****", unmaskedPrefixLength = 3)
    public String ssn;

    @Mask(maskWith = "####", unmaskedPrefixLength = 2)
    public Integer accountNumber;

    public Address address;

    public User(String name, String ssn, Integer accountNumber, Address address) {
        this.name = name;
        this.ssn = ssn;
        this.accountNumber = accountNumber;
        this.address = address;
    }
}




