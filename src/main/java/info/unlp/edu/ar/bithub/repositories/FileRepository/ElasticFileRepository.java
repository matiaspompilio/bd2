package info.unlp.edu.ar.bithub.repositories.FileRepository;

import info.unlp.edu.ar.bithub.model.File;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticFileRepository extends ElasticsearchRepository<File, ObjectId> {
}
