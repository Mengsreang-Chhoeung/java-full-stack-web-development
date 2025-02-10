package com.ms.spring_security_jwt.infrastructure.annotation.validation.enum_subset;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnumSubsetValidator implements ConstraintValidator<EnumSubset, Object> {
    private Set<String> validValues;

    @Override
    public void initialize(EnumSubset constraint) {
        validValues = new HashSet<>();
        Class<? extends Enum<?>> enumClass = constraint.enumClass();
        String[] anyOf = constraint.anyOf();

        // Populate valid values from the enum
        if (anyOf.length > 0) {
            validValues.addAll(Arrays.asList(anyOf));
        } else {
            // If no subset specified, use all enum constants
            for (Enum<?> constant : enumClass.getEnumConstants()) {
                validValues.add(constant.name());
            }
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value == null || validValues.contains(value.toString());
    }
}
