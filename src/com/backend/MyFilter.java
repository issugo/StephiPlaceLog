package com.backend;

import org.jetbrains.annotations.NotNull;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * classe de filtre pour la récupération de fichier
 */
public class MyFilter extends FileFilter {

    String [] lesSuffixes;
    String  laDescription;

    /**
     * constructeur
     * @param lesSuffixes
     * @param laDescription
     */
    public MyFilter(String []lesSuffixes, String laDescription){
        this.lesSuffixes = lesSuffixes;
        this.laDescription = laDescription;
    }

    /**
     * vérifie que le suffixe est autorisé
     * @param suffixe
     * @return un boolean de verifiation
     */
    boolean appartient(String suffixe){
        for (String lesSuffix : lesSuffixes)
            if (suffixe.equals(lesSuffix))
                return true;
        return false;
    }

    /**
     *
     * @param f
     * @return un boolean d'acceptation
     */
    public boolean accept(@NotNull File f) {
        if (f.isDirectory())  return true;
        String suffixe = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if(i > 0 &&  i < s.length() - 1)
            suffixe=s.substring(i+1).toLowerCase();
        return suffixe!=null&&appartient(suffixe);
    }

    /**
     * getter de la description du filtre
     * @return la description du filtre
     */
    public String getDescription() {
        return laDescription;
    }
}
