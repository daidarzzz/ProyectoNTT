package com.learnhub.user.application.dto;

import java.time.LocalDateTime;

public record LoginResponse(
    String token,
    UserResponse user
) {}
