package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position position){
        return ((x == position.getX()) && (y == position.getY()));
    }

    public String toString(){
        return String.format("(%d, %d)", x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
