package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class Coup {
    private final Joueur joueur;
    private final Position position;

    public Coup(Joueur joueur, Position position) {
        this.joueur = joueur;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public String toString(){
        String joueur;
        if (this.joueur == Joueur.CROSS)
            joueur = "X";
        else if (this.joueur  == Joueur.CIRCLE)
            joueur = "O";
        else
            joueur = "@";

        return String.format("%s:(%d, %d)", joueur, position.getX(), position.getY());
    }

    public boolean equals(Coup c){
        return ((this.joueur == c.getJoueur()) && (this.position.equals(c.getPosition())));
    }
}
