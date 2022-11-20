package models.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME) // indicate time when annotation is implemented
@Target(value = {ElementType.TYPE}) // Indicate the place where annotation is applied
public @interface ComponentCssSelector {
    String value();
}
