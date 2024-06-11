package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.util.List;


public class DictBeanSerializerModifier extends BeanSerializerModifier {

    private final DictSerializerFactory dictSerializerFactory;

    public DictBeanSerializerModifier(DictSerializerFactory dictSerializerFactory) {
        this.dictSerializerFactory = dictSerializerFactory;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            Dict dict = writer.getAnnotation(Dict.class);
            if (dict != null) {
                @SuppressWarnings("unchecked")
                JsonSerializer<Object> dictSerializer = (JsonSerializer<Object>) dictSerializerFactory.getDictSerializer(
                        writer.getType().getRawClass(), dict);
                if (dictSerializer != null) {
                    writer.assignSerializer(dictSerializer);
                    continue;
                }
            }
            writer.assignSerializer(new NestedObjectMaskingSerializer(writer.getSerializer()));
        }
        return beanProperties;
    }

    private static class NestedObjectMaskingSerializer extends JsonSerializer<Object> {
        private final JsonSerializer<Object> defaultSerializer;

        public NestedObjectMaskingSerializer(JsonSerializer<Object> defaultSerializer) {
            this.defaultSerializer = defaultSerializer;
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (defaultSerializer != null) {
                defaultSerializer.serialize(value, gen, serializers);
            } else {
                serializers.defaultSerializeValue(value, gen);
            }
        }
    }
}
