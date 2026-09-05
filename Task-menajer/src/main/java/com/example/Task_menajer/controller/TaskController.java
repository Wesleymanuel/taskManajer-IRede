package com.example.Task_menajer.controller;

import com.example.Task_menajer.DTOs.CreateTaskRequestDTO;
import com.example.Task_menajer.DTOs.CreateTaskResponseDTO;
import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.service.CreateTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskService createTaskService;

    public TaskController(CreateTaskService createTaskService) {
        this.createTaskService = createTaskService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponseDTO> createTask(
            @Valid
            @RequestBody CreateTaskRequestDTO dto
    ) {
        UUID userUuid = dto.userId();
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskService.create(new CreateTaskRequestDTO(dto.title(), userUuid, dto.description())));
    }
}
