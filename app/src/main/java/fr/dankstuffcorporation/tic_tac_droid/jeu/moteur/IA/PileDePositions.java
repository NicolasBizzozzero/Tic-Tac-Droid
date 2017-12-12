package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA;

import java.util.ArrayList;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Position;

/**
 * Created by Nicolas on 25/06/2016.
 */
public class PileDePositions {
    private ArrayList<Position> pile;

    public PileDePositions() {
        pile = new ArrayList<>();
    }

    public void ajouterPosition(Position pos){
        if (pile.size() < 9)
            pile.add(pos);
    }

    public void retirerPosition(Position pos){
        for (Position p : pile){
            if (p.equals(pos)){
                p = null;
                break;
            }
        }
    }

    public PileDePositions getPositionsDansUnCoin(){
        PileDePositions pileDePositionsDansUnCoin = new PileDePositions();
        for (Position p : pile){
            if (p.equals(new Position(0, 0))){
                pileDePositionsDansUnCoin.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(0, 2))){
                pileDePositionsDansUnCoin.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(2, 2))){
                pileDePositionsDansUnCoin.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(2, 0))){
                pileDePositionsDansUnCoin.ajouterPosition(p);
                continue;
            }
        }

        return pileDePositionsDansUnCoin;
    }

    public PileDePositions getPositionsDansUnCote(){
        PileDePositions pileDePositionsDansUnCote = new PileDePositions();
        for (Position p : pile){
            if (p.equals(new Position(1, 0))){
                pileDePositionsDansUnCote.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(0, 1))){
                pileDePositionsDansUnCote.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(2, 1))){
                pileDePositionsDansUnCote.ajouterPosition(p);
                continue;
            } else if (p.equals(new Position(1, 2))){
                pileDePositionsDansUnCote.ajouterPosition(p);
                continue;
            }
        }

        return pileDePositionsDansUnCote;
    }

    public ArrayList<Position> getPile() {
        return pile;
    }

    public int getLength(){
        return pile.size();
    }

    public Position get(int index){
        return pile.get(index);
    }

    public String toString(){
        String s = "[";

        for (Position p : pile){
            s += p.toString();
            s += ", ";
        }
        s += "]";

        // On pop le dernier char
        s = s.substring(0, s.length()-1);

        return s;
    }
}
