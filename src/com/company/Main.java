package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            new Application();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
