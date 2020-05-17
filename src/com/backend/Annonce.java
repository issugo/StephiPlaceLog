package com.backend;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Annonce {

    Integer id_bien, id_agent, id, nb_favoris, nb_visites;
    String titre;

    public Annonce() {
        this.id_bien = null;
        this.id_agent = null;
        this.id = null;
        this.nb_visites = 0;
        this.nb_favoris = 0;
        this.titre = null;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer setId_bien(Integer id_bien) {
        this.id_bien = id_bien;
        return this.id_bien;
    }

    public Integer getId_bien() {
        return this.id_bien;
    }

    public Integer setId_agent(Integer id_agent) {
        this.id_agent = id_agent;
        return this.id_agent;
    }

    public Integer getId_agent() {
        return this.id_agent;
    }

    public Integer setNb_favoris(Integer nb_favoris) {
        this.nb_favoris = nb_favoris;
        return this.nb_favoris;
    }

    public Integer getNb_favoris() {
        return this.nb_favoris;
    }

    public Integer setNb_visites(Integer nb_visites) {
        this.nb_visites = nb_visites;
        return this.nb_visites;
    }

    public Integer getNb_visites() {
        return this.nb_visites;
    }

    public String setTitre(String titre) {
        this.titre = titre;
        return this.titre;
    }

    public String getTitre() {
        return this.titre;
    }



    public static @NotNull Annonce find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM annonce WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Annonce instance = new Annonce();
        instance.setId(result.getInt("id"));
        instance.setId_bien(result.getInt("id_bien"));
        instance.setId_agent(result.getInt("id_agent"));
        instance.setNb_favoris(result.getInt("nb_favoris"));
        instance.setNb_visites(result.getInt("nb_visites"));
        instance.setTitre(result.getString("titre"));
        return instance;
    }

    public static @NotNull List<Annonce> findAll() throws SQLException, ClassNotFoundException {
        List<Annonce> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM annonce";
        ResultSet result = db.select(query);
        while(result.next()) {
            Annonce instance = new Annonce();
            instance.setId(result.getInt("id"));
            instance.setId_bien(result.getInt("id_bien"));
            instance.setId_agent(result.getInt("id_agent"));
            instance.setNb_favoris(result.getInt("nb_favoris"));
            instance.setNb_visites(result.getInt("nb_visites"));
            instance.setTitre(result.getString("titre"));
            retour.add(instance);
        }
        return retour;
    }

    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "INSERT INTO annonce(id_bien, id_agent, nb_favoris, nb_visites, titre) VALUES (" + this.getId_bien() + "," + this.getId_agent() + "," + this.getNb_favoris() + "," + this.getNb_visites() + "," + this.getTitre() + ")";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    public boolean update(String champ, String newValue) throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "UPDATE annonce SET " + champ + " = " + newValue + " WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    public boolean delete() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "DELETE FROM annonce WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

}
