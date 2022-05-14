package br.com.drogaria.dao;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FabricanteDAO {

    //Cria strings para inserções no banco na tabela fabricante e atributo descricao
    public void salvar(Fabricante f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO fabricante ");
        sql.append("(descricao) ");
        sql.append("VALUES (?)");

        Connection conexao = ConexaoFactory.conectar(); // CONECTA NO BANCO

        PreparedStatement comando = conexao.prepareStatement(sql.toString()); // Converte comandos sql em java (troca interrogações pela string)
        comando.setString(1, f.getDescricao());

        comando.executeUpdate(); // EXECUTA INSERÇÃO.
    }

    public void excluir(Fabricante f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM fabricante ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setLong(1, f.getCodigo());

        comando.executeUpdate();

    }

    public void editar(Fabricante f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE fabricante ");
        sql.append("SET descricao = ? ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, f.getDescricao());
        comando.setLong(2, f.getCodigo());

        comando.executeUpdate();

    }

    public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fabricante ");
        sql.append("WHERE codigo = ? ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setLong(1, f.getCodigo());

        ResultSet resultado = comando.executeQuery();

        Fabricante retorno = null;

        if (resultado.next()) {
            retorno = new Fabricante();
            retorno.setCodigo(resultado.getLong("codigo"));
            retorno.setDescricao(resultado.getString("descricao"));

        }
        return retorno;
    }

    public ArrayList<Fabricante> listar() throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fabricante ");
        sql.append("ORDER BY descricao ASC ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());

        ResultSet resultado = comando.executeQuery();

        ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

        while (resultado.next()) {
            Fabricante f = new Fabricante();
            f.setCodigo(resultado.getLong("codigo"));
            f.setDescricao(resultado.getString("descricao"));
            lista.add(f);

        }

        return lista;
    }

    public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fabricante ");
        sql.append("WHERE descricao LIKE ? ");
        sql.append("ORDER BY descricao ASC ");

        Connection conexao = ConexaoFactory.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setString(1, "%" + f.getDescricao() + "%");
        ResultSet resultado = comando.executeQuery();

        ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

        while (resultado.next()) {
            Fabricante item = new Fabricante();
            item.setCodigo(resultado.getLong("codigo"));
            item.setDescricao(resultado.getString("descricao"));
            lista.add(item);

        }

        return lista;

    }

    public static void main(String[] args) {
        /*   Fabricante f1 = new Fabricante();
        f1.setDescricao("DESCRICAO 1");
        
        Fabricante f2 = new Fabricante();
        f2.setDescricao("DESCRICAO 2");
        
        FabricanteDAO fdao = new FabricanteDAO();
        
        try {
            fdao.salvar(f1);
            fdao.salvar(f2);
            System.out.println("Fabricantes inseridos !!!");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir fabricantes");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
       }

       /Fabricante f1 = new Fabricante();
        f1.setCodigo(1L);

        Fabricante f2 = new Fabricante();
        f2.setCodigo(2L);

        FabricanteDAO fdao = new FabricanteDAO();

        try {
            fdao.excluir(f1);
            fdao.excluir(f2);
            System.out.println("Fabricantes removidos com suceso !!! ");
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar excluir fabricante !!!");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Fabricante f1 = new Fabricante();
        f1.setCodigo(4L);
        f1.setDescricao("DESCRICAO 150");
        
        FabricanteDAO fdao = new FabricanteDAO();
        
        try {
            fdao.editar(f1);
            System.out.println("Fabricante alterado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar editar !!! ");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Fabricante f1 = new Fabricante();
        f1.setCodigo(4L);
        
        Fabricante f2 = new Fabricante();
        f2.setCodigo(5L);
        
        FabricanteDAO fdao = new FabricanteDAO();
        
        try {
            Fabricante f3 = fdao.buscarPorCodigo(f1);
            Fabricante f4 = fdao.buscarPorCodigo(f2);
            System.out.println("Resultado 1 : " + f3);
            System.out.println("Resultado 2 : " + f4);
        } catch (SQLException ex) {
            System.out.println("Erro !!!");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        FabricanteDAO fdao = new FabricanteDAO();
        try {
            ArrayList<Fabricante> lista = fdao.listar();
            for(Fabricante f : lista){
                System.out.println("Resultado - " + f);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar !!!!");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Fabricante f1 = new Fabricante();
        f1.setDescricao("150");

        FabricanteDAO fdao = new FabricanteDAO();
        try {
            ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
            for (Fabricante f : lista) {
                System.out.println("Resultadio - " + f);

            }

        } catch (SQLException ex) {
            System.out.println("Erro");
            Logger.getLogger(FabricanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
}
