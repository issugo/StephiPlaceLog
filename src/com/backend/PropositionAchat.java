package com.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropositionAchat {

    Integer id, id_bien, id_client;
    Float prix;

    /**
     * constructeur
     */
    public PropositionAchat() {
        this.id = null;
        this.id_bien = null;
        this.id_client = null;
        this.prix = null;
    }

    /**
     * setter de l'id des propositions d'achat
     * @param id
     * @return l'id de la propostion d'achat
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id des propositions d'achat
     * @return 'id de la propostion d'achat
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter de l'id du bien lié à la proposition d'achat
     * @param id_bien
     * @return l'id du bien lié à la proposition d'achat
     */
    public Integer setIdBien(Integer id_bien) {
        this.id_bien = id_bien;
        return this.id_bien;
    }

    /**
     * getter de l'id du bien lié à la proposition d'achat
     * @return l'id du bien lié à la proposition d'achat
     */
    public Integer getIdBien() {
        return this.id_bien;
    }

    /**
     * setter de l'id du client lié à la proposition d'achat
     * @param id_client
     * @return l'id du client lié à la proposition d'achat
     */
    public Integer setIdClient(Integer id_client) {
        this.id_client = id_client;
        return this.id_client;
    }

    /**
     * getter de l'id du client lié à la proposition d'achat
     * @return l'id du client lié à la proposition d'achat
     */
    public Integer getidClient() {
        return this.id_client;
    }

    /**
     * setter du prix de la proposition d'achat
     * @param prix
     * @return le prix de la proposition d'achat
     */
    public Float setPrix(Float prix) {
        this.prix = prix;
        return this.prix;
    }

    /**
     * getter du prix de la proposition d'achat
     * @return prix de la proposition d'achat
     */
    public Float getPrix() {
        return this.prix;
    }

    /**
     * methode pour récupérer une instance de PropositionAchat en fonction de son id
     * @param id
     * @return une instance de PropositionAchat en fonction de son id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static PropositionAchat find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM PropositionAchat WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        PropositionAchat instance = new PropositionAchat();
        instance.setId(result.getInt("id"));
        instance.setPrix(result.getFloat("prix"));
        instance.setIdBien(result.getInt("id_bien"));
        instance.setIdClient(result.getInt("id_client"));
        db.close();
        return instance;
    }

    /**
     * methode pour récupérer la List de toute les instances de PropositionAchat
     * @return une List de toute les instances de PropositionAchat
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<PropositionAchat> findAll() throws SQLException, ClassNotFoundException {
        List<PropositionAchat> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM PropositionAchat";
        ResultSet result = db.select(query);
        while (result.next()) {
            PropositionAchat instance = new PropositionAchat();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdBien(result.getInt("id_bien"));
            instance.setIdClient(result.getInt("id_client"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer la List de toute les instances de PropositionAchat en fonction de l'id du client
     * @param id
     * @return la List de toute les instances de PropositionAchat en fonction de l'id du client
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<PropositionAchat> findByIdClient(Integer id) throws SQLException, ClassNotFoundException {
        List<PropositionAchat> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM PropositionAchat WHERE id_client = " + id;
        ResultSet result = db.select(query);
        while (result.next()) {
            PropositionAchat instance = new PropositionAchat();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdBien(result.getInt("id_bien"));
            instance.setIdClient(result.getInt("id_client"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer la List de toute les instances de PropositionAchat en fonction de l'id du bien
     * @param id
     * @return la List de toute les instances de PropositionAchat en fonction de l'id du bien
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<PropositionAchat> findByIdBien(Integer id) throws SQLException, ClassNotFoundException {
        List<PropositionAchat> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM PropositionAchat WHERE id_bien = " + id;
        ResultSet result = db.select(query);
        while (result.next()) {
            PropositionAchat instance = new PropositionAchat();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdBien(result.getInt("id_bien"));
            instance.setIdClient(result.getInt("id_client"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }
}
