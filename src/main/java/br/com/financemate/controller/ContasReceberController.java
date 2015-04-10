/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


import br.com.financemate.facade.ContasReceberFacade;
import br.com.financemate.model.Contasreceber;
import br.com.financemate.modelView.Viewcontasreceber;
import br.com.financemate.modelView.Viewcontasreceberfluxo;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class ContasReceberController {
    
    ContasReceberFacade contasReceberFacade;
    
    public Contasreceber salvar(Contasreceber conta) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            return contasReceberFacade.salvar(conta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Contas Receber " + ex);
            return null;
        }
    }
    
    public List<Contasreceber> listar(String sql) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            return contasReceberFacade.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Contas Receber " + ex);
            return null;
        }
    }
    
    public Contasreceber consultar(int idConta) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            return contasReceberFacade.consultar(idConta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Contas Receber " + ex);
            return null;
        }
    }
    
    public void excluir(int idConta) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            contasReceberFacade.excluir(idConta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Excluir Contas Receber " + ex);
        }
    }
    
    public Contasreceber consultarVendaFornecedor(int idVenda) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            return contasReceberFacade.consultarVendaFornecedor(idVenda);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Contas Receber " + ex);
            return null;
        }
    }
    
    public List<Viewcontasreceberfluxo> listaFluxo(String sql) {
        contasReceberFacade = new ContasReceberFacade();
        try {
            return contasReceberFacade.listaFluxo(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Fluxo Contas Receber " + ex);
            return null;
        }
    }
    
}
