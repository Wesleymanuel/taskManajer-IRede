package com.example.Task_menajer.DTOs;

import java.util.UUID;

public record CreateTaskResponseDTO(
        UUID task_id,
        String title,
        String description,
        String status
) {}
