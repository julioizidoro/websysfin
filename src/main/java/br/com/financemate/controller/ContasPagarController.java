/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;



import br.com.financemate.facade.ContasPagarFacade;
import br.com.financemate.model.Arquivocontaspagar;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.modelView.Viewcontaspagar;
import br.com.financemate.modelView.Viewcontaspagarfluxo;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wolverine
 */
public class ContasPagarController {
    
    ContasPagarFacade contasPagarFacade;
    
    public Contaspagar salvar(Contaspagar conta) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.salvar(conta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro salvar contas pagar "  + ex);
            return null;
        }
    }
    
    public List<Contaspagar> listar(String sql) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro listar contas pagar "  + ex);
            return null;
        }
    }
    
    public List<Contaspagar> listarContas(String sql) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.listarContas(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro listar contas pagar "  + ex);
            return null;
        }
    }
    
    public Contaspagar consultar(int idConta) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.consultar(idConta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro consultar contas pagar "  + ex);
            return null;
        }
    }
    
    public void excluir(int idConta) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            contasPagarFacade.excluir(idConta);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro excluir contas pagar "  + ex);
        }
    }
    
    public Contaspagar consultarVenda(String sql) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.consultarVenda(sql);
        
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro consultar contas a pagar"  + ex);
            return null;
        }
    }
    
    public void salvarArquivo(Arquivocontaspagar arquivo){
        contasPagarFacade = new ContasPagarFacade();
        try {
            contasPagarFacade.salvarArquivo(arquivo);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Arquivo " + ex);
        }
    }
    
    public Arquivocontaspagar consultarArquivo(int idContasPagar) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.consultarArquivo(idContasPagar);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Arquivo " + ex);
            return null;
        }
    }
    
    public void excluirArquivo(int idArquivo) {
        contasPagarFacade = new ContasPagarFacade();
        try {
            contasPagarFacade.excluirArquivo(idArquivo);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Excluir Arquivo " + ex);
        }
    }
    
    public List<Viewcontaspagarfluxo> listaFluxo(String sql){
        contasPagarFacade = new ContasPagarFacade();
        try {
            return contasPagarFacade.listaFluxo(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Fluxo Caixa Contas Pagar " + ex);
            return null;
        }
    }
}
