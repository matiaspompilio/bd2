package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
@Named
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserRepository getUserRepository(){
        return this.userRepository;
    }

    public List<User> getAllUser() {
        return this.getUserRepository().findAll();
    }

    public Optional<User> getUser(ObjectId id) {
        return this.getUserRepository().findById(id);
    }

    public void addUser(String name, String email) {
        User user = new User(name, email);
        this.getUserRepository().save(user);
    }
}
