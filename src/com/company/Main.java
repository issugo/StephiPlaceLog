package com.company;

import com.backend.Annonce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*try {
            new Application();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }*/

        Annonce voila = new Annonce();
        voila.getAllAnnonces();
    }


}
