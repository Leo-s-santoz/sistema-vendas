package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {

    @GetMapping("/administrativo")
    public String loadHome(){
        return "administrativo/home";
    }
}
