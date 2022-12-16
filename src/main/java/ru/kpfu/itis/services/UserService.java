package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.forms.SignInForm;
import ru.kpfu.itis.dto.forms.SignUpForm;
import ru.kpfu.itis.dto.UserDto;

public interface UserService {
    UserDto signUp(SignUpForm signUpForm);
    UserDto signIn(SignInForm signInForm);
}
