package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;

/**
 * Created by Nicolas on 02/07/2016.
 */
public abstract class TasDeFonctionsPourIAMoyenne {

    /**
     * On joue aleatoirement un coup difficile ou un coup facile.
     */
    public static final Position getUnCoupMoyen(JeuDeMorpion etatDuJeuActuel, PileDePositions pileDePositionsPossibles, Joueur symboleDeLIA){
        Position positionAJouer;

        if (symboleDeLIA == Joueur.CIRCLE){
            if (etatDuJeuActuel.getNombreDeCoupsJoues() == 3 ||
                    etatDuJeuActuel.getNombreDeCoupsJoues() == 7){
                positionAJouer = TasDeFonctionsPourIAFacile.getUnCoupFacile(pileDePositionsPossibles);
            }  else {
                positionAJouer = TasDeFonctionsPourIADifficile.getUnCoupDifficile(etatDuJeuActuel, symboleDeLIA);
            }
        } else {
            if (etatDuJeuActuel.getNombreDeCoupsJoues() == 3 ||
                    etatDuJeuActuel.getNombreDeCoupsJoues() == 7){
                positionAJouer = TasDeFonctionsPourIAFacile.getUnCoupFacile(pileDePositionsPossibles);
            }  else {
                positionAJouer = TasDeFonctionsPourIADifficile.getUnCoupDifficile(etatDuJeuActuel, symboleDeLIA);
            }
        }

        return positionAJouer;
    }
}
