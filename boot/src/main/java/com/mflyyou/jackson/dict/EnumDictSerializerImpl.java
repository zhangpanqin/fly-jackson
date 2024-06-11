package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumDictSerializerImpl extends AbstractDictSerializer<Enum<?>> {
    public EnumDictSerializerImpl(Class<Enum<?>> clazz, Dict dict) {
        super(clazz, dict);
    }

    @Override
    public void customizeSerialize(Enum<?> code, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (this.isDict()) {
            return;
        }

        DictMode dictMode = this.mode();

        if (dictMode == DictMode.NORMAL) {
            gen.writeString(code.name());
            return;
        }

        if (dictMode == DictMode.OBJECT) {
            gen.writeStartObject();
            gen.writeStringField(CODE, code.name());
            gen.writeStringField(DESCRIPTION, code.toString());
            gen.writeEndObject();
        }
    }
}
