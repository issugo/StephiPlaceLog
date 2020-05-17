package com.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Agent {
    Integer id, code_agent;

    public Agent() {
        this.id = null;
        this.code_agent = null;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer setCode_agent(Integer code_agent) {
        this.code_agent = code_agent;
        return this.code_agent;
    }

    public Integer getCode_agent() {
        return this.code_agent;
    }

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
}
