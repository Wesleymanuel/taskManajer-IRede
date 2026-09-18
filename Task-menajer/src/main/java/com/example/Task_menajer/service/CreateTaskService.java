package com.example.Task_menajer.service;

import com.example.Task_menajer.DTOs.CreateTaskRequestDTO;
import com.example.Task_menajer.DTOs.CreateTaskResponseDTO;
import com.example.Task_menajer.domain.entitys.Task;
import com.example.Task_menajer.domain.entitys.User;
import com.example.Task_menajer.exceptions.TaskAlreadyExistException;
import com.example.Task_menajer.exceptions.UserNotFoundException;
import com.example.Task_menajer.repository.TaskRepository;
import com.example.Task_menajer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CreateTaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CreateTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public CreateTaskResponseDTO create(CreateTaskRequestDTO taskDto, Principal principal) throws UserNotFoundException, TaskAlreadyExistException {
        String email = principal.getName();

        UserDetails userDetails = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o e-mail: " + email));

        User user = (User) userDetails;

        Optional<Task> taksExit = taskRepository.findTaskByTitle(taskDto.title());

        if (taksExit.isPresent()) {
            throw new TaskAlreadyExistException("Task already exist");
        }
            Task newTask = new Task();
            newTask.setTitle(taskDto.title());
            newTask.setDescription(taskDto.description());

            boolean statusValue = taskDto.status() != null ? taskDto.status() : false;
            newTask.setStatus(statusValue);
            newTask.setUserId(user);

            String statusResponse = newTask.isStatus() ? "completa" : "incompleta";

            taskRepository.save(newTask);

            return new CreateTaskResponseDTO(newTask.getTask_id(),newTask.getTitle(), newTask.getDescription(), statusResponse);
    }
}
