package com.funcionarios.rest.Controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.funcionarios.rest.Model.Funcionarios;  
import com.funcionarios.rest.Repositorio.FuncionariosRepositorio;  

@RestController
@RequestMapping("/Funcionarios")
public class FuncionariosController {

    @Autowired
    private FuncionariosRepositorio Funcrep;

    @GetMapping
    public List<Funcionarios> listar() {
        return Funcrep.findByAtivoTrue();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionarios adicionar(@RequestBody Funcionarios funcionarios) {
        validarFuncionario(funcionarios);
        return Funcrep.save(funcionarios);
    }

    @GetMapping("/{id}")
    public Funcionarios obterPorId(@PathVariable Long id) {
        return Funcrep.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com o ID: " + id));
    }

    @PutMapping("/{id}")
    public Funcionarios atualizar(@PathVariable Long id, @RequestBody Funcionarios funcionariosAtualizados) {
        validarFuncionario(funcionariosAtualizados);

        Funcionarios funcionarioExistente = Funcrep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com o ID: " + id));

        if (!funcionarioExistente.isAtivo()) {
            throw new IllegalArgumentException("Funcionário inativo encontrado com o ID: " + id);
        }

        if (funcionariosAtualizados.getSobrenome() != null) {
            funcionarioExistente.setSobrenome(funcionariosAtualizados.getSobrenome());
        }
        if (funcionariosAtualizados.getTelefone() != null) {
            funcionarioExistente.setTelefone(funcionariosAtualizados.getTelefone());
        }
        if (funcionariosAtualizados.getTelefoneEmergencia() != null) {
            funcionarioExistente.setTelefoneEmergencia(funcionariosAtualizados.getTelefoneEmergencia());
        }
        if (funcionariosAtualizados.getCargo() != null) {
            funcionarioExistente.setCargo(funcionariosAtualizados.getCargo());
        }
        if (funcionariosAtualizados.getTempoContrato() > 0) {
            funcionarioExistente.setTempoContrato(funcionariosAtualizados.getTempoContrato());
        }
        funcionarioExistente.setAtivo(true); // Define o funcionário como ativo

        return Funcrep.save(funcionarioExistente);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        Funcrep.findById(id).ifPresent(funcionario -> {
            funcionario.setAtivo(false);
            Funcrep.save(funcionario);
        });
    }

    private void validarFuncionario(Funcionarios funcionarios) {
        if (funcionarios == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.");
        }

        if (funcionarios.getNome() == null || funcionarios.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        if (funcionarios.getSobrenome() == null || funcionarios.getSobrenome().trim().isEmpty()) {
            throw new IllegalArgumentException("Sobrenome inválido.");
        }

        if (funcionarios.getCpf() == null || funcionarios.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        } else if (!validarCpf(funcionarios.getCpf())) {
            throw new IllegalArgumentException("CPF inválido. Deve ser digitado xxx.xxx.xxx-xx ou xxxxxxxxxxx.");
        }

        if (funcionarios.getIdade() <= 0) {
            throw new IllegalArgumentException("Idade deve ser maior que zero.");
        }

        if (funcionarios.getTelefone() == null || funcionarios.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio.");
        }

        if (funcionarios.getTelefoneEmergencia() == null || funcionarios.getTelefoneEmergencia().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone de emergência não pode ser nulo ou vazio.");
        }

        if (funcionarios.getCargo() == null || funcionarios.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo não pode ser nulo ou vazio.");
        }

        if (funcionarios.getTempoContrato() <= 0) {
            throw new IllegalArgumentException("Tempo de contrato deve ser maior que zero.");
        }
    }
    
    public List<Funcionarios> listarInativos() {
        return Funcrep.findByAtivoFalse(); // Retorna apenas funcionários inativos
    }

    @PutMapping("/restaurar/{id}")
    public Funcionarios restaurarFuncionario(@PathVariable Long id) {
        return Funcrep.findById(id)
                .map(funcionarioExistente -> {
                    funcionarioExistente.setAtivo(true); // Define o funcionário como ativo
                    return Funcrep.save(funcionarioExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com o ID: " + id));
    }

    private boolean validarCpf(String cpf) {
        String regexCpfFormatado = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        String regexCpfSemFormato = "\\d{11}";

        Pattern patternCpfFormatado = Pattern.compile(regexCpfFormatado);
        Pattern patternCpfSemFormato = Pattern.compile(regexCpfSemFormato);

        Matcher matcherCpfFormatado = patternCpfFormatado.matcher(cpf);
        Matcher matcherCpfSemFormato = patternCpfSemFormato.matcher(cpf);

        return matcherCpfFormatado.matches() || matcherCpfSemFormato.matches();
    }
}

