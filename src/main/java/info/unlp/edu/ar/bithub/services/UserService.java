package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.UserRepository.MongoUserRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Named
public class UserService {

    @Autowired
    private MongoUserRepository userRepository;

    private RestHighLevelClient client;

    public UserService(RestHighLevelClient client){
        this.client = client;
    }

    private MongoUserRepository getUserRepository(){
        return this.userRepository;
    }

    public List<User> getAllUser() {
        return this.getUserRepository().findAll();
    }

    public Optional<User> getUser(ObjectId id) {
        return this.getUserRepository().findById(id);
    }

    public void addUser(String name, String email) throws IOException {
        User user = new User(name, email);
        this.getUserRepository().save(user);
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "fer");
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();

        IndexRequest request = new IndexRequest("twitter").id(user.getId().toString());
        request.source(builder);
        client.index(request, RequestOptions.DEFAULT);

    }
}
