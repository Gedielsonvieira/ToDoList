package br.com.gedielsonvieira.todolist.task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    //Spring gerencia o ciclo de vida da instancia
    private final ITaskRepository taskRepository;


    @PostMapping
    public TaskModel create(@RequestBody TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

}
