package com.company;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.backend.*;
import com.backend.Image;

/**
 * Classe du panel des clients
 */
public class ClientPanel extends JPanel {

    JButton choix1, choix2, choix3;

    JLabel nom, prenom, email, addresse, tel, password, CIN;
    JTextField nomField, prenomField, emailField, addresseField, passwordField;
    JButton CINField, submit;
    JFormattedTextField telField;
    ClientPanel save;

    /**
     * constructeur
     */
    public ClientPanel() {
        this.save = this;
        this.choix1 = new JButton("voir les clients");
        this.choix1.addActionListener(e -> {
            try {
                showAllClients();
            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
                throwables.printStackTrace();
            }
        });
        this.choix2 = new JButton("cr\u00e9er client");
        this.choix2.addActionListener(e -> {
            try {
                showCreateVendeurForm();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        this.choix3 = new JButton("vois les vendeurs");
        this.choix3.addActionListener(e -> {
            try {
                showAllVendeurs();
            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
                throwables.printStackTrace();
            }
        });

        this.setLayout(new GridLayout(1, 3));
        this.add(this.choix1);
        this.add(this.choix2);
        this.add(this.choix3);
    }

    /**
     * methode pour afficher tout les clients
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showAllClients() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        this.removeAll();
        List<Client> allClient = Client.findAll();
        this.setLayout(new GridLayout(allClient.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Client client: allClient) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(7, 2));
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
            JButton modifier = new JButton("modifier");
            modifier.addActionListener(e -> {
                save.removeAll();
                try {
                    if(client.isVendeur()) {
                        save.setLayout(new GridLayout(7, 2));
                    } else {
                        save.setLayout(new GridLayout(6, 2));
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                //ajout pour le nom
                save.nom = new JLabel("nom :");
                save.add(save.nom);
                save.nomField = new JTextField();
                save.nomField.setText(client.getNom());
                save.add(save.nomField);

                //ajout pour le prenom
                save.prenom = new JLabel("prenom :");
                save.add(save.prenom);
                save.prenomField = new JTextField();
                save.prenomField.setText(client.getPrenom());
                save.add(save.prenomField);

                //ajout pour l'email
                save.email = new JLabel("email :");
                save.add(save.email);
                save.emailField = new JTextField();
                save.emailField.setText(client.getEmail());
                save.add(save.emailField);

                //ajout tel
                save.tel = new JLabel("telephone :");
                save.add(save.tel);
                try {
                    save.telField = new JFormattedTextField(new MaskFormatter("##.##.##.##.##"));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                save.telField.setText(client.getTelephone());
                save.add(save.telField);

                //ajout carte identite
                final String[] lienImage = {null};
                try {
                    if (client.isVendeur()) {
                        save.CIN = new JLabel("carte d'identite :");
                        save.add(save.CIN);
                        save.CINField = new JButton("images");
                        save.CINField.addActionListener(j -> {
                            try {
                                lienImage[0] = Image.getImage();
                                save.CINField.setText(lienImage[0]);
                            } catch (IOException | SQLException ioException) {
                                ioException.printStackTrace();
                            }
                        });
                        save.add(save.CINField);
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                //boutton de submit
                save.submit = new JButton("submit");
                save.submit.addActionListener(h -> {

                    if(!Client.isValidEmail(save.emailField.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "L'email n'est pas valide");
                    } else {
                        client.setNom(save.nomField.getText());
                        client.setPrenom(save.prenomField.getText());
                        client.setEmail(save.emailField.getText());
                        client.setTelephone(save.telField.getText());
                        try {
                            client.update();
                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        }
                        Integer idTemp = null;
                        try {
                            idTemp = Objects.requireNonNull(Client.findByEmail(save.emailField.getText())).getId();
                        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            if (client.isVendeur()) {
                                Vendeur temp2 = null;
                                try {
                                    temp2 = Vendeur.findByIdClient(client.getId());
                                } catch (SQLException | ClassNotFoundException throwables) {
                                    throwables.printStackTrace();
                                }
                                try {
                                    temp2.setCINTemp(new FileInputStream(lienImage[0]));
                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                }
                                try {
                                    temp2.update();
                                } catch (SQLException | ClassNotFoundException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        try {
                            refresh(1);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
                save.add(new JPanel());
                save.add(save.submit);

                save.revalidate();
                save.repaint();
            });
            temp.add(modifier);
            JButton supprimer = new JButton("supprimer");
            supprimer.addActionListener(e -> {
                try {
                    if (client.isVendeur()) {
                        List<Bien> biens = Vendeur.findByIdClient(client.getId()).getBiens();
                        List<Annonce> annonces = new ArrayList<>();
                        for (Bien bien: biens) {
                            if (bien.hasAnnonce()) {
                                annonces.add(Annonce.findByIdBien(bien.getId()));
                            }
                        }
                        for (Annonce annonce: annonces) {
                            annonce.delete();
                        }
                        for (Bien bien: biens) {
                            bien.delete();
                        }
                        Vendeur.findByIdClient(client.getId()).delete();
                    }
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    client.delete();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    refresh(1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            temp.add(supprimer);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(temp);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour afficher tout les vendeurs
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showAllVendeurs() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        this.removeAll();
        List<Client> allClient = Client.findAll();
        this.setLayout(new GridLayout(allClient.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Client client: allClient) {
            if (client.isVendeur()) {
                JPanel temp = new JPanel();
                temp.setLayout(new GridLayout(7, 2));
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
                JButton modifier = new JButton("modifier");
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        save.removeAll();
                        try {
                            if (client.isVendeur()) {
                                save.setLayout(new GridLayout(7, 2));
                            } else {
                                new GridLayout(6, 2);
                            }
                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        }

                        //ajout pour le nom
                        save.nom = new JLabel("nom :");
                        save.add(save.nom);
                        save.nomField = new JTextField();
                        save.nomField.setText(client.getNom());
                        save.add(save.nomField);

                        //ajout pour le prenom
                        save.prenom = new JLabel("prenom :");
                        save.add(save.prenom);
                        save.prenomField = new JTextField();
                        save.prenomField.setText(client.getPrenom());
                        save.add(save.prenomField);

                        //ajout pour l'email
                        save.email = new JLabel("email :");
                        save.add(save.email);
                        save.emailField = new JTextField();
                        save.emailField.setText(client.getEmail());
                        save.add(save.emailField);

                        //ajout tel
                        save.tel = new JLabel("telephone :");
                        save.add(save.tel);
                        try {
                            save.telField = new JFormattedTextField(new MaskFormatter("##.##.##.##.##"));
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        save.telField.setText(client.getTelephone());
                        save.add(save.telField);

                        //ajout carte identite
                        final String[] lienImage = {null};
                        try {
                            if (client.isVendeur()) {
                                save.CIN = new JLabel("carte d'identite :");
                                save.add(save.CIN);
                                save.CINField = new JButton("images");
                                save.CINField.addActionListener(j -> {
                                    try {
                                        lienImage[0] = Image.getImage();
                                        save.CINField.setText(lienImage[0]);
                                    } catch (IOException | SQLException ioException) {
                                        ioException.printStackTrace();
                                    }
                                });
                                save.add(save.CINField);
                            }
                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        }

                        //boutton de submit
                        save.submit = new JButton("submit");
                        save.submit.addActionListener(h -> {

                            if (!Client.isValidEmail(save.emailField.getText())) {
                                JOptionPane.showMessageDialog(new JFrame(), "L'email n'est pas valide");
                            } else {
                                client.setNom(save.nomField.getText());
                                client.setPrenom(save.prenomField.getText());
                                client.setEmail(save.emailField.getText());
                                client.setTelephone(save.telField.getText());
                                try {
                                    client.update();
                                } catch (SQLException | ClassNotFoundException throwables) {
                                    throwables.printStackTrace();
                                }
                                Integer idTemp = null;
                                try {
                                    idTemp = Objects.requireNonNull(Client.findByEmail(save.emailField.getText())).getId();
                                } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
                                    throwables.printStackTrace();
                                }
                                try {
                                    if (client.isVendeur()) {
                                        Vendeur temp2 = null;
                                        try {
                                            temp2 = Vendeur.findByIdClient(client.getId());
                                        } catch (SQLException | ClassNotFoundException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        try {
                                            temp2.setCINTemp(new FileInputStream(lienImage[0]));
                                        } catch (FileNotFoundException fileNotFoundException) {
                                            fileNotFoundException.printStackTrace();
                                        }
                                        try {
                                            temp2.update();
                                        } catch (SQLException | ClassNotFoundException throwables) {
                                            throwables.printStackTrace();
                                        }
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                                try {
                                    refresh(1);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                        save.add(new JPanel());
                        save.add(save.submit);

                        save.revalidate();
                        save.repaint();
                    }
                });
                temp.add(modifier);
                JButton supprimer = new JButton("supprimer");
                supprimer.addActionListener(e -> {
                    try {
                        if (client.isVendeur()) {
                            List<Bien> biens = Vendeur.findByIdClient(client.getId()).getBiens();
                            List<Annonce> annonces = new ArrayList<>();
                            for (Bien bien : biens) {
                                if (bien.hasAnnonce()) {
                                    annonces.add(Annonce.findByIdBien(bien.getId()));
                                }
                            }
                            for (Annonce annonce : annonces) {
                                annonce.delete();
                            }
                            for (Bien bien : biens) {
                                bien.delete();
                            }
                            Vendeur.findByIdClient(client.getId()).delete();
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        client.delete();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        refresh(1);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
                temp.add(supprimer);
                temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(temp);
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour créer un client en tant que vendeur
     * @throws Exception
     */
    public void showCreateVendeurForm() throws Exception {
        this.removeAll();
        this.setLayout(new GridLayout(8,2));

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
        final String[] lienImage = {null};
        this.CIN = new JLabel("carte d'identite :");
        this.add(this.CIN);
        this.CINField = new JButton("images");
        this.CINField.addActionListener(e -> {
            try {
                lienImage[0] = Image.getImage();
                this.CINField.setText(lienImage[0]);
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        });
        this.add(this.CINField);

        //boutton de submit
        this.submit = new JButton("submit");
        this.submit.addActionListener(e -> {

            if(!Client.isValidEmail(this.emailField.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "L'email n'est pas valide");
            } else {
                Client temp = new Client();
                temp.setNom(this.nomField.getText());
                temp.setPrenom(this.prenomField.getText());
                temp.setEmail(this.emailField.getText());
                temp.setTelephone(this.telField.getText());
                try {
                    temp.setPassword(this.passwordField.getText());
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
                try {
                    temp.save();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                Integer idTemp = null;
                try {
                    idTemp = Objects.requireNonNull(Client.findByEmail(this.emailField.getText())).getId();
                } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException throwables) {
                    throwables.printStackTrace();
                }
                Vendeur temp2 = new Vendeur();
                temp2.setIdClient(idTemp);
                try {
                    temp2.setCINTemp(new FileInputStream(lienImage[0]));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {
                    temp2.save();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    refresh(1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        this.add(new JPanel());
        this.add(this.submit);

        this.revalidate();
        this.repaint();

    }

    /**
     * methode de rafraichissement du panel
     * @param etat
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void refresh(Integer etat) throws Exception {
        if (etat == 0) {
            //blabla
        } else if (etat == 1) {
            this.showAllClients();
        } else if (etat == 2) {
            this.showCreateVendeurForm();
        }
    }
}
