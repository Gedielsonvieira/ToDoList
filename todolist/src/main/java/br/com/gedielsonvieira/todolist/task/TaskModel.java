package br.com.gedielsonvieira.todolist.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    //problemas de passar o id do usuario ao cadastrar uma tarefa:
    //cadastrar uma tarefa para um usuario que não existe
    //cadastrar uma tarefa para um usuario que não tem permissão
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
