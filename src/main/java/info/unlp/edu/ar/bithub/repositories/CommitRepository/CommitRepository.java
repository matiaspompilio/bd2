package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import info.unlp.edu.ar.bithub.model.Commit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommitRepository extends MongoRepository<Commit, ObjectId> {

    public List<Commit> findByAuthor(ObjectId author);

    public List<Commit> findByAuthor_Name(String name);
}
