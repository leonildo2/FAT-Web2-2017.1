/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.repository;

import br.edu.fatal.biblio.model.Exemplar;
import br.edu.fatal.biblio.model.Publicacao;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ffmas
 */
public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {
    
    @Query("SELECT e FROM Exemplar e WHERE e.publicacao = :publicacao AND e.disponivel = true")
    public List<Exemplar> recuperarExemplarDisponivelParaPublicacao(
            @Param("publicacao") Publicacao p);
    
}
