package fr.dankstuffcorporation.tic_tac_droid.jeu.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsDApplicationAndroid;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsMathematiques;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonActivite;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class MenuPrincipal extends MonActivite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_menuprincipal);

        TextView copyright = (TextView) findViewById(R.id.textView2);
        copyright.setText(getString(R.string.copyright_ndank_stuff_corporation_nall_rights_reserved, MesFonctionsDApplicationAndroid.getAnneeActuelleDuSysteme()));

        //TODO: retirer ça
        Log.d("JETESTDESTRUCS", "" + MesFonctionsMathematiques.min(4, 8, -3));
        Log.d("JETESTDESTRUCS", "" + MesFonctionsMathematiques.min(1, -19, 0));
        Log.d("JETESTDESTRUCS", "" + MesFonctionsMathematiques.min(-55, 7, 8));
    }

    public void onClickGoToSelectionNombreDeJoueurs(View v){
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, SelectionNombreDeJoueurs.class);
    }

    public void onClickGoToOptions(View v){
        MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, Options.class);
    }

    public void onClickGoToStatistiques(View v){
        MesFonctionsDApplicationAndroid.afficherUnToastDIndisponibilite(app, R.string.fonctionindisponible);
        //MesFonctionsDApplicationAndroid.passerAUneAutreActivite(this, Statistiques.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MesFonctionsDApplicationAndroid.detruireLApplication();
    }
}
