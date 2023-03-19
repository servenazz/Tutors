package org.example.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubjectRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Недопустимые символы в поле")
    String title;
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Недопустимые символы в поле")
    String description;
}
