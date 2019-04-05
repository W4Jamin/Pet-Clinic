package by9ye.springframework.petclinic.controllers;

import by9ye.springframework.petclinic.model.Pet;
import by9ye.springframework.petclinic.model.Visit;
import by9ye.springframework.petclinic.services.PetService;
import by9ye.springframework.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

//        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException{
//                setValue(LocalDate.parse(text));
//            }
//        });
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable String petId, Model model) {
        Pet pet = petService.findById(Long.valueOf(petId));
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        visit.setPet(pet);
        pet.getVisits().add(visit);
        model.addAttribute("visit", visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable String petId, Model model) {

        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/*/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }
        else {
            visitService.save(visit);
            return "redirect:/owners/" + visit.getPet().getOwner().getId();
        }
    }
}
