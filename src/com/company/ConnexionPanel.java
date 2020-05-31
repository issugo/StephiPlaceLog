package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ConnexionPanel extends JFrame {
    JLabel codeAgent;
    JTextField codeAgentField;
    JButton submit;


    public ConnexionPanel() {
        // setup composant
        this.codeAgent = new JLabel("code agent :");
        this.codeAgentField = new JTextField();
        this.codeAgentField.setPreferredSize(new Dimension(100, 20));
        this.submit = new JButton("submit");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();

        // setup graphisme
        this.setLayout(new FlowLayout());
        this.setSize(500, 200);
        this.add(this.codeAgent);
        this.add(this.codeAgentField);
        this.add(new JPanel());
        this.add(this.submit);
        this.setTitle("StephiPlaceSoftware");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(width/2-250, height/2-100);
        this.setVisible(true);

        // action bouton submit
        this.submit.addActionListener(e -> {
            int codeAVerifier = Integer.parseInt(codeAgentField.getText());
            String url = "jdbc:mysql://localhost:3306/stephiplacelog";
            Connection con = null;
            ResultSet resultat = null;
            PreparedStatement st = null;
            Integer rowNumber = null;
            try {
                con = DriverManager.getConnection(url, "root", "");
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la connection à la BDD");
                throwables.printStackTrace();
            }
            try {
                assert con != null;
                st = con.prepareStatement("SELECT * FROM agent WHERE code_agent = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                assert st != null;
                st.setInt(1, codeAVerifier);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultat = st.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                assert resultat != null;
                resultat.last();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                rowNumber = resultat.getRow();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (rowNumber!=0) {
                try {
                    new Application(resultat.getInt("id"));
                    this.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Mauvais code agent");
            }
        });
    }
}
