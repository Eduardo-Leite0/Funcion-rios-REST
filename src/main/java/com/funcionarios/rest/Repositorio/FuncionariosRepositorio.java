package com.funcionarios.rest.Repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.funcionarios.rest.Model.Funcionarios;

public interface FuncionariosRepositorio extends JpaRepository<Funcionarios, Long> {
    List<Funcionarios> findByAtivoTrue();
    Optional<Funcionarios> findByIdAndAtivoTrue(Long id);
    List<Funcionarios> findByAtivoFalse(); // buscar funcionários inativos
}
