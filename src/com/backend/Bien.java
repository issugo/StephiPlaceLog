package com.backend;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bien {

    Integer id, nb_pieces;
    Boolean jardin, cave, ceillier, loggia, terrasse, garage, verranda;
    String description, type;
    Float superficie, prix_min, frais_agence;

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
    }

    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer setNb_pieces(Integer nb_pieces) {
        this.nb_pieces = nb_pieces;
        return this.nb_pieces;
    }

    public Integer getNb_pieces() {
        return this.nb_pieces;
    }

    public boolean setJardin(boolean jardin) {
        this.jardin = jardin;
        return this.jardin;
    }

    public boolean getJardin() {
        return this.jardin;
    }

    public boolean setCave(boolean cave){
        this.cave = cave;
        return this.cave;
    }

    public boolean getCave() {
        return this.cave;
    }

    public boolean setCeillier(boolean ceillier) {
        this.ceillier = ceillier;
        return this.ceillier;
    }

    public boolean getCeillier() {
        return this.ceillier;
    }

    public boolean setLoggia(boolean loggia) {
        this.loggia = loggia;
        return this.loggia;
    }

    public boolean getLoggia() {
        return this.loggia;
    }

    public boolean setTerrasse(boolean terrasse) {
        this.terrasse = terrasse;
        return this.terrasse;
    }

    public boolean getTerrasse() {
        return this.terrasse;
    }

    public boolean setGarage(boolean garage) {
        this.garage = garage;
        return this.garage;
    }

    public boolean getGarage() {
        return this.garage;
    }

    public boolean setVerranda(boolean verranda) {
        this.verranda = verranda;
        return this.verranda;
    }

    public boolean getVerranda() {
        return this.verranda;
    }

    public Float setSuperficie(Float superficie) {
        this.superficie = superficie;
        return this.superficie;
    }

    public Float getSuperficie() {
        return this.superficie;
    }

    public String setType(String type) {
        this.type = type;
        return this.type;
    }

    public String getType() {
        return this.type;
    }

    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public float setPrix_min(float prix_min) {
        this.prix_min = prix_min;
        return this.prix_min;
    }

    public float getPrix_min() {
        return this.prix_min;
    }

    public float setFrais_agence(float frais_agence) {
        this.frais_agence = frais_agence;
        return this.frais_agence;
    }

    public float getFrais_agence() {
        return this.frais_agence;
    }

    public String toString() {
        return "id : " + this.id + "; superficie : " + this.superficie + "; nb pieces : " + this.nb_pieces + "; type : " + this.type + "; description : " + this.description + "; jardin " + this.jardin + "; cave : " + this.cave + "; ceillier : " + this.ceillier + "; loggia : " + this.loggia + "; terrasse : " + this.terrasse + "; garage : " + this.garage + "; verranda : " + this.verranda + "; prix min : " + this.prix_min + "; frais agences : " + this.frais_agence + ";";
    }

    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "INSERT INTO bien(superficie, nb_pieces, type, description, jardin, cave, ceillier, loggia, terrasse, garage, verranda, prix_min, frais_agence) VALUES (" + this.getSuperficie() + "," + this.getNb_pieces() + "," + this.getType() + "," + this.getDescription() + "," + this.getJardin() + "," + this.getCave() + "," + this.getCeillier() + "," + this.getLoggia() + "," + this.getTerrasse() + "," + this.getGarage() + "," + this.getVerranda() + "," + this.getPrix_min() + "," + this.getFrais_agence() + ")";
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
        String query = "UPDATE bien SET " + champ + " = " + newValue + " WHERE id = " + this.getId() + ";";
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
        String query = "DELETE FROM bien WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

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
        return instance;
    }

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
            retour.add(instance);
        }
        return retour;
    }

}
