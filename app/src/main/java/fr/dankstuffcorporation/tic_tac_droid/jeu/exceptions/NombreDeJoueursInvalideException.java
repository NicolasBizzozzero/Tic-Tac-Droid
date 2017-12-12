package fr.dankstuffcorporation.tic_tac_droid.jeu.exceptions;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class NombreDeJoueursInvalideException extends Exception{
    public NombreDeJoueursInvalideException(int nombreDeJoueurs){
        super(String.format("Impossible d'avoir %d comme nombre de joueurs.", nombreDeJoueurs));
    }
}
