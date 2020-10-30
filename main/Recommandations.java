import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe qui dresse une liste de recommandations liee a la planification d'activites.
 */

public class Recommandations {

    public static ArrayList<String> tabRecommandations = new ArrayList<>();

    public static ArrayList<String> validerRecommandations(Planification planification) {
        ArrayList<Activite> tabActivites = planification.getTabActivites();

        for (int i = 0; i < tabActivites.size(); i++) {
            validerPrix(tabActivites.get(i).getPrixParEleve(), tabActivites, i);
            validerExterieur(tabActivites);
            validerDateAnneeScolaire(tabActivites);
            validerDistance(tabActivites.get(i).getTransport(), tabActivites.get(i).getDistance(), planification.getEleves().getAge());
        }
        List<String> listeSansDuplications = tabRecommandations.stream().distinct().collect(Collectors.toList());
        return (ArrayList<String>) listeSansDuplications;
    }

    public static void validerPrix(String prixParEleve, ArrayList<Activite> tabActivites, int i) {
        double prixParEleveEnDouble = CalculActivites.formaterPrixADouble(prixParEleve);
        double totalPayable = CalculActivites.calculerTotalPayable(tabActivites);

        if (prixParEleveEnDouble > 45.00) {
            tabRecommandations.add("\"" + tabActivites.get(i).getDescription() + Constantes.RECOMMANDATION_PRIX);
        } if (totalPayable > 300.00) {
            tabRecommandations.add(Constantes.RECOMMANDATION_PRIX_ANNEE);
        }
    }

    public static void validerExterieur(ArrayList<Activite> tabActivites) {
        boolean exterieur = false;

        for (int i = 0; i < tabActivites.size() && !exterieur; i++) {
            int distance = tabActivites.get(i).getDistance();

            if (distance == 0) {
                exterieur = true;
                tabRecommandations.add(Constantes.RECOMMANDATION_HORS_ECOLE);
            }
        }
    }

    public static void validerDateAnneeScolaire(ArrayList<Activite> tabActivites) {
        for (int i = 0; i < tabActivites.size(); i++) {
            LocalDate date = LocalDate.parse(tabActivites.get(i).getDate());

            if (!MonthDay.of(date.getMonth(), date.getDayOfMonth()).isAfter(MonthDay.of(8,31))
                    && !MonthDay.of(date.getMonth(), date.getDayOfMonth()).isBefore(MonthDay.of(6, 25))) {
                tabRecommandations.add(Constantes.RECOMMANDATION_ANNEE_SCOLAIRE);
            } else {
                validerEcartDates(tabActivites);
            }
        }
    }

    public static void validerEcartDates(ArrayList<Activite> tabActivites) {
        ArrayList<String> tabDates = trierDates(tabActivites);
        for (int i = 0; i < tabDates.size() - 1; i++) {
            long ecartMois = ChronoUnit.MONTHS.between(LocalDate.parse(tabDates.get(i)), LocalDate.parse(tabDates.get(i + 1)));
            long ecartSem = ChronoUnit.WEEKS.between(LocalDate.parse(tabDates.get(i)), LocalDate.parse(tabDates.get(i + 1)));
            if (ecartMois > 2) {
            tabRecommandations.add(Constantes.RECOMMANDATION_ECART_MAX);
            } if (ecartSem < 3) {
            tabRecommandations.add(Constantes.RECOMMANDATION_ECART_MIN);
            }
        }
    }

    public static ArrayList<String> trierDates(ArrayList<Activite> tabActivites) {
        ArrayList<String> tabDates = new ArrayList<>();

        for (int i = 0; i < tabActivites.size(); i++) {
            tabDates.add(tabActivites.get(i).getDate());
        }

        Collections.sort(tabDates);
        return tabDates;
    }

    public static void validerDistance(int transport, double distance, int age) {
        if (transport == Constantes.CODE_MARCHE && distance > Constantes.DISTANCE_MAX_MARCHE_MOINS_10_ANS && age < 10) {
            tabRecommandations.add(Constantes.RECOMMANDATION_MARCHE_MAX_8KM);
        } if (transport == Constantes.CODE_MARCHE && distance > Constantes.DISTANCE_MAX_MARCHE_AUTRES && age >= 10) {
            tabRecommandations.add(Constantes.RECOMMANDATION_MARCHE_MAX_12KM);
        } if (transport == Constantes.CODE_METRO && distance > Constantes.DISTANCE_MAX_METRO) {
            tabRecommandations.add(Constantes.RECOMMANDATION_METRO_MAX);
        } if (transport == Constantes.CODE_AUTOBUS && distance > Constantes.DISTANCE_MAX_BUS) {
            tabRecommandations.add(Constantes.RECOMMANDATION_BUS_MAX);
        }
    }
}
