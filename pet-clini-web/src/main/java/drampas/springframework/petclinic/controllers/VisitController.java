package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.model.Pet;
import drampas.springframework.petclinic.model.Visit;
import drampas.springframework.petclinic.services.PetService;
import drampas.springframework.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class VisitController {
    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }
    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }
//    @ModelAttribute("visit")
//    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model){
//        Pet pet = petService.findById(petId);
//        model.put("pet",pet);
//        Visit visit=new Visit();
//        pet.getVisits().add(visit);
//        visit.setPet(pet);
//        return visit;
//    }
    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId,Model model){
        Pet pet = petService.findById(petId);
        Visit visit=new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        model.addAttribute("pet",pet);
        model.addAttribute("visit",visit);
        return "pets/visitForm";
    }
}
