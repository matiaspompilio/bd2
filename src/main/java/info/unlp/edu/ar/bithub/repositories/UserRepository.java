package info.unlp.edu.ar.bithub.repositories;

import info.unlp.edu.ar.bithub.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository {

    public User findByName(String name);
}
