package com.backend;

import javax.swing.*;
import java.io.*;
import java.sql.*;

public class Image {

    Integer id, id_bien;
    Blob binaries;
    InputStream inBinaries;

    public Image() {
        this.id = null;
        this.id_bien = null;
        this.binaries = null;
    }

    public Image(Blob binaries, Integer id_bien) {
        this.id_bien = id_bien;
        this.binaries = binaries;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public Integer setId_bien(Integer id_bien) {
        this.id_bien = id_bien;
        return this.id_bien;
    }

    public Integer getId_bien() {
        return this.id_bien;
    }

    public Blob setBinaries(Blob binaries) {
        this.binaries = binaries;
        return this.binaries;
    }

    public Blob getBinaries() {
        return this.binaries;
    }

    public InputStream setInBinaries(InputStream in){
        this.inBinaries = in;
        return this.inBinaries;
    }

    public InputStream getInBinaries() {
        return this.inBinaries;
    }

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

    private boolean save() throws SQLException, ClassNotFoundException {
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

    public static void getImages() throws IOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/stephiplacelog";
        Connection con = DriverManager.getConnection(url, "root", "");
        JFileChooser choix = new JFileChooser();
        choix.setMultiSelectionEnabled(true);
        MyFilter mf = new MyFilter(new String[] {"gif", "jpeg", "tif", "jpg", "png"}, "fichiers images");
        choix.setFileFilter(mf);
        int retour = choix.showOpenDialog(choix);
        if (retour == JFileChooser.APPROVE_OPTION) {
            //fichiers choisis donc sortie = OK
            File[] fs = choix.getSelectedFiles();
            for (File f : fs) {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO image(`image`, `id_bien`) VALUES(?, ?)");
                InputStream in = new FileInputStream(f.getAbsolutePath());
                pstmt.setBlob(1, in);
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
            }
        } else {
            System.out.println("aucun fichier choisi");
        }
    }
}
