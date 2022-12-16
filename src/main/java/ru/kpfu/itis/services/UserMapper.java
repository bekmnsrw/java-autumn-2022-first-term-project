package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.forms.SignUpForm;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.User;

public interface UserMapper {
    User toUserFromSignUpForm(SignUpForm signUpForm);
    UserDto toUserDtoFromUser(User user);
}
