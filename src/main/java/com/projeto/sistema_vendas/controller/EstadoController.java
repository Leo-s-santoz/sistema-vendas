package com.projeto.sistema_vendas.controller;

import com.projeto.sistema_vendas.models.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.projeto.sistema_vendas.repository.EstadoRepository;

import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado){
        ModelAndView modelAndView = new ModelAndView("administrativo/estados/cadastro");
        modelAndView.addObject("estado", estado);
        return modelAndView;
    }

    @GetMapping("/listaEstado")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("/administrativo/estados/lista");
        modelAndView.addObject("listaEstados", estadoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Estado> estado = estadoRepository.findById(id);
        return cadastrar(estado.get());
    }

    @GetMapping("/excluirEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        estadoRepository.delete(estado.get());
        return listar();
    }
    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(estado);
        }
        estadoRepository.save(estado);
        return listar();
    }
}
