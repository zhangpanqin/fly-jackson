package com.mflyyou.mask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MaskSerializer extends StdSerializer<Object> {
    private final String maskWith;
    private final int unmaskedPrefixLength;

    public MaskSerializer(String maskWith, int unmaskedPrefixLength) {
        super(Object.class);
        this.maskWith = maskWith;
        this.unmaskedPrefixLength = unmaskedPrefixLength;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else if (value instanceof String) {
            String strValue = (String) value;
            String maskedValue = maskString(strValue);
            gen.writeString(maskedValue);
        } else if (value instanceof Integer) {
            String strValue = String.valueOf(value);
            String maskedValue = maskString(strValue);
            gen.writeString(maskedValue);
        } else {
            provider.defaultSerializeValue(value, gen); // Handle nested objects
        }
    }

    private String maskString(String value) {
        if (value.length() <= unmaskedPrefixLength) {
            return value;
        }
        String prefix = value.substring(0, unmaskedPrefixLength);
        return prefix + maskWith;
    }
}
