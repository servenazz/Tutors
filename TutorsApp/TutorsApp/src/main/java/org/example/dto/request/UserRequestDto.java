package org.example.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entity.UserRoleType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {

    @Min(value = 5, message = "Слишком коротки логин")
    @Max(value = 18, message = "Превышено количество символов")
    String login;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,16}$", message = "Пароль должен содержать одну заглавную букву, одну цифру и один символ")
    String password;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Неверный формат почтового адреса")
    String email;
    @Min(value = 2, message = "Имя слишком короткое")
    @Max(value = 18, message = "Превышаете количество символов в поле")
    String firstName;
    @Min(value = 2, message = "Фамилия слишком короткая")
    @Max(value = 18, message = "Превышаете количество символов в поле")
    String lastName;
    UserRoleType role;
}
