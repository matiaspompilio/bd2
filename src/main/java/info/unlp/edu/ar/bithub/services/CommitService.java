package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CommitRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

@Named
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    private RestHighLevelClient client;

    public CommitService(RestHighLevelClient client) {this.client=client;}

    private CommitRepository getCommitRepository(){ return this.commitRepository;}

    public List<Commit> getAllCommitsFromMongo(){ return this.getCommitRepository().findAll();}

    public void addCommit(String message, String hash, User author, List<File> files){
        Commit commit=new Commit(message,hash,author,files);
        this.getCommitRepository().save(commit);
    }

}
