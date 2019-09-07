package info.unlp.edu.ar.bithub.repositories;

import info.unlp.edu.ar.bithub.model.File;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, ObjectId> {

    public File findByName(String name);
}