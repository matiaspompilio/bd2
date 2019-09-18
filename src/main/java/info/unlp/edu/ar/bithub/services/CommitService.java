package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CommitRepository;
import info.unlp.edu.ar.bithub.repositories.UserRepository.UserRepository;
import org.bson.types.ObjectId;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private UserRepository userRepository;

    private RestHighLevelClient client;

    public CommitService(RestHighLevelClient client) {this.client=client;}

    private CommitRepository getCommitRepository(){ return this.commitRepository;}

    public List<Commit> getAllCommitsFromMongo(){ return this.getCommitRepository().findAll();}

    public List<Commit> getAllCommitsFromAuthorFromMongo(ObjectId author){
        return this.getCommitRepository().findByAuthor(author);
    }

    public void addCommit(String message, String hash, ObjectId userId, List<File> files) throws Exception {
        Commit commit= new Commit(message,hash);
        Optional<User> author = this.userRepository.findById(userId);
        if(author.isPresent()){
            commit.setAuthor(author.get());
        }else {
            throw new Exception();
        }
        this.getCommitRepository().save(commit);
    }

    public Commit addCommit(String message, String hash, User author, List<File> files) {
        Commit commit= new Commit(message, hash, author, files);
        this.getCommitRepository().save(commit);
        return commit;
    }
}
