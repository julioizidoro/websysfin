/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


import br.com.financemate.facade.ProdutoFacade;
import br.com.financemate.model.Produto;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class ProdutoController {
    
    ProdutoFacade produtoFacade;
    
    public List<Produto> listar(int idCliente) {
        produtoFacade = new ProdutoFacade();
        try {
            return produtoFacade.listar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Produto " + ex);
            return null;
        }
    }
    
    public Produto consultar(int idProduto) {
        produtoFacade = new ProdutoFacade();
        try {
            return produtoFacade.consultar(idProduto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Produto " + ex);
            return null;
        }
    }
    
    public Produto salvar(Produto produto) {
        produtoFacade = new ProdutoFacade();
        try {
            return produtoFacade.salvar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Produto " + ex);
            return null;
        }
    }
    
}
