package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Pet;
import drampas.springframework.petclinic.model.PetType;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.PetService;
import drampas.springframework.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;


@Controller
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }
    @ModelAttribute("types")
    public Set<PetType> getPetTypes(){
        return petTypeService.findAll();
    }
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }
    @RequestMapping("{ownerId}/pets/new")
    public String getPetForm(Owner owner, Model model){
        Pet pet=new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return "pets/petForm";
    }
    @PostMapping("{ownerId}/pets/new")
    public String addPet(Model model,Owner owner,Pet pet){
        petService.save(pet);
        model.addAttribute("owner",owner);
        return "redirect:/"+owner.getId();
    }
}
