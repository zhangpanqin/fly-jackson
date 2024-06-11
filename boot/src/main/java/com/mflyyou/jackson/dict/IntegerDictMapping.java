package com.mflyyou.jackson.dict;

public class IntegerDictMapping implements DictMapping<Integer> {
    @Override
    public boolean support(Class<?> clazz) {
        return clazz == Integer.class;
    }

    @Override
    public String getDict(String type, Integer code) {
        return null;
    }
}
