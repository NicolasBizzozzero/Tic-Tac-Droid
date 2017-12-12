package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.Difficultee;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.IA.IA;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.JeuDeMorpion;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.MaSurfaceView;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsDApplicationAndroid;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class Jeu extends MonActivite {
    private int nombreDeJoueurs;
    private Joueur symbolePremierJoueur;

    // Variables relatives à l'IA
    private IA ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activite_jeu);

        // On lance la partie !
        app.setLaPartieEstTerminee(false);

        // Recuperation du premier joueur à jouer
        SharedPreferences sp = this.getSharedPreferences("options", Context.MODE_PRIVATE);
        String nomDuPremierJoueurAJouer = sp.getString("nom1erjoueur", "CROSS");
        symbolePremierJoueur = (nomDuPremierJoueurAJouer.equals("CROSS")?Joueur.CROSS:Joueur.CIRCLE);

        // Création de la partie
        JeuDeMorpion jeuDeMorpion = new JeuDeMorpion(symbolePremierJoueur);
        app.setJeuDeMorpion(jeuDeMorpion);

        // Recuperation des variables choisies par le joueur
        Bundle b = getIntent().getExtras();
        // Recuperation du nombre de joueurs
        nombreDeJoueurs = b.getInt("nombreDeJoueurs");
        if (nombreDeJoueurs == 1){
            // Le joueur veut jouer avec un ordinateur, on le créé donc
            // On récupère d'abord sa difficultée
            String difficultee = sp.getString("difficultee", "EASY");
            Difficultee dif;
            if (difficultee.equals("DUMB")){
                dif = Difficultee.DEBILE;
            } else if (difficultee.equals("EASY")){
                dif = Difficultee.FACILE;
            } else if (difficultee.equals("MEDIUM")){
                dif = Difficultee.NORMALE;
            } else if (difficultee.equals("HARD")) {
                dif = Difficultee.DIFFICILE;
            } else {
                dif = Difficultee.FACILE;
            }
            Joueur symboleDeLIA = symbolePremierJoueur==Joueur.CROSS?Joueur.CIRCLE:Joueur.CROSS;
            ia = new IA(dif, app, symboleDeLIA);
            app.setSymboleDeLIA(symboleDeLIA);
            app.setUneIAEstCree(true);

            // Ensuite, on recupere le booleen de la vitesse de jeu de l'IA
            app.setlIADoitJouerVite(sp.getBoolean("vitesseDeReflexion", false));
        } else {
            app.setUneIAEstCree(false);
            app.setSymboleDeLIA(null);
        }

        changerLeTexteIndiquantQuelJoueurDoitJouer(true);
        //MesFonctionsDApplicationAndroid.lancerLaPub(this, R.string.TESTGOOGLE_banniere, R.id.adView);
    }

    public void lancementFinDuJeu(Joueur joueurGagnant){
        app.setLaPartieEstTerminee(true);
        app.setSymboleDeLIA(null);
        app.setUneIAEstCree(false);
        Bundle b = new Bundle();
        b.putSerializable("joueurGagnant", joueurGagnant);
        b.putSerializable("joueur1", app.getJeuDeMorpion().getJoueur1());
        b.putInt("nombreDeJoueurs", nombreDeJoueurs);
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, FinDuJeu.class, b);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
        finish();
    }

    public void faireJouerUnCoupALIA(){
        ia.jouerUnCoup();

        // On check si le jeu est terminé
        Joueur joueurGagnant = app.getJeuDeMorpion().quelquunAtIlGagne();
        if (joueurGagnant != null){
            lancementFinDuJeu(joueurGagnant);
        }

        // On actualise le canvas
        MaSurfaceView maSurfaceView = (MaSurfaceView) findViewById(R.id.grille);
        maSurfaceView.actualiserCanvas();

        // On actualise le TextView
        changerLeTexteIndiquantQuelJoueurDoitJouer(false);
    }

    public void changerLeTexteIndiquantQuelJoueurDoitJouer(boolean premierTour){
        // On récupère le TextView
        TextView texteMessageJoueurCourant = (TextView) findViewById(R.id.texte_joueur_courant);

        // C'est le premier tour, on ecrit le nom du joueur humain
        if (premierTour){
            if (nombreDeJoueurs == 1)
                texteMessageJoueurCourant.setText(getString(R.string.player_1_apos_s_turn, getString(R.string.human)));
            else {
                if (symbolePremierJoueur.equals(Joueur.CROSS)){
                    texteMessageJoueurCourant.setText(getString(R.string.player_1_apos_s_turn, getString(R.string.cross)));
                } else if (symbolePremierJoueur.equals(Joueur.CIRCLE)){
                    texteMessageJoueurCourant.setText(getString(R.string.player_1_apos_s_turn, getString(R.string.circle)));
                } else {
                    texteMessageJoueurCourant.setText(getString(R.string.player_1_apos_s_turn, "YOU SHOULDN'T READ THIS"));
                }
            }
            return;
        }

        // On récupère l'ancien texte pour switcher en fonction de ce qui etait ecrit
        String ancienTexte = texteMessageJoueurCourant.getText().toString();
        String texteAEcrire;
        if (ancienTexte.equals(getString(R.string.player_1_apos_s_turn, getString(R.string.human)))){
            texteAEcrire = getString(R.string.player_1_apos_s_turn, getString(R.string.computer));
        } else if (ancienTexte.equals(getString(R.string.player_1_apos_s_turn, getString(R.string.computer)))){
            texteAEcrire = getString(R.string.player_1_apos_s_turn, getString(R.string.human));
        } else if (ancienTexte.equals(getString(R.string.player_1_apos_s_turn, getString(R.string.cross)))){
            texteAEcrire = getString(R.string.player_1_apos_s_turn, getString(R.string.circle));
        } else if (ancienTexte.equals(getString(R.string.player_1_apos_s_turn, getString(R.string.circle)))) {
            texteAEcrire = getString(R.string.player_1_apos_s_turn, getString(R.string.cross));
        } else {
            texteAEcrire = "YOU SHOULDN'T READ THIS";
        }

        // Puis on change enfin le texte
        texteMessageJoueurCourant.setText(texteAEcrire);
    }
}
