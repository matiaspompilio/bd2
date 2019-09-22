package info.unlp.edu.ar.bithub.services;

import com.google.gson.Gson;
import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository.UserRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Named
public class UserService {

    private Gson gson = new Gson();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestHighLevelClient client;

    public UserService(){}

    public UserService(RestHighLevelClient client){
        this.client = client;
    }

    public List<User> getAllUsersFromMongo() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersFromElastic(){
        SearchRequest searchRequest = new SearchRequest("bd2.user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder.size(1000));
        SearchResponse searchResponse;
        List<User> users = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits) {
                String json = hit.getSourceAsString();
                users.add(gson.fromJson(json, User.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Optional<User> getUserFromMongo(ObjectId id) {
        return this.userRepository.findById(id);
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

    public User addUser(String name, String email) {
        User user = new User(name, email);
        this.userRepository.save(user);
        return user;
    }

}
