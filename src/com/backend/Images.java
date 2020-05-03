package com.backend;

import javax.swing.*;
import java.io.File;

public class Images {

    public static void getImage() {
        JFileChooser choix = new JFileChooser();
        choix.setMultiSelectionEnabled(true);
        MyFilter mf = new MyFilter(new String[] {"gif", "jpeg", "tif", "jpg", "png"}, "fichiers images");
        choix.setFileFilter(mf);
        int retour = choix.showOpenDialog(choix);
        if (retour == JFileChooser.APPROVE_OPTION) {
            //fichiers choisis donc sortie = OK
            File[] fs = choix.getSelectedFiles();
            for (File f : fs) {
                //nom du fichier
                String name = f.getName();
                System.out.println(name);
                //chemin du fichier
                String path = f.getAbsolutePath();
                System.out.println(path);
            }
        } else {
            System.out.println("aucun fichier choisi");
        }
    }
}
