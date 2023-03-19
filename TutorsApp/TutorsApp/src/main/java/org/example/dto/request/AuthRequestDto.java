package org.example.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequestDto {
    @Min(value = 5, message = "Слишком коротки логин")
    @Max(value = 18, message = "Превышено количество символов")
    String login;
    @NotNull(message = "Пароль не может быть null")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Min(value = 8, message = "Пароль должен быть не менее 8 символов")
    String password;
}
