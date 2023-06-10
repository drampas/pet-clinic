package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.model.Owner;
import drampas.springframework.petclinic.model.Pet;
import drampas.springframework.petclinic.model.PetType;
import drampas.springframework.petclinic.services.OwnerService;
import drampas.springframework.petclinic.services.PetService;
import drampas.springframework.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@RequestMapping("{ownerId}")
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
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    @RequestMapping("/pets/new")
    public String getPetForm(Owner owner, Model model){
        Pet pet=new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return "pets/petForm";
    }
    @PostMapping("/pets/new")
    public String addPet(Owner owner,Pet pet){
        owner.getPets().add(pet);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/"+owner.getId();
    }
    @GetMapping("/pets/{petId}/edit")
    public String getPopulatedPetForm(@PathVariable Long petId,Model model,Owner owner){
        Pet pet=petService.findById(petId);
        model.addAttribute("pet",pet);
        return "pets/petForm";
    }
    @PostMapping("/pets/{petId}/edit")
    public String updatePet(Owner owner,Pet pet){
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/"+owner.getId();
    }
}
