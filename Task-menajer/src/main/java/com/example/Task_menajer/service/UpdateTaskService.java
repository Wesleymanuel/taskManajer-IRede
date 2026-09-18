package com.example.Task_menajer.service;

import com.example.Task_menajer.DTOs.UpdateTaskRequestDTO;
import com.example.Task_menajer.DTOs.UpdateTaskResponseDTO;
import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.domain.entitys.User;
import com.example.Task_menajer.repository.TaskRepository;
import com.example.Task_menajer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public UpdateTaskResponseDTO updateTask(UUID taskId, UpdateTaskRequestDTO dto){
        Task task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("task nao existe"));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());

        Task updatedTask = this.taskRepository.save(task);

        return new UpdateTaskResponseDTO(
                updatedTask.getTask_id(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.isStatus()
        );
    }
}
