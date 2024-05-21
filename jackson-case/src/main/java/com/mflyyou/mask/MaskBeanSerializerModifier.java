package com.mflyyou.mask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.util.List;

public class MaskBeanSerializerModifier extends BeanSerializerModifier {
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            Mask mask = writer.getAnnotation(Mask.class);
            if (mask != null) {
                JsonSerializer<Object> serializer = new MaskSerializer(mask.maskWith(), mask.unmaskedPrefixLength());
                writer.assignSerializer(serializer);
            } else {
                writer.assignSerializer(new NestedObjectMaskingSerializer(writer.getSerializer()));
            }
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
