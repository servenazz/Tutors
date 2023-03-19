package org.example.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ServiseRequestDto {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Недопустимые символы")
    String servise00;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Недопустимые символы")
    String service01;
}
