package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Classe principale de l'application
 */
public class Application extends JFrame{

    //main variable de l'app
    Application save;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int)dimension.getHeight();
    int width = (int)dimension.getWidth();
    Integer idAgentCo;

    //toolbar
    JButton close, annoncesTrigger, clientsTrigger, biensTrigger;
    JToolBar toolbar;

    //panel que l'on affiche
    JPanel mainPanel;
    AnnoncePanel annoncePanel;
    ClientPanel clientsPanel;
    BienPanel bienPanel;

    /**
     * constructeur
     * @throws Exception
     */
    public Application(Integer idAgent) throws Exception {
        //preparation des components
        this.setTitle("Stephi Place Software");
        this.save = this;
        this.idAgentCo = idAgent;
        this.mainPanel = new JPanel();
        this.annoncePanel = new AnnoncePanel(this.idAgentCo);
        this.clientsPanel = new ClientPanel();
        this.bienPanel = new BienPanel();


        //set css default
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        //defini les preset
        setTitle("Stephi Place Logiciel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            save.afficherAnnoncePanel();
        });
        this.toolbar.add(this.annoncesTrigger);
        //set clients button
        this.clientsTrigger = new JButton("clients");
        this.clientsTrigger.addActionListener(e -> {
            //on enleve l'affichage du centre et mon met le formulaire de nouveau bien
            save.afficherClientPanel();
        });
        this.toolbar.add(this.clientsTrigger);
        //set biens button
        this.biensTrigger = new JButton("biens");
        this.biensTrigger.addActionListener(e -> {
            //on enleve l'affichage du centre et mon met le formulaire de nouveau bien
            save.afficherBienPanel();
        });
        this.toolbar.add(this.biensTrigger);
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

    /**
     * methode pour afficher le panel client
     */
    public void afficherClientPanel() {
        try {
            this.annoncePanel = new AnnoncePanel(this.idAgentCo);
            this.clientsPanel = new ClientPanel();
            this.bienPanel = new BienPanel();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.mainPanel.removeAll();
        this.mainPanel.add(this.clientsPanel, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    /**
     * methode pour afficher le panel des annonces
     */
    public void afficherAnnoncePanel() {
        try {
            this.annoncePanel = new AnnoncePanel(this.idAgentCo);
            this.clientsPanel = new ClientPanel();
            this.bienPanel = new BienPanel();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.mainPanel.removeAll();
        this.mainPanel.add(this.annoncePanel, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    /**
     * methode pour afficher le panel des biens
     */
    public void afficherBienPanel() {
        try {
            this.annoncePanel = new AnnoncePanel(this.idAgentCo);
            this.clientsPanel = new ClientPanel();
            this.bienPanel = new BienPanel();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.mainPanel.removeAll();
        this.mainPanel.add(this.bienPanel, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();

    }

}
