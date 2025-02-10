package com.ms.spring_security_jwt.infrastructure.annotation.validation.enum_subset;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for validating that a field's value is within a specified subset of an enum's constants.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumSubsetValidator.class)
public @interface EnumSubset {
    /**
     * Specifies the enum class to validate against.
     *
     * @return the enum class
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * Specifies the subset of valid values from the enum.
     * If empty, all constants of the enum are considered valid.
     *
     * @return an array of valid enum constant names
     */
    String[] anyOf() default {};

    /**
     * Specifies the error message when validation fails.
     *
     * @return the error message
     */
    String message() default "value must be one of {anyOf}";

    /**
     * Specifies the validation groups the constraint belongs to.
     *
     * @return the validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Specifies additional information about the validation.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}
