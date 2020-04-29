package com.company;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Toolbar extends JToolBar {

    JButton close, newAnnonces;

    public Toolbar() throws Exception{
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        //set newAnnonces button
        this.newAnnonces = new JButton("annonces");
        add(this.newAnnonces);

        //set close button
        this.close = new JButton(new ImageIcon( "icons/fermer.png"));
        this.close.addActionListener(e -> System.exit(0));
        add(this.close);
    }
}
