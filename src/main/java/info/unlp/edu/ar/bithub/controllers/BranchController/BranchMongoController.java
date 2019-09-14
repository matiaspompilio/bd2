package info.unlp.edu.ar.bithub.controllers.BranchController;

import info.unlp.edu.ar.bithub.model.Branch;
import info.unlp.edu.ar.bithub.services.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/mongo/branch")
public class BranchMongoController {

    @Inject
    private BranchService branchService;

    private BranchService getBranchService(){
        return this.branchService;
    }

    @GetMapping
    public List<Branch> getAllUsers() {
        return this.getBranchService().getAllBranchesFromMongo();
    }

    @PostMapping
    public ResponseEntity<?> addBranch(@RequestParam(value = "name")String name) {
        ResponseEntity<?> response = null;
        try {
            this.getBranchService().addBranch(name);
            response = ResponseEntity.status(200).build();
        } catch(Exception e1){
            response = ResponseEntity.status(500).body("Hubo un error al insertar el branch");
        }
        return response;
    }
}
