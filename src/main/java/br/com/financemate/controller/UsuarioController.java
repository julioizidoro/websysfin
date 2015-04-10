/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Controller;



import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Wolverine
 */
public class UsuarioController {
    
    UsuarioFacade usuarioFacade;
    
    public Usuario salvar(Usuario usuario) {
        usuarioFacade = new UsuarioFacade();
        try {
            return usuarioFacade.salvar(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Usuário " + ex);
            return null;
        }
    }
    
    public Usuario consultar(String login, String senha) {
        usuarioFacade = new UsuarioFacade();
        try {
            return usuarioFacade.consultar(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Usuário " + ex);
            return null;
        }
    }
    
    public List<Usuario> listar(String nome) {
        usuarioFacade = new UsuarioFacade();
        try {
            return usuarioFacade.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Usuário " + ex);
            return null;
        }
    }
    
    public Usuario consultar(int idUsuario) {
        usuarioFacade = new UsuarioFacade();
        try {
            return usuarioFacade.consultar(idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Usuário " + ex);
            return null;
        }
    }
    
}
