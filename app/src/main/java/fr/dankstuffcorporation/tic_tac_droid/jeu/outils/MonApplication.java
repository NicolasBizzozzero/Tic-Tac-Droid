package fr.dankstuffcorporation.tic_tac_droid.jeu.outils;

import android.app.Application;

import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class MonApplication extends Application {
    private JeuDeMorpion jeuDeMorpion;
    private boolean laPartieEstTerminee;

    // Variables relatives à l'IA
    private Joueur symboleDeLIA;
    private boolean uneIAEstCree;
    private boolean lIADoitJouerVite;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public JeuDeMorpion getJeuDeMorpion() {
        return jeuDeMorpion;
    }

    public void setJeuDeMorpion(JeuDeMorpion jeuDeMorpion) {
        this.jeuDeMorpion = jeuDeMorpion;
    }

    public boolean isLaPartieEstTerminee() {
        return laPartieEstTerminee;
    }

    public void setLaPartieEstTerminee(boolean laPartieEstTerminee) {
        this.laPartieEstTerminee = laPartieEstTerminee;
    }

    public boolean isUneIAEstCree() {
        return uneIAEstCree;
    }

    public void setUneIAEstCree(boolean uneIAEstCree) {
        this.uneIAEstCree = uneIAEstCree;
    }

    public boolean islIADoitJouerVite() {
        return lIADoitJouerVite;
    }

    public void setlIADoitJouerVite(boolean lIADoitJouerVite) {
        this.lIADoitJouerVite = lIADoitJouerVite;
    }

    public Joueur getSymboleDeLIA() {
        return symboleDeLIA;
    }

    public void setSymboleDeLIA(Joueur symboleDeLIA) {
        this.symboleDeLIA = symboleDeLIA;
    }
}
