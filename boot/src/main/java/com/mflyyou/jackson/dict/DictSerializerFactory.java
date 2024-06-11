package com.mflyyou.jackson.dict;

import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@SuppressWarnings("unchecked")
public final class DictSerializerFactory {
    private final List<DictMapping<?>> dictMappings;

    public DictSerializerFactory(List<DictMapping<?>> dictMappings) {
        this.dictMappings = dictMappings;
    }

    public JsonSerializer<?> getDictSerializer(Class<?> clazz, Dict dict) {
        DictMapping<?> dictMapping = getDictMapping(clazz);
        if (clazz == String.class) {
            return new StringDictSerializerImpl(dict,(DictMapping<String>)dictMapping);
        }

        if (clazz == Integer.class || clazz == Integer.TYPE) {
            return new IntegerDictSerializerImpl(dict);
        }

        if (clazz.isEnum()) {
            return new EnumDictSerializerImpl((Class<Enum<?>>) clazz, dict);
        }

        log.warn("{} 没有提供 的 Jackson AbstractDictSerializer", clazz.getName());
        return null;
    }

    private DictMapping<?> getDictMapping(Class<?> clazz) {
        for (DictMapping<?> dictMapping : this.dictMappings) {
            if (dictMapping.support(clazz)) {
                return dictMapping;
            }
        }
        return new DictMapping.DefaultDictMapping();
    }
}
