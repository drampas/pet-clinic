package drampas.springframework.petclinic.controllers;

import drampas.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners/index","/owners/index.html","/owners","/owners/find"})
    public String ownerList(Model model){

        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
}
