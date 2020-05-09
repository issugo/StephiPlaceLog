package com.backend;

import java.sql.*;

public class Mysql {

    String url, login, password;
    Connection cn;
    Statement st;

    public Mysql (String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        this.cn = null;
        this.st = null;
    }

    public Mysql(String host, String port, String database, String login, String password) {
        this.url = Mysql.urlGenerator(host, port, database);
        this.login = login;
        this.password = password;
        this.cn = null;
        this.st = null;
    }

    public static String urlGenerator(String host, String port, String database) {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.cn = DriverManager.getConnection(this.url, this.login, this.password);
        this.st = cn.createStatement();
    }

    public void close() throws SQLException {
        this.cn.close();
        this.st.close();
    }

    public ResultSet select(String requete) throws SQLException {
        ResultSet resultat;
        this.st = this.cn.createStatement();
        resultat = this.st.executeQuery(requete);
        return resultat;
    }

    public Integer insertOrUpdate(String requete) throws SQLException {
        this.st = this.cn.createStatement();
        return this.st.executeUpdate(requete);
    }
}
