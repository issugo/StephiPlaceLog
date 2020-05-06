package com.company;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

import com.backend.ClientBack;
import com.backend.Images;

public class Client extends JPanel {

    JButton choix1, choix2;

    JLabel nom, prenom, email, addresse, tel, password, CIN;
    JTextField nomField, prenomField, emailField, addresseField, passwordField;
    JButton CINField, submit;
    JFormattedTextField telField;

    public Client() {
        this.choix1 = new JButton("voir les clients");
        this.choix1.addActionListener(e -> showAllClients());
        this.choix2 = new JButton("créer client");
        this.choix2.addActionListener(e -> {
            try {
                showCreateVendeurForm();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        this.setLayout(new GridLayout(1, 2));
        this.add(this.choix1);
        this.add(this.choix2);
    }

    public void showAllClients() {
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String client: ClientBack.getAllClient()) {
            this.add(new JLabel(client));
        }
        this.revalidate();
        this.repaint();
    }

    public void showCreateVendeurForm() throws Exception {
        this.removeAll();
        this.setLayout(new GridLayout(9,2));

        //ajout pour le nom
        this.nom = new JLabel("nom :");
        this.add(this.nom);
        this.nomField = new JTextField();
        this.add(this.nomField);

        //ajout pour le prenom
        this.prenom = new JLabel("prenom :");
        this.add(this.prenom);
        this.prenomField = new JTextField();
        this.add(this.prenomField);

        //ajout pour l'email
        this.email = new JLabel("email :");
        this.add(this.email);
        this.emailField = new JTextField();
        this.add(this.emailField);

        //ajout addresse
        this.addresse = new JLabel("addresse :");
        this.add(this.addresse);
        this.addresseField = new JTextField();
        this.add(this.addresseField);

        //ajout tel
        this.tel = new JLabel("telephone :");
        this.add(this.tel);
        this.telField = new JFormattedTextField(new MaskFormatter("##.##.##.##.##"));
        this.add(this.telField);

        //ajout password
        this.password = new JLabel("mot de passe :");
        this.add(this.password);
        this.passwordField = new JTextField();
        this.add(this.passwordField);

        //ajout carte identite
        this.CIN = new JLabel("carte d'identite :");
        this.add(this.CIN);
        this.CINField = new JButton("images");
        this.CINField.addActionListener(e -> getCartesIdentites());
        this.add(this.CINField);

        //boutton de submit
        this.submit = new JButton("submit");
        this.add(new JPanel());
        this.add(this.submit);

        this.revalidate();
        this.repaint();

    }

    public static void getCartesIdentites() {
        Images.getImage();
    }



}
