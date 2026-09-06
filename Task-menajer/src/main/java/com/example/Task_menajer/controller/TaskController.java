package com.example.Task_menajer.controller;

import com.example.Task_menajer.DTOs.*;
import com.example.Task_menajer.service.CreateTaskService;
import com.example.Task_menajer.service.DeleteTaskService;
import com.example.Task_menajer.service.UpdateTaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private  CreateTaskService createTaskService;
    @Autowired
    private UpdateTaskService updateTaskService;
    @Autowired
    private DeleteTaskService deleteTaskService;

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponseDTO> createTask(
            @Valid
            @RequestBody CreateTaskRequestDTO dto
    ) {
        UUID userUuid = dto.userId();
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskService.create(new CreateTaskRequestDTO(dto.title(), userUuid, dto.description())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskResponseDTO> updateTask(
            @PathVariable UUID id,
            @Valid
            @RequestBody UpdateTaskRequestDTO dto
    ){
        UpdateTaskResponseDTO res = this.updateTaskService.updateTask(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id){
        this.deleteTaskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
