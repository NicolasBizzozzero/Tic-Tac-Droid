package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.minimaxalgorithm;

import android.util.Log;

import java.util.ArrayList;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Coup;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsMathematiques;

/**
 * Created by Nicolas on 28/06/2016.
 */
public class ArbreDeJeu {
    private JeuDeMorpion etatDuJeuActuel;
    private Joueur symboleDeLIA;
    private Joueur symboleAdversaireDeLIA;

    public ArbreDeJeu(JeuDeMorpion etatDuJeuActuel, Joueur symboleDeLIA){
        this.etatDuJeuActuel = etatDuJeuActuel;
        this.symboleDeLIA = symboleDeLIA;
        this.symboleAdversaireDeLIA = symboleDeLIA==Joueur.CROSS?Joueur.CIRCLE:Joueur.CROSS;
    }

    public Position getLaMeilleureDesPositionsPossible(){
        // On choisit des positions prédéfinies en fonction des coups optimaux décris ici :
        // https://xkcd.com/832/
        if (etatDuJeuActuel.getNombreDeCoupsJoues() == 1) {
            if (etatDuJeuActuel.getGrille().getGrilleDeCoups()[1][1] == null)
                return new Position(1, 1);
            else {
                Position[] tabPos = {new Position(0, 0), new Position(0, 2), new Position(2, 0), new Position(2, 2)};
                return tabPos[MesFonctionsMathematiques.getEntierAleatoire(0, 3)];
            }
        }

        int maxVal = Integer.MIN_VALUE;
        ArrayList<Coup> listeDeCoupsPossibles = new ArrayList<>();

        // Pour tous les coups possibles
        PileDePositions pileDePositionsPossibles = etatDuJeuActuel.getPositionsVidesDuJeu();
        for (Position p : pileDePositionsPossibles.getPile()){
            JeuDeMorpion etatDuJeuClone = new JeuDeMorpion(etatDuJeuActuel);
            etatDuJeuClone.jouer(p);         //simuler(coup_actuel)
            int val = min(etatDuJeuClone, 9-etatDuJeuActuel.getNombreDeCoupsJoues());

            if (val > maxVal) {
                maxVal = val;
                listeDeCoupsPossibles.clear();
                listeDeCoupsPossibles.add(new Coup(etatDuJeuActuel.getJoueurDontAQuiCEstLeTour(), p));
            } else if (val == maxVal){
                listeDeCoupsPossibles.add(new Coup(etatDuJeuActuel.getJoueurDontAQuiCEstLeTour(), p));
            }

            Log.d("CONNARD", p.toString() + " = " + val);

            //annuler_coup(coup_actuel)
        }

        return listeDeCoupsPossibles.get((int) Math.round((listeDeCoupsPossibles.size()-1)*Math.random())).getPosition();
    }

    public int min(JeuDeMorpion etatDuJeu, int profondeur){
        if (profondeur == 0)
            return eval(etatDuJeu, null, true);
        else {
            Joueur joueurGagnant = etatDuJeu.quelquunAtIlGagne();
            if (joueurGagnant != null)
                return eval(etatDuJeu, joueurGagnant, false);
        }

        int minVal = Integer.MAX_VALUE;

        // Pour tous les coups possibles
        PileDePositions pileDePositionsPossibles = etatDuJeu.getPositionsVidesDuJeu();
        for (Position p : pileDePositionsPossibles.getPile()) {
            JeuDeMorpion etatDuJeuClone = new JeuDeMorpion(etatDuJeu);
            etatDuJeuClone.jouer(p);         //simuler(coup_actuel)
            int val = max(etatDuJeuClone, profondeur-1);

            if (val < minVal) {
                minVal = val;
            }

            //annuler_coup(coup_actuel)
        }

        return minVal;
    }

    public int max(JeuDeMorpion etatDuJeu, int profondeur){
        if (profondeur == 0)
            return eval(etatDuJeu, null, true);
        else {
            Joueur joueurGagnant = etatDuJeu.quelquunAtIlGagne();
            if (joueurGagnant != null)
                return eval(etatDuJeu, joueurGagnant, false);
        }

        int maxVal = Integer.MIN_VALUE;

        // Pour tous les coups possibles
        PileDePositions pileDePositionsPossibles = etatDuJeu.getPositionsVidesDuJeu();
        for (Position p : pileDePositionsPossibles.getPile()) {
            JeuDeMorpion etatDuJeuClone = new JeuDeMorpion(etatDuJeu);
            etatDuJeuClone.jouer(p);         //simuler(coup_actuel)
            int val = min(etatDuJeu, profondeur-1);

            if (val > maxVal) {
                maxVal = val;
            }

            //annuler_coup(coup_actuel)
        }

        return maxVal;
    }

    public int eval(JeuDeMorpion etatDuJeu, Joueur joueurGagnant, boolean onDoitCheckSiQuelQunAGagne){
        if (onDoitCheckSiQuelQunAGagne){
            joueurGagnant = etatDuJeu.quelquunAtIlGagne();
        }

        int bidule = 0;
        Log.d("JEPRINTDESTRUCS", "Voici le jeu gagnant :\n" + etatDuJeu.toString());
        if (joueurGagnant != null)
            Log.d("JEPRINTDESTRUCS", "Je trouve que le joueur gagnant est :" + joueurGagnant.name());
        else {
            Log.d("JEPRINTDESTRUCS", "Il n'y a pas de gagnant");
            bidule = etatDuJeu.getLeNombreDeSeriesDe2PionsAlignes(symboleDeLIA) - etatDuJeu.getLeNombreDeSeriesDe2PionsAlignes(symboleAdversaireDeLIA);
            Log.d("JEPRINTDESTRUCS", "Je m'apprete donc à retourner " + bidule);
        }

        if (joueurGagnant == symboleDeLIA){
            return 1000 - etatDuJeu.getNombreDeCoupsJoues();
        } else if (joueurGagnant == symboleAdversaireDeLIA){
            return -1000 + etatDuJeu.getNombreDeCoupsJoues();
        } else if (joueurGagnant == Joueur.NOBODY){
            return 0;
        }

        return bidule;
    }
}
