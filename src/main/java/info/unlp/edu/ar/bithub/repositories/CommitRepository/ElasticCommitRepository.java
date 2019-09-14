package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import info.unlp.edu.ar.bithub.model.Commit;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticCommitRepository extends ElasticsearchRepository<Commit, ObjectId> {
}
