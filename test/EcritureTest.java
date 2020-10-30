import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les formats JSON.
 */

public class EcritureTest {

    private String resultatContenu;

    private Planification planification;
    private Eleves eleves;
    private Activite activite;
    private ArrayList<Activite> tabActivites;
    private Statistiques statistiques;

    @Before
    public void setUp() {
        resultatContenu = "";
        Ecriture.contenuPlanif = "";
        Ecriture.contenuStats = "";
        eleves = new Eleves(25,15);
        activite = new Activite("Peinture", 8, "45.85 $", "60.85 $", 0, 0, "2019-12-12", "50,95 $");
        tabActivites = new ArrayList<>();
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        statistiques = new Statistiques();
    }
    
    @Test
    public void ecrireActivites() {
        Ecriture.ecrireActivites(tabActivites);
        resultatContenu = Ecriture.contenuPlanif;
        String resultatAttendu = "{\n    \"activites\": [\n        {\n            \"description\": \"Peinture\",\n"
                + "            \"prix_par_eleve\": \"50.95 $\"\n        }\n    ]";

        assertEquals(resultatAttendu, resultatContenu);
    }

    @Test
    public void ecrireRecommandations() {
        Ecriture.ecrireRecommandations(planification);
        resultatContenu = Ecriture.contenuPlanif;
        String resultatAttendu = ",\n    \"recommandations\": [\n        "
                + "\"Peinture : cette activité est trop dispendieuse (plus de 45.00 $).\",\n        "
                + "\"Il faudrait avoir au moins une sortie hors de l'école.\"\n    ]";

        assertEquals(resultatAttendu, resultatContenu);
    }

    @Test
    public void ecrireStats() {
        Ecriture.ecrireStats(statistiques);
        resultatContenu = Ecriture.contenuStats;
        String resultatAttendu = "{\n    \"total_activites\": 0,\n    \"activites_moins_de_20_dollars\": 0,\n    "
                + "\"activites_entre_20_et_40_dollars\": 0,\n    \"activites_plus_de_40_dollars\": 0,\n    "
                + "\"activites_par_marche\": 0,\n    \"activites_par_metro\": 0,\n    \"activites_par_autobus\": 0,\n    "
                + "\"distance_maximale\": 0,\n    \"prix_par_eleve_maximal\": 0.0\n}";

        assertEquals(resultatAttendu, resultatContenu);
    }
}
