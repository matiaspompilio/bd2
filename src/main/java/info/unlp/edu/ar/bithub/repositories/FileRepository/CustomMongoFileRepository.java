package info.unlp.edu.ar.bithub.repositories.FileRepository;

import info.unlp.edu.ar.bithub.model.File;

import java.util.List;

public interface CustomMongoFileRepository {

    List<File> getByIncludedContentAndNotByExcludedContentFromMongo(String includedContent, String excludedContent);

}
