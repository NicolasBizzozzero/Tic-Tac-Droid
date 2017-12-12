package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur;


import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.PileDePositions;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class JeuDeMorpion {
    private final Joueur joueur1, joueur2;
    private Joueur joueurDontAQuiCEstLeTour;
    private Grille grille;
    private int nombreDeCoupsJoues;
    private Coup dernierCoupJoue;

    public JeuDeMorpion(Joueur joueurQuiCommence){
        joueur1 = Joueur.CROSS;
        joueur2 = Joueur.CIRCLE;
        joueurDontAQuiCEstLeTour = joueurQuiCommence;
        grille = new Grille(3);
        nombreDeCoupsJoues = 0;
    }

    public JeuDeMorpion(JeuDeMorpion objetACloner){
        joueur1 = Joueur.CROSS;
        joueur2 = Joueur.CIRCLE;
        joueurDontAQuiCEstLeTour = objetACloner.getJoueurDontAQuiCEstLeTour();
        grille = new Grille(objetACloner.getGrille());
        nombreDeCoupsJoues = objetACloner.getNombreDeCoupsJoues();
        dernierCoupJoue = objetACloner.getDernierCoupJoue();
    }

    /**
     * Joue un coup sur la grille avec le joueur a qui c'est le tour puis inverse le joueur a qui
     * c'est le tour SI le joueur peut jouer sur la case (elle n'est pas nulle), puis retourne true.
     * Sinon retourne false.
     */
    public boolean jouer(Position pos){
        Coup[][] grilleDeCoup = grille.getGrilleDeCoups();

        // Cas où la case est vide
        if (grilleDeCoup[pos.getY()][pos.getX()] == null){
            Coup coup = new Coup(joueurDontAQuiCEstLeTour, pos);
            grilleDeCoup[pos.getY()][pos.getX()] = coup;
            dernierCoupJoue = coup;
            nombreDeCoupsJoues++;
            inverserLeJoueurDontAQuiCEstLeTour();
            return true;
        } else {
            return false;
        }
    }

    private void inverserLeJoueurDontAQuiCEstLeTour(){
        if (joueurDontAQuiCEstLeTour == Joueur.CROSS)
            joueurDontAQuiCEstLeTour = Joueur.CIRCLE;
        else
            joueurDontAQuiCEstLeTour = Joueur.CROSS;
    }

    public PileDePositions getPositionsVidesDuJeu() {
        PileDePositions positionsVides = new PileDePositions();
        Coup[][] grilleDeCoups = grille.getGrilleDeCoups();

        for (int y=0; y<grille.getTaille(); y++) {
            for (int x = 0; x < grille.getTaille(); x++){
                if (grilleDeCoups[y][x] == null)
                    positionsVides.ajouterPosition(new Position(x, y));
            }
        }

        return positionsVides;
    }

    public int getLeNombreDeSeriesDe2PionsAlignes(Joueur joueur){
        int nombreDeSeries = 0;
        Coup[][] grilleDeCoups = grille.getGrilleDeCoups();
        Joueur joueurEnnemi = (joueur == Joueur.CROSS?Joueur.CIRCLE:Joueur.CROSS);

        // On check les lignes horizontales
        for (int y=0; y<grille.getTaille(); y++) {
            int nombreDeCoupsDuMemeJoueur = 0;
            boolean unEnnemiAJoue = false;
            for (int x = 0; x < grille.getTaille(); x++){
                if (grilleDeCoups[y][x] == null)
                    continue;
                else if (grilleDeCoups[y][x].getJoueur() == joueur){
                    nombreDeCoupsDuMemeJoueur++;
                } else {
                    unEnnemiAJoue = true;
                    break;
                }
            }
            if (!unEnnemiAJoue){
                if (nombreDeCoupsDuMemeJoueur == 2)
                    nombreDeSeries++;
            }
        }

        // Puis les lignes verticales
        for (int x=0; x<grille.getTaille(); x++) {
            int nombreDeCoupsDuMemeJoueur = 0;
            boolean unEnnemiAJoue = false;
            for (int y=0; y < grille.getTaille(); y++){
                if (grilleDeCoups[y][x] == null)
                    continue;
                else if (grilleDeCoups[y][x].getJoueur() == joueur){
                    nombreDeCoupsDuMemeJoueur++;
                } else {
                    unEnnemiAJoue = true;
                    break;
                }
            }
            if (!unEnnemiAJoue){
                if (nombreDeCoupsDuMemeJoueur == 2)
                    nombreDeSeries++;
            }
        }

        // Puis la diagonale
        int nombreDeCoupsDuMemeJoueur = 0;
        for (int y=0; y<grille.getTaille(); y++) {
            if (grilleDeCoups[y][y] == null)
                continue;
            else if (grilleDeCoups[y][y].getJoueur() == joueur){
                nombreDeCoupsDuMemeJoueur++;
            } else {
                break;
            }

            if (nombreDeCoupsDuMemeJoueur == 2)
                nombreDeSeries++;
        }

        // Puis l'anti-diagonale
        nombreDeCoupsDuMemeJoueur = 0;
        for (int y=0; y<grille.getTaille(); y++) {
            if (grilleDeCoups[y][2-y] == null)
                continue;
            else if (grilleDeCoups[y][2-y].getJoueur() == joueur){
                nombreDeCoupsDuMemeJoueur++;
            } else {
                break;
            }

            if (nombreDeCoupsDuMemeJoueur == 2)
                nombreDeSeries++;
        }

        return nombreDeSeries;
    }

    /**
     * Regarde tous les cas possible de la grille pour verifier si il reste encore une case vide.
     * Si non, alors on retourne null.
     * Si oui, alors on retourne le joueur gagnant (CROSS, CIRCLE ou NOBODY).
     */
    public Joueur quelquunAtIlGagne(){
        Coup[][] grilleDeCoups = grille.getGrilleDeCoups();
        int taille = grille.getTaille();
        int x = dernierCoupJoue.getPosition().getX();
        int y = dernierCoupJoue.getPosition().getY();
        Joueur dernierJoueurAAvoirJoue = dernierCoupJoue.getJoueur();

        // Check colonnes
        for(int i=0; i < taille; i++){
            if (grilleDeCoups[i][x] == null || grilleDeCoups[i][x].getJoueur() != dernierJoueurAAvoirJoue)
                break;
            if (i == taille-1){
                return dernierJoueurAAvoirJoue;
            }
        }

        // Check lignes
        for(int i = 0; i < taille; i++){
            if(grilleDeCoups[y][i] == null || grilleDeCoups[y][i].getJoueur() != dernierJoueurAAvoirJoue)
                break;
            if (i == taille-1){
                return dernierJoueurAAvoirJoue;
            }
        }

        // Check diagonale
        if(x == y){
            //we're on a diagonal
            for(int i = 0; i < taille; i++){
                if(grilleDeCoups[i][i] == null || grilleDeCoups[i][i].getJoueur() != dernierJoueurAAvoirJoue)
                    break;
                if (i == taille-1){
                    return dernierJoueurAAvoirJoue;
                }
            }
        }

        // Check anti-dialognale
        for(int i=0; i < taille; i++){
            if(grilleDeCoups[i][(taille-1)-i] == null || grilleDeCoups[i][(taille-1)-i].getJoueur() != dernierJoueurAAvoirJoue)
                break;
            if (i == taille-1){
                return dernierJoueurAAvoirJoue;
            }
        }

        // Check Egalité
        if (nombreDeCoupsJoues >= (taille*taille)){
            return Joueur.NOBODY;
        }

        return null;
    }

    public String toString(){
        return grille.toString();
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Joueur getJoueurDontAQuiCEstLeTour() {
        return joueurDontAQuiCEstLeTour;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setJoueurDontAQuiCEstLeTour(Joueur joueurDontAQuiCEstLeTour) {
        this.joueurDontAQuiCEstLeTour = joueurDontAQuiCEstLeTour;
    }

    public int getNombreDeCoupsJoues() {
        return nombreDeCoupsJoues;
    }

    public void setNombreDeCoupsJoues(int nombreDeCoupsJoues) {
        this.nombreDeCoupsJoues = nombreDeCoupsJoues;
    }

    public Coup getDernierCoupJoue() {
        return dernierCoupJoue;
    }

    public void setDernierCoupJoue(Coup dernierCoupJoue) {
        this.dernierCoupJoue = dernierCoupJoue;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }
}
