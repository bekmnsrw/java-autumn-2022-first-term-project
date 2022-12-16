package ru.kpfu.itis.services.validation;

import ru.kpfu.itis.dto.forms.SignUpForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(SignUpForm signUpForm);
}
