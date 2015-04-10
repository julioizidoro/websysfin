/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


import br.com.financemate.facade.PlanoContasFacade;
import br.com.financemate.model.Planocontas;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class PlanoContasController {
    
    PlanoContasFacade planoContasFacade;
    
    public Planocontas salvar(Planocontas plano) {
        planoContasFacade = new PlanoContasFacade();
        try {
            return planoContasFacade.salvar(plano);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Plano de Contas "+ ex);
            return null;
        }
    }
    
    public List<Planocontas> listar() {
        planoContasFacade = new PlanoContasFacade();
        try {
            return planoContasFacade.listar();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Plano de Contas "+ ex);
            return null;
        }
    }
    
    public Planocontas consultar(int idPlanoContas) {
        planoContasFacade = new PlanoContasFacade();
        try {
            return planoContasFacade.consultar(idPlanoContas);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Plano de Contas "+ ex);
            return null;
        }
    }
    
    public List<Planocontas> listar(String descricao) {
        planoContasFacade = new PlanoContasFacade();
        try {
            return planoContasFacade.listar(descricao);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Plano de Contas "+ ex);
            return null;
        }
    }
    
}
