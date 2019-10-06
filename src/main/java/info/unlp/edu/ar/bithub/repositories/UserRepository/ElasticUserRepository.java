package info.unlp.edu.ar.bithub.repositories.UserRepository;

import com.google.gson.Gson;
import info.unlp.edu.ar.bithub.model.User;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
public class ElasticUserRepository {

    private Gson gson = new Gson();

    @Autowired
    private RestHighLevelClient client;

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

    public Optional<User> getUserFromElastic(ObjectId id) {
        SearchRequest searchRequest = new SearchRequest("bd2.user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new TermQueryBuilder("_id",id.toString()));
        searchRequest.source(searchSourceBuilder.size(1));
        SearchResponse searchResponse;
        Optional<User> user = Optional.of(new User());
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit hit = searchResponse.getHits().getHits()[0];
            String json = hit.getSourceAsString();
            user = Optional.of(gson.fromJson(json, User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUsersByNameFromElastic(String name){
        SearchRequest searchRequest = new SearchRequest("bd2.user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new MatchQueryBuilder("name",name));
        searchRequest.source(searchSourceBuilder.size(1000));
        SearchResponse searchResponse;
        List<User> users = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits) {
                String json = hit.getSourceAsString();
                User user = gson.fromJson(json, User.class);
                ObjectId objId = new ObjectId(hit.getId());
                user.setId(objId);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
