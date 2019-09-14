package info.unlp.edu.ar.bithub.repositories.BranchRepository;

import info.unlp.edu.ar.bithub.model.Branch;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoBranchRepository extends MongoRepository<Branch, ObjectId> {
}
