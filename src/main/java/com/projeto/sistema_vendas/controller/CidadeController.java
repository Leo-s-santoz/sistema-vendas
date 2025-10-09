package com.projeto.sistema_vendas.controller;

import com.projeto.sistema_vendas.models.Cidade;
import com.projeto.sistema_vendas.repository.CidadeRepository;
import com.projeto.sistema_vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroCidade")
    public ModelAndView cadastrar(Cidade cidade) {
        ModelAndView modelAndView = new ModelAndView("administrativo/cidades/cadastro");
        modelAndView.addObject("cidade", cidade);
        modelAndView.addObject("listaEstados", estadoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/listaCidade")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("administrativo/cidades/lista");
        modelAndView.addObject("listaCidades", cidadeRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cadastrar(cidade.get());
    }

    @GetMapping("/excluirCidade/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        cidadeRepository.delete(cidade.get());
        return new ModelAndView("redirect:/listaCidade");
    }

    @PostMapping("/salvarCidade")
    public ModelAndView salvar(Cidade cidade, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(cidade);
        }
        cidadeRepository.save(cidade);
        return new ModelAndView("redirect:/listaCidade");
    }
}
