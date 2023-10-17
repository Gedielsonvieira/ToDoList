package br.com.gedielsonvieira.todolist.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "tb_users")
public class UserModel {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @Column(unique = true)
   private String username;
   private String name;
   private String password;

   @CreationTimestamp
   private LocalDateTime createdAt;
}
