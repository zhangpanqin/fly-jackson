package com.mflyyou.jackson.dict;

public interface DictMapping<T> {
    boolean support(Class<?> clazz);

    String getDict(String type, T code);

    class DefaultDictMapping implements DictMapping<Object> {
        @Override
        public boolean support(Class<?> clazz) {
            return true;
        }

        @Override
        public String getDict(String type, Object code) {
            if (code == null) {
                return null;
            }
            return code.toString();
        }
    }
}
