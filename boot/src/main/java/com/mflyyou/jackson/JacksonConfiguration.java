package com.mflyyou.jackson;


import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mflyyou.jackson.dict.*;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

@Configuration
public class JacksonConfiguration {

    @Bean
    public DictSerializerFactory dictSerializerFactory(List<DictMapping<?>> dictMappings) {
        return new DictSerializerFactory(dictMappings);
    }

    @Bean
    public StringDictMapping stringDictMapping() {
        return new StringDictMapping();
    }

    @Bean
    public IntegerDictMapping integerDictMapping() {
        return new IntegerDictMapping();
    }

    @Bean
    public MyJackson2ObjectMapperBuilderCustomizer myJackson2ObjectMapperBuilderCustomizer(DictSerializerFactory dictSerializerFactory) {
        return new MyJackson2ObjectMapperBuilderCustomizer(dictSerializerFactory);
    }

    public static class MyJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer {
        private final DictSerializerFactory dictSerializerFactory;

        public MyJackson2ObjectMapperBuilderCustomizer(DictSerializerFactory dictSerializerFactory) {
            this.dictSerializerFactory = dictSerializerFactory;
        }

        @Override
        public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.setSerializerModifier(new DictBeanSerializerModifier(dictSerializerFactory));
            jacksonObjectMapperBuilder.modules(simpleModule);
        }
    }
}
