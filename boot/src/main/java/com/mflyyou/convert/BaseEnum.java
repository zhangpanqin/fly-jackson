package com.mflyyou.convert;

import java.io.Serializable;

public interface BaseEnum<T extends Serializable> {
     T code();
}
