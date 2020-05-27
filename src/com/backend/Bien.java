package com.backend;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Bien qui représente l'entité bien de la base donnée
 */
public class Bien {

    Integer id, nb_pieces, id_vendeur;
    Boolean jardin, cave, ceillier, loggia, terrasse, garage, verranda;
    String description, type;
    Float superficie, prix_min, frais_agence;

    /**
     * constructeur
     */
    public Bien() {
        this.id = null;
        this.nb_pieces = null;
        this.jardin = null;
        this.cave = null;
        this.ceillier = null;
        this.loggia = null;
        this.terrasse = null;
        this.garage = null;
        this.verranda = null;
        this.description = null;
        this.type = null;
        this.superficie = null;
        this.prix_min = null;
        this.frais_agence = null;
        this.id_vendeur = null;
    }

    /**
     * setter de l'id du bien
     * @param id
     * @return l'id du bien
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id du bien
     * @return l'id du bien
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * setter du nombre de peice de l'instance
     * @param nb_pieces
     * @return
     */
    public Integer setNb_pieces(Integer nb_pieces) {
        this.nb_pieces = nb_pieces;
        return this.nb_pieces;
    }

    /**
     * getter du nombre de pieces
     * @return le nombre de piece
     */
    public Integer getNb_pieces() {
        return this.nb_pieces;
    }

    /**
     * setter du boolean jardin
     * @param jardin
     * @return le boolean jardin
     */
    public boolean setJardin(boolean jardin) {
        this.jardin = jardin;
        return this.jardin;
    }

    /**
     * getter du boolean du jardin
     * @return le boolean du jardin
     */
    public boolean getJardin() {
        return this.jardin;
    }

    /**
     * setter du boolean cave
     * @param cave
     * @return le boolean de cave
     */
    public boolean setCave(boolean cave){
        this.cave = cave;
        return this.cave;
    }

    /**
     * getter du boolean cave
     * @return le boolean cave
     */
    public boolean getCave() {
        return this.cave;
    }

    /**
     * setter du boolean ceillier
     * @param ceillier
     * @return le boolean de ceillier
     */
    public boolean setCeillier(boolean ceillier) {
        this.ceillier = ceillier;
        return this.ceillier;
    }

    /**
     * getter du boolean du ceillier
     * @return le boolean du ceillier
     */
    public boolean getCeillier() {
        return this.ceillier;
    }

    /**
     * setter du boolean loggia
     * @param loggia
     * @return le boolean de loggia
     */
    public boolean setLoggia(boolean loggia) {
        this.loggia = loggia;
        return this.loggia;
    }

    /**
     * getter du boolean du loggia
     * @return le boolean du loggia
     */
    public boolean getLoggia() {
        return this.loggia;
    }

    /**
     * setter du boolean terrasse
     * @param terrasse
     * @return le boolean de terrasse
     */
    public boolean setTerrasse(boolean terrasse) {
        this.terrasse = terrasse;
        return this.terrasse;
    }

    /**
     * getter du boolean du terrasse
     * @return le boolean du terrasse
     */
    public boolean getTerrasse() {
        return this.terrasse;
    }

    /**
     * setter du boolean garage
     * @param garage
     * @return le boolean de garage
     */
    public boolean setGarage(boolean garage) {
        this.garage = garage;
        return this.garage;
    }

    /**
     * getter du boolean du garage
     * @return le boolean du garage
     */
    public boolean getGarage() {
        return this.garage;
    }

    /**
     * setter du boolean verranda
     * @param verranda
     * @return le boolean de verranda
     */
    public boolean setVerranda(boolean verranda) {
        this.verranda = verranda;
        return this.verranda;
    }

    /**
     * getter du boolean du verranda
     * @return le boolean du verranda
     */
    public boolean getVerranda() {
        return this.verranda;
    }

    /**
     * setter de la superficie
     * @param superficie
     * @return la superficie
     */
    public Float setSuperficie(Float superficie) {
        this.superficie = superficie;
        return this.superficie;
    }

    /**
     * getter de la superficie du bien
     * @return la superficie du bien
     */
    public Float getSuperficie() {
        return this.superficie;
    }

    /**
     * setter du type de bien
     * @param type
     * @return le type de bien
     */
    public String setType(String type) {
        this.type = type;
        return this.type;
    }

    /**
     * getter du type de bien
     * @return le type de bien
     */
    public String getType() {
        return this.type;
    }

    /**
     * setter de la description du bien
     * @param description
     * @return la description du bien
     */
    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    /**
     * getter de la description
     * @return la description du bien
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * getter du prix minimum du bien
     * @param prix_min
     * @return le prix minimum du bien
     */
    public float setPrix_min(float prix_min) {
        this.prix_min = prix_min;
        return this.prix_min;
    }

    /**
     * getter du prix minimum du bien
     * @return la valeur du prix minimum
     */
    public float getPrix_min() {
        return this.prix_min;
    }

    /**
     * setter des frais d'agences pour le bien
     * @param frais_agence
     * @return les frais d'agences
     */
    public float setFrais_agence(float frais_agence) {
        this.frais_agence = frais_agence;
        return this.frais_agence;
    }

    /**
     * getter des frais d'agence
     * @return les frais d'agences
     */
    public float getFrais_agence() {
        return this.frais_agence;
    }

    /**
     * setter de l'id du vendeur
     * @param id
     * @return l'id du vendeur
     */
    public Integer setIdVendeur(Integer id) {
        this.id_vendeur = id;
        return this.id_vendeur;
    }

    /**
     * getter de l'id du vendeur
     * @return l'id du vendeur
     */
    public Integer getIdVendeur() {
        return this.id_vendeur;
    }

    /**
     * methode pour ecrire la description de l'instance
     * @return la description de l'instance
     */
    public String toString() {
        return "id : " + this.id + "; superficie : " + this.superficie + "; nb pieces : " + this.nb_pieces + "; type : " + this.type + "; description : " + this.description + "; jardin " + this.jardin + "; cave : " + this.cave + "; ceillier : " + this.ceillier + "; loggia : " + this.loggia + "; terrasse : " + this.terrasse + "; garage : " + this.garage + "; verranda : " + this.verranda + "; prix min : " + this.prix_min + "; frais agences : " + this.frais_agence + "; id vendeur : " + this.id_vendeur + ";";
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
        String query = "INSERT INTO bien(superficie, nb_pieces, type, description, jardin, cave, ceillier, loggia, terrasse, garage, verranda, prix_min, frais_agence, id_vendeur) VALUES (" + this.getSuperficie() + "," + this.getNb_pieces() + ",'" + this.getType() + "','" + this.getDescription() + "'," + this.getJardin() + "," + this.getCave() + "," + this.getCeillier() + "," + this.getLoggia() + "," + this.getTerrasse() + "," + this.getGarage() + "," + this.getVerranda() + "," + this.getPrix_min() + "," + this.getFrais_agence() + "," + this.getIdVendeur() + ")";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour modifier une string d'une instance en base
     * @param champ
     * @param newValue
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateString(String champ, String newValue) throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "UPDATE bien SET " + champ + " = '" + newValue + "' WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour modifier un int de l'instance en base
     * @param champ
     * @param newValue
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateInt(String champ, Integer newValue) throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "UPDATE bien SET " + champ + " = " + newValue + " WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
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
        String query = "DELETE FROM bien WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour retouver une instance de la classe Bien en fonction de son id
     * @param id
     * @return une instance de la classe Bien
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static @NotNull Bien find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM bien WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
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
        db.close();
        return instance;
    }

    /**
     * methode pour recuperer une list de toutes les instances en base de Bien
     * @return une list de bien
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static @NotNull List findAll() throws SQLException, ClassNotFoundException {
        List<Bien> retour = new ArrayList<>();
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM bien";
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
     * methode pour verifier si une instance à une annonce en fonction de son id
     * @param id
     * @return un boolean de validation
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean hasAnnonce(Integer id) throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "select * from annonce where id_bien = " + id;
        ResultSet result = db.select(query);
        ResultSetMetaData meta = result.getMetaData();
        while (result.next()) {
            retour = true;
        }
        return retour;
    }

    /**
     * verifie si l'instance de Bien à une annonce
     * @return un boolean de validation
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean hasAnnonce() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "select * from annonce where id_bien = " + this.getId();
        ResultSet result = db.select(query);
        ResultSetMetaData meta = result.getMetaData();
        while (result.next()) {
            retour = true;
        }
        return retour;
    }
}
