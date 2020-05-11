package com.company;

import com.backend.Annonce;
import com.backend.Image;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

public class AnnoncePanel extends JPanel {

    JButton choix1, choix2;

    JLabel superficie, nbPieces, type, description, jardin, cave, ceillier, loggia, terrasse, garage, verranda, prixMin, prixVente, images;
    JTextField descriptionField;
    JCheckBox jardinField, caveField, ceillierField, loggiaField, terrasseField, garageField, verrandaField;
    JComboBox<String> typeField;
    JSpinner nbPiecesField, prixMinField, prixVenteField;
    SpinnerModel pieceModel, prixModel;
    JTextField superficieField;
    JButton imagesField, submit;

    String[] myTypeTab = {"maison", "appartement"};

    public AnnoncePanel() {

        this.choix1 = new JButton("voir les annonces");
        //this.choix1.addActionListener(e -> showAllAnnonces());
        this.choix2 = new JButton("créer annonce");
        this.choix2.addActionListener(e -> {
            try {
                showCreateAnnonceForm();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        this.setLayout(new GridLayout(1, 2));
        this.add(this.choix1);
        this.add(this.choix2);

    }

    public void showAllAnnonces() throws SQLException, ClassNotFoundException {
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));
        /*
         * voila quoi, faut afficher les annonces
         */
        this.revalidate();
        this.repaint();
    }

    public void showCreateAnnonceForm() {
        this.removeAll();
        // setup layout
        this.setLayout(new GridLayout(15, 2));
        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));

        // add superficie
        this.superficie = new JLabel("superficie :");
        this.superficieField = new JTextField();
        this.add(this.superficie);
        this.add(this.superficieField);

        // add nb_pieces
        this.nbPieces = new JLabel("nombre de pieces :");
        this.pieceModel = new SpinnerNumberModel(0, 0, 20, 1);
        this.nbPiecesField = new JSpinner(this.pieceModel);
        this.add(this.nbPieces);
        this.add(this.nbPiecesField);

        // add type
        this.type = new JLabel("type : ");
        this.typeField = new JComboBox<>(this.myTypeTab);
        this.add(this.type);
        this.add(this.typeField);

        // add description
        this.description = new JLabel("description : ");
        this.descriptionField = new JTextField();
        this.add(this.description);
        this.add(this.descriptionField);

        // add jardin
        this.jardin = new JLabel("jardin : ");
        this.jardinField = new JCheckBox();
        this.add(this.jardin);
        this.add(this.jardinField);

        // add cave
        this.cave = new JLabel("cave : ");
        this.caveField = new JCheckBox();
        this.add(this.cave);
        this.add(this.caveField);

        // add ceillier
        this.ceillier = new JLabel("ceillier : ");
        this.ceillierField = new JCheckBox();
        this.add(this.ceillier);
        this.add(this.ceillierField);

        // add loggia
        this.loggia = new JLabel("loggia : ");
        this.loggiaField = new JCheckBox();
        this.add(this.loggia);
        this.add(this.loggiaField);

        // add terrasse
        this.terrasse = new JLabel("terrasse : ");
        this.terrasseField = new JCheckBox();
        this.add(this.terrasse);
        this.add(this.terrasseField);

        // add garage
        this.garage = new JLabel("garage : ");
        this.garageField = new JCheckBox();
        this.add(this.garage);
        this.add(this.garageField);

        // add verranda
        this.verranda = new JLabel("verranda : ");
        this.verrandaField = new JCheckBox();
        this.add(this.verranda);
        this.add(this.verrandaField);

        // add prixMin
        this.prixMin = new JLabel("prix minimum : ");
        this.prixModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        this.prixMinField = new JSpinner(prixModel);
        this.add(this.prixMin);
        this.add(this.prixMinField);

        // add prixVente
        this.prixVente = new JLabel("prix de vente : ");
        this.prixModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        this.prixVenteField = new JSpinner(prixModel);
        this.add(this.prixVente);
        this.add(this.prixVenteField);

        // add images
        this.images = new JLabel("image(s) : ");
        this.imagesField = new JButton("image(s)");
        this.imagesField.addActionListener(e -> {
            try {
                AnnoncePanel.getImages();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        this.add(this.images);
        this.add(this.imagesField);

        //boutton submit
        this.add(new JPanel());
        this.submit = new JButton("submit");
        this.add(this.submit);

        this.revalidate();
        this.repaint();
    }

    public static void getImages() throws IOException, SQLException {
        Image.getImages();
    }
}
