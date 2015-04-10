/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Controller;


import br.com.financemate.facade.FluxoCaixaFacade;
import br.com.financemate.model.Fluxocaixa;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Julio
 */
public class FluxoCaixaController {
    
    FluxoCaixaFacade fluxoCaixaFacade;
    
    public Fluxocaixa salvar(Fluxocaixa fluxo){
        fluxoCaixaFacade = new FluxoCaixaFacade();
        try {
            return fluxoCaixaFacade.salvar(fluxo);
        } catch (SQLException ex) {
            Logger.getLogger(FluxoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Salvar Fluxo de Caixa " +  ex);
            return null;
        }
    }
    
    public List<Fluxocaixa> consultar(int idCliente) {
        fluxoCaixaFacade = new FluxoCaixaFacade();
        try {
            return fluxoCaixaFacade.listar(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(FluxoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Listar Fluxo de Caixa " +  ex);
            return null;
        }
    }
    
    public void excluir(int idCliente){
        fluxoCaixaFacade = new FluxoCaixaFacade();
        try {
            fluxoCaixaFacade.excluir(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(FluxoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro Excluir Fluxo de Caixa " +  ex);
        }
    }
    
}
