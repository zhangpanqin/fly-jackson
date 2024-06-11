package com.mflyyou.convert;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SerializableToBaseEnum implements ConditionalGenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> set = new HashSet<ConvertiblePair>() {{
            add(new ConvertiblePair(String.class, BaseEnum.class));
        }};
        return Collections.unmodifiableSet(set);
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> type = targetType.getType();
        for (Class<?> anInterface : targetType.getType().getInterfaces()) {
            if (anInterface==BaseEnum.class){
                return true;
            }
        }
        return type.isEnum();
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        for (Object enumConstant : targetType.getType().getEnumConstants()) {
            if (enumConstant instanceof BaseEnum) {
                if (source.equals(((BaseEnum<?>) enumConstant).code().toString())) {
                    return enumConstant;
                }
            }
        }
        return null;
    }
}
