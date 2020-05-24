package com.company;

import com.backend.Annonce;
import com.backend.Bien;
import com.backend.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BienPanel extends JPanel {
    JButton choix1, choix2, choix3;

    public BienPanel() {
        this.choix1 = new JButton("voir tout les biens");
        this.choix1.addActionListener(e -> {
            try {
                showAllBiens();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
        this.choix2 = new JButton("voir les biens sans annonces");
        this.choix2.addActionListener(e -> {
            try {
                showAllBiensWithoutAnnonce();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
        this.choix3 = new JButton("créer bien");
        /*this.choix3.addActionListener(e -> {
            try {
                showCreateAnnonceForm();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });*/

        this.setLayout(new GridLayout(1, 3));
        this.add(this.choix1);
        this.add(this.choix2);
        this.add(this.choix3);
    }

    public void showAllBiens() throws SQLException, ClassNotFoundException {
        this.removeAll();
        List<Bien> allBiens = Bien.findAll();
        this.setLayout(new GridLayout(allBiens.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Bien bien: allBiens) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(14,2));
            temp.add(new JLabel("id :"));
            temp.add(new JLabel(String.valueOf(bien.getId())));
            temp.add(new JLabel("superficie :"));
            temp.add(new JLabel(String.valueOf(bien.getSuperficie())));
            temp.add(new JLabel("nb_pieces :"));
            temp.add(new JLabel(String.valueOf(bien.getNb_pieces())));
            temp.add(new JLabel("type :"));
            temp.add(new JLabel(bien.getType()));
            temp.add(new JLabel("description :"));
            temp.add(new JLabel(bien.getDescription()));
            temp.add(new JLabel("jardin :"));
            if (bien.getJardin()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("cave :"));
            if (bien.getCave()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("ceillier"));
            if (bien.getCeillier()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("loggia :"));
            if (bien.getLoggia()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("terrasse :"));
            if (bien.getTerrasse()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("garage :"));
            if (bien.getGarage()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("verranda :"));
            if (bien.getVerranda()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("prix_min :"));
            temp.add(new JLabel(String.valueOf(bien.getPrix_min())));
            temp.add(new JLabel("frais_agence :"));
            temp.add(new JLabel(String.valueOf(bien.getFrais_agence())));
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(temp);
        }
        this.revalidate();
        this.repaint();
    }

    public void showAllBiensWithoutAnnonce() throws SQLException, ClassNotFoundException {
        this.removeAll();
        List<Bien> allBiens = Bien.findAll();
        List<Bien> allBiensWithoutAnnonce = new ArrayList<>();
        for (Bien bien: allBiens) {
            if (!bien.hasAnnonce()) {
                allBiensWithoutAnnonce.add(bien);
            }
        }
        this.setLayout(new GridLayout(allBiensWithoutAnnonce.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Bien bien: allBiensWithoutAnnonce) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(15,2));
            temp.add(new JLabel("id :"));
            temp.add(new JLabel(String.valueOf(bien.getId())));
            temp.add(new JLabel("superficie :"));
            temp.add(new JLabel(String.valueOf(bien.getSuperficie())));
            temp.add(new JLabel("nb_pieces :"));
            temp.add(new JLabel(String.valueOf(bien.getNb_pieces())));
            temp.add(new JLabel("type :"));
            temp.add(new JLabel(bien.getType()));
            temp.add(new JLabel("description :"));
            temp.add(new JLabel(bien.getDescription()));
            temp.add(new JLabel("jardin :"));
            if (bien.getJardin()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("cave :"));
            if (bien.getCave()) {
                temp.add(new JLabel("oui"));
            } else {
               temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("ceillier"));
            if (bien.getCeillier()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("loggia :"));
            if (bien.getLoggia()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("terrasse :"));
            if (bien.getTerrasse()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("garage :"));
            if (bien.getGarage()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("verranda :"));
            if (bien.getVerranda()) {
                temp.add(new JLabel("oui"));
            } else {
                temp.add(new JLabel("non"));
            }
            temp.add(new JLabel("prix_min :"));
            temp.add(new JLabel(String.valueOf(bien.getPrix_min())));
            temp.add(new JLabel("frais_agence :"));
            temp.add(new JLabel(String.valueOf(bien.getFrais_agence())));
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JButton temp2 = new JButton("créer annonce");
            temp2.addActionListener(e -> showFormAnnonce(bien.getId()));
            temp.add(temp2);
            this.add(temp);
        }
        this.revalidate();
        this.repaint();
    }

    public void showFormAnnonce(Integer id) {
        this.removeAll();
        System.out.println(id);
        this.setLayout(new GridLayout(2, 2));
        this.add(new JLabel("titre :"));
        JTextField temp2 = new JTextField();
        this.add(temp2);
        this.add(new JLabel("agent :"));
        JTextField temp3 = new JTextField();
        this.add(temp3);
        JButton temp = new JButton("save");
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id_agent = null;
                ResultSet result = null;
                Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
                try {
                    db.connect();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //recuperation code agent
                String query1 = "SELECT * FROM agent WHERE code_agent = " + Integer.parseInt(temp3.getText());
                try {
                    result = db.select(query1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                while(true) {
                    try {
                        if (!result.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        id_agent = result.getInt("id");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                //sauvegarde annonce
                Annonce annonce = new Annonce();
                annonce.setTitre(temp2.getText());
                annonce.setId_agent(id_agent);
                annonce.setId_bien(id);
                try {
                    annonce.save();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        this.add(temp);
        this.revalidate();
        this.repaint();
    }
}
