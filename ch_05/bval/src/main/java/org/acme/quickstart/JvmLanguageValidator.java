package org.acme.quickstart;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JvmLanguageValidator
    implements ConstraintValidator<JvmLanguage, String> { // <1> <2>

        private List<String> favoriteLanguages = Arrays.asList("java",                                                             "groovy", "kotlin", "scala");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return favoriteLanguages.stream()
            .anyMatch(l -> l.equalsIgnoreCase(value)); // <3>
    }
}
