package com.company;

/**
 * Main classe du projet
 */
public class Main {

    public static void main(String[] args) {
        try {
            //new Application();
            new ConnexionPanel();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
