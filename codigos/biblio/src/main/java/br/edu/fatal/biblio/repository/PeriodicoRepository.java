/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.repository;

import br.edu.fatal.biblio.model.Livro;
import br.edu.fatal.biblio.model.Periodico;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ffmas
 */
public interface PeriodicoRepository extends CrudRepository<Periodico, Long>{
    
}
