package com.vbs.custom.annotations;

/**
 * @author DL
 * Jun 19, 2015
 * 
 * this annotation is again annotated with @Controller and we will use this to define restful endpoints
 * 
 */


import org.springframework.stereotype.Controller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface RestEndpoint
{
    String value() default "";
}
