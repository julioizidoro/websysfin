/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;



import br.com.financemate.facade.VendasFacade;
import br.com.financemate.model.Emissaonota;
import br.com.financemate.model.Vendas;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wolverine
 */
public class VendasController {

    public VendasController() {
    }
    
    
    
    VendasFacade vendasFacade;
    
    public Vendas salvar(Vendas venda) {
        vendasFacade = new VendasFacade();
        try {
            return vendasFacade.salvar(venda);
        } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Salvar Vendas");
            return null;
        }
    }
    
    public List<Vendas> listar(String sql) {
        vendasFacade = new VendasFacade();
        try {
            return vendasFacade.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Listar Vendas");
            return null;
        }
    }
    
    public Vendas consultar(int idVenda) {
        vendasFacade = new VendasFacade();
        try {
            return vendasFacade.consultar(idVenda);
        } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Consultar Vendas");
            return null;
        }
    }
    
    public void Excluir(int idVendas) {
        vendasFacade = new VendasFacade();
        try {
            vendasFacade.Excluir(idVendas);
        } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Excluir Vendas");
        }
    }
    
    public Emissaonota salvar(Emissaonota emissaonota){
        vendasFacade = new VendasFacade();
        try {
            return vendasFacade.salvar(emissaonota);
        } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Salvar Emissão");
            return null;
        }
    }
    
    public Emissaonota getEmissao(int idVendas){
        vendasFacade = new VendasFacade();
        try {
            return vendasFacade.getEmissao(idVendas);
         } catch (SQLException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, " Erro Consultar Emissão");
            return null;
        }
    }
    
}
