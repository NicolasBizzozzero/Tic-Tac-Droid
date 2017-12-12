package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.content.Intent;
import android.os.Bundle;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

public class Aide extends MonActivite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_aide);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
        finish();
    }
}
