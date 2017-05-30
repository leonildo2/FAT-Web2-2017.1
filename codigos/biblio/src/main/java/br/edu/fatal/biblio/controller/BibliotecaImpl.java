/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.controller;

import br.edu.fatal.biblio.model.Atendente;
import br.edu.fatal.biblio.model.Emprestimo;
import br.edu.fatal.biblio.model.Publicacao;
import br.edu.fatal.biblio.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ffmas
 */
@Service
public class BibliotecaImpl {

    public Emprestimo emprestarExemplares(Usuario locador, 
            Atendente atendente, List<Publicacao> publicacoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
