package com.company;

import com.backend.*;
import com.backend.Image;

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
import java.util.Objects;
import javax.swing.*;

/**
 * Classe du panel des annonces
 */
public class AnnoncePanel extends JPanel {

    JButton choix1, choix2;

    /**
     * constructeur
     */
    public AnnoncePanel() {

        this.choix1 = new JButton("voir les annonces");
        this.choix1.addActionListener(e -> {
            try {
                showAllAnnonces();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
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

    /**
     * methode pour faire afficher toutes les annonces
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showAllAnnonces() throws SQLException, ClassNotFoundException {
        // suppression de tout les components actuellement présents
        this.removeAll();

        // recupere toute les annonces
        List<Annonce> allAnnonces = Annonce.findAll();

        // set l'affichage
        this.setLayout(new GridLayout(allAnnonces.size()/2,2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        // affichage annonce par annonce
        for (Annonce annonce: allAnnonces) {
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(6, 2));
            temp.add(new JLabel("id :"));
            temp.add(new JLabel(String.valueOf(annonce.getId())));
            temp.add(new JLabel("titre :"));
            temp.add(new JLabel(annonce.getTitre()));
            temp.add(new JLabel("nb_favoris :"));
            temp.add(new JLabel(String.valueOf(annonce.getNb_favoris())));
            temp.add(new JLabel("nb_visites :"));
            temp.add(new JLabel(String.valueOf(annonce.getNb_visites())));
            temp.add(new JLabel("id_bien :"));
            JButton temp2 = new JButton(String.valueOf(annonce.getId_bien()));
            temp2.addActionListener(e -> {
                try {
                    showBien(annonce.getId_bien());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
            temp.add(temp2);
            temp.add(new JLabel("agent :"));
            temp2 = new JButton(String.valueOf(Agent.find(annonce.getId_agent()).getCode_agent()));
            temp2.addActionListener(e -> {
                try {
                    showAgent(annonce.getId_agent());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });
            temp.add(temp2);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.add(temp);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     *
     */
    public void showCreateAnnonceForm() {
        this.removeAll();
        // setup layout
        this.setLayout(new GridLayout(18, 2));
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

        // add titre
        JLabel titre = new JLabel("titre : ");
        JTextField titreField = new JTextField();
        this.add(titre);
        this.add(titreField);

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

        // add id_agent
        JLabel code_agent = new JLabel("code agent :");
        JTextField code_agentField = new JTextField();
        this.add(code_agent);
        this.add(code_agentField);

        //boutton submit
        this.add(new JPanel());
        JButton submit = new JButton("submit");
        submit.addActionListener(e -> {
            Client clientTemp = new Client();
            try {
                clientTemp = Client.findByEmail(emailVendeurField.getText());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                assert clientTemp != null;
                if(!clientTemp.isVendeur()) {
                    JOptionPane.showMessageDialog(new JFrame(), "le client n'est pas vendeur");
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            // enregistrement du bien
            Bien temp = new Bien();
            temp.setSuperficie(Float.valueOf(superficieField.getText()));
            temp.setFrais_agence(Float.parseFloat(fraisAgenceField.getValue().toString()));
            temp.setPrix_min(Float.parseFloat(prixMinField.getValue().toString()));
            temp.setVerranda(verrandaField.isSelected());
            temp.setTerrasse(terrasseField.isSelected());
            temp.setLoggia(loggiaField.isSelected());
            temp.setCeillier(ceillierField.isSelected());
            temp.setJardin(jardinField.isSelected());
            temp.setDescription(descriptionField.getText());
            temp.setType(Objects.requireNonNull(typeField.getSelectedItem()).toString());
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
                assert recuperationId != null;
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
            //enregistrement des images
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
            //enregistrement de l'annonce
            Annonce temp3 = new Annonce();
            temp3.setTitre(titreField.getText());
            temp3.setId_bien(id);
            try {
                temp3.setId_agent(Agent.findByCodeAgent(Integer.parseInt(code_agentField.getText())).getId());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                temp3.save();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });
        this.add(submit);
        this.revalidate();
        this.repaint();
    }

    /**
     * methode pour afficher un bien en fonction de son id
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showBien(Integer id) throws SQLException, ClassNotFoundException {
        this.removeAll();
        Bien bien = Bien.find(id);
        this.setLayout(new GridLayout(15,2));
        this.add(new JLabel("id :"));
        this.add(new JLabel(String.valueOf(bien.getId())));
        this.add(new JLabel("superficie :"));
        this.add(new JLabel(String.valueOf(bien.getSuperficie())));
        this.add(new JLabel("nb_pieces :"));
        this.add(new JLabel(String.valueOf(bien.getNb_pieces())));
        this.add(new JLabel("type :"));
        this.add(new JLabel(bien.getType()));
        this.add(new JLabel("description :"));
        this.add(new JLabel(bien.getDescription()));
        this.add(new JLabel("jardin :"));
        if (bien.getJardin()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("cave :"));
        if (bien.getCave()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("ceillier"));
        if (bien.getCeillier()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("loggia :"));
        if (bien.getLoggia()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("terrasse :"));
        if (bien.getTerrasse()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("garage :"));
        if (bien.getGarage()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("verranda :"));
        if (bien.getVerranda()) {
            this.add(new JLabel("oui"));
        } else {
            this.add(new JLabel("non"));
        }
        this.add(new JLabel("prix_min :"));
        this.add(new JLabel(String.valueOf(bien.getPrix_min())));
        this.add(new JLabel("frais_agence :"));
        this.add(new JLabel(String.valueOf(bien.getFrais_agence())));
        this.add(new JLabel("email vendeur :"));
        this.add(new JLabel(Vendeur.find(bien.getIdVendeur()).findClient().getEmail()));
        this.revalidate();
        this.repaint();
    }

    /**
     * affiche un agent en fonction de son id
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void showAgent(Integer id) throws SQLException, ClassNotFoundException {
        this.removeAll();
        Agent agent = Agent.find(id);
        this.setLayout(new GridLayout(2,2));
        this.add(new JLabel("id :"));
        this.add(new JLabel(String.valueOf(agent.getId())));
        this.add(new JLabel("code_agent :"));
        this.add(new JLabel(String.valueOf(agent.getCode_agent())));
        this.revalidate();
        this.repaint();
    }
}
