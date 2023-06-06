package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners/index","/owners/index.html","/owners","/owners/find"})
    public String findOwners(Model model){
        model.addAttribute("owner",new Owner());
        return "owners/findOwners";
    }
    @GetMapping("/owners")
    public String findForm(Owner owner, Model model){
        if(owner.getLastName()==null){
            owner.setLastName("");
        }
        List<Owner> result= ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if(result.isEmpty()){
            return "owners/findOwners";
        } else if (result.size()==1) {
            owner = result.get(0);
            model.addAttribute("owner",owner);
            return "owners/ownerDetails";
        }else{
            model.addAttribute("owners",result);
            return "owners/index";
        }
    }
}
