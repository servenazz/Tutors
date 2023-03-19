package org.example.demo.unit;

import org.example.dto.request.UserRequestDto;
import org.example.entity.User;
import org.example.exception.BaseRuntimeException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.example.entity.UserRoleType;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setLogin("testuser");
        request.setPassword("password");
        request.setEmail("testuser@example.com");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setRole(UserRoleType.STUDENT);

        when(userRepository.findByLogin(request.getLogin())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        // Act
        userService.create(request);

        // Assert
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    void testCreateAdminUser() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setLogin("testadmin");
        request.setPassword("password");
        request.setEmail("testadmin@example.com");
        request.setFirstName("Test");
        request.setLastName("Admin");
        request.setRole(UserRoleType.ADMIN);

        // Act and Assert
        assertThatThrownBy(() -> userService.create(request))
                .isInstanceOf(BaseRuntimeException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.BAD_REQUEST)
                .hasMessage("Регистрация с ролью администратор недопустима");
    }

    @Test
    void testCreateDuplicateLoginUser() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setLogin("testuser");
        request.setPassword("password");
        request.setEmail("testuser2@example.com");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setRole(UserRoleType.STUDENT);

        when(userRepository.findByLogin(request.getLogin()))
                .thenReturn(Optional.of(new User()));

        // Act and Assert
        assertThatThrownBy(() -> userService.create(request))
                .isInstanceOf(BaseRuntimeException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.BAD_REQUEST)
                .hasMessage("Пользователь с таким логином уже существует");
    }

    @Test
    void testCreateDuplicateEmailUser() {
        // Arrange
        UserRequestDto request = new UserRequestDto();
        request.setLogin("testuser2");
        request.setPassword("password");
        request.setEmail("testuser@example.com");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setRole(UserRoleType.STUDENT);

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new User()));

        // Act and Assert
        assertThatThrownBy(() -> userService.create(request))
                .isInstanceOf(BaseRuntimeException.class)
                .hasFieldOrPropertyWithValue("status", HttpStatus.BAD_REQUEST)
                .hasMessage("Пользователь с такой электронной почтой уже существует");
    }
}