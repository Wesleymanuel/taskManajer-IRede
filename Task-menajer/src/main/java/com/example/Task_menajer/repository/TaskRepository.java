package com.example.Task_menajer.repository;

import com.example.Task_menajer.domain.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findTaskByTitle(String title);
}
