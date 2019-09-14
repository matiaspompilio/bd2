package info.unlp.edu.ar.bithub.controllers.UserController;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/mongo/users")
public class UserMongoController {

    @Inject
    private UserService userService;

    private UserService getUserService(){
        return this.userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.getUserService().getAllUser();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestParam(value = "name")String name,
                                     @RequestParam(value = "email")String email) {
        ResponseEntity<?> response = null;
        try {
           this.getUserService().addUser(name, email);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el usuario");
        }
        return response;
    }
}
