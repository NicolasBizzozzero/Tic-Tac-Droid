package fr.dankstuffcorporation.tic_tac_droid.jeu.moteur;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.dankstuffcorporation.tic_tac_droid.R;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MesFonctionsMathematiques;
import fr.dankstuffcorporation.tic_tac_droid.jeu.outils.MonApplication;
import fr.dankstuffcorporation.tic_tac_droid.jeu.activites.Jeu;

/**
 * Created by Nicolas on 24/06/2016.
 */
public class MaSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private MonApplication app;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int largeurCanvas, hauteurCanvas;
    private final Drawable drawableCroix;
    private final Drawable drawableCercle;
    private Context contexteDuJeu;
    private boolean surfaceOK;
    private boolean lIADoitJouer;

    public MaSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        // Chargement du contexte de l'application
        app = (MonApplication) (this.getContext().getApplicationContext());

        // Chargement du contexte du jeu
        contexteDuJeu = context;

        // On recupere les drawables
        drawableCroix = getResources().getDrawable(R.drawable.drawable_croix);
        drawableCercle = getResources().getDrawable(R.drawable.drawable_cercle);

        surfaceOK = false;
        lIADoitJouer = false;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceOK = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        largeurCanvas = holder.getSurfaceFrame().width();
        hauteurCanvas = largeurCanvas;
        holder.setFixedSize(largeurCanvas, largeurCanvas);
        essayerDeDessiner();
        surfaceOK = true;
    }

    private void essayerDeDessiner() {
        surfaceHolder = this.getHolder();
        canvas = surfaceHolder.lockCanvas();

        if (canvas != null) {
            nettoyerLaGrille();
            dessinerGrilleDuJeu();
            dessinerPiecesDuJeu();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void nettoyerLaGrille(){
        canvas.drawColor(0xffffffff);
    }

    private void dessinerGrilleDuJeu() {
        // Dessiner les lignes verticales
        Paint peintureGrille = new Paint();
        peintureGrille.setColor(0xff000000);
        float[] tableauLignesHorizontales = {largeurCanvas/3, 0, largeurCanvas/3, largeurCanvas,
                                             (largeurCanvas/3)*2, 0,(largeurCanvas/3)*2, largeurCanvas};
        canvas.drawLines(tableauLignesHorizontales, peintureGrille);

        // Dessiner les lignes horizontales
        float[] tableauLignesVerticales = {0, largeurCanvas/3, largeurCanvas, largeurCanvas/3,
                0, (largeurCanvas/3)*2, largeurCanvas, (largeurCanvas/3)*2};
        canvas.drawLines(tableauLignesVerticales, peintureGrille);
    }

    private void dessinerPiecesDuJeu() {
        int ZONE_BLANCHE = 20;
        Paint peintureCroix = new Paint();
        Paint peintureCercle = new Paint();
        peintureCroix.setColor(0xff000000);
        peintureCercle.setColor(0xff0000ff);
        Coup[][] grille = app.getJeuDeMorpion().getGrille().getGrilleDeCoups();

        for (int y=0; y<3; y++){
            for (int x=0; x<3; x++){
                // On recupere le coup a l'emplacement prévu
                Coup c = grille[y][x];

                // On dessine le coup si il existe
                if (c == null)
                    continue;
                switch(c.getJoueur()){
                    case CROSS:
                        drawableCroix.setBounds((x*(largeurCanvas/3))+ZONE_BLANCHE,
                                                (y*(largeurCanvas/3))+ZONE_BLANCHE,
                                                ((x+1)*(largeurCanvas/3))-ZONE_BLANCHE,
                                                ((y+1)*(largeurCanvas/3))-ZONE_BLANCHE);
                        drawableCroix.draw(canvas);
                        break;

                    case CIRCLE:
                        drawableCercle.setBounds((x*(largeurCanvas/3))+ZONE_BLANCHE,
                                (y*(largeurCanvas/3))+ZONE_BLANCHE,
                                ((x+1)*(largeurCanvas/3))-ZONE_BLANCHE,
                                ((y+1)*(largeurCanvas/3))-ZONE_BLANCHE);
                        drawableCercle.draw(canvas);
                        break;

                    default:

                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Si l'IA doit jouer, on block les inputs
        if (lIADoitJouer) {
            return false;
        }

        float x = event.getX();
        float y = event.getY();

        // On transforme le click en position
        Position positionClick = floatToPosition(x, y);

        // Gestion des différents MotionEvent
        final int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                /// On fait jouer le joueur
                boolean leJoueurACorrectementJoue = app.getJeuDeMorpion().jouer(positionClick);
                // On check si le jeu est terminé
                Joueur joueurGagnant = app.getJeuDeMorpion().quelquunAtIlGagne();
                if (joueurGagnant != null){
                    Jeu jeu = (Jeu) contexteDuJeu;
                    jeu.lancementFinDuJeu(joueurGagnant);
                }

                // Si le joueur a correctement joué, on dit à l'IA de jouer si elle existe
                if (leJoueurACorrectementJoue) {
                    Jeu jeu = (Jeu) contexteDuJeu;
                    jeu.changerLeTexteIndiquantQuelJoueurDoitJouer(false);
                    actualiserCanvas();

                    if (app.isUneIAEstCree()) {
                        lIADoitJouer = true;
                        Runnable r = new Runnable() {
                            @Override
                            public void run(){
                                Jeu jeu = (Jeu) contexteDuJeu;
                                jeu.faireJouerUnCoupALIA();
                                actualiserCanvas();
                                lIADoitJouer = false;
                            }
                        };
                        Handler h = new Handler();
                        if (app.islIADoitJouerVite())
                            h.post(r);
                        else
                            h.postDelayed(r, MesFonctionsMathematiques.getEntierAleatoire(200, 1200));
                    }
                }
                break;
        }

        actualiserCanvas();
        return true ;
    }

    private Position floatToPosition(float x, float y){
        return new Position((int) Math.floor(x / (largeurCanvas/3)), (int) Math.floor(y / (hauteurCanvas/3)));
    }

    public void actualiserCanvas(){
        if (surfaceOK) {
            surfaceHolder.lockCanvas();
            nettoyerLaGrille();
            dessinerGrilleDuJeu();
            dessinerPiecesDuJeu();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
