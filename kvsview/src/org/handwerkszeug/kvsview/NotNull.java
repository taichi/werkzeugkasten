package org.handwerkszeug.kvsview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target( { ElementType.METHOD, ElementType.PARAMETER })
public @interface NotNull {

}
