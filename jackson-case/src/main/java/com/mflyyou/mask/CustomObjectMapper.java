package com.mflyyou.mask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapper {
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new MaskBeanSerializerModifier());
        mapper.registerModule(module);

        return mapper;
    }
}
