package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dto.request.AuthRequestDto;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.AuthResponseDto;
import org.example.dto.response.UserResponseDto;
import org.example.entity.StatusType;
import org.example.entity.User;
import org.example.entity.UserRoleType;
import org.example.exception.BaseRuntimeException;
import org.example.repository.UserRepository;
import org.example.service.JWTMapper;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository  userRepository;
    JWTMapper jwtMapper;

    public UserServiceImpl(UserRepository userRepository, JWTMapper jwtMapper) {
        this.userRepository = userRepository;
        this.jwtMapper = jwtMapper;
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserRequestDto request) {

        if (request.getRole().equals(UserRoleType.ADMIN)){
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Регистрация с ролью администратор недопустима");
        }
        Optional<User> user = userRepository.findByLogin(request.getLogin());
        if (user.isPresent()){
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Пользователь с таким логином уже существует");
        }
        user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Пользователь с такой электронной почтой уже существует");
        }

        User newUser = new User();
        newUser.setLogin(request.getLogin());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setRole(request.getRole());
        newUser.setCreatedAt(new Date());
        newUser.setStatus(StatusType.FREE);
        userRepository.save(newUser);
    }

    @Override
    public UserResponseDto get(long userId) {
        return null;
    }

    @Override
    public void update(UserRequestDto request) {

    }

    @Override
    public void delete(long userId) {

    }

    @Override
    public AuthResponseDto login(AuthRequestDto request){
        var user = userRepository.findByLogin(request.getLogin());
        if(user.isEmpty())
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Пользователь не найден");

        if(!user.get().getPassword().equals(request.getPassword()))
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Неверный пароль");

//        String token = Jwts.builder()
//                .setClaims(tokenDetailToMap(details))
//                .setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + expirationTime))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .setExpiration(ZonedDateTime.now().plusYears(1).)
//                .compact();
//        return new AuthResponseDto(token);
        var token = jwtMapper.create(new JWTMapper.TokenDetails(user.get().getId(), user.get().getLogin()));
        return new AuthResponseDto(token, user.get().getFirstName() + " " + user.get().getLastName());
    }
}
