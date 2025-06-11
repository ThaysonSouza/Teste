package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private String driver;
    private String url;
    private String usuario;
    private String senha;

    /*inicializar um construtor, de modo que quando essa classe
    Conexao.java insranciar um objeto, o construtor sera executado
    e a classe Conexao.java inicializada
    */

    public Conexao() {
        carregarConfiguracoes();
    }
    //Metodo com os parametros de configuraçao das variaveis de ambiente

    private void carregarConfiguracoes() {
        Properties props = new Properties();
        try ( InputStream inputPropsConfig = getClass().getClassLoader().getResourceAsStream
                ("config.properties")) {
            props.load(inputPropsConfig);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            usuario = props.getProperty("usuario");
            senha = props.getProperty("senha");
        }
        catch (IOException erro){
            System.out.println("Erro ao carregar configuraçoes: " + erro.getMessage());
        }
    }

    public Connection conectar() {
        Connection condb = null;
        try {
            Class.forName(driver);
            condb = DriverManager.getConnection(url, usuario, senha);
            return condb;
        } catch (SQLException | ClassNotFoundException erro) {
            System.out.println("Erro ao conectar ao Banco de Dados" + erro);
            return null;
        }
    }
}
