package com.example.Task_menajer.DTOs;

import java.util.UUID;

public record GetIndividualTaskResponseDTO(
        UUID taskId,
        String title,
        String description,
        boolean status
) {}
