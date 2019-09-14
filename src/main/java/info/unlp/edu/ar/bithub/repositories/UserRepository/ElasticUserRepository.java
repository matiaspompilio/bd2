package info.unlp.edu.ar.bithub.repositories.UserRepository;

import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticUserRepository extends ElasticsearchRepository<User, ObjectId> {
    List<User> findByName(String name);
}
