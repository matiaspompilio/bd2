package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository.UserRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Named
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private RestHighLevelClient client;

    public UserService(RestHighLevelClient client){
        this.client = client;
    }

    private UserRepository getUserRepository(){
        return this.userRepository;
    }

    public List<User> getAllUsersFromMongo() {
        return this.getUserRepository().findAll();
    }

    public List<User> getAllUsersFromElastic(){
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<User> getUserFromMongo(ObjectId id) {
        return this.getUserRepository().findById(id);
    }

    public Optional<User> getUserFromElastic(ObjectId id) {
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new MatchQueryBuilder("id",id));
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(String name, String email) {
        User user = new User(name, email);
        this.getUserRepository().save(user);
    }
}
