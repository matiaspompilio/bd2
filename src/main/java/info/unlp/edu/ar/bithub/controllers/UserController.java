package info.unlp.edu.ar.bithub.controllers;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.services.impl.IUserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @GetMapping(value = "/")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping
    public Optional<User> getUserById(@RequestParam(value = "id") ObjectId id){
        return  userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestParam(value = "name")String name,
                                     @RequestParam(value = "email")String email) {
        ResponseEntity<?> response = null;
        try {
            userService.addUser(name, email);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el usuario");
        }

        return response;

    }
}
