package com.backend;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Annonce représentant l'entité annonce en base
 */
public class Annonce {

    Integer id_bien, id_agent, id, nb_favoris, nb_visites;
    String titre;

    /**
     * constructeur par défaut de la classe
     */
    public Annonce() {
        this.id_bien = null;
        this.id_agent = null;
        this.id = null;
        this.nb_visites = 0;
        this.nb_favoris = 0;
        this.titre = null;
    }

    /**
     * setter pour l'id de l'annonce
     * @param id id de l'annonce
     * @return id de l'instance
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id de l'annonce
     * @return id de l'annonce
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter de l'id du bien lié à l'annonce
     * @param id_bien id du bien
     * @return id du bien que l'on vient de mettre
     */
    public Integer setId_bien(Integer id_bien) {
        this.id_bien = id_bien;
        return this.id_bien;
    }

    /**
     * getter de l'id du bien associé à l'annonce
     * @return id du bien associé à l'annonce
     */
    public Integer getId_bien() {
        return this.id_bien;
    }

    /**
     * setter id_agent du créateur de l'annonce
     * @param id_agent id de l'agent ayant créé l'annonce
     * @return l'id de l'agent
     */
    public Integer setId_agent(Integer id_agent) {
        this.id_agent = id_agent;
        return this.id_agent;
    }

    /**
     * getter de l'id agent
     * @return l'id agent de l'instance de l'annonce
     */
    public Integer getId_agent() {
        return this.id_agent;
    }

    /**
     * setter du nombre de favoris de l'annonce
     * @param nb_favoris
     * @return le nombre de favoris de l'annonce
     */
    public Integer setNb_favoris(Integer nb_favoris) {
        this.nb_favoris = nb_favoris;
        return this.nb_favoris;
    }

    /**
     * getter du nombre de favoris d'une annonce
     * @return le nombre de favoris de l'annnonce
     */
    public Integer getNb_favoris() {
        return this.nb_favoris;
    }

    /**
     * setter du nombre de visites
     * @param nb_visites
     * @return le nombre de visites de l'annonce
     */
    public Integer setNb_visites(Integer nb_visites) {
        this.nb_visites = nb_visites;
        return this.nb_visites;
    }

    /**
     * getter du nombre de visites de l'annonce
     * @return le nombre de visite de l'annonce
     */
    public Integer getNb_visites() {
        return this.nb_visites;
    }

    /**
     * setter du titre de l'annonce
     * @param titre
     * @return le titre de l'annonce
     */
    public String setTitre(String titre) {
        this.titre = titre;
        return this.titre;
    }

    /**
     * getter du titre de l'annonce
     * @return le titre de l'annonce
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * methode pour retrouver une Annonce par son id
     * @param id
     * @return une instance de la classe annonce
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * methode pour retourner une List de toutes les annonces
     * @return liste de toute les annonces
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * methode pour sauvegarder l'instance de la classe dans la BDD
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "INSERT INTO annonce(id_bien, id_agent, nb_favoris, nb_visites, titre) VALUES (" + this.getId_bien() + "," + this.getId_agent() + "," + this.getNb_favoris() + "," + this.getNb_visites() + ",'" + this.getTitre() + "');";
        System.out.println(query);
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour modifier un champ d'une instance de Annonce en bdd
     * @param champ
     * @param newValue
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * methode pour supprimer une instance de la classe annonce de la bdd
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * methode pour trouver une annonce en fonction de l'id du bien
     * @param id
     * @return l'instance de Annonce lier au bien que l'on veut
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Annonce findByIdBien(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM annonce WHERE id_bien = ";
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

}
