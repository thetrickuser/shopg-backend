package com.shopg.authservice.util;

import com.shopg.authservice.model.ROLES;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidateRole, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // Check if it's a valid enum value
            ROLES.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}