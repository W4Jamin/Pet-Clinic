package by9ye.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets", "/vets/index.html", "/vets/index"})
    public String listVets() {
        return "vets/index";
    }
}