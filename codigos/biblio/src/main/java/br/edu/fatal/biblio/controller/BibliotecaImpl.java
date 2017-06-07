/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.fatal.biblio.controller;

import br.edu.fatal.biblio.model.Aluno;
import br.edu.fatal.biblio.model.Atendente;
import br.edu.fatal.biblio.model.Emprestimo;
import br.edu.fatal.biblio.model.Exemplar;
import br.edu.fatal.biblio.model.Professor;
import br.edu.fatal.biblio.model.Publicacao;
import br.edu.fatal.biblio.model.Usuario;
import br.edu.fatal.biblio.repository.AtendenteRepository;
import br.edu.fatal.biblio.repository.EmprestimoRepository;
import br.edu.fatal.biblio.repository.ExemplarRepository;
import br.edu.fatal.biblio.repository.PublicacaoRepository;
import br.edu.fatal.biblio.repository.UsuarioRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ffmas
 */
@Service
public class BibliotecaImpl {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AtendenteRepository atendenteRepository;
    @Autowired
    PublicacaoRepository publicacaoRepository;
    @Autowired
    ExemplarRepository exemplarRepository;
    @Autowired
    EmprestimoRepository emprestimoRepository;

    public Emprestimo emprestarExemplares(Usuario locador, 
            Atendente atendente, List<Exemplar> exemplares) {
        Emprestimo emprestimo = new Emprestimo();
        
        emprestimo.setAtendente(atendente);
        emprestimo.setExemplares(new HashSet(exemplares));
        emprestimo.setLocador(locador);
        
        Date dataEmprestimo = new Date();
        Date dataDevolucao = null;
                
        if ((locador instanceof Aluno) || 
                (locador instanceof Atendente)) {
            dataDevolucao = adicionaDias(dataEmprestimo, 7);
        }
        if (locador instanceof Professor) {
            dataDevolucao = adicionaDias(dataEmprestimo, 15);
        }
        
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        locador.getEmprestimosRealizados().add(emprestimo);
        
        atendente.getEmprestimosIntermediados().add(emprestimo);
        
        emprestimoRepository.save(emprestimo);
        usuarioRepository.save(locador);
        usuarioRepository.save(atendente);
                
        for (Exemplar exemplar : exemplares) {
            exemplar.setDisponivel(Boolean.FALSE);
            exemplar.getEmprestimos().add(emprestimo);
            exemplarRepository.save(exemplar);
        }      
        
        return emprestimo;
    }
    
    private Date adicionaDias (Date data, int quant) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, quant);
        return c.getTime();
    }
    
}
