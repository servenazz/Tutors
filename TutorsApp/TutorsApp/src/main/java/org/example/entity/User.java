package org.example.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.base.PersistentObject;
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends PersistentObject {
    @Column(name = "login", nullable = false, unique = true)
    String login;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "role")
    UserRoleType role;
    @Column(name = "status")
    StatusType status;
    @Column(name = "experience")
    String experience;
    @Column(name = "rating")
    double rating;
}
