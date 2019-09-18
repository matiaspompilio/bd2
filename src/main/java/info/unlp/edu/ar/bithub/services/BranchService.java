package info.unlp.edu.ar.bithub.services;

import info.unlp.edu.ar.bithub.model.Branch;
import info.unlp.edu.ar.bithub.repositories.BranchRepository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

@Named
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    private BranchRepository getBranchRepository(){
        return this.branchRepository;
    }

    public List<Branch> getAllBranchesFromMongo(){
        return this.getBranchRepository().findAll();
    }

    public Branch addBranch(String name){
        Branch branch = new Branch(name);
        this.branchRepository.save(branch);
        return branch;
    }
}
