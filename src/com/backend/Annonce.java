package com.backend;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Annonce {
    int id_bien, id_agent, id_annonce;
    List<Integer> reference = new ArrayList<Integer>();
    Mysql db;

    public Annonce() throws SQLException, ClassNotFoundException {
        this.db = new Mysql("localhost", "3306", "testjavauf", "root", "");
        this.db.connect();
    }

    @Override
    public void finalize() throws SQLException {
        this.db.close();
    }

    @Contract(value = " -> new", pure = true)
    public List getAllAnnonces() throws SQLException {
        List<List<Integer>> allReference = new ArrayList<List<Integer>>();
        ResultSet resultat = this.db.select("select * from annonces");
        while(resultat.next()) {
            this.id_annonce = Integer.parseInt(resultat.getObject("id").toString());
            this.id_agent = Integer.parseInt(resultat.getObject("id_agents").toString());
            this.id_bien = Integer.parseInt(resultat.getObject("id_biens").toString());

            this.reference = new ArrayList<Integer>();
            this.reference.add(this.id_annonce);
            this.reference.add(this.id_agent);
            this.reference.add(this.id_bien);

            allReference.add(this.reference);
        }

        return allReference;
    }
}
