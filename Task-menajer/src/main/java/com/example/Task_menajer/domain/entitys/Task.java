package com.example.Task_menajer.domain.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID task_id;
    @Column(nullable = false, unique = true, name = "task_title")
    private String title;
    @Column(nullable = false, name = "task_description")
    private String description;
    @Column(nullable = false)
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User userId;

    public Task(String title, String description, boolean status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
