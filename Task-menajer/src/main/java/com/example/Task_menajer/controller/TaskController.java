package com.example.Task_menajer.controller;

import com.example.Task_menajer.DTOs.*;
import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.repository.TaskRepository;
import com.example.Task_menajer.service.CreateTaskService;
import com.example.Task_menajer.service.DeleteTaskService;
import com.example.Task_menajer.service.UpdateTaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @Autowired
    private TaskRepository taskRepository;

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

    @GetMapping("/get")
    public ResponseEntity<List<GetAllTasksResponseDTO>> getAllTasks(){
        List<GetAllTasksResponseDTO> tasks = this.taskRepository.findAll()
                .stream().map((tk) -> new GetAllTasksResponseDTO(
                        tk.getTask_id(),
                        tk.getTitle(),
                        tk.getDescription(),
                        tk.isStatus()
                )).toList();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIndividualTaskResponseDTO> getTask(@PathVariable UUID id){
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new  EntityNotFoundException("task nao encontrada com id: " + id));
        return ResponseEntity.ok(new GetIndividualTaskResponseDTO(
                task.getTask_id(),
                task.getTitle(),
                task.getDescription(),
                task.isStatus()
        ));
    }
}
