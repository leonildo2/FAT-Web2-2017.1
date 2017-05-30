/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.repository;

import br.edu.fatal.biblio.model.Livro;
import br.edu.fatal.biblio.model.Publicacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ffmas
 */
public interface PublicacaoRepository extends CrudRepository<Publicacao, Long> {
    
    @Query("SELECT p FROM Publicacao p WHERE p.numTombo = :numTombo ")
    public Publicacao retornarPublicacaoPeloNumTombo(
            @Param("numTombo") String numTombo);
    
}
