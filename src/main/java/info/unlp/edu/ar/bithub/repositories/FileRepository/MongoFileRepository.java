package info.unlp.edu.ar.bithub.repositories.FileRepository;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MongoFileRepository extends MongoRepository<File, ObjectId>{

    public List<File> findByContentRegex(String content, Pageable pageable);

    @Query("{'content' : { $regex: :#{#incl}, $not: { $regex: :#{#excl} } }}")
    List<File> getByIncludedContentAndNotByExcludedContentFromMongo(@Param("incl") String includedContent,@Param("excl") String excludedContent, Pageable pageable);

    List<File> findAllBy(TextCriteria criteria, Pageable pageable);
}
