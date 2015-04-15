/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.controller;

import br.com.financemate.facade.NomeArquivoFacade;
import br.com.financemate.model.Nomearquivo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wolverine
 */
public class NomeArquivoController {
    
    NomeArquivoFacade nomeArquivoFacade;
    
    public Nomearquivo salvar(Nomearquivo nomeArquivo){
        nomeArquivoFacade = new NomeArquivoFacade();
        try {
            return nomeArquivoFacade.salvar(nomeArquivo);
        } catch (SQLException ex) {
            Logger.getLogger(NomeArquivoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Salvar Nome Arquivo " +  ex);
            return null;
        }
    }
            
    
}
