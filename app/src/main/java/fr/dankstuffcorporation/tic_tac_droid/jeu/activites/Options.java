package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsDApplicationAndroid;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class Options extends MonActivite {
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_options);

        // Obtention des SP et de leur éditeur
        sp = getSharedPreferences("options", Context.MODE_PRIVATE);
        spe = sp.edit();

        // On check les box correspondantes aux anciennes preferences du joueur
        // Box du premier symbole à jouer
        String premierJoueur = sp.getString("nom1erjoueur", "CROSS");
        RadioGroup rdGroup = (RadioGroup) findViewById(R.id.radioGroupFirstPlayer);
        if (premierJoueur.equals("CROSS")){
            rdGroup.check(R.id.radiobuttoncross);
        } else if (premierJoueur.equals("CIRCLE")){
            rdGroup.check(R.id.radiobuttoncircle);
        } else {
            rdGroup.clearCheck();
        }
        // Box de la difficultée de l'ordinateur
        String difficulteeOrdinateur = sp.getString("difficultee", "EASY");
        final RadioGroup rdGroup2 = (RadioGroup) findViewById(R.id.radioGroupComputerDifficukty);
        if (difficulteeOrdinateur.equals("DUMB")){
            //rdGroup2.check(R.id.radioButtondumb);
        } else if (difficulteeOrdinateur.equals("EASY")){
            rdGroup2.check(R.id.radioButtoneasy);
        } else if (difficulteeOrdinateur.equals("MEDIUM")){
            rdGroup2.check(R.id.radioButtonmedium);
        } else if (difficulteeOrdinateur.equals("HARD")){
            rdGroup2.check(R.id.radioButtonhard);
        } else {
            rdGroup2.clearCheck();
        }
        // Switch de la vitesse de reflexion de l'ordinateur
        boolean vitesse = sp.getBoolean("vitesseDeReflexion", false);
        final Switch switchVitesse = (Switch) findViewById(R.id.switchOrdinateurPlusRapide);
        if (vitesse){
            switchVitesse.setChecked(true);
        } else {
            switchVitesse.setChecked(false);
        }

        // On pose un écouteur sur le switch du changement de premier joueur
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radiobuttoncross:
                        spe.putString("nom1erjoueur", "CROSS");
                        spe.apply();
                        break;

                    case R.id.radiobuttoncircle:
                        spe.putString("nom1erjoueur", "CIRCLE");
                        spe.apply();
                        break;

                    default:
                }
            }
        });
        // On pose un écouteur sur la box de changement de difficultée
        rdGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    /*case R.id.radioButtondumb:
                        spe.putString("difficultee", "DUMB");
                        spe.apply();
                        break;*/

                    case R.id.radioButtoneasy:
                        spe.putString("difficultee", "EASY");
                        spe.apply();
                        break;

                    case R.id.radioButtonmedium:
                        spe.putString("difficultee", "MEDIUM");
                        spe.apply();
                        break;

                    case R.id.radioButtonhard:
                        spe.putString("difficultee", "HARD");
                        spe.apply();
                        break;

                    default:
                }
            }
        });

        // On pose un ecouteur sur le switch de la vitesse de reflexion de l'ordinateur
        switchVitesse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    spe.putBoolean("vitesseDeReflexion", true);
                    spe.apply();
                } else {
                    spe.putBoolean("vitesseDeReflexion", false);
                    spe.apply();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
        finish();
    }
}
