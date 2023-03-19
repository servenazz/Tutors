package org.example.security;

import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticatedUser {

    UserRepository userRepository;

    private Optional<Authentication> getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken));
    }

    public Optional<User> get() {
        return getAuthentication().flatMap(authentication -> userRepository.findByLogin(authentication.getName()));
    }

    public User getWithThrow() {
        return getAuthentication().flatMap(authentication -> userRepository.findByLogin(authentication.getName()))
                .orElseThrow(() -> {
                    log.warn("user not found user = {}", getUserDetails());
                    return new UserNotFoundException();
                });
    }


    public UserDetailsWithId getUserDetails(){
        return (UserDetailsWithId) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}