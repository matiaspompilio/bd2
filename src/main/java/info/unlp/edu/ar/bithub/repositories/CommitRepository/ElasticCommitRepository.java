package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import com.google.gson.Gson;
import info.unlp.edu.ar.bithub.model.Commit;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
public class ElasticCommitRepository {

    private Gson gson = new Gson();

    @Autowired
    private RestHighLevelClient client;

    public List<Commit> getAllCommitsFromAuthorFromElastic(ObjectId id){
        SearchRequest searchRequest = new SearchRequest("bd2.commit");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("author.$id",id.toString()));
        searchRequest.source(searchSourceBuilder.size(1000));
        SearchResponse searchResponse;
        List<Commit> commits = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit:hits) {
                String json = hit.getSourceAsString();
                commits.add(gson.fromJson(json, Commit.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commits;
    }
}
