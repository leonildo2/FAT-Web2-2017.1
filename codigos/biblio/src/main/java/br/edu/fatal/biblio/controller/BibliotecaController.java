/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.controller;

import br.edu.fatal.biblio.model.Atendente;
import br.edu.fatal.biblio.model.Emprestimo;
import br.edu.fatal.biblio.model.Exemplar;
import br.edu.fatal.biblio.model.Publicacao;
import br.edu.fatal.biblio.model.Usuario;
import br.edu.fatal.biblio.repository.AtendenteRepository;
import br.edu.fatal.biblio.repository.ExemplarRepository;
import br.edu.fatal.biblio.repository.PublicacaoRepository;
import br.edu.fatal.biblio.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ffmas
 */
@Controller
public class BibliotecaController {
    
    @Autowired BibliotecaImpl bibliotecaImpl;
    @Autowired UsuarioRepository usuarioRepository;
    @Autowired AtendenteRepository atendenteRepository;
    @Autowired PublicacaoRepository publicacaoRepository; 
    @Autowired ExemplarRepository exemplarRepository;
    
    // emprestar exemplares
    // passa identificadores para atendente, usuário (locador) e 
    // número de tombos de publicacões a serem emprestadas
    
    @RequestMapping(path = "emprestarExemplares/{idLocador}/{idAtendente}/{numsTombo}")
    public @ResponseBody String emprestarExemplares (
            @PathVariable(name = "idLocador") Long idLocador, 
            @PathVariable(name = "idAtendente") Long idAtendente, 
            @PathVariable(name = "numsTombo") List<String> numsTombo) {
        
        // validação e preparar entrada para a implementação
        
        Usuario locador = usuarioRepository.findOne(idLocador);
        
        if (locador == null) {
            return "Usuário não encontrado para id: " + idLocador; 
        }
                
        Atendente atendente = atendenteRepository.findOne(idAtendente);
        
        if (atendente == null) {
            return "Atendente não encontrado para id: " + idAtendente;
        }
        
        List<Publicacao> publicacoes = new ArrayList();
        for (String numTombo : numsTombo) {
            Publicacao p = publicacaoRepository.
                    retornarPublicacaoPeloNumTombo(numTombo);
            if (p == null) {
                return "Não existe publicação para o número de tombo: " +
                        numTombo;
            }            
            publicacoes.add(p);
        }
        // implementação
        
        List<Exemplar> exemplaresASeremEmprestados = new ArrayList();
        
        for (Publicacao p : publicacoes) {
//            Set<Exemplar> exemplares = p.getExemplares();
//            Exemplar exemplarDisponivel = null;
//            for (Exemplar e : exemplares) {
//                if (e.getDisponivel()) {
//                    exemplarDisponivel = e;
//                }
//            }

            List<Exemplar> exemplaresDisponiveis
                    = exemplarRepository.
                    recuperarExemplarDisponivelParaPublicacao(p);

            if (exemplaresDisponiveis.isEmpty()) {
                return "Exemplar disponível não "
                        + "encontrado para a publicação: " + p.getNumTombo();
            }
            exemplaresASeremEmprestados.add(exemplaresDisponiveis.get(0));
        }
        
        Emprestimo emprestimo = bibliotecaImpl.emprestarExemplares(
                locador, atendente, exemplaresASeremEmprestados);
        
        return "Emprestimo realizado com id: " + emprestimo.getId();
        
    }
    
}
