package com.example.Task_menajer.DTOs;

import lombok.Builder;

@Builder
public record JwtUserData(String userId, String email) {
}
