package org.example.dto.response;

public class LogoutResponseDto {
    private String message;

    public LogoutResponseDto() {
    }

    public LogoutResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogoutResponseDto logout() {
        return new LogoutResponseDto("Пользователь успешно вышел из системы.");
    }
}
