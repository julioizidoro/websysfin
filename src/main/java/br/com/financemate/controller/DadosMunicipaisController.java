/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


import br.com.financemate.facade.DadosMunicipaisFacade;
import br.com.financemate.model.Dadosmunicipais;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class DadosMunicipaisController {
    
    DadosMunicipaisFacade dadosMunicipaisFacade;
    
    public Dadosmunicipais salvar(Dadosmunicipais dados) {
        dadosMunicipaisFacade = new DadosMunicipaisFacade();
        try {
            return dadosMunicipaisFacade.salvar(dados);
        } catch (SQLException ex) {
            Logger.getLogger(DadosMunicipaisController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Salvar Dados Municipais " + ex);
            return null;
        }
    }
    
    public Dadosmunicipais consultar(int idCliente) {
        dadosMunicipaisFacade = new DadosMunicipaisFacade();
        try {
            return dadosMunicipaisFacade.consultar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(DadosMunicipaisController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Consultar Dados Municipais " + ex);
            return null;
        }
    }
    
}
