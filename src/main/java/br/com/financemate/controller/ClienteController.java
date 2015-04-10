/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;

import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.model.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class ClienteController {
    
    ClienteFacade clienteFacade;
    
    public Cliente salvar(Cliente cliente) {
        clienteFacade = new ClienteFacade();
        try {
            return clienteFacade.salvar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Cliente "+ ex);
            return null;
        }
    }
    
    public Cliente consultar(int idCliente) {
        clienteFacade = new ClienteFacade();
        try {
            return clienteFacade.consultar(idCliente);
       } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Cliente "+ ex);
            return null;
        }
    }
    
    public List<Cliente> listar(String nome) {
        clienteFacade = new ClienteFacade();
        try {
            return clienteFacade.listar(nome);
       } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Cliente "+ ex);
            return null;
        }
    }
    
}
