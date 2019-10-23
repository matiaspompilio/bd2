package info.unlp.edu.ar.bithub.repositories.UserRepository;

import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoUserRepository extends MongoRepository<User, ObjectId> {

    public User findByName(String name);

    public User findByEmail(String email);

    public List<User> findByNameRegex(String name);
}
