package com.example.Task_menajer.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SingInUserResponseDTO(
        String token
) {}
