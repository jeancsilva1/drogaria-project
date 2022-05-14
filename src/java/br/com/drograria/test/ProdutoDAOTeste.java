
package br.com.drograria.test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Ignore;
import org.junit.Test;

public class ProdutoDAOTeste {
  // @Test
    //@Ignore
    /*
    public void salvar() throws SQLException{
        Produto p = new Produto();
        p.setDescricao("ATURGYL");
        p.setPreco(2.45);
        p.setQuantidade(13L);
        
        Fabricante f = new Fabricante();
        f.setCodigo(16L);
        
        p.setFabricante(f);
        
        ProdutoDAO dao = new ProdutoDAO();
        dao.salvar(p);
    }
    @Test
    @Ignore
    public void listar() throws SQLException{
        ProdutoDAO dao = new ProdutoDAO();
        ArrayList<Produto> lista = dao.listar();
        
        for(Produto p : lista){
            System.out.println("Código do Produto : " + p.getCodigo());
            System.out.println("Descrição do Produto: " + p.getDescricao());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Código do Fabricante: "+ p.getFabricante().getCodigo());
            System.out.println("Descrição do Fabricante: "+ p.getFabricante().getDescricao());
            System.out.println(" ");
        }
        
    }
    
    public void excluir () throws SQLException{
        Produto p = new Produto();
        p.setCodigo(11L);
        
        ProdutoDAO dao = new ProdutoDAO();
        dao.excluir(p);
    }*/
    @Test
    public void editar() throws SQLException{
        Produto p = new Produto();
        p.setCodigo(12L);
        p.setDescricao("CATAFLAN POMADA 60gr");
        p.setPreco(15.30);
        p.setQuantidade(10L);
        
        Fabricante f = new Fabricante();
        f.setCodigo(15L);
        p.setFabricante(f);
        
        ProdutoDAO dao = new ProdutoDAO();
        dao.editar(p);

    }
}
