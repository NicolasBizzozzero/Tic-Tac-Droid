package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions;

import android.util.Log;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Coup;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.minimaxalgorithm.ArbreDeJeu;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;

/**
 * Created by Nicolas on 27/06/2016.
 */
public abstract class TasDeFonctionsPourIADifficile {

    public static Position getUnCoupDifficile(JeuDeMorpion etatDuJeuActuel, Joueur symboleDeLIA){
        ArbreDeJeu arbreDeJeu = new ArbreDeJeu(etatDuJeuActuel, symboleDeLIA);
        return arbreDeJeu.getLaMeilleureDesPositionsPossible();
    }
}
