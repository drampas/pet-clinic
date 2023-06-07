package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
    @GetMapping("/owners/new")
    public String getOwnerForm(Model model){
        model.addAttribute("owner",new Owner());
        return "owners/ownerForm";
    }
    @PostMapping("/owners/new")
    public String addNewOwner(Owner owner){
        Owner savedOwner=ownerService.save(owner);
        return "redirect:/"+savedOwner.getId();
    }
    @GetMapping("/{ownerId}/edit")
    public String getPopulatedOwnerForm(@PathVariable Long ownerId, Model model){
        Owner owner=ownerService.findById(ownerId);
        model.addAttribute("owner",owner);
        return "owners/ownerForm";
    }
    @PostMapping("/{ownerId}/edit")
    public String updateOwner(@PathVariable Long ownerId,Owner owner){
        owner.setId(ownerId);
        Owner savedOwner=ownerService.save(owner);
        return "redirect:/"+savedOwner.getId();
    }
}
