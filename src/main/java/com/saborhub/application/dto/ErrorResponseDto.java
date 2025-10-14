package com.saborhub.application.dto;

import java.time.ZonedDateTime;

public record ErrorResponseDto(
        int status,
        String error,
        String message,
        ZonedDateTime timestamp,
        String path
) {
    public ErrorResponseDto(int status, String error, String message, String path) {
        this(status, error, message, ZonedDateTime.now(), path);
    }
}