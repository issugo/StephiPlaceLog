package com.backend;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe Vendeur pour les clients autorisés
 */
public class Vendeur {
    Integer id, id_client;
    Blob CIN;

    /**
     * constructeur
     */
    public Vendeur() {
        this.id = null;
        this.id_client = null;
        this.CIN = null;
    }

    /**
     * setter de l'id du vendeur
     * @param id
     * @return l'id du vendeur
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id du vendeur
     * @return l'id du vendeur
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter du Blob de la carte d'identité
     * @param CIN
     * @return le blob de la carte d'identité
     */
    public Blob setCIN(Blob CIN) {
        this.CIN = CIN;
        return this.CIN;
    }

    /**
     * getter du blob de la carte d'identité
     * @return le blob de la carte d'identité
     */
    public Blob getCIN() {
        return this.CIN;
    }

    /**
     * setter de l'id  du client qui est vendeur
     * @param idClient
     * @return l'id du client
     */
    public Integer setIdClient(Integer idClient) {
        this.id_client = idClient;
        return this.id_client;
    }

    /**
     * getter de l'id du client qui est vendeur
     * @return l'id du client
     */
    public Integer getIdClient() {
        return this.id_client;
    }

    /**
     * methode pour récupérer une instance de vendeur en fonction de son id
     * @param id
     * @return une isntance de Vendeur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Vendeur find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM vendeur WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Vendeur instance = new Vendeur();
        instance.setId(result.getInt("id"));
        instance.setIdClient(result.getInt("id_Client"));
        instance.setCIN(result.getBlob("carte_identite"));
        return instance;
    }

    /**
     * methode pour trouver un vendeur en ofnction de son id client
     * @param id
     * @return instance de Vendeur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Vendeur findByIdClient(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM vendeur WHERE id_client = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Vendeur instance = new Vendeur();
        instance.setId(result.getInt("id"));
        instance.setIdClient(result.getInt("id_Client"));
        instance.setCIN(result.getBlob("carte_identite"));
        return instance;
    }

    /**
     * methode qui retourne l'instance de Client associé au vendeur
     * @return une isnatnce de Client
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Client findClient() throws SQLException, ClassNotFoundException {
        return Client.find(this.getIdClient());
    }
}
