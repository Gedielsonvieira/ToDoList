package br.com.gedielsonvieira.todolist.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final IUserRepository iUserRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {
        UserModel user = iUserRepository.findByUsername(userModel.getUsername());

        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
        }

        return ResponseEntity.ok(iUserRepository.save(userModel));
    }

}
