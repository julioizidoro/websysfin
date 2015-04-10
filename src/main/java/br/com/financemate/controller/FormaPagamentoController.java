/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


//
import br.com.financemate.facade.FormaPagamentoFacade;
import br.com.financemate.model.Formapagamento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class FormaPagamentoController {
    
    FormaPagamentoFacade formaPagamentoFacade;
    
    public Formapagamento salvar(Formapagamento formaPagamento) {
        formaPagamentoFacade = new FormaPagamentoFacade();
        try {
            return formaPagamentoFacade.salvar(formaPagamento);
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Forma de Pagamento "+ ex);
            return null;
        }
    }
    
    public void Excluir(int idFormaPagamento) {
        formaPagamentoFacade = new FormaPagamentoFacade();
        try {
            formaPagamentoFacade.Excluir(idFormaPagamento);
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Excluir Forma de Pagamento "+ ex);
        }
    }
    
    public List<Formapagamento> listar(int idVenda) {
        formaPagamentoFacade = new FormaPagamentoFacade();
        try {
            return formaPagamentoFacade.listar(idVenda);
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Forma de Pagamento "+ ex);
            return null;
        }
    }
    
}
