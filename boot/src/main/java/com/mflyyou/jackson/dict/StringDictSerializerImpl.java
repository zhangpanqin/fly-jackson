package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StringDictSerializerImpl extends AbstractDictSerializer<String> {
    public StringDictSerializerImpl(Dict dict, DictMapping<String> dictMapping) {
        super(String.class, dict, dictMapping);
    }

    @Override
    public void customizeSerialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (this.getDict().isDict()) {
            // todo
            return;
        }

        DictMode dictMode = this.mode();

        if (dictMode == DictMode.NORMAL) {
            gen.writeString(value);
            return;
        }

        if (dictMode == DictMode.OBJECT) {
            gen.writeStartObject();
            gen.writeStringField(CODE, value);
            gen.writeStringField(DESCRIPTION, value);
            gen.writeEndObject();
        }
    }
}
