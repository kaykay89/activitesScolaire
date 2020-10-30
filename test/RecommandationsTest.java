import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les recommandations possibles.
 */

public class RecommandationsTest {

    private ArrayList<String> tabRecommandations;
    private Planification planification;
    private Eleves eleves;
    private Activite activite1;
    private Activite activite2;
    private Activite activite3;
    private ArrayList<Activite> tabActivites;

    @Before
    public void setUp() {
        Recommandations.tabRecommandations.clear();
        eleves = new Eleves(26, 6);
        activite1 = new Activite("Piscine", 0, "5.85 $", "0.00 $", 0, 6, "2019-12-03", "0.00 $");
        activite2 = new Activite("Promenade au Mont-Royal", 6, "0.00 $", "0.00 $", 1, 19, "2019-10-03", "0.00 $");
        activite3 = new Activite("Zoo de Granby", 3, "19.99 $", "29.99 $", 2, 35, "2020-02-03", "0.00 $");
        tabActivites = new ArrayList<>();
        tabActivites.add(activite1);
        tabActivites.add(activite2);
        tabActivites.add(activite3);
        planification = new Planification(eleves, tabActivites);
    }

    @Test
    public void validerPrixParEleves() {
        planification.getTabActivites().get(0).setPrixParEleve("45.05 $");
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains("\"" + planification.getTabActivites().get(0).getDescription() + Constantes.RECOMMANDATION_PRIX));
    }

    @Test
    public void validerTotalPayableAnnee() {
        planification.getTabActivites().get(0).setPrixParEleve("100.00 $");
        planification.getTabActivites().get(1).setPrixParEleve("100.00 $");
        planification.getTabActivites().get(2).setPrixParEleve("100.05 $");
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_PRIX_ANNEE));
    }

    @Test
    public void validerExterieur() {
        for (int i = 0; i < tabActivites.size(); i++) {
            planification.getTabActivites().get(i).setDistance(0);
        }
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_HORS_ECOLE));
    }

    @Test
    public void validerDateAnneeScolaire() {
        planification.getTabActivites().get(0).setDate("2019-06-25");
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_ANNEE_SCOLAIRE));
    }

    @Test
    public void validerEcartDatesSup() {
        planification.getTabActivites().get(0).setDate("2019-10-05");
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_ECART_MAX));
    }

    @Test
    public void validerEcartDatesInf() {
        planification.getTabActivites().get(0).setDate("2019-10-05");
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_ECART_MIN));
    }

    @Test
    public void validerDistanceMarcheInf10Ans() {
        planification.getTabActivites().get(0).setDistance(9);
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_MARCHE_MAX_8KM));
    }

    @Test
    public void validerDistanceMarcheSup10Ans() {
        planification.getEleves().setAge(11);
        planification.getTabActivites().get(0).setDistance(13);
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_MARCHE_MAX_12KM));
    }

    @Test
    public void validerDistanceMetro() {
        planification.getTabActivites().get(1).setDistance(41);
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_METRO_MAX));
    }

    @Test
    public void validerDistanceAutobus() {
        planification.getTabActivites().get(2).setDistance(81);
        tabRecommandations = Recommandations.validerRecommandations(planification);

        assertTrue(tabRecommandations.contains(Constantes.RECOMMANDATION_BUS_MAX));
    }
}
