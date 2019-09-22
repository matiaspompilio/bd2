package info.unlp.edu.ar.bithub.controllers.UserController;


import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/es/user")
public class UserElasticController {

    @Inject
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsersFromElastic();
    }

    @GetMapping("/{id}")
    public User getUserFromElastic (@PathVariable ObjectId id){
        Optional<User> optionalUser = userService.getUserFromElastic(id);
        return optionalUser.orElse(null);
    }

}
