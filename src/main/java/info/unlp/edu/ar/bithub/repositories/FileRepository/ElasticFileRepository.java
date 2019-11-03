package info.unlp.edu.ar.bithub.repositories.FileRepository;

import com.google.gson.Gson;
import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
public class ElasticFileRepository {

    private Gson gson = new Gson();

    @Autowired
    private RestHighLevelClient client;

        public List<File> getByContentFromElastic(String content) {
        SearchRequest searchRequest = new SearchRequest("bd2.file");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new MatchQueryBuilder("content", content));
        searchRequest.source(searchSourceBuilder.size(10000));
        SearchResponse searchResponse;
        List<File> files = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                String json = hit.getSourceAsString();
                files.add(gson.fromJson(json, File.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


}
