/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;


import br.com.financemate.facade.BancoFacade;
import br.com.financemate.model.Banco;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class BancoController {
    
    BancoFacade bancoFacade;
    
    public Banco salvar(Banco banco) {
        bancoFacade = new BancoFacade();
        try {
            return bancoFacade.salvar(banco);
        } catch (SQLException ex) {
            Logger.getLogger(BancoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Banco " + ex);
            return null;
        }
    }
    
    public List<Banco> listar(int idCliente){
        bancoFacade = new BancoFacade();
        try {
            return bancoFacade.listar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(BancoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Banco " + ex);
            return null;
        }
    }
    
    public Banco consultar(int idBanco) {
        bancoFacade = new BancoFacade();
        try {
            return bancoFacade.consultar(idBanco);
        } catch (SQLException ex) {
            Logger.getLogger(BancoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Banco " + ex);
            return null;
        }
    }
    
    public Banco consultar(int idCliente, String nome) {
        bancoFacade = new BancoFacade();
        try {
            return bancoFacade.consultar(idCliente, nome);
        } catch (SQLException ex) {
            Logger.getLogger(BancoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Banco " + ex);
            return null;
        }
    }
    
}
