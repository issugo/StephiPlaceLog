package com.company;

import java.sql.SQLException;

/**
 * Main classe du projet
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            new Application();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
