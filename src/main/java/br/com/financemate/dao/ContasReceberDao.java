/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.dao;

import br.com.financemate.Connection.ConectionFactory;
import br.com.financemate.model.Contasreceber;
import br.com.financemate.modelView.Viewcontasreceber;
import br.com.financemate.modelView.Viewcontasreceberfluxo;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;



/**
 *
 * @author Wolverine
 */
public class ContasReceberDao {
    
    
    public Contasreceber salvar(Contasreceber conta) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        conta = manager.merge(conta);
        manager.getTransaction().commit();
        return conta;
    }
    
    public List<Contasreceber> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Contasreceber> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public Contasreceber consultar(int idConta) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Contasreceber conta = manager.find(Contasreceber.class, idConta);
        manager.getTransaction().commit();
        return conta;
    }
    
    public void excluir(int idConta) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Contasreceber conta = manager.find(Contasreceber.class, idConta);
        manager.remove(conta);
        manager.getTransaction().commit();
    }
    
    public Contasreceber consultarVendaFornecedor(int idVenda) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select c from Contasreceber c where c.vendaComissao=" + idVenda);
        Contasreceber conta = null;
        if (q.getResultList().size()>0){
            conta =  (Contasreceber) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return conta;
    }
    
    
    public List<Viewcontasreceberfluxo> listaFluxo(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Viewcontasreceberfluxo> listaFluxo= q.getResultList();
        manager.getTransaction().commit();
        return listaFluxo;
    }
}
