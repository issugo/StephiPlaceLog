package com.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropositionAchat {

    Integer id, id_annonce, id_client;
    Float prix;

    /**
     * constructeur
     */
    public PropositionAchat() {
        this.id = null;
        this.id_annonce = null;
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
     * @param id_annonce
     * @return l'id du bien lié à la proposition d'achat
     */
    public Integer setIdAnnonce(Integer id_annonce) {
        this.id_annonce = id_annonce;
        return this.id_annonce;
    }

    /**
     * getter de l'id du bien lié à la proposition d'achat
     * @return l'id du bien lié à la proposition d'achat
     */
    public Integer getIdAnnonce() {
        return this.id_annonce;
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
     * methode pour sauvegarder l'instance en base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "INSERT INTO PropositionAchat(prix, id_client, id_annonce) VALUES (" + this.getPrix() + "," + this.getidClient() + "," + this.getIdAnnonce() + ")";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour modifier une entité en base
     * @return
     * @throws SQLException
     */
    public boolean update() throws SQLException {
        boolean retour = false;
        String url = "jdbc:mysql://localhost:3306/stephiplacelog";
        Connection con = DriverManager.getConnection(url, "root", "");
        PreparedStatement pstmt = con.prepareStatement("UPDATE propositionachat SET prix = ?, id_Client = ?, id_annonce = ? WHERE id = ?;");
        pstmt.setFloat(1, this.getPrix());
        pstmt.setInt(2, this.getidClient());
        pstmt.setInt(3, this.getIdAnnonce());
        pstmt.setInt(4, this.getId());
        Integer test  = pstmt.executeUpdate();
        if (test > 1) {
            retour = true;
        }
        con.close();
        return retour;
    }

    /**
     * methode pour supprimer l'instance en base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean delete() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "DELETE FROM PropositionAchat WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
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
        instance.setIdAnnonce(result.getInt("id_annonce"));
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
            instance.setIdAnnonce(result.getInt("id_annonce"));
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
            instance.setIdAnnonce(result.getInt("id_annonce"));
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
    public static List<PropositionAchat> findByIdAnnonce(Integer id) throws SQLException, ClassNotFoundException {
        List<PropositionAchat> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM PropositionAchat WHERE id_annonce = " + id;
        ResultSet result = db.select(query);
        while (result.next()) {
            PropositionAchat instance = new PropositionAchat();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdAnnonce(result.getInt("id_annonce"));
            instance.setIdClient(result.getInt("id_client"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour savoir si la proposition a des contre-propositions
     * @return un boolean de validation
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean hasContreProposition() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "select * from ContreProposition where id_PropositionAchat = " + this.getId();
        ResultSet result = db.select(query);
        while (result.next()) {
            retour = true;
        }
        return retour;
    }
}
