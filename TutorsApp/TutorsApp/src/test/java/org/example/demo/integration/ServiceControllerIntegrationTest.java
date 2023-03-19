package org.example.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.exception.NotFoundException;
import org.example.dto.request.AuthRequestDto;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.AuthResponseDto;
import org.example.entity.Service;
import org.example.entity.User;
import org.example.entity.UserRoleType;
import org.example.repository.ServiceRepository;
import org.example.repository.UserRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(initializers = {ServiceControllerIntegrationTest.Initializer.class})
public class ServiceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ClassRule
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            .withExposedPorts(3306);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            mySQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
                    "spring.jpa.ddl-auto=create-drop",
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

//    @BeforeAll
//    static void setUp() {

//
//        mySQLContainer
//        mySQLContainer.start();
//
//        System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
//        System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
//        System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
//
//    }

    private static User createUserWithRole(UserRoleType roleType) {
        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        user.setEmail("testmail@mailk.ru");
        user.setCreatedAt(new Date());
        user.setRole(roleType);
        return user;
    }

    private static String authentificate(MockMvc mockMvc, String login, String password) throws Exception {
        AuthRequestDto request = new AuthRequestDto(login, password);
        var response = mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var token = objectMapper.readValue(response, AuthResponseDto.class);
        return token.getToken();
    }

//    @AfterAll
//    static void tearDown() {
//        mySQLContainer.stop();
//    }

    @Test
    @DisplayName("Create Service API - Secure")
    public void testCreateServiceApiSecure() throws Exception {

        User defaultUser = createUserWithRole(UserRoleType.STUDENT);
        userRepository.save(defaultUser);
        String token = authentificate(mockMvc, defaultUser.getLogin(), defaultUser.getPassword());

        // Create user request
        UserRequestDto request = new UserRequestDto();
        request.setLogin("testLogin");
        request.setPassword("testPassword");
        request.setEmail("testuser2@example.com");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setRole(UserRoleType.STUDENT);

        mockMvc.perform(post("/service").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Service service = serviceRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Service not found"));

        assertEquals(request.getRole(), service.getUser().getRole(), "");
        assertEquals(request.getFirstName(), service.getUser().getFirstName(), "");
        assertEquals(request.getLastName(), service.getUser().getLastName(),"");
        assertEquals(request.getEmail(), service.getUser().getEmail(),"");
        assertTrue(passwordEncoder.matches(request.getPassword(), service.getUser().getPassword()), "");
    }
}