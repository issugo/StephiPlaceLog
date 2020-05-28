package com.company;

import com.backend.*;
import com.backend.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'affichage du panel des biens
 */
public class BienPanel extends JPanel {
    JButton choix1, choix2, choix3;

    public static int RIEN = 0;
    public static int TOUT_BIEN = 1;
    public static int BIEN_SANS_ANNONCE = 2;
    public static int FORM_BIEN = 3;

    /**
     * constructeur
     */
    public BienPanel() {
        this.removeAll();
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
        this.choix3.addActionListener(e -> {
            try {
                showCreateBienForm();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        this.setLayout(new GridLayout(1, 3));
        this.add(this.choix1);
        this.add(this.choix2);
        this.add(this.choix3);
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour afficher tout les biens
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showAllBiens() throws SQLException, ClassNotFoundException {
        this.removeAll();
        List<Bien> allBiens = Bien.findAll();
        this.setLayout(new GridLayout(allBiens.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        for (Bien bien: allBiens) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(16,2));
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
            temp.add(new JLabel("email vendeur :"));
            temp.add(new JLabel(Vendeur.find(bien.getIdVendeur()).findClient().getEmail()));
            temp.add(new JPanel());
            JButton suppression = new JButton("supprimer");
            suppression.addActionListener(e -> {
                try {
                    if (bien.hasAnnonce()) {
                        Annonce.findByIdBien(bien.getId()).delete();
                    }
                    for (Image image: bien.getImages()) {
                        image.delete();
                    }
                    bien.delete();
                    refresh(BienPanel.TOUT_BIEN);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
            temp.add(suppression);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(temp);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour afficher les biens qui n'ont pas d'annonces
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * methode pour afficher le formulaire de creation d'annonce pour les biens qui n'en avaient pas
     * @param id
     */
    public void showFormAnnonce(Integer id) {
        this.removeAll();
        System.out.println(id);
        this.setLayout(new GridLayout(3, 2));
        this.add(new JLabel("titre :"));
        JTextField temp2 = new JTextField();
        this.add(temp2);
        this.add(new JLabel("agent :"));
        JTextField temp3 = new JTextField();
        this.add(temp3);
        JButton temp = new JButton("save");
        temp.addActionListener(e -> {
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
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
        this.add(temp);
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour afficher le formulaire de création de bien
     */
    public void showCreateBienForm() {
        this.removeAll();
        // setup layout
        this.setLayout(new GridLayout(16, 2));
        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));

        // add superficie
        JLabel superficie = new JLabel("superficie :");
        JTextField superficieField = new JTextField();
        this.add(superficie);
        this.add(superficieField);

        // add nb_pieces
        JLabel nbPieces = new JLabel("nombre de pieces :");
        SpinnerNumberModel pieceModel = new SpinnerNumberModel(0, 0, 20, 1);
        JSpinner nbPiecesField = new JSpinner(pieceModel);
        this.add(nbPieces);
        this.add(nbPiecesField);

        // add type
        JLabel type = new JLabel("type : ");
        String[] myTypeTab = {"maison", "appartement"};
        JComboBox typeField = new JComboBox<>(myTypeTab);
        this.add(type);
        this.add(typeField);

        // add description
        JLabel description = new JLabel("description : ");
        JTextField descriptionField = new JTextField();
        this.add(description);
        this.add(descriptionField);

        // add jardin
        JLabel jardin = new JLabel("jardin : ");
        JCheckBox jardinField = new JCheckBox();
        this.add(jardin);
        this.add(jardinField);

        // add cave
        JLabel cave = new JLabel("cave : ");
        JCheckBox caveField = new JCheckBox();
        this.add(cave);
        this.add(caveField);

        // add ceillier
        JLabel ceillier = new JLabel("ceillier : ");
        JCheckBox ceillierField = new JCheckBox();
        this.add(ceillier);
        this.add(ceillierField);

        // add loggia
        JLabel loggia = new JLabel("loggia : ");
        JCheckBox loggiaField = new JCheckBox();
        this.add(loggia);
        this.add(loggiaField);

        // add terrasse
        JLabel terrasse = new JLabel("terrasse : ");
        JCheckBox terrasseField = new JCheckBox();
        this.add(terrasse);
        this.add(terrasseField);

        // add garage
        JLabel garage = new JLabel("garage : ");
        JCheckBox garageField = new JCheckBox();
        this.add(garage);
        this.add(garageField);

        // add verranda
        JLabel verranda = new JLabel("verranda : ");
        JCheckBox verrandaField = new JCheckBox();
        this.add(verranda);
        this.add(verrandaField);

        // add prixMin
        JLabel prixMin = new JLabel("prix minimum : ");
        SpinnerNumberModel prixModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        JSpinner prixMinField = new JSpinner(prixModel);
        this.add(prixMin);
        this.add(prixMinField);

        // add frais-agence
        JLabel fraisAgence = new JLabel("frais agence : ");
        SpinnerNumberModel fraisModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        JSpinner fraisAgenceField = new JSpinner(fraisModel);
        this.add(fraisAgence);
        this.add(fraisAgenceField);

        // add email vendeur
        JLabel emailVendeur = new JLabel("email vendeur : ");
        JTextField emailVendeurField = new JTextField();
        this.add(emailVendeur);
        this.add(emailVendeurField);

        // add images
        List<String> liensImage = new ArrayList<>();
        JLabel images = new JLabel("image(s) : ");
        JButton imagesField = new JButton("image(s)");
        imagesField.addActionListener(e -> {
            try {
                for(String lien: Image.getImages()) {
                    liensImage.add(lien);
                }
            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        });
        this.add(images);
        this.add(imagesField);

        //boutton submit
        this.add(new JPanel());
        JButton submit = new JButton("submit");
        submit.addActionListener(e -> {
            Client clientTemp = new Client();
            try {
                clientTemp = Client.findByEmail(emailVendeurField.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            try {
                if(!clientTemp.isVendeur()) {
                    JOptionPane.showMessageDialog(new JFrame(), "le client n'est pas vendeur");
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            if(clientTemp == null) {
                return;
            }
            Bien temp = new Bien();
            temp.setSuperficie(Float.valueOf(superficieField.getText()));
            temp.setFrais_agence(Float.valueOf(fraisAgenceField.getValue().toString()));
            temp.setPrix_min(Float.valueOf(prixMinField.getValue().toString()));
            temp.setVerranda(verrandaField.isSelected());
            temp.setTerrasse(terrasseField.isSelected());
            temp.setLoggia(loggiaField.isSelected());
            temp.setCeillier(ceillierField.isSelected());
            temp.setJardin(jardinField.isSelected());
            temp.setDescription(descriptionField.getText());
            temp.setType(typeField.getSelectedItem().toString());
            temp.setNb_pieces((Integer)nbPiecesField.getValue());
            temp.setCave(caveField.isSelected());
            temp.setGarage(garageField.isSelected());
            try {
                temp.setIdVendeur(Vendeur.findByIdClient(clientTemp.getId()).getId());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                temp.save();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            Mysql db = new Mysql("localhost", "3306", "stephiplacelog", "root", "");
            try {
                db.connect();
            } catch (ClassNotFoundException | SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            ResultSet recuperationId = null;
            try {
                recuperationId = db.select("SELECT * FROM bien ORDER BY id DESC LIMIT 1;");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                recuperationId.next();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Integer id = null;
            try {
                 id = recuperationId.getInt("id");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            for(String lien: liensImage) {
                Image temp2 = new Image();
                try {
                    temp2.setInBinaries(new FileInputStream(lien));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                temp2.setId_bien(id);
                try {
                    temp2.save();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                refresh(BienPanel.TOUT_BIEN);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });
        this.add(submit);
        this.revalidate();
        this.repaint();
    }

    /**
     * methode de rafraichissement du panel
     * @param etat
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void refresh(Integer etat) throws SQLException, ClassNotFoundException {
        if (etat == 0) {
            //blabla
        } else if (etat == 1) {
            this.showAllBiens();
        } else if (etat == 2) {
            this.showAllBiensWithoutAnnonce();
        } else if (etat == 3) {
            this.showCreateBienForm();
        }
    }
}
