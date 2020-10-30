import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe qui valide les erreurs possibles liees aux fichiers et a la planification d'activites.
 */

public class Erreurs {

    public static String message = "";

    public static void validerContenu(String fichierEntree, String fichierSortie){
        Lecture.jsonPlanification = Lecture.objetDeclaration(fichierEntree);
        message = validerProprietes();
        validerMessageErreur(message, fichierSortie);

        Planification planification = Lecture.getPlanification(Lecture.jsonPlanification);
        message = validerPlanification(planification);
        validerMessageErreur(message, fichierSortie);
        Gestion.produireCalculs(planification, fichierSortie);
    }

    public static void validerEntreesUtilisateur (String fichierEntree, String fichierSortie) {
        if (fichierEntree.endsWith(".json") && fichierSortie.endsWith(".json")) {
            validerContenu(fichierEntree, fichierSortie);
        } else {
            Gestion.afficherInvalideJSON();
        }
    }

    public static void validerMessageErreur (String message, String fichierSortie) {
        if (!message.isEmpty()) {
            System.out.println(message);
            Ecriture.ecrireMessage(message, fichierSortie);
        }
    }

    public static void validerOptionsStatistiques(String args) {
        if (args.equals("-S")) {
            Gestion.afficherStatistiquesFormat(Lecture.objetDeclaration("statistiques/statistiques.json"));
        } else if (args.equals("-SR")) {
            Statistiques stats = new Statistiques();
            Ecriture.ecrireStatsJSON(stats);
            System.out.println(Constantes.MSG_STATS_REINITIALISES);
        } else {
            System.out.println(Constantes.MSG_OPTION_INVALIDES);
            System.exit(-1);
        }
    }

    public static String validerPlanification(Planification planification) {
        for (int j = 0; message.isEmpty() && j == 0; j++) {
            message = validerNbEleves(planification.getEleves().getNombreEleves());
            message = validerAge(planification.getEleves().getAge());
            message = validerActivites(planification.getEleves().getNombreEleves(), planification.getTabActivites());
        } return message;
    }

    public static String validerActivites(int nombreEleves, ArrayList<Activite> tabActivites) {
        for (int i = 0; i < tabActivites.size(); i++) {
            for (int j = 0; message.isEmpty() && j == 0; j++) {
                message = validerDescription(tabActivites);
                message = validerNbAdultes(tabActivites.get(i).getNombreParentsAccompagnateurs() + Constantes.NB_PROF, nombreEleves);
                message = validerPrixEnfantsAdultes(tabActivites.get(i).getPrixUnitaireEnfant(), tabActivites.get(i).getPrixUnitaireAdulte());
                message = validerDistance(tabActivites.get(i).getDistance());
                message = validerFormatDate(tabActivites.get(i).getDate());
                message = validerTransport(tabActivites.get(i).getTransport());
            }
        } return message;
    }

    public static String validerProprietes() {
        String proprietes = Lecture.validerObjetsActivite();

        if (!proprietes.isEmpty()) {
            message = Constantes.MSG_JSON_PROPRIETE_MANQUANTE + proprietes;
        } return message;
    }

    public static String validerDescription(ArrayList<Activite> tabActivites) {
        HashSet<String> setDescriptionActivites = new HashSet<>();

        for (int i = 0; i < tabActivites.size(); i++) {
            if (!setDescriptionActivites.add(tabActivites.get(i).getDescription())) {
                message = Constantes.MSG_DESCRIPTION_NON_UNIQUE;
            } else if (tabActivites.get(i).getDescription().length() == 0) {
                message = Constantes.MSG_DESCRIPTION_VIDE;
            }
        } return message;
    }

    public static String validerNbEleves(int nombreEleves) {
        if (nombreEleves < Constantes.NOMBRE_ELEVES_MIN) {
            message = Constantes.MSG_NOMBRE_ELEVES_0;
        } else if (nombreEleves > Constantes.NOMBRE_ELEVES_MAX) {
            message = Constantes.MSG_NOMBRE_ELEVES_PLUS_32;
        } return message;
    }

    public static String validerAge(int ageEleves) {
        if (ageEleves < Constantes.AGE_ELEVES_MIN) {
            message = Constantes.MSG_AGE_INFERIEUR_4;
        } else if (ageEleves > Constantes.AGE_ELEVES_MAX) {
            message = Constantes.MSG_AGE_SUPERIEUR_17;
        } return message;
    }

    public static String validerNbAdultes(int nombreAdultes, int nombreEleves) {
        if (nombreAdultes > nombreEleves) {
            message = Constantes.MSG_PLUS_ADULTES_QUE_ENFANTS;
        } return message;
    }

    public static String validerPrixEnfantsAdultes(String prixEnfant, String prixAdulte) {
        if (!validerFormatPrix(prixEnfant) || !validerFormatPrix(prixAdulte)) {
            message = Constantes.MSG_MONTANT_INVALIDE;
        } else if (prixEnfant.charAt(0) == '-' || prixAdulte.charAt(0) == '-') {
            message = Constantes.MSG_MONTANT_NEGATIF;
        } return message;
    }

    public static boolean validerFormatPrix(String prix) {
        boolean validation = true;

        if (!prix.matches("^[-]?[0-9]+([\\.]|[\\,])[0-9]{2}[\\s][\\$]$")) {
            validation = false;
        } return validation;
    }

    public static String validerDistance ( double distance) {
        if (distance < 0) {
            message = Constantes.MSG_DISTANCE_NEGATIVE;
        } else if (distance > Constantes.DISTANCE_MAX_ACTIVITE) {
            message = Constantes.MSG_DISTANCE_SUPERIEURE_120KM;
        } return message;
    }

    public static String validerFormatDate(String date) {
        if (!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            message = Constantes.MSG_FORMAT_DATE;
        } else {
            try {
                LocalDate.parse(date);
            } catch (Exception e) {
                message = Constantes.MSG_FORMAT_DATE;
            }
        } return message;
    }

    public static String validerTransport(int transport) {
        if (transport != Constantes.CODE_MARCHE && transport != Constantes.CODE_METRO && transport != Constantes.CODE_AUTOBUS) {
            message = Constantes.MSG_TYPE_TRANSPORT;
        } return message;
    }
}
