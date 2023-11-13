package com.funcionarios.rest.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.funcionarios.rest.Model.Funcionarios;
import com.funcionarios.rest.Repositorio.FuncionariosRepositorio;

@RestController
@RequestMapping("/Funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionariosRepositorio Funcrep;

    @GetMapping
    public List<Funcionarios> listar() {
        return Funcrep.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionarios adicionar(@RequestBody Funcionarios funcionarios) {
    	return Funcrep.save(funcionarios);
    }
}
