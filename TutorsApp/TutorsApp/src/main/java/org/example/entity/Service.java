package org.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.base.PersistentObject;

import javax.persistence.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "services")
public class Service extends PersistentObject {
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "price", nullable = false)
    long price;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    User user;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = true)
    Subject subject;
}
