package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class Grille {
    private Coup[][] grilleDeCoups;
    private final int taille;

    public Grille(int taille){
        this.taille = taille;
        grilleDeCoups = new Coup[taille][taille];
    }

    public Grille(Grille grilleACloner) {
        taille = grilleACloner.getTaille();
        grilleDeCoups = new Coup[taille][taille];
        for (int y=0; y<taille; y++) {
            for (int x = 0; x < taille; x++){
                Coup c = grilleACloner.getGrilleDeCoups()[y][x];
                if (c == null)
                    grilleDeCoups[y][x] = null;
                else {
                    grilleDeCoups[y][x] = new Coup(c.getJoueur(), new Position(x, y));
                }
            }
        }
    }

    public Grille clone(){
        Grille clone = new Grille(taille);
        clone.setGrilleDeCoups(grilleDeCoups.clone());

        return clone;
    }

    public Coup[][] getGrilleDeCoups() {
        return grilleDeCoups;
    }

    public int getTaille() {
        return taille;
    }

    public String toString(){
        String s = "";

        for (int y=0; y<taille; y++) {
            for (int x = 0; x < taille; x++){
                Coup c = grilleDeCoups[y][x];
                if (c == null)
                    s += "   ";
                else if (c.getJoueur() == Joueur.CROSS)
                    s += " X ";
                else if (c.getJoueur() == Joueur.CIRCLE)
                    s += " O ";
            }
            s += "\n";
        }

        return s;
    }

    public void setGrilleDeCoups(Coup[][] grilleDeCoups) {
        this.grilleDeCoups = grilleDeCoups;
    }
}
