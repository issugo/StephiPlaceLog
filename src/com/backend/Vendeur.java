package com.backend;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Vendeur pour les clients autorisés
 */
public class Vendeur {
    Integer id, id_client;
    Blob CIN;
    InputStream CINTemp;

    /**
     * constructeur
     */
    public Vendeur() {
        this.id = null;
        this.id_client = null;
        this.CIN = null;
        this.CINTemp = null;
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
     * setter de l'InputStream de la carte d'identite
     * @param CINTemp
     * @return l'InputStream
     */
    public InputStream setCINTemp(InputStream CINTemp) {
        this.CINTemp = CINTemp;
        return this.CINTemp;
    }

    /**
     * getter de l'InputStream de la carte d'identite
     * @return l'InputStream
     */
    public InputStream getCINTemp() {
        return this.CINTemp;
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
     * methode pour sauvegarder en base une instance de vendeur
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        String url = "jdbc:mysql://localhost:3306/stephiplacelog";
        Connection con = DriverManager.getConnection(url, "root", "");
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO vendeur(`id_Client`, `carte_identite`) VALUES(?, ?)");
        pstmt.setBlob(2, this.getCINTemp());
        pstmt.setInt(1, this.getIdClient());
        int test  = pstmt.executeUpdate();
        if (test > 1) {
            retour = true;
        }
        con.close();
        return retour;
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
    public Client findClient() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        return Client.find(this.getIdClient());
    }

    /**
     * methode pour récupérer les biens associé à un vendeur
     * @return une List de Bien
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Bien> getBiens() throws SQLException, ClassNotFoundException {
        List<Bien> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM bien WHERE id_vendeur = ";
        query += this.getId();
        ResultSet result = db.select(query);
        while (result.next()) {
            Bien instance = new Bien();
            instance.setId(result.getInt("id"));
            instance.setSuperficie(result.getFloat("superficie"));
            instance.setNb_pieces(result.getInt("nb_pieces"));
            instance.setType(result.getString("type"));
            instance.setDescription(result.getString("description"));
            instance.setJardin(result.getBoolean("jardin"));
            instance.setCave(result.getBoolean("cave"));
            instance.setCeillier(result.getBoolean("ceillier"));
            instance.setLoggia(result.getBoolean("loggia"));
            instance.setTerrasse(result.getBoolean("terrasse"));
            instance.setGarage(result.getBoolean("garage"));
            instance.setVerranda(result.getBoolean("verranda"));
            instance.setPrix_min(result.getFloat("prix_min"));
            instance.setFrais_agence(result.getFloat("frais_agence"));
            instance.setIdVendeur(result.getInt("id_vendeur"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour modifier l'instance en base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean update() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        String url = "jdbc:mysql://localhost:3306/stephiplacelog";
        Connection con = DriverManager.getConnection(url, "root", "");
        PreparedStatement pstmt = con.prepareStatement("UPDATE vendeur SET carte_identite = ?, id_Client = ? WHERE id = ?;");
        pstmt.setBlob(1, this.getCINTemp());
        pstmt.setInt(2, this.getIdClient());
        pstmt.setInt(3, this.getId());
        Integer test  = pstmt.executeUpdate();
        if (test > 1) {
            retour = true;
        }
        con.close();
        return retour;
    }

    /**
     * methode pour detruire une instance en base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean delete() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "DELETE FROM vendeur WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }
}
