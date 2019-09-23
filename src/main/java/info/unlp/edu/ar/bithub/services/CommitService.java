package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.model.User;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.CommitRepository;
import info.unlp.edu.ar.bithub.repositories.CommitRepository.ElasticCommitRepository;
import info.unlp.edu.ar.bithub.repositories.UserRepository.MongoUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Named
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private UserService userService;

    @Inject
    private ElasticCommitRepository elasticCommitRepository;

    @Autowired
    private MongoUserRepository userRepository;

    private CommitRepository getCommitRepository(){ return this.commitRepository;}

    public List<Commit> getAllCommitsFromMongo(){ return this.getCommitRepository().findAll();}

    public List<Commit> getAllCommitsFromAuthorFromElastic(ObjectId id){
        return this.elasticCommitRepository.getAllCommitsFromAuthorFromElastic(id);
    }

    public List<Commit> getAllCommitsByUserNameFromElastic(String name){
        List<User> users = this.userService.getUsersByNameFromElastic(name);
        List<Commit> commits = new ArrayList<>();
        users.forEach(user -> commits.addAll(this.getAllCommitsFromAuthorFromElastic(user.getId())));
        return commits;
    }

    public List<File> getAllFilesFromAuthorFromElastic(ObjectId id){
        List<Commit> commits = this.getAllCommitsFromAuthorFromElastic(id);
        List<File> files = new ArrayList<>();
        for (Commit commit: commits) {
            if (commit.getFiles() != null) {
                files.addAll(commit.getFiles());
            }
        }
        return files;
    }

    public List<Commit> getAllCommitsFromAuthorFromMongo(ObjectId author){
        return this.getCommitRepository().findByAuthor(author);
    }

    public List<File> getAllFilesFromAuthorFromMongo(ObjectId author){
        List<Commit> commits= this.getCommitRepository().findByAuthor(author);
        List<File> files= new java.util.ArrayList<>(Collections.emptyList());
        commits.forEach((commit -> {
            files.addAll(commit.getFiles());
        }));
        return files;
    }
    /*
    Este método no anda porque el commit no tiene los datos del author ya que lo carga recién cuando lo mapea y antes sólo tiene el objectId porque está
    con la annotation  @DbRef
    public List<Commit> getAllCommitsByAuthorsNameFromMongo(String name){
        return this.getCommitRepository().findByAuthor_Name(name);
    }

     */

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
