/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Integracao;

import br.com.financemate.model.Cliente;
import br.com.financemate.model.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class IntegracaoFacade {
    
    IntegracaoDao integracaoDao;
    
    public List<Viewvendasintegracao> consultarVendas() throws SQLException{
        integracaoDao = new IntegracaoDao();
        return integracaoDao.consultarVendas();
    }
    
  
    
    public List<SParcelamentopagamento> listarParcelmanetoFormaPagamento(int idFormaPagamento) throws SQLException{
        integracaoDao = new IntegracaoDao();
        return integracaoDao.listarParcelmanetoFormaPagamento(idFormaPagamento);
    }
    
   
    
    public Produto consultarProduto(int idCliente, int codigosystm) throws SQLException{
        integracaoDao = new IntegracaoDao();
        return integracaoDao.consultarProduto(idCliente, codigosystm);
    }
    
    public Cliente consultarClietne(int codigosystm) throws SQLException{
        integracaoDao = new IntegracaoDao();
        return integracaoDao.consultarClietne(codigosystm);
    }
    
    public Orcamentoprodutosorcamento consultarOrcamentoProdutoOrcamento(int idOrcamento, int idProdutoOrcamento) throws SQLException{
        integracaoDao = new IntegracaoDao();
        return integracaoDao.consultarOrcamentoProdutoOrcamento(idOrcamento, idProdutoOrcamento);
    }
    
    public void salvarVendaSysTM(int idVenda) throws IOException {
        integracaoDao = new IntegracaoDao();
        integracaoDao.salvarVendaSysTM(idVenda);
    }
    
}
