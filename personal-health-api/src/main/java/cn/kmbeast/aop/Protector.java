package cn.kmbeast.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Interface protection annotation.
 *This annotation is added to the interface, which automatically authenticates and parses user identity information.
 *Only those who meet the user's identity can use specific interface functions.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Protector {
    /**
     *Role Name
     *
     * @return String
     */
    String role() default "";
}
