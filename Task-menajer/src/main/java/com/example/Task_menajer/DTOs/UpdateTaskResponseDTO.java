package com.example.Task_menajer.DTOs;

import java.util.UUID;

public record UpdateTaskResponseDTO(
        UUID taskId,
        String title,
        String description,
        Boolean status
) {
}
