package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsDApplicationAndroid;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class SelectionNombreDeJoueurs extends MonActivite {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_selectionnombredejoueurs);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
        finish();
    }

    public void onClickGotoSelectionSymbole(View v){
        int id = v.getId();
        int nombreDeJoueurs = 0;

        switch(id){
            case R.id.bouton_1_joueur:
                nombreDeJoueurs = 1;
                break;

            case R.id.bouton_2_joueurs:
                nombreDeJoueurs = 2;
                break;

            default:
        }

        Bundle b = new Bundle();
        b.putInt("nombreDeJoueurs", nombreDeJoueurs);
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, Jeu.class, b);
    }
}
