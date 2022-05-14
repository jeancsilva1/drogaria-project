package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    // Cria variáveis e parametros para conexão com o banco
    private static final String USUARIO = "root"; 
    private static final String SENHA = "123456";
    private static final String URL = "jdbc:mysql://localhost:3306/drogaria";

    // Cria método para conectar com o banco 
    //throws -  Quem chama o método é obrigado a tratar a excecão.
    public static Connection conectar() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA); 
        return conexao;
    }
     //TESTE DE CONEXÃO COMO BANCO
    //TRATAMENTO DE EXCEÇÕES DE ERROS DE CONEXÃO COM O BANCO
    public static void main(String[] args) {
        try {
            Connection conexao = ConexaoFactory.conectar();
            System.out.println("Conexão Realizada com Sucesso !!!");
        } catch (SQLException ex) {
            ex.printStackTrace(); // ESPECIFICA O ERRO DE FALHA NA CONEXÃO
            System.out.println("Falha na conexão !!!");
        }

    }
}
