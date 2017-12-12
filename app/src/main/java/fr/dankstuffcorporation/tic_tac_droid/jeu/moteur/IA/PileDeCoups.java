package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA;

import java.util.ArrayList;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Coup;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;

/**
 * Created by Nicolas on 25/06/2016.
 */
public class PileDeCoups {
    private ArrayList<Coup> pile;

    public PileDeCoups(){
        pile = new ArrayList<>();
    }

    public void ajouterCoup(Coup coup){
        pile.add(coup);
    }

    public void retirerCoup(Coup coup){
        for (Coup c : pile){
            if (c.equals(coup)){
                c = null;
                break;
            }
        }
    }

    public PileDeCoups getCoupsDansUnCoin(){
        PileDeCoups pileDeCoupsDansUnCoin = new PileDeCoups();
        for (Coup c : pile){
            if (c.getPosition().equals(new Position(0, 0))){
                pileDeCoupsDansUnCoin.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(0, 2))){
                pileDeCoupsDansUnCoin.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(2, 0))){
                pileDeCoupsDansUnCoin.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(2, 2))){
                pileDeCoupsDansUnCoin.ajouterCoup(c);
                continue;
            }
        }

        return pileDeCoupsDansUnCoin;
    }

    public PileDeCoups getCoupsDansUnCote(){
        PileDeCoups pileDeCoupsDansUnCote = new PileDeCoups();
        for (Coup c : pile){
            if (c.getPosition().equals(new Position(1, 0))){
                pileDeCoupsDansUnCote.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(0, 1))){
                pileDeCoupsDansUnCote.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(2, 1))){
                pileDeCoupsDansUnCote.ajouterCoup(c);
                continue;
            } else if (c.getPosition().equals(new Position(1, 2))){
                pileDeCoupsDansUnCote.ajouterCoup(c);
                continue;
            }
        }

        return pileDeCoupsDansUnCote;
    }

    public PileDeCoups getCoupsEnFonctionDuJoueur(Joueur joueur){
        PileDeCoups pileDeCoupsEnFonctionDuJoueur = new PileDeCoups();
        for (Coup c : pile){
            if (c.getJoueur() == joueur)
                pileDeCoupsEnFonctionDuJoueur.ajouterCoup(c);
        }

        return pileDeCoupsEnFonctionDuJoueur;
    }

    public ArrayList<Coup> getPile() {
        return pile;
    }

    public int getLength(){
        return pile.size();
    }

    public Coup get(int index){
        return pile.get(index);
    }

    public String toString(){
        String s = "[";

        for (Coup c : pile){
            s += c.toString();
            s += ", ";
        }
        s += "]";

        return s;
    }
}
