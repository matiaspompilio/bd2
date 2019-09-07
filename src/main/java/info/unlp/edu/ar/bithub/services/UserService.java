package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUser();

    public Optional<User> getUser(ObjectId id);

    public void addUser(String name, String email);


}
