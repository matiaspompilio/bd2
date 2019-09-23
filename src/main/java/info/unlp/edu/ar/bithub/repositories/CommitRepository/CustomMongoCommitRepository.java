package info.unlp.edu.ar.bithub.repositories.CommitRepository;

import info.unlp.edu.ar.bithub.model.Commit;

import java.util.List;

public interface CustomMongoCommitRepository {

    List<Commit> findByUserName(String name);

}
