import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui ecrit le fichier de sortie avec un format d'indentation.
 */

public class Ecriture {

    public static String contenuPlanif = "";
    public static String contenuMessage = "";
    public static String contenuStats = "";

    public static void ecrirePlanifJSON(Planification planification, String fichierSortie) {
        try {
            ecrireActivites(planification.getTabActivites());
            ecrireRecommandations(planification);
            contenuPlanif += "\n}";
            DiskFile.saveStringIntoFile(fichierSortie, contenuPlanif);
        } catch (IOException e) {
            System.out.println(Constantes.MSG_FICHIER_SORTIE_IMPOSSIBLE + fichierSortie);
            System.exit(-1);
        }
    }

    public static void ecrireStatsJSON(Statistiques stats) {
        try {
            ecrireStats(stats);
            DiskFile.saveStringIntoFile("statistiques/statistiques.json", contenuStats);
        } catch (IOException e) {
            System.out.println(Constantes.MSG_FICHIER_SORTIE_IMPOSSIBLE + "statistiques/statistiques.json");
            System.exit(-1);
        }
    }

    public static void ecrireActivites(ArrayList<Activite> tabActivites) {
        contenuPlanif = "{\n" + Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_ACTIVITES + "[\n";

        for (int i = 0; i < tabActivites.size(); i++) {
            contenuPlanif += Constantes.INDENT_NIV_2 + "{\n" + Constantes.INDENT_NIV_3 + Constantes.JSON_OBJET_DESCRIPTION
                + "\"" + tabActivites.get(i).getDescription() + "\"," + "\n" + Constantes.INDENT_NIV_3 + Constantes.JSON_OBJET_PRIX_PAR_ELEVE
                + "\"" + tabActivites.get(i).getPrixParEleve() + "\"\n" + Constantes.INDENT_NIV_2 + "}";

            validerJSONCouples(tabActivites, i);
        }
    }

    public static void ecrireRecommandations(Planification planification) {
        ArrayList<String> tabRecommandations = Recommandations.validerRecommandations(planification);

        if (tabRecommandations != null) {
            contenuPlanif += ",\n" + Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_RECOMMANDATIONS + "[\n";

            for (int i = 0; i < tabRecommandations.size(); i++) {
                contenuPlanif += Constantes.INDENT_NIV_2 + tabRecommandations.get(i);
                validerJSONCouples(tabRecommandations, i);
            }
        }
    }

    public static void ecrireMessage(String message, String fichierSortie) {
        try {
            contenuMessage = "{\n" + Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_MESSAGE + "\"" + message + "\"\n}";
            DiskFile.saveStringIntoFile(fichierSortie, contenuMessage);
            System.exit(-2);
        } catch (IOException e) {
            System.out.println(Constantes.MSG_FICHIER_SORTIE_IMPOSSIBLE + fichierSortie);
            System.exit(-1);
        }
    }

    public static void ecrireStats(Statistiques stats) {
        contenuStats = "{\n" + Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_TOTAL + stats.getTotalActivites() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_MOINS_20_DOLLARS + stats.getNbMoinsDe20Dollars() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_ENTRE_20_ET_40_DOLLARS + stats.getNbEntre20Et40Dollars() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_PLUS_40_DOLLARS + stats.getNbPlusDe40Dollars() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_PAR_MARCHE + stats.getNbParMarche() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_PAR_METRO +  stats.getNbParMetro() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_ACTIVITES_PAR_AUTOBUS + stats.getNbParAutobus() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_DISTANCE_MAXIMALE + stats.getDistanceMaximale() + ",\n";
        contenuStats += Constantes.INDENT_NIV_1 + Constantes.JSON_OBJET_STATS_PRIX_PAR_ELEVE_MAXIMAL + stats.getPrixParElevesMaximal() + "\n}";
    }

    public static void validerJSONCouples(ArrayList tabCouples, int i) {
        if (i < tabCouples.size() - 1) {
            contenuPlanif += ",\n";
        } else {
            contenuPlanif += "\n" + Constantes.INDENT_NIV_1 + "]";
        }
    }

    public static String formaterArgent(String prix) {
        prix = prix.replace(",", ".");

        if (!prix.endsWith(" $")) {
            prix += " $";
        } return prix;
    }
}
