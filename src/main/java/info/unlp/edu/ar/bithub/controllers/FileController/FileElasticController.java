package info.unlp.edu.ar.bithub.controllers.FileController;

import info.unlp.edu.ar.bithub.model.File;
import info.unlp.edu.ar.bithub.services.CommitService;
import info.unlp.edu.ar.bithub.services.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/es/file")
public class FileElasticController {

    @Inject
    private FileService fileService;


    @GetMapping("/{content}")
    public List<File> getByContentFromElastic(@PathVariable String content){
        return fileService.getByContentFromElastic(content);
    }

    @GetMapping("/{includedContent}/{excludedContent}")
    public List<File> getByIncludedContentAndNotByExcludedContentFromElastic(@PathVariable String includedContent, @PathVariable String excludedContent){
        return fileService.getByIncludedContentAndNotByExcludedContentFromElastic(includedContent,excludedContent);
    }

}
