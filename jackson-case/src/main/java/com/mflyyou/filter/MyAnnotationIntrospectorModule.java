package com.mflyyou.filter;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class MyAnnotationIntrospectorModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.insertAnnotationIntrospector(new MyAnnotationIntrospector());
    }
}