package com.mflyyou.jackson.dict;

public class StringDictMapping implements DictMapping<String> {
    @Override
    public boolean support(Class<?> clazz) {
        return clazz == String.class;
    }

    @Override
    public String getDict(String type, String code) {
        return null;
    }
}
