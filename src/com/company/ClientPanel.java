package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.backend.Client;
import com.backend.Image;
import org.jetbrains.annotations.NotNull;

public class ClientPanel extends JPanel {

    JButton choix1, choix2;

    JLabel nom, prenom, email, addresse, tel, password, CIN;
    JTextField nomField, prenomField, emailField, addresseField, passwordField;
    JButton CINField, submit;
    JFormattedTextField telField;

    public ClientPanel() {
        this.choix1 = new JButton("voir les clients");
        this.choix1.addActionListener(e -> {
            try {
                showAllClients();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
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

    public void showAllClients() throws SQLException, ClassNotFoundException {
        this.removeAll();
        List<Client> allClient = Client.findAll();
        this.setLayout(new GridLayout(allClient.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Client client: allClient) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(6, 2));
            temp.add(new JLabel("id :"));
            temp.add(new JLabel(String.valueOf(client.getId())));
            temp.add(new JLabel("nom :"));
            temp.add(new JLabel(client.getNom()));
            temp.add(new JLabel("prenom :"));
            temp.add(new JLabel(client.getPrenom()));
            temp.add(new JLabel("email :"));
            temp.add(new JLabel(client.getEmail()));
            temp.add(new JLabel("telephone :"));
            temp.add(new JLabel(client.getTelephone()));
            temp.add(new JLabel("vendeur :"));
            if (client.isVendeur()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(temp);
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
        this.CINField.addActionListener(e -> {
            try {
                getCartesIdentites();
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        });
        this.add(this.CINField);

        //boutton de submit
        this.submit = new JButton("submit");
        this.add(new JPanel());
        this.add(this.submit);

        this.revalidate();
        this.repaint();

    }

    public static void getCartesIdentites() throws IOException, SQLException {
        Image.getImages();
    }



}
