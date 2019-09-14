package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import info.unlp.edu.ar.bithub.model.Commit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitRepository extends MongoRepository<Commit, ObjectId> {
}
