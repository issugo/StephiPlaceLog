package com.backend;

import com.company.ConnexionPanel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContreProposition {
    Integer id, id_vendeur, id_proposition;
    Float prix;

    /**
     * constructeur
     */
    public ContreProposition() {
        this.id = null;
        this.id_vendeur = null;
        this.id_proposition = null;
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
     * setter de l'id du vendeur lié à la contre-proposition
     * @param id_vendeur
     * @return l'id du vendeur lié à la contre-proposition
     */
    public Integer setIdVendeur(Integer id_vendeur) {
        this.id_vendeur = id_vendeur;
        return this.id_vendeur;
    }

    /**
     * getter de l'id du vendeur lié à la contre-proposition
     * @return l'id du vendeur lié à la contre-proposition
     */
    public Integer getIdVendeur() {
        return this.id_vendeur;
    }

    /**
     * setter de l'id de la proposition lié à la contre-proposition
     * @param id_proposition
     * @return l'id de la proposition lié à la contre-proposition
     */
    public Integer setIdProposition(Integer id_proposition) {
        this.id_proposition = id_proposition;
        return this.id_proposition;
    }

    /**
     * getter de l'id de la proposition lié à la contre-proposition
     * @return l'id de la proposition lié à la contre-proposition
     */
    public Integer getidProposition() {
        return this.id_proposition;
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
        String query = "INSERT INTO ContreProposition(prix, id_vendeur, id_propositionachat) VALUES (" + this.getPrix() + "," + this.getIdVendeur() + "," + this.getidProposition() + ")";
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
        PreparedStatement pstmt = con.prepareStatement("UPDATE contreproposition SET prix = ?, id_Vendeur = ?, id_PropositionAchat = ? WHERE id = ?;");
        pstmt.setFloat(1, this.getPrix());
        pstmt.setInt(2, this.getIdVendeur());
        pstmt.setInt(3, this.getidProposition());
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
        String query = "DELETE FROM ContreProposition WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer une instance de ContreProposition en fonction de son id
     * @param id
     * @return une instance de ContreProposition en fonction de son id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ContreProposition find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM ContreProposition WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        ContreProposition instance = new ContreProposition();
        instance.setId(result.getInt("id"));
        instance.setPrix(result.getFloat("prix"));
        instance.setIdVendeur(result.getInt("id_Vendeur"));
        instance.setIdProposition(result.getInt("id_PropositionAchat"));
        db.close();
        return instance;
    }

    /**
     * methode pour récupérer la List de toute les instances de ContreProposition
     * @return une List de toute les instances de Contreproposition
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<ContreProposition> findAll() throws SQLException, ClassNotFoundException {
        List<ContreProposition> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM ContreProposition";
        ResultSet result = db.select(query);
        while (result.next()) {
            ContreProposition instance = new ContreProposition();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdVendeur(result.getInt("id_Vendeur"));
            instance.setIdProposition(result.getInt("id_PropositionAchat"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer la List de toute les instances de ContreProposition en fonction de l'id de la proposition
     * @param id
     * @return la List de toute les instances de ContreProposition en fonction de l'id de la proposition
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<ContreProposition> findByIdProposition(Integer id) throws SQLException, ClassNotFoundException {
        List<ContreProposition> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM ContreProposition WHERE id_PropositionAchat = " + id;
        ResultSet result = db.select(query);
        while (result.next()) {
            ContreProposition instance = new ContreProposition();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdProposition(result.getInt("id_PropositionAchat"));
            instance.setIdVendeur(result.getInt("id_Vendeur"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer la List de toute les instances de ContreProposition en fonction de l'id du vendeur
     * @param id
     * @return la List de toute les instances de ContreProposition en fonction de l'id du vendeur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<ContreProposition> findByIdVendeur(Integer id) throws SQLException, ClassNotFoundException {
        List<ContreProposition> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM ContreProposition WHERE id_Vendeur = " + id;
        ResultSet result = db.select(query);
        while (result.next()) {
            ContreProposition instance = new ContreProposition();
            instance.setId(result.getInt("id"));
            instance.setPrix(result.getFloat("prix"));
            instance.setIdVendeur(result.getInt("id_Vendeur"));
            instance.setIdProposition(result.getInt("id_PropositionAchat"));
            retour.add(instance);
        }
        db.close();
        return retour;
    }
}
