package com.backend;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    Integer id;
    String nom, prenom, email, addresse, telephone, password;

    public Client() {
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.addresse = null;
        this.telephone = null;
        this.password = null;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return id;
    }

    public Integer getId() {
        return this.id;
    }

    public String setNom(String nom) {
        this.nom = nom;
        return nom;
    }

    public String getNom() {
        return this.nom;
    }

    public String setPrenom(String prenom) {
        this.prenom = prenom;
        return prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getEmail() {
        return this.email;
    }

    public String setAddresse(String addresse) {
        this.addresse = addresse;
        return addresse;
    }

    public String getAddresse() {
        return this.addresse;
    }

    public String setTelephone(String telephone) {
        this.telephone = telephone;
        return telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "id : " + this.getId() + "; nom : " + this.getNom() + "; prenom : " + this.getPrenom() + "; email :" + this.getEmail() + "; telephone :" + this.getTelephone() + "; addresse : " + this.getAddresse() + ";";
    }

    public Boolean save() throws SQLException, ClassNotFoundException {
        Boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "testufjava", "root", "");
        db.connect();
        String query = "INSERT INTO clients(nom, prenom, email, addresse, telephone, password) VALUES (" + this.getNom() + "," + this.getPrenom() + "," + this.getEmail() + "," + this.getAddresse() + "," + this.getTelephone() + "," + this.getPassword() + ")";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        return retour;
    }

    public static @NotNull Client find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "testufjava", "root", "");
        db.connect();
        String query = "SELECT * FROM clients WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        Client instance = new Client();
        instance.setId(result.getInt("id"));
        instance.setNom(result.getString("nom"));
        instance.setPrenom(result.getString("prenom"));
        instance.setEmail(result.getString("email"));
        instance.setPassword(result.getString("addresse"));
        instance.setTelephone(result.getString("telephone"));
        return instance;
    }

    public static @NotNull List findAll() throws SQLException, ClassNotFoundException {
        List<Client> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "testufjava", "root", "");
        db.connect();
        String query = "SELECT * FROM clients";
        ResultSet result = db.select(query);
        while (result.next()) {
            Client instance = new Client();
            instance.setId(result.getInt("id"));
            instance.setNom(result.getString("nom"));
            instance.setPrenom(result.getString("prenom"));
            instance.setEmail(result.getString("email"));
            instance.setPassword(result.getString("addresse"));
            instance.setTelephone(result.getString("telephone"));
            retour.add(instance);
        }
        return retour;
    }

    public boolean isVendeur() throws SQLException, ClassNotFoundException {
        Boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "testufjava", "root", "");
        db.connect();
        String query = "SELECT * FROM vendeur WHERE id = ";
        query += this.getId();
        ResultSet result = db.select(query);
        ResultSetMetaData meta = result.getMetaData();
        if(meta.getColumnCount() != 0) {
            retour = true;
        }
        return retour;
    }
}
