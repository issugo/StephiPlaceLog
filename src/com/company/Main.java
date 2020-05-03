package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            new Application();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
