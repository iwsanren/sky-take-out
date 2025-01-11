package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A custom annotation used to mark a method that requires automatic field filling functionality
 */

@Target(ElementType.METHOD) // used to
@Retention(RetentionPolicy.RUNTIME)

public @interface AutoFill {
    // sky-common.enumeration.OperationType
    // operation type of database: insert update
    OperationType value();
}
