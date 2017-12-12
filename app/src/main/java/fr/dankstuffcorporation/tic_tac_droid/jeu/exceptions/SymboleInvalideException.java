package fr.dankstuffcorporation.tic_tac_droid.jeu.exceptions;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class SymboleInvalideException extends Exception {
    public SymboleInvalideException(Joueur joueurPasseEnArgument) {
        super(String.format("Impossible de jouer avec le joueur %s passé en argument.", joueurPasseEnArgument.name()));
    }
}
