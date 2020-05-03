package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Application extends JFrame{

    //main variable de l'app
    Application save;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int)dimension.getHeight();
    int width = (int)dimension.getWidth();

    //toolbar
    JButton close, annoncesTrigger, clientsTrigger;
    JToolBar toolbar;

    //panel que l'on affiche
    JPanel mainPanel;
    NewBien newBien;
    Client clientsPanel;

    public Application() throws Exception {
        //preparation des components
        this.save = this;
        this.mainPanel = new JPanel();
        this.newBien = new NewBien();
        this.clientsPanel = new Client();

        //set css default
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        //defini les preset
        setTitle("Stephi Place Logiciel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("width : " + this.width);
        System.out.println("height : " + this.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setMaximumSize(new Dimension(this.width, this.height));
        setLayout(new BorderLayout());

        // ajout toolbar
        this.toolbar = new JToolBar();
        //set newAnnonces button
        this.annoncesTrigger = new JButton("annonces");
        this.annoncesTrigger.addActionListener(e -> {
            //on enleve l'affichage du centre et mon met le formulaire de nouveau bien
            save.afficherBienPanel();
        });
        this.toolbar.add(this.annoncesTrigger);
        //set clients button
        this.clientsTrigger = new JButton("clients");
        this.clientsTrigger.addActionListener(e -> {
            //on enleve l'affichage du centre et mon met le formulaire de nouveau bien
            save.afficherClientPanel();
        });
        this.toolbar.add(this.clientsTrigger);
        //set close button
        this.close = new JButton(new ImageIcon( "icons/fermer.png"));
        this.close.addActionListener(e -> System.exit(0));
        this.toolbar.add(this.close);
        add(this.toolbar, BorderLayout.NORTH);

        //ajout mainPanel
        add(this.mainPanel, BorderLayout.CENTER);

        //rend visible l'appli
        setVisible(true);
    }

    public void afficherClientPanel() {
        try {
            this.newBien = new NewBien();
            this.clientsPanel = new Client();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.mainPanel.removeAll();
        this.mainPanel.add(this.clientsPanel, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    public void afficherBienPanel() {
        this.mainPanel.removeAll();
        this.mainPanel.add(newBien, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        try {
            this.newBien = new NewBien();
            this.clientsPanel = new Client();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
