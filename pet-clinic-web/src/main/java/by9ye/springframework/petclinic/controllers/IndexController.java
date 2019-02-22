package by9ye.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index"; // look for a templates called index
    }

    @RequestMapping("/oups")
    public String oupsHandler() {
        return "notImplemented";
    }
}
