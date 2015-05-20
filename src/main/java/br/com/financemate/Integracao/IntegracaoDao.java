/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Integracao;

import br.com.financemate.Connection.ConectionFactoryTM;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Wolverine
 */
public class IntegracaoDao {
    
    private EntityManager manager;
    
    
    
    public List<Viewvendasintegracao> consultarVendas() throws SQLException{
        manager = ConectionFactoryTM.getConnection();
        manager.getTransaction().begin();
        String sql = "select v from Viewvendasintegracao v order by v.dataVenda";
        Query q = manager.createQuery(sql);
        List<Viewvendasintegracao> listaVendas = q.getResultList();
        manager.getTransaction().commit();
        return listaVendas;
    }
    
    public List<SParcelamentopagamento> listarParcelmanetoFormaPagamento(int idFormaPagamento) throws SQLException{
        manager = ConectionFactoryTM.getConnection();
        Query q = manager.createQuery("select p from SParcelamentopagamento p where p.idformapagamento=" + idFormaPagamento);
        return q.getResultList();
    }
    
    
    public Produto consultarProduto(int idCliente, int codigosystm) throws SQLException{
        EntityManager manager = ConectionFactoryTM.getConnection();
        Query q = manager.createQuery("Select p from Produto p where p.cliente=" + idCliente + " and p.codigosystm=" + codigosystm);
        Produto produto = null;
        if (q.getResultList().size()>0){
            produto = (Produto) q.getResultList().get(0);
        }
        return produto;
    }
    
    public Cliente consultarClietne(int codigosystm) throws SQLException{
        EntityManager manager = ConectionFactoryTM.getConnection();
        Query q = manager.createQuery("select c from Cliente c where c.codigosystm=" + codigosystm);
        Cliente cliente = null;
        if (q.getResultList().size()>0){
            cliente = (Cliente) q.getResultList().get(0);
        }
        return cliente;
    }
    
    public Orcamentoprodutosorcamento consultarOrcamentoProdutoOrcamento(int idOrcamento, int idProdutoOrcamento) throws SQLException{
        EntityManager manager = ConectionFactoryTM.getConnection();
        Query q = manager.createQuery("select o from Orcamentoprodutosorcamento o where o.orcamento=" + idOrcamento + 
                " and o.produtosOrcamento=" + idProdutoOrcamento);
        if (q.getResultList().size() > 0) {
            return  (Orcamentoprodutosorcamento) q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public void salvarVendaSysTM(int idVenda) throws IOException {
        
        String banco = "systm";
        String local = "186.215.116.63";
        String senha = "jfhmaster123";
        String usuario = "root";
        String porta = "8082";
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String conexao = "jdbc:mysql://" + local + ":" + porta + "/" + banco;
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(conexao, usuario, senha);
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement stm = null;
        try {
            stm = (Statement) conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stm.execute("USE systm"); //Nome do DATABASE A SER ACESSADO  
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "Update vendas set vendaimportada=1 where idvendas=" + idVenda + " ";
        try {
            stm.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IntegracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
   
