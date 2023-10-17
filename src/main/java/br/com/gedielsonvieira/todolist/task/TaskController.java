package br.com.gedielsonvieira.todolist.task;

import br.com.gedielsonvieira.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    //Spring gerencia o ciclo de vida ao instanciar a clase
    private final ITaskRepository taskRepository;


    @PostMapping
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest httpServletRequest) {
        Object idUser = httpServletRequest.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início | data de término: deve ser maior que a atual");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor que a data de término");
        }

        TaskModel taskSaved = taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskSaved);
    }

    @GetMapping
    public List findAll(HttpServletRequest httpServletRequest) {
        return taskRepository.findByIdUser((UUID) httpServletRequest.getAttribute("idUser"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest httpServletRequest) {
        Optional<TaskModel> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tarefa " + id + "não encontrada");
        } else {

            Object idUser = httpServletRequest.getAttribute("idUser");

            if(!task.get().getIdUser().equals(idUser)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário não tem permissão para alterar esta tarefa");
            }
            //Atualiza quem é dono da tarefa
            Utils.copyNonNullProperties(taskModel, task.get());
            return ResponseEntity.ok(taskRepository.save(task.get()));
        }

    }

}
