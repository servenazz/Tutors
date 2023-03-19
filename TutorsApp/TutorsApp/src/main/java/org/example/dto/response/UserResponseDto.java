package org.example.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entity.User;
import org.example.entity.UserRoleType;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    long id;
    String login;
    String firstName;
    String lastName;
    UserRoleType role;
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }
}
