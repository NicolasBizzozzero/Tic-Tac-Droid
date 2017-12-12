package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.minimaxalgorithm;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDeCoups;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;

/**
 * Created by Nicolas on 28/06/2016.
 */
public class Etat {
    private final int numero;
    private final PileDeCoups coupsSurLaGrille;
    private final Joueur joueurQuiVaJouer;
    public Etat(int numero, PileDeCoups coupsSurLaGrille, Joueur joueurQuiVaJouer){
        this.numero = numero;
        this.coupsSurLaGrille = coupsSurLaGrille;
        this.joueurQuiVaJouer = joueurQuiVaJouer;
    }

    public int getNumero() {
        return numero;
    }

    public PileDeCoups getCoupsSurLaGrille() {
        return coupsSurLaGrille;
    }

    public Joueur getJoueurQuiVaJouer() {
        return joueurQuiVaJouer;
    }

    /*public int getScore(){
        Joueur joueurGagnant = quelqunATIlGagne();

        if (joueurGagnant == null || joueurGagnant == Joueur.NOBODY)
            return 0;
        else if (joueurGagnant == joueurQuiVaJouer)
            return 10;
        else
            return -10;
    }*/

    /*public Joueur quelqunATIlGagne(){
        if (coupsSurLaGrille.getLength() < 5)
            return null;

        if ()

        return null;
    }*/


}
