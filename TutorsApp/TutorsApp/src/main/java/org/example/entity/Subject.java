package org.example.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.base.PersistentObject;

import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subjects")
public class Subject  extends PersistentObject {
    @Column(name = "Title", nullable = false)
    String title;
    @Column(name = "Description", nullable = false)
    String description;
}