package com.company;

import com.backend.Images;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class NewBien extends JPanel {

    JLabel superficie, nbPieces, type, description, jardin, cave, ceillier, loggia, terrasse, garage, verranda, prixMin, prixVente, images;
    JTextField descriptionField;
    JCheckBox jardinField, caveField, ceillierField, loggiaField, terrasseField, garageField, verrandaField;
    JComboBox<String> typeField;
    JSpinner nbPiecesField, prixMinField, prixVenteField;
    SpinnerModel pieceModel, prixModel;
    JTextField superficieField;
    JButton imagesField;

    String[] myTypeTab = {"maison", "appartement"};

    public NewBien() throws Exception {
        //set css default
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        // setup layout
        setLayout(new GridLayout(14, 2));

        // add superficie
        this.superficie = new JLabel("superficie :");
        this.superficieField = new JTextField();
        add(this.superficie);
        add(this.superficieField);

        // add nb_pieces
        this.nbPieces = new JLabel("nombre de pieces :");
        this.pieceModel = new SpinnerNumberModel(0, 0, 20, 1);
        this.nbPiecesField = new JSpinner(this.pieceModel);
        add(this.nbPieces);
        add(this.nbPiecesField);

        // add type
        this.type = new JLabel("type : ");
        this.typeField = new JComboBox<>(this.myTypeTab);
        add(this.type);
        add(this.typeField);

        // add description
        this.description = new JLabel("description : ");
        this.descriptionField = new JTextField();
        add(this.description);
        add(this.descriptionField);

        // add jardin
        this.jardin = new JLabel("jardin : ");
        this.jardinField = new JCheckBox();
        add(this.jardin);
        add(this.jardinField);

        // add cave
        this.cave = new JLabel("cave : ");
        this.caveField = new JCheckBox();
        add(this.cave);
        add(this.caveField);

        // add ceillier
        this.ceillier = new JLabel("ceillier : ");
        this.ceillierField = new JCheckBox();
        add(this.ceillier);
        add(this.ceillierField);

        // add loggia
        this.loggia = new JLabel("loggia : ");
        this.loggiaField = new JCheckBox();
        add(this.loggia);
        add(this.loggiaField);

        // add terrasse
        this.terrasse = new JLabel("terrasse : ");
        this.terrasseField = new JCheckBox();
        add(this.terrasse);
        add(this.terrasseField);

        // add garage
        this.garage = new JLabel("garage : ");
        this.garageField = new JCheckBox();
        add(this.garage);
        add(this.garageField);

        // add verranda
        this.verranda = new JLabel("verranda : ");
        this.verrandaField = new JCheckBox();
        add(this.verranda);
        add(this.verrandaField);

        // add prixMin
        this.prixMin = new JLabel("prix minimum : ");
        this.prixModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        this.prixMinField = new JSpinner(prixModel);
        add(this.prixMin);
        add(this.prixMinField);

        // add prixVente
        this.prixVente = new JLabel("prix de vente : ");
        this.prixModel = new SpinnerNumberModel(0, 0, 999999999, 0.01);
        this.prixVenteField = new JSpinner(prixModel);
        add(this.prixVente);
        add(this.prixVenteField);

        // add images
        this.images = new JLabel("image(s) : ");
        this.imagesField = new JButton("image(s)");
        this.imagesField.addActionListener(e -> NewBien.getImages());
        add(this.images);
        add(this.imagesField);
    }

    public static void getImages() {
        Images.getImage();
    }
}
