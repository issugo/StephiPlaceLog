package com.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe Agent représentant les salariés de l'entreprise Stephi Real Estate
 */
public class Agent {
    Integer id, code_agent;

    /**
     * constructeur par défaut de la classe Agent
     */
    public Agent() {
        this.id = null;
        this.code_agent = null;
    }

    /**
     * Setter de l'id de l'agent
     *
     * @param id id que l'on veut attribuer à l'agent
     * @return id qui vient d'être set
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id de l'agent
     * @return l'id de l'instance de l'agent
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter du code agent
     * @param code_agent
     * @return le code agent de l'instance de l'agent
     */
    public Integer setCode_agent(Integer code_agent) {
        this.code_agent = code_agent;
        return this.code_agent;
    }

    /**
     * getter du code_agent
     * @return le code agent de l'instance de l'agent
     */
    public Integer getCode_agent() {
        return this.code_agent;
    }

    /**
     * methode static find qui retourne une instance de l'agent trouvé en base de données
     * @param id
     * @return une instance de Agent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Agent find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM agent WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Agent instance = new Agent();
        instance.setId(result.getInt("id"));
        instance.setCode_agent(result.getInt("code_agent"));
        return instance;
    }

    /**
     * methode static findByCodeAgent qui retourne une instance de l'agent trouvé en base de données en fonction du code de l'agent
     * @param code_agent
     * @return une instance de Agent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Agent findByCodeAgent(Integer code_agent) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM agent WHERE code_agent = ";
        query += code_agent;
        ResultSet result = db.select(query);
        result.next();
        Agent instance = new Agent();
        instance.setId(result.getInt("id"));
        instance.setCode_agent(result.getInt("code_agent"));
        return instance;
    }
}
