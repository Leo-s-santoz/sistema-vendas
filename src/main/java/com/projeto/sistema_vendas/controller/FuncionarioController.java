package com.projeto.sistema_vendas.controller;

import com.projeto.sistema_vendas.models.Funcionario;
import com.projeto.sistema_vendas.repository.CidadeRepository;
import com.projeto.sistema_vendas.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView modelAndView = new ModelAndView("administrativo/funcionario/cadastro");
        modelAndView.addObject("funcionario", funcionario);
        modelAndView.addObject("listaCidades", cidadeRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/listaFuncionario")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("administrativo/funcionario/lista");
        modelAndView.addObject("listaFuncionario", funcionarioRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return cadastrar(funcionario.get());
    }

    @GetMapping("/excluirFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        funcionarioRepository.delete(funcionario.get());
        return new ModelAndView("/redirect:listaFuncionario");
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(funcionario);
        }
        funcionarioRepository.save(funcionario);
        return new ModelAndView("/redirect:listaFuncionario");
    }
}
