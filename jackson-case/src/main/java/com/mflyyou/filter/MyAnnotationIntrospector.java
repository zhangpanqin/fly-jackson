package com.mflyyou.filter;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.mflyyou.mask.Mask;
import com.mflyyou.mask.MaskSerializer;

public class MyAnnotationIntrospector
        extends AnnotationIntrospector
        implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Version version() {
        return com.fasterxml.jackson.databind.cfg.PackageVersion.VERSION;
    }

    @Override
    public Object findSerializer(Annotated am) {
        Mask annotation = am.getAnnotation(Mask.class);
        if (annotation == null) {
            return super.findSerializer(am);
        }
        return new MaskSerializer(annotation.maskWith(), annotation.unmaskedPrefixLength());
    }
}