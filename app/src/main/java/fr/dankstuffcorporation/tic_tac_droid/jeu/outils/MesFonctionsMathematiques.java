package fr.dankstuffcorporation.tic_tac_droid.jeu.outils;

public abstract class MesFonctionsMathematiques {

    /**
     * Retourne un entier aléatoire compris dans l'intervalle [floor; ceil].
     */
    public static int getEntierAleatoire(int floor, int ceil){
        return (int) Math.round(((Math.random()*(ceil-floor))+floor));
    }

    /**
     * Retourne un rationnel aléatoire compris dans l'intervalle [floor; ceil].
     */
    public static float getRationnelAleatoire(float floor, float ceil){
        return (float) (Math.random()*(ceil-floor))+floor;
    }

    /**
     * Retourne le maximum entier entre deux nombres.
     */
    public static int max(int a, int b){
        return a>b?a:b;
    }

    /**
     * Retourne le maximum flottant entre deux nombres.
     */
    public static float max(float a, float b){
        return a>b?a:b;
    }

    /**
     * Retourne le maximum entier entre trois nombres.
     */
    public static int max(int a, int b, int c){
        return a>b?(b>c?a:(a>c?a:c)):(b>c?b:(a>c?a:c));
    }

    /**
     * Retourne le maximum flottant entre trois nombres.
     */
    public static float max(float a, float b, float c){
        return a>b?(b>c?a:(a>c?a:c)):(b>c?b:(a>c?a:c));
    }

    /**
     * Retourne le minimum entier entre deux nombres.
     */
    public static int min(int a, int b){
        return a<b?a:b;
    }

    /**
     * Retourne le minimum flottant entre deux nombres.
     */
    public static float min(float a, float b){
        return a<b?a:b;
    }

    /**
     * Retourne le minimum entier entre trois nombres.
     */
    public static int min(int a, int b, int c){
        return a<b?(b<c?a:(a<c?a:c)):(b<c?b:(a<c?a:c));
    }

    /**
     * Retourne le minimum flottant entre trois nombres.
     */
    public static float min(float a, float b, float c){
        return a<b?(b<c?a:(a<c?a:c)):(b<c?b:(a<c?a:c));
    }
}
