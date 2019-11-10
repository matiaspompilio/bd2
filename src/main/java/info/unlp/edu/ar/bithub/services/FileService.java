package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.repositories.FileRepository.ElasticFileRepository;
import info.unlp.edu.ar.bithub.repositories.FileRepository.MongoFileRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.util.List;

@Named
public class FileService {

    @Autowired
    private MongoFileRepository mongoFileRepository;

    @Autowired
    private ElasticFileRepository elasticFileRepository;

    private RestHighLevelClient client;

    public FileService(RestHighLevelClient client){
        this.client=client;
    }

    private MongoFileRepository getMongoFileRepository(){ return this.mongoFileRepository;}

    public List<File> getAllFilesFromMongo(){
        return this.getMongoFileRepository().findAll();
    }

    public List<File> getByContentFromMongo(String content){
        int timeStart = (int) System.currentTimeMillis();
        List<File> files = this.getMongoFileRepository().findByContentRegex(content, PageRequest.of(0, 100));
        int timeEnd = (int) System.currentTimeMillis();
        System.out.println("Se tardó: " + (timeEnd - timeStart) + " en mongo.");
        return files;
    }

    public File addFile(String content, String filename) {
        File file= new File(content,filename);
        this.mongoFileRepository.save(file);
        return file;
    }

    public List<File> getByContentFromElastic(String content){
        return this.elasticFileRepository.getByContentFromElastic(content);
    }

    public File save(File file){
        return this.mongoFileRepository.save(file);
    }

    public List<File> getByIncludedContentAndNotByExcludedContentFromElastic(String includedContent, String excludedContent){
        return this.elasticFileRepository.getByIncludedContentAndNotByExcludedContentFromElastic(includedContent,excludedContent);
    }

    public List<File> getByIncludedContentAndNotByExcludedContentFromMongo(String includedContent, String excludedContent) {
        return this.mongoFileRepository.getByIncludedContentAndNotByExcludedContentFromMongo(includedContent, excludedContent);
    }

    public List<File> getTextIncludedContentAndNotExcludedContentFromMongo(String includedContent, String excludedContent) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(includedContent).notMatching(excludedContent);
        List <File> files = this.mongoFileRepository.findAllBy(criteria);
        return files;
    }
}
