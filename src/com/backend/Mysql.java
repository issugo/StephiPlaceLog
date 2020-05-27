package com.backend;

import java.sql.*;

/**
 * classe Mysql pour les échanges avec la BDD
 */
public class Mysql {

    String url, login, password;
    Connection cn;
    Statement st;

    /**
     * constructeur avec l'url fourni
     * @param url
     * @param login
     * @param password
     */
    public Mysql (String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        this.cn = null;
        this.st = null;
    }

    /**
     * constructeur avec tout les éléments nécessaire
     * @param host
     * @param port
     * @param database
     * @param login
     * @param password
     */
    public Mysql(String host, String port, String database, String login, String password) {
        this.url = Mysql.urlGenerator(host, port, database);
        this.login = login;
        this.password = password;
        this.cn = null;
        this.st = null;
    }

    /**
     * générateur d'url de connection jdbc
     * @param host
     * @param port
     * @param database
     * @return l'url de connection
     */
    public static String urlGenerator(String host, String port, String database) {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    /**
     * connecte à la BDD
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.cn = DriverManager.getConnection(this.url, this.login, this.password);
        this.st = cn.createStatement();
    }

    /**
     * ferme la connection à la BDD
     * @throws SQLException
     */
    public void close() throws SQLException {
        this.cn.close();
        this.st.close();
    }

    /**
     * selection dans la BASE
     * @param requete
     * @return la liste des résultats selectionnées
     * @throws SQLException
     */
    public ResultSet select(String requete) throws SQLException {
        ResultSet resultat;
        this.st = this.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultat = this.st.executeQuery(requete);
        return resultat;
    }

    /**
     * insertion ou modification dans la base
     * @param requete
     * @return nombre de ligne affectés
     * @throws SQLException
     */
    public Integer insertOrUpdate(String requete) throws SQLException {
        this.st = this.cn.createStatement();
        return this.st.executeUpdate(requete);
    }
}
