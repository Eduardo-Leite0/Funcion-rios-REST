package com.funcionarios.rest.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.funcionarios.rest.Model.Funcionarios;
@Repository

public interface FuncionariosRepositorio extends JpaRepository<Funcionarios , Long>{
	

}
