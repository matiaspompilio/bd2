package info.unlp.edu.ar.bithub.repositories.FileRepository;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoFileRepository extends MongoRepository<File, ObjectId> {

    public List<File> findByContentRegex(String content, Pageable pageable);
}
