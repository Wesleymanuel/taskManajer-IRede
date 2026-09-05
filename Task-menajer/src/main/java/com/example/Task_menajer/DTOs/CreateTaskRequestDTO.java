package com.example.Task_menajer.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateTaskRequestDTO(
        @NotBlank(message = "o titulo da task nao pode ser vazio")
        String title,
        @NotBlank(message = "o id do usuario nao pode ser vazio")
        UUID userId,
        @NotBlank(message = "a descricao da task nao pode ser vazia")
        String description
) {}
