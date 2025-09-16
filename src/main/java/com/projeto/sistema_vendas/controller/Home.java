package com.projeto.sistema_vendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/administrativo")
    public String loadHome(){
        return "administrativo/home";
    }
}
