package com.company;

import java.awt.*;
import javax.swing.*;

public class Application extends JFrame{

    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int)dimension.getHeight();
    int width = (int)dimension.getWidth();
    Toolbar toolbar;
    NewBien newBien;

    public Application() {
        //defini les preset
        setTitle("Stephi Place Logiciel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("width : " + this.width);
        System.out.println("height : " + this.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setMaximumSize(new Dimension(this.width, this.height));
        setLayout(new BorderLayout());

        //ajoute la toolbar
        try {
            this.toolbar = new Toolbar();
        } catch(Exception e) {
            System.out.println(e);
        }
        add(this.toolbar, BorderLayout.NORTH);

        //ajoute newBien
        this.newBien = new NewBien();
        add(this.newBien, BorderLayout.CENTER);

        //rend visible l'appli
        setVisible(true);
    }
}
