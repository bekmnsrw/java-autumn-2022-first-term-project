package ru.kpfu.itis.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dto.forms.SignUpForm;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.HashService;
import ru.kpfu.itis.services.UserMapper;

@AllArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final HashService hashService;

    @Override
    public User toUserFromSignUpForm(SignUpForm signUpForm) {
        return User.builder()
                .firstName(signUpForm.getFirstName())
                .secondName(signUpForm.getSecondName())
                .email(signUpForm.getEmail())
                .hashedPassword(hashService.hashPassword(signUpForm.getPassword()))
                .build();
    }

    @Override
    public UserDto toUserDtoFromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .isAdmin(user.getIsAdmin())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .build();
    }
}
