package info.unlp.edu.ar.bithub.repositories.FileRepository;

import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import info.unlp.edu.ar.bithub.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.util.List;

public class ICustomMongoFileRepository implements CustomMongoFileRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<File> getByIncludedContentAndNotByExcludedContentFromMongo(String includedContent, String excludedContent) {
        TextQuery textQuery = TextQuery.queryText(new TextCriteria().matchingAny(includedContent)).sortByScore();
        List<File> result = mongoTemplate.find(textQuery, File.class, "file");
        return result;
    }
}
