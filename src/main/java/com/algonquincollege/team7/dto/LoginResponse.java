package com.algonquincollege.team7.dto;

public record LoginResponse(
    String token,
    Long userId,
    String email,
    String firstName,
    String lastName,
    String type
) {}