package com.backend;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Client représentant les Clients qu'ils soient vendeur ou acheteur
 */
public class Client {

    Integer id;
    String nom, prenom, email, addresse, telephone, password;

    /**
     * constructeur
     */
    public Client() {
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.addresse = null;
        this.telephone = null;
        this.password = null;
    }

    /**
     * setter de l'id du client
     * @param id
     * @return l'id du client
     */
    public Integer setId(Integer id) {
        this.id = id;
        return id;
    }

    /**
     * getter de l'id du client
     * @return l'id du client
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter du nom du client
     * @param nom
     * @return le nom du client
     */
    public String setNom(String nom) {
        this.nom = nom;
        return nom;
    }

    /**
     * getter du nom du client
     * @return
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * setter du prenom du client
     * @param prenom
     * @return le prenom du client
     */
    public String setPrenom(String prenom) {
        this.prenom = prenom;
        return prenom;
    }

    /**
     * getter du prenom du client
     * @return le prenom du client
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * setter de l'email du client
     * @param email
     * @return l'email du client
     */
    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    /**
     * getter de l'email du client
     * @return l'email du client
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * setter du telephone du client
     * @param telephone
     * @return le telephone du client
     */
    public String setTelephone(String telephone) {
        this.telephone = telephone;
        return telephone;
    }

    /**
     * getter du telephone du client
     * @return le telephone du client
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * setter du password du client
     * @param password
     * @return le password du client
     */
    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    /**
     * getter du mot de passe du client
     * @return le mot de passe du client
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * ecriture en String de la description de la classe
     * @return
     */
    public String toString() {
        return "id : " + this.getId() + "; nom : " + this.getNom() + "; prenom : " + this.getPrenom() + "; email :" + this.getEmail() + "; telephone :" + this.getTelephone() + ";";
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
        String query = "INSERT INTO client(nom, prenom, email, telephone, password) VALUES ('" + this.getNom() + "','" + this.getPrenom() + "','" + this.getEmail() + "'," + this.getTelephone() + ",'" + this.getPassword() + "')";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
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
    public boolean update(String champ, String newValue) throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "UPDATE client SET " + champ + " = " + newValue + " WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
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
        String query = "DELETE FROM client WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour recuperer une instance de Client en fonction de son id
     * @param id
     * @return une instance de Client
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static @NotNull Client find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM client WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Client instance = new Client();
        instance.setId(result.getInt("id"));
        instance.setNom(result.getString("nom"));
        instance.setPrenom(result.getString("prenom"));
        instance.setEmail(result.getString("email"));
        instance.setPassword(result.getString("password"));
        instance.setTelephone(result.getString("telephone"));
        return instance;
    }

    /**
     * methode pour recuperer une instance de Client en fonction de son email
     * @param email
     * @return une instance de Client
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Client findByEmail(String email) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM client WHERE email = '" + email + "';";
        ResultSet result = db.select(query);
        result.last();
        int nombreLignes = result.getRow();
        if(nombreLignes == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "impossible de trouver un client avec cette email.");
            return null;
        }
        result.beforeFirst();
        result.next();
        Client instance = new Client();
        instance.setId(result.getInt("id"));
        instance.setNom(result.getString("nom"));
        instance.setPrenom(result.getString("prenom"));
        instance.setEmail(result.getString("email"));
        instance.setPassword(result.getString("password"));
        instance.setTelephone(result.getString("telephone"));
        return instance;
    }

    /**
     * methode pour recuperer une List de Client
     * @return une list de Client
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static @NotNull List findAll() throws SQLException, ClassNotFoundException {
        List<Client> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM client";
        ResultSet result = db.select(query);
        while (result.next()) {
            Client instance = new Client();
            instance.setId(result.getInt("id"));
            instance.setNom(result.getString("nom"));
            instance.setPrenom(result.getString("prenom"));
            instance.setEmail(result.getString("email"));
            instance.setPassword(result.getString("password"));
            instance.setTelephone(result.getString("telephone"));
            retour.add(instance);
        }
        return retour;
    }

    /**
     * methode pour savoir si le client est un vendeur
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isVendeur() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM vendeur WHERE id_client = ";
        query += this.getId();
        ResultSet result = db.select(query);
        ResultSetMetaData meta = result.getMetaData();
        while (result.next()) {
            retour = true;
        }
        return retour;
    }
}
