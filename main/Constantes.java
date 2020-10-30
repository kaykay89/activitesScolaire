public class Constantes {

    public static final int NB_PROF = 1;

    public static final int CODE_MARCHE = 0;
    public static final int CODE_METRO = 1;
    public static final int CODE_AUTOBUS = 2;

    public static final int TRANCHE_AGE_1 = 5;
    public static final int TRANCHE_AGE_2_DEB = 6;
    public static final int TRANCHE_AGE_2_FIN = 17;
    public static final int TRANCHE_AGE_3 = 18;

    public static final double PRIX_MARCHE = 0.00;
    public static final double PRIX_METRO_TRANCHE_AGE_1 = 0.00;
    public static final double PRIX_METRO_TRANCHE_AGE_2 = 4.50;
    public static final double PRIX_METRO_TRANCHE_AGE_3 = 6.50;
    public static final double PRIX_AUTOBUS_MOINS_TRANCHE_KM = 4.00;
    public static final double PRIX_AUTOBUS_TRANCHE_KM_ET_PLUS = 4.95;
    public static final double PRIX_AUTOBUS_PLEIN = 30.00;

    public static final int AUTOBUS_CAPACITE_MAX = 30;
    public static final int AUTOBUS_TRANCHE_KM = 40;
    public static final int AUTOBUS_NB_PARENTS_RABAIS = 4;

    public static final double RABAIS_AUTOBUS_AGE = 0.10;
    public static final double RABAIS_AUTOBUS_PARENTS = 0.10;
    public static final double RABAIS_SIMULTANES = 0.20;

    public static final double PRIX_GARDE = 9.00;

    public static final int NOMBRE_ELEVES_MAX = 32;
    public static final int NOMBRE_ELEVES_MIN = 1;

    public static final int AGE_ELEVES_MAX = 17;
    public static final int AGE_ELEVES_MIN = 4;

    public static final int DISTANCE_MAX_ACTIVITE = 120;
    public static final int DISTANCE_MAX_MARCHE_MOINS_10_ANS = 8;
    public static final int DISTANCE_MAX_MARCHE_AUTRES = 12;
    public static final int DISTANCE_MAX_METRO = 40;
    public static final int DISTANCE_MAX_BUS = 80;

    public static final String INDENT_NIV_1 = "    ";
    public static final String INDENT_NIV_2 = "        ";
    public static final String INDENT_NIV_3 = "            ";

    public static final String JSON_OBJET_ACTIVITES = "\"activites\": ";
    public static final String JSON_OBJET_DESCRIPTION = "\"description\": ";
    public static final String JSON_OBJET_PRIX_PAR_ELEVE = "\"prix_par_eleve\": ";

    public static final String JSON_OBJET_MESSAGE = "\"message\": ";
    public static final String JSON_OBJET_RECOMMANDATIONS = "\"recommandations\": ";

    public static final String JSON_OBJET_STATS_ACTIVITES_TOTAL = "\"total_activites\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_MOINS_20_DOLLARS = "\"activites_moins_de_20_dollars\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_ENTRE_20_ET_40_DOLLARS = "\"activites_entre_20_et_40_dollars\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_PLUS_40_DOLLARS = "\"activites_plus_de_40_dollars\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_PAR_MARCHE = "\"activites_par_marche\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_PAR_METRO = "\"activites_par_metro\": ";
    public static final String JSON_OBJET_STATS_ACTIVITES_PAR_AUTOBUS = "\"activites_par_autobus\": ";
    public static final String JSON_OBJET_STATS_DISTANCE_MAXIMALE = "\"distance_maximale\": ";
    public static final String JSON_OBJET_STATS_PRIX_PAR_ELEVE_MAXIMAL = "\"prix_par_eleve_maximal\": ";

    public static final String FORMAT_TABLEAU = "---------------------------------------------------";
    public static final String FORMAT_STATS_ACTIVITES_TOTAL = "Nombre total d'activités soumises: ";
    public static final String FORMAT_STATS_ACTIVITES_MOINS_20_DOLLARS = "Nombre total d'activités de moins de 20$ : ";
    public static final String FORMAT_STATS_ACTIVITES_ENTRE_20_ET_40_DOLLARS = "Nombre total d'activités entre 20 et 40$ : ";
    public static final String FORMAT_STATS_ACTIVITES_PLUS_40_DOLLARS = "Nombre total d'activités de plus de 40$ : ";
    public static final String FORMAT_STATS_ACTIVITES_PAR_MARCHE = "Nombre total d'activités par marche : ";
    public static final String FORMAT_STATS_ACTIVITES_PAR_METRO = "Nombre total d'activités par métro: ";
    public static final String FORMAT_STATS_ACTIVITES_PAR_AUTOBUS = "Nombre total d'activités par autobus: ";
    public static final String FORMAT_STATS_DISTANCE_MAXIMALE = "Distance maximale soumise : ";
    public static final String FORMAT_STATS_PRIX_PAR_ELEVE_MAXIMAL = "Prix par élève maximal pour une activité : ";

    public static final String MSG_STATS_REINITIALISES = "Les statistiques ont été réinitialisés.";
    public static final String MSG_NB_ARGUMENTS = "Le nombre d'arguments n'est pas valide.";
    public static final String MSG_OPTION_INVALIDES = "L'option n'est pas valide.";
    public static final String MSG_FICHIER_FORMAT_JSON_INVALIDE = "Le fichier d'entrée/sortie n'est pas un fichier JSON.";
    public static final String MSG_FICHIER_JSON_INVALIDE = "Le fichier JSON n'est pas valide : ";
    public static final String MSG_FICHIER_EXISTE_PAS = "Le fichier JSON n'existe pas : ";
    public static final String MSG_JSON_PROPRIETE_MANQUANTE = "Certaines propriétés du fichier JSON sont manquantes :";
    public static final String MSG_FICHIER_SORTIE_IMPOSSIBLE = "Création impossible du fichier de sortie : ";

    public static final String MSG_DESCRIPTION_VIDE = "Une des descriptions d'activité est vide.";
    public static final String MSG_DESCRIPTION_NON_UNIQUE = "Il y a des descriptions identiques.";
    public static final String MSG_NOMBRE_ELEVES_PLUS_32 = "Le nombre d'élèves est supérieur à 32.";
    public static final String MSG_NOMBRE_ELEVES_0 = "Le nombre d'élèves doit être au moins 1.";
    public static final String MSG_AGE_INFERIEUR_4 = "L'âge des élèves est inférieur à 4 ans.";
    public static final String MSG_AGE_SUPERIEUR_17 = "L'âge des élèves est supérieur à 17 ans.";
    public static final String MSG_PLUS_ADULTES_QUE_ENFANTS = "Le nombre d'adultes dépasse le nombre d'enfants lors d'une activité.";
    public static final String MSG_MONTANT_INVALIDE = "L'un des prix d'activités n'est pas dans le bon format.";
    public static final String MSG_MONTANT_NEGATIF= "L'un des prix d'activités est négatif.";
    public static final String MSG_DISTANCE_NEGATIVE = "L'une des distance est négative.";
    public static final String MSG_DISTANCE_SUPERIEURE_120KM = "L'une des distance est supérieure à 120 KM.";
    public static final String MSG_TYPE_TRANSPORT = "Le type de transport n'est pas valide.";
    public static final String MSG_FORMAT_DATE = "Le format de date n'est pas AAAA-MM-JJ.";

    public static final String RECOMMANDATION_PRIX = " : cette activité est trop dispendieuse (plus de 45.00 $).\"";
    public static final String RECOMMANDATION_HORS_ECOLE = "\"Il faudrait avoir au moins une sortie hors de l'école.\"";
    public static final String RECOMMANDATION_ANNEE_SCOLAIRE = "\"Une des activités ne se situe pas dans l'année scolaire du 1er septembre"
            + " au 24 juin.\"";
    public static final String RECOMMANDATION_ECART_MAX = "\"L'écart maximal entre 2 activités devrait être de 2 mois.\"";
    public static final String RECOMMANDATION_ECART_MIN = "\"L'écart minimal entre 2 activités devrait être de 3 semaines.\"";
    public static final String RECOMMANDATION_PRIX_ANNEE = "\"Le montant total par élève sur l'année ne doit pas dépasser 300.00 $.\"";
    public static final String RECOMMANDATION_BUS_MAX = "\"Une des activités par autobus dépasse les 80 KM de permis.\"";
    public static final String RECOMMANDATION_METRO_MAX = "\"Une des activités par métro dépasse les 40 KM de permis.\"";
    public static final String RECOMMANDATION_MARCHE_MAX_8KM = "\"Une des activités par marche dépasse les 8 KM permis pour les élèves de"
            + " moins de 10 ans.\"";
    public static final String RECOMMANDATION_MARCHE_MAX_12KM = "\"Une des activités par marche dépasse le 12 KM permis pour les élèves "
            + "de 10 ans et plus.\"";
}
