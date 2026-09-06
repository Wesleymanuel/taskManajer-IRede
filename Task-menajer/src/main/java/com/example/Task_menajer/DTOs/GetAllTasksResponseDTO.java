package com.example.Task_menajer.DTOs;

import java.util.UUID;

public record GetAllTasksResponseDTO(
        UUID taskId,
        String title,
        String description,
        boolean status
) {}
