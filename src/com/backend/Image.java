package com.backend;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Image pour les images des biens
 */
public class Image {

    Integer id, id_bien;
    Blob binaries;
    InputStream inBinaries;

    /**
     * constructeur
     */
    public Image() {
        this.id = null;
        this.id_bien = null;
        this.binaries = null;
    }

    /**
     * constructeur avec des paramètres déjà défini
     * @param binaries
     * @param id_bien
     */
    public Image(Blob binaries, Integer id_bien) {
        this.id_bien = id_bien;
        this.binaries = binaries;
    }

    /**
     * setter de l'id de l'image
     * @param id
     * @return l'id de l'image
     */
    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    /**
     * getter de l'id de l'image
     * @return l'id de l'image
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter de l'id du bien associé
     * @param id_bien
     * @return l'id du bien associé
     */
    public Integer setId_bien(Integer id_bien) {
        this.id_bien = id_bien;
        return this.id_bien;
    }

    /**
     * getter de l'id du bien associé
     * @return l'id du bien associé
     */
    public Integer getId_bien() {
        return this.id_bien;
    }

    /**
     * setter des binaries (blob) de l'image
     * @param binaries
     * @return le blob de l'image
     */
    public Blob setBinaries(Blob binaries) {
        this.binaries = binaries;
        return this.binaries;
    }

    /**
     * getter du blob de l'image
     * @return le blob de l'image
     */
    public Blob getBinaries() {
        return this.binaries;
    }

    /**
     * setter de l'InputStream pour sauver l'image en base
     * @param in
     * @return l'inputstream de l'image
     */
    public InputStream setInBinaries(InputStream in){
        this.inBinaries = in;
        return this.inBinaries;
    }

    /**
     * getter de l'InputStream de l'image
     * @return l'InputStream de l'image
     */
    public InputStream getInBinaries() {
        return this.inBinaries;
    }

    /**
     * methode pour récupérer une instance de Image en fonction de son id
     * @param id
     * @return une instance de Image
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Image find(Integer id) throws SQLException, ClassNotFoundException {
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "SELECT * FROM image WHERE id = ";
        query += id;
        ResultSet result = db.select(query);
        result.next();
        Image instance = new Image();
        instance.setId(result.getInt("id"));
        instance.setId_bien(result.getInt("id_bien"));
        instance.setBinaries(result.getBlob("image"));
        return instance;
    }

    /**
     * sauvegarde une instance de Image en base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean save() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        String url = "jdbc:mysql://localhost:3306/stephiplacelog";
        Connection con = DriverManager.getConnection(url, "root", "");
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO image(`image`, `id_bien`) VALUES(?, ?)");
        pstmt.setBlob(1, this.inBinaries);
        pstmt.setInt(2, this.getId_bien());
        Integer test  = pstmt.executeUpdate();
        if (test > 1) {
            retour = true;
        }
        con.close();
        return retour;
    }

    /**
     * methode pour supprimer les images de la base
     * @return un boolean de verification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean delete() throws SQLException, ClassNotFoundException {
        boolean retour = false;
        Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
        db.connect();
        String query = "DELETE FROM image WHERE id = " + this.getId() + ";";
        Integer test = db.insertOrUpdate(query);
        if (test > 1) {
            retour = true;
        }
        db.close();
        return retour;
    }

    /**
     * methode pour récupérer plusieurs liens d'images à upload
     * @return une List de lien
     * @throws IOException
     * @throws SQLException
     */
    public static List<String> getImages() throws IOException, SQLException {
        List<String> allLiens = new ArrayList<>();
        JFileChooser choix = new JFileChooser();
        choix.setMultiSelectionEnabled(true);
        MyFilter mf = new MyFilter(new String[] {"gif", "jpeg", "tif", "jpg", "png"}, "fichiers images");
        choix.setFileFilter(mf);
        int retour = choix.showOpenDialog(choix);
        if (retour == JFileChooser.APPROVE_OPTION) {
            //fichiers choisis donc sortie = OK
            File[] fs = choix.getSelectedFiles();
            for (File f : fs) {
                allLiens.add(f.getAbsolutePath());
            }
        } else {
            System.out.println("aucun fichier choisi");
        }
        return allLiens;
    }

    /**
     * methode pour récupérer un lien d'images à upload
     * @return une List de lien
     * @throws IOException
     * @throws SQLException
     */
    public static String getImage() throws IOException, SQLException {
        String lien = null;
        JFileChooser choix = new JFileChooser();
        choix.setMultiSelectionEnabled(false);
        MyFilter mf = new MyFilter(new String[] {"gif", "jpeg", "tif", "jpg", "png"}, "fichiers images");
        choix.setFileFilter(mf);
        int retour = choix.showOpenDialog(choix);
        if (retour == JFileChooser.APPROVE_OPTION) {
            //fichiers choisis donc sortie = OK
            File fs = choix.getSelectedFile();
            lien = fs.getAbsolutePath();
        } else {
            System.out.println("aucun fichier choisi");
        }
        return lien;
    }
}
