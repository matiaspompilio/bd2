package info.unlp.edu.ar.bithub.repositories.BranchRepository;

import info.unlp.edu.ar.bithub.model.Branch;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticBranchRepository extends ElasticsearchRepository<Branch, ObjectId> {
}
