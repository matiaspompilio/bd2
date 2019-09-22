package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository.ElasticUserRepository;
import info.unlp.edu.ar.bithub.repositories.UserRepository.MongoUserRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Named
public class UserService {

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Inject
    private ElasticUserRepository elasticUserRepository;


    public UserService(){}

    public List<User> getAllUsersFromMongo() {
        return this.mongoUserRepository.findAll();
    }

    public List<User> getAllUsersFromElastic(){
        return this.elasticUserRepository.getAllUsersFromElastic();
    }

    public Optional<User> getUserFromMongo(ObjectId id) {
        return this.mongoUserRepository.findById(id);
    }

    public Optional<User> getUserFromElastic(ObjectId id) {
        return this.elasticUserRepository.getUserFromElastic(id);
    }

    public User addUser(String name, String email) {
        User user = new User(name, email);
        this.mongoUserRepository.save(user);
        return user;
    }

}
