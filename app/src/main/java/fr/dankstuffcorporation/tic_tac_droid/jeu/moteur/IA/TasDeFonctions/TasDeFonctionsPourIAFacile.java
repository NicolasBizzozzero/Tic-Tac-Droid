package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;

/**
 * Created by Nicolas on 27/06/2016.
 */
public abstract class TasDeFonctionsPourIAFacile {

    /**
     * On retourne aléatoirement une position parmis celles possibles.
     * */
    public static final Position getUnCoupFacile(final PileDePositions pileDePositionsPossibles){
        int indexAleatoire = TasDeFonctionsPourIA.getUnIndexAleatoire(pileDePositionsPossibles);
        return pileDePositionsPossibles.get(indexAleatoire);
    }
}
