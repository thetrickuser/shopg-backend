package com.shopg.userservice.util;

import com.shopg.userservice.model.ROLES;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidateRole, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // Convert the string value to uppercase and check if it's a valid enum value
            ROLES.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}