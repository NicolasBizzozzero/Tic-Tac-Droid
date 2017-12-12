package fr.dankstuffcorporation.tic_tac_droid.jeu.outils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;
import java.util.Date;

public abstract class MesFonctionsDApplicationAndroid {
    private class YouReFuckedException extends Exception {
        public YouReFuckedException(){
            super("Good luck.");
        }
    }

    public static void afficherUnToastDIndisponibilite(Application contexteDeLApplication, int identifiantDeLaString){
        // Textes à afficher (copier-coller en cas de perte)
        // Français : Nous sommes desolés, cette option n\'est pas disponible actuellement. Nous travaillons dessus pour la prochaine mise à jour.
        // Anglais  : We\'re sorry, this feature is not available yet. We\'re working on it for the next update.
        (Toast.makeText(contexteDeLApplication, identifiantDeLaString, Toast.LENGTH_LONG)).show();
    }

    public static void relierLeXMLALActivite(Activity cetteActivite, int idDuXML){
        cetteActivite.setContentView(idDuXML);
    }

    public static void passerAUneAutreActivite(Activity activiteDeDepart, Class<?> nomDeLActiviteDArrivee){
        Intent intent = new Intent(activiteDeDepart, nomDeLActiviteDArrivee);
        activiteDeDepart.startActivity(intent);
        activiteDeDepart.finish();
    }

    public static void passerAUneAutreActivite(Activity activiteDeDepart, Class<?> nomDeLActiviteDArrivee, Bundle bundleAEnvoyerDansLActivite){
        Intent intent = new Intent(activiteDeDepart, nomDeLActiviteDArrivee);
        intent.putExtras(bundleAEnvoyerDansLActivite);
        activiteDeDepart.startActivity(intent);
        activiteDeDepart.finish();
    }

    public static void lancerLaPub(Activity cetteActivite, int identifiantBanniere, int idAdView){
        MobileAds.initialize(cetteActivite.getApplicationContext(), cetteActivite.getString(identifiantBanniere));
        AdView monAdView = (AdView) cetteActivite.findViewById(idAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        monAdView.loadAd(adRequest);
    }

    public static void detruireLApplication(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static int getAnneeActuelleDuSysteme(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Date getDateDuSysteme(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
