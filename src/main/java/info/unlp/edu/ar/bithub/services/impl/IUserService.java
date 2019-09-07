package info.unlp.edu.ar.bithub.services.impl;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository;
import info.unlp.edu.ar.bithub.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IUserService implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUser(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public void addUser(String name, String email) {
        User user = new User(name, email);
        repository.save(user);
    }
}
