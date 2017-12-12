package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Coup;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions.TasDeFonctionsPourIADifficile;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions.TasDeFonctionsPourIAFacile;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.TasDeFonctions.TasDeFonctionsPourIAMoyenne;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonApplication;

/**
 * Created by Nicolas on 25/06/2016.
 */
public class IA {
    private final Difficultee difficultee;
    private final Joueur symboleDeLIA;
    private final MonApplication app;
    private int nombreDeCoupsJouesParlIA;

    public IA(Difficultee difficultee, MonApplication app, Joueur symboleDeLIA){
        this.difficultee = difficultee;
        this.app = app;
        this.symboleDeLIA = symboleDeLIA;
        nombreDeCoupsJouesParlIA = 0;
    }

    public PileDePositions calculerCoupsPossibles(){
        JeuDeMorpion jeu = app.getJeuDeMorpion();
        PileDePositions pile = new PileDePositions();
        Coup[][] grille = jeu.getGrille().getGrilleDeCoups();

        for (int y=0; y<jeu.getGrille().getTaille(); y++){
            for (int x=0; x<jeu.getGrille().getTaille(); x++){
                if (grille[y][x] == null){
                    pile.ajouterPosition(new Position(x, y));
                }
            }
        }

        return pile;
    }

    public PileDeCoups calculerCoupsEffectuesSurLaGrille(JeuDeMorpion jeu){
        PileDeCoups pile = new PileDeCoups();
        Coup[][] grille = jeu.getGrille().getGrilleDeCoups();

        for (int y=0; y<jeu.getGrille().getTaille(); y++){
            for (int x=0; x<jeu.getGrille().getTaille(); x++){
                if (grille[y][x] != null){
                    pile.ajouterCoup(grille[x][y]);
                }
            }
        }

        return pile;
    }


    public void jouerUnCoup(){
        Position positionALaquelleLOrdinateurVaJouer = null;

        PileDePositions pileDePositionsPossibles = calculerCoupsPossibles();
        JeuDeMorpion etatDuJeuActuel = app.getJeuDeMorpion();
        switch (difficultee){
            case DEBILE:

                break;

            case FACILE:
                positionALaquelleLOrdinateurVaJouer = TasDeFonctionsPourIAFacile.getUnCoupFacile(pileDePositionsPossibles);
                break;

            case NORMALE:
                positionALaquelleLOrdinateurVaJouer = TasDeFonctionsPourIAMoyenne.getUnCoupMoyen(etatDuJeuActuel, pileDePositionsPossibles, app.getSymboleDeLIA());
                break;

            case DIFFICILE:
                positionALaquelleLOrdinateurVaJouer = TasDeFonctionsPourIADifficile.getUnCoupDifficile(etatDuJeuActuel, app.getSymboleDeLIA());
                break;
        }

        JeuDeMorpion jeu = app.getJeuDeMorpion();
        jeu.jouer(positionALaquelleLOrdinateurVaJouer);
        nombreDeCoupsJouesParlIA++;
    }
}
