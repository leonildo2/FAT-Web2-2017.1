/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.repository;

import br.edu.fatal.biblio.model.Aluno;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ffmas
 */
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    
}
