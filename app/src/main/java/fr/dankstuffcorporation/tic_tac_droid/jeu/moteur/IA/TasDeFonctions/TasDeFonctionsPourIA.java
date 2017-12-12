package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;

/**
 * Created by Nicolas on 27/06/2016.
 */
public abstract class TasDeFonctionsPourIA {
    public static final int getUnIndexAleatoire(final PileDePositions pileDePositionsPossibles){
        return (int) Math.round((Math.random()*(pileDePositionsPossibles.getLength()-1)));
    }
}
