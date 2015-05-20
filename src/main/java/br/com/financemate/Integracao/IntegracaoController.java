/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Integracao;

import br.com.financemate.model.Cliente;
import br.com.financemate.model.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wolverine
 */
public class IntegracaoController {
    
    IntegracaoFacade integracaoFacade;
    
    public List<Viewvendasintegracao> consultarVendas() {
        integracaoFacade = new IntegracaoFacade();
        try {
            return integracaoFacade.consultarVendas();
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro consultar vendas " + ex);
            return null;
        }
    }
    
    public List<SParcelamentopagamento> listarParcelmanetoFormaPagamento(int idFormaPagamento) {
        integracaoFacade = new IntegracaoFacade();
        try {
            return integracaoFacade.listarParcelmanetoFormaPagamento(idFormaPagamento);
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro consultar Forma de Parcelamento " + ex);
            return null;
        }
    }
    
    public Produto consultarProduto(int idCliente, int codigosystm) {
        integracaoFacade = new IntegracaoFacade();
        try {
            return integracaoFacade.consultarProduto(idCliente, codigosystm);
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Cliente consultarClietne(int codigosystm) {
        integracaoFacade = new IntegracaoFacade();
        try {
            return integracaoFacade.consultarClietne(codigosystm);
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Orcamentoprodutosorcamento consultarOrcamentoProdutoOrcamento(int idOrcamento, int idProdutoOrcamento) {
        integracaoFacade = new IntegracaoFacade();
        try {
            return integracaoFacade.consultarOrcamentoProdutoOrcamento(idOrcamento, idProdutoOrcamento);
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro consultar produto orçamento " + ex);
            return null;
        }
    }
    
    public void salvarVendaSysTM(int idVenda) {
        integracaoFacade = new IntegracaoFacade();
        try {
            integracaoFacade.salvarVendaSysTM(idVenda);
        } catch (IOException ex) {
            Logger.getLogger(IntegracaoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro salvar venda SysTM " + ex);
        }
    }
    
}
