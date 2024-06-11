package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Getter;
import org.springframework.util.Assert;

import java.io.IOException;

@Getter
public abstract class AbstractDictSerializer<T> extends StdSerializer<T> {

    static final String CODE = "code";
    static final String DESCRIPTION = "description";

    private final Dict dict;
    private final DictMapping<T> dictMapping;

    protected AbstractDictSerializer(Class<T> t,
                                     Dict dict,
                                     DictMapping<T> dictMapping) {
        super(t);
        Assert.notNull(dict, "dict 不能为 null");
        this.dict = dict;
        this.dictMapping = dictMapping;
    }

    public boolean isDict() {
        return this.dict.isDict();
    }

    public DictMode mode() {
        return this.dict.mode();
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        customizeSerialize(value, gen, provider);
    }

    public abstract void customizeSerialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException;
}
