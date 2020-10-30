import net.sf.json.JSONObject;
import java.text.DecimalFormat;

/**
 * Classe de gestion soutenant le deroulement du programme.
 */

public class Gestion {

    private static DecimalFormat df = new DecimalFormat("0.00"); //Permet d'afficher le prix avec 2 décimales

    public static void produireCalculs(Planification planification, String fichierSortie) {
        planification = CalculActivites.calculerActivites(planification);
        Ecriture.ecrirePlanifJSON(planification, fichierSortie);

        Statistiques stats = new Statistiques();
        JSONObject anciennesStats = Lecture.objetDeclaration("statistiques/statistiques.json");
        stats.calculerStats(planification, anciennesStats);
        Ecriture.ecrireStatsJSON(stats);
    }

    public static void afficherInvalideJSON() {
        System.out.println(Constantes.MSG_FICHIER_FORMAT_JSON_INVALIDE);
        System.exit(-1);
    }

    public static void afficherNbArgumentsErreur() {
        System.out.println(Constantes.MSG_NB_ARGUMENTS);
        System.exit(-1);
    }

    public static void afficherStatistiquesFormat(JSONObject stats) {
        System.out.print("\n" + Constantes.FORMAT_TABLEAU);
        afficherStatistiques(stats);
        System.out.print("\n" + Constantes.FORMAT_TABLEAU + "\n");
    }

    public static void afficherStatistiques(JSONObject stats) {
        String prixParEleveMaximale = Ecriture.formaterArgent(df.format(stats.getDouble("prix_par_eleve_maximal")));
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_TOTAL + stats.getInt("total_activites") + "\n");
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_MOINS_20_DOLLARS + stats.getInt("activites_moins_de_20_dollars"));
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_ENTRE_20_ET_40_DOLLARS + stats.getInt("activites_entre_20_et_40_dollars"));
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_PLUS_40_DOLLARS + stats.getInt("activites_plus_de_40_dollars") + "\n");
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_PAR_MARCHE + stats.getInt("activites_par_marche"));
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_PAR_METRO + stats.getInt("activites_par_metro"));
        System.out.print("\n" + Constantes.FORMAT_STATS_ACTIVITES_PAR_AUTOBUS + stats.getInt("activites_par_autobus") + "\n");
        System.out.print("\n" + Constantes.FORMAT_STATS_DISTANCE_MAXIMALE + stats.getInt("distance_maximale") + " KM");
        System.out.print("\n" + Constantes.FORMAT_STATS_PRIX_PAR_ELEVE_MAXIMAL + prixParEleveMaximale);
    }
}
