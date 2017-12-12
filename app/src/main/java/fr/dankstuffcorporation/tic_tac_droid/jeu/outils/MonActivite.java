package fr.dankstuffcorporation.tic_tac_droid.jeu.outils;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class MonActivite extends Activity {
    protected MonApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des variables
        app = (MonApplication) this.getApplication();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    public MonApplication getApp(){
        return app;
    }
}
