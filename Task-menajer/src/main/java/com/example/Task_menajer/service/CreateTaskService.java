package com.example.Task_menajer.service;

import com.example.Task_menajer.DTOs.CreateTaskRequestDTO;
import com.example.Task_menajer.DTOs.CreateTaskResponseDTO;
import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.domain.entitys.User;
import com.example.Task_menajer.exceptions.TaskAlreadyExistException;
import com.example.Task_menajer.exceptions.UserNotFoundException;
import com.example.Task_menajer.repository.TaskRepository;
import com.example.Task_menajer.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateTaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CreateTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public CreateTaskResponseDTO create(CreateTaskRequestDTO task) throws UserNotFoundException, TaskAlreadyExistException {

        Optional<User> user = userRepository.findById(task.userId());
        Optional<Task> taksExit = taskRepository.findTaskByTitle(task.title());

        if (user.isEmpty()) {
            throw new UserNotFoundException("User" + task.userId() + "not found");
        }
        else if (taksExit.isPresent()) {
            throw new TaskAlreadyExistException("Task already exist");
        }
            Task newTask = new Task();
            newTask.setTitle(task.title());
            newTask.setUserId(user.get());
            newTask.setDescription(task.description());
            newTask.setStatus(false);

            taskRepository.save(newTask);

            return new CreateTaskResponseDTO(newTask.getTask_id(),newTask.getTitle(), newTask.getDescription(), "incompleta");
    }
}
