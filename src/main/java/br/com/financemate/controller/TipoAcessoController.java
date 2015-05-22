/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.controller;

import br.com.financemate.facade.TipoAcessoFacade;
import br.com.financemate.model.Tipoacesso;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Kamila
 */
public class TipoAcessoController {
    
    TipoAcessoFacade tipoAcessoFacade;
    
    public Tipoacesso salvar(Tipoacesso tipoacesso) {
        tipoAcessoFacade = new TipoAcessoFacade();
        try {
            return tipoAcessoFacade.salvar(tipoacesso);
        } catch (SQLException ex) {
            Logger.getLogger(TipoAcessoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Tipo de Acesso "+ ex);
            return null;
        }
    }
    public List<Tipoacesso> listar() {
        tipoAcessoFacade = new TipoAcessoFacade();
        try {
            return tipoAcessoFacade.listar();
        } catch (SQLException ex) {
            Logger.getLogger(TipoPlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Tipo de Acesso "+ ex);
            return null;
        }
    }
    public Tipoacesso consultar(int idtipoacesso) {
        tipoAcessoFacade = new TipoAcessoFacade();
        try {
            return tipoAcessoFacade.consultar(idtipoacesso);
        } catch (SQLException ex) {
            Logger.getLogger(TipoPlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Tipo de Acesso"+ ex);
            return null;
        }
    }
    
}
