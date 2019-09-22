package info.unlp.edu.ar.bithub.controllers.CommitController;

import info.unlp.edu.ar.bithub.model.Commit;
import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.services.CommitService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/es/commit")
public class CommitElasticController {

    @Inject
    private CommitService commitService;

    @GetMapping("/{id}")
    public List<Commit> getAllCommitsFromAuthorFromElastic (@PathVariable ObjectId id){
        return this.commitService.getAllCommitsFromAuthorFromElastic(id);
    }

    @GetMapping("/files/{id}")
    public List<File> getAllFilesFromAuthor (@PathVariable ObjectId id){
        return commitService.getAllFilesFromAuthorFromElastic(id);
    }
}
