package com.example.Task_menajer.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequestDTO(
        @NotBlank(message = "o titulo da task nao pode ser vazio")
        String title,
        @NotBlank(message = "a descricao da task nao pode ser vazia")
        String description,
        Boolean status
) {}
