package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.moteur.Joueur;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsDApplicationAndroid;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class FinDuJeu extends MonActivite {
    private Joueur joueurGagnant;
    private Joueur joueur1;
    private int nombreDeJoueurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_findujeu);

        // Recuperation du gagnant
        Bundle b = getIntent().getExtras();
        joueurGagnant = (Joueur) b.getSerializable("joueurGagnant");
        joueur1 = (Joueur) b.getSerializable("joueur1");
        nombreDeJoueurs = b.getInt("nombreDeJoueurs");

        TextView texteVictoire = (TextView) findViewById(R.id.texte_victoire);
        if (joueurGagnant == Joueur.NOBODY)
            texteVictoire.setText(getString(R.string.texte_egalite));
        else
            texteVictoire.setText(getString(R.string.texte_victoire, getNomDuGagnant()));
    }

    private String getNomDuGagnant(){
        if (nombreDeJoueurs == 1){
            // Recuperation du premier joueur à jouer
            SharedPreferences sp = this.getSharedPreferences("options", Context.MODE_PRIVATE);
            String nomDuPremierJoueurAJouer = sp.getString("nom1erjoueur", "CROSS");
            Joueur symbolePremierJoueur = (nomDuPremierJoueurAJouer.equals("CROSS")?Joueur.CROSS:Joueur.CIRCLE);

            if (joueurGagnant == symbolePremierJoueur){
                return getString(R.string.human);
            } else {
                return getString(R.string.computer);
            }
        } else {
            if (joueurGagnant == Joueur.CROSS){
                return getString(R.string.cross);
            } else {
                return getString(R.string.circle);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, MenuPrincipal.class);
    }

    public void onClickRejouer(View v){
        Bundle b = new Bundle();
        b.putInt("nombreDeJoueurs", nombreDeJoueurs);
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, Jeu.class, b);
    }

    public void onClickGoToMenu(View v){
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, MenuPrincipal.class);
    }
}
