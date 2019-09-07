package info.unlp.edu.ar.bithub.repositories;

import info.unlp.edu.ar.bithub.model.Commit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitRepository extends MongoRepository<Commit, ObjectId> {

    public Commit findByName(String name);
}