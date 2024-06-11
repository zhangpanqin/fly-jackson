package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class IntegerDictSerializerImpl extends AbstractDictSerializer<Integer> {
    public IntegerDictSerializerImpl(Dict dict) {
        super(Integer.class, dict);
    }

    @Override
    public void customizeSerialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (this.isDict()) {
            return;
        }

        DictMode dictMode = this.mode();
        if (dictMode == DictMode.NORMAL) {
            gen.writeNumber(value);
            return;
        }
        if (dictMode == DictMode.OBJECT) {
            gen.writeStartObject();
            gen.writeNumberField(CODE, value);
            gen.writeNumberField(DESCRIPTION, value);
            gen.writeEndObject();
        }
    }
}
