package info.unlp.edu.ar.bithub.repositories.impl;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CustomMongoCommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class ICommitRepository implements CustomMongoCommitRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Commit> findByUserName(String name){
        LookupOperation lookup = Aggregation.lookup("user","author","_id", "user");
        MatchOperation match = Aggregation.match(new Criteria("user.name").is(name));

        Aggregation aggregation = Aggregation.newAggregation(lookup, match);
        AggregationResults<Commit> result = mongoTemplate.aggregate(aggregation, "commit", Commit.class);
        return result.getMappedResults();
    }

}
