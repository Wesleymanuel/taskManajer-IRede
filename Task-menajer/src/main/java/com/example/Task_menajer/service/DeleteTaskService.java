package com.example.Task_menajer.service;

import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void delete(UUID id){
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new  EntityNotFoundException("nenhuma task encontrada com id: " + id));

        this.taskRepository.delete(task);
    }
}
