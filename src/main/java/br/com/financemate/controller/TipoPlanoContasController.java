/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.controller;

import br.com.financemate.facade.TipoPlanoContasFacede;
import br.com.financemate.model.Tipoplanocontas;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Kamila
 */
public class TipoPlanoContasController {
    
    TipoPlanoContasFacede tipoPlanoContasFacede;
    
    public Tipoplanocontas salvar(Tipoplanocontas tipoplano) {
        tipoPlanoContasFacede = new TipoPlanoContasFacede();
        try {
            return tipoPlanoContasFacede.salvar(tipoplano);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Tipo de Plano de Contas "+ ex);
            return null;
        }
    }
    public List<Tipoplanocontas> listar() {
        tipoPlanoContasFacede = new TipoPlanoContasFacede();
        try {
            return tipoPlanoContasFacede.listar();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Listar Tipo de Plano de Contas "+ ex);
            return null;
        }
    }
    public Tipoplanocontas consultar(int idtipoplanocontas) {
        tipoPlanoContasFacede = new TipoPlanoContasFacede();
        try {
            return tipoPlanoContasFacede.consultar(idtipoplanocontas);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Consultar Tipo dePlano de Contas "+ ex);
            return null;
        }
    }
    
}
