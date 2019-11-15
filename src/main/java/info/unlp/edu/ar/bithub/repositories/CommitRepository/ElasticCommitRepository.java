package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import com.google.gson.Gson;
import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import org.bson.types.ObjectId;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
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
        searchSourceBuilder.query(QueryBuilders.termQuery("author",id.toString()));
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

    public List<Commit> getAllCommitsFromListOfAuthorsFromElastic(List<String> ids){
        SearchRequest searchRequest = new SearchRequest("bd2.commit");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new TermsQueryBuilder("author",ids));
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

    @Deprecated
    public int getAmountOfFilesFromElastic(){
        SearchRequest searchRequest = new SearchRequest("bd2.commit");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(AggregationBuilders
                .sum("files")
                .field("files")
                //TERMINAR ESTA QUERY muy bonita, javier si estás leyendo esto, dice mati que el cajón que le diste estaba rancio.
        );
        searchRequest.source(searchSourceBuilder.size(0));
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public List<File> getFilesByContentFromElastic(String content) {
//        SearchRequest searchRequest = new SearchRequest("bd2.commit");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(new TermQueryBuilder("file", content));
//        searchRequest.source(searchSourceBuilder.size(1000));
//        SearchResponse searchResponse;
//        List<Commit> commits = new ArrayList<>();
//        try {
//            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            SearchHit[] hits = searchResponse.getHits().getHits();
//            for (SearchHit hit : hits) {
//                String json = hit.getSourceAsString();
//                commits.add(gson.fromJson(json, Commit.class));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return commits;
//    }
}
