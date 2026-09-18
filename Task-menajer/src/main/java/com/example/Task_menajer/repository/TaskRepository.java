package com.example.Task_menajer.repository;

import com.example.Task_menajer.domain.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findTaskByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.userId.user_id = :userId")
    List<Task> findTasksByUserId(@Param("userId") UUID userId);
}
