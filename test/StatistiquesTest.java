import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Classe qui permet de tester les statistiques produites.
 */

public class StatistiquesTest {

    private ByteArrayOutputStream sortieConsole = new ByteArrayOutputStream();
    
    private static JSONObject anciennesStats = new JSONObject();
    private Statistiques statistiques;
    private Planification planification;
    private Eleves eleves;
    private Activite activite1;
    private Activite activite2;
    private Activite activite3;
    private ArrayList<Activite> tabActivites;
    
    @BeforeClass
    public static void setUpAnciennesStats() {
        anciennesStats.put("total_activites", 1);
        anciennesStats.put("activites_moins_de_20_dollars", 1);
        anciennesStats.put("activites_entre_20_et_40_dollars", 1);
        anciennesStats.put("activites_plus_de_40_dollars", 1);
        anciennesStats.put("activites_par_marche", 1);
        anciennesStats.put("activites_par_metro", 1);
        anciennesStats.put("activites_par_autobus", 1);
        anciennesStats.put("distance_maximale", 1);
        anciennesStats.put("prix_par_eleve_maximal", 1);
    }
    
    @Before
    public void setUpStats() {
        statistiques = new Statistiques();
        eleves = new Eleves(65,18);
        activite1 = new Activite("Patin", 8, "9.85 $", "9.85 $", 0, 22, "2019-12-12", "20.25 $");
        activite2 = new Activite("Ski", 10, "0,00 $", "0.00 $", 1, 27, "2019-12-12", "16.60 $");
        activite3 = new Activite("Spectacle", 3, "29,99 $", "29,99 $", 2, 33, "2019-12-12", "47,90 $");
        tabActivites = new ArrayList<>();
        tabActivites.add(activite1);
        tabActivites.add(activite2);
        tabActivites.add(activite3);
        planification = new Planification(eleves, tabActivites);
    }

    @Test
    public void ajouterTotalActivite() {
        statistiques.calculerStats(planification, anciennesStats);
        assertEquals(4, statistiques.getTotalActivites());
    }

    @Test
    public void ajouterNbMoinsDe20Dollars() {
        statistiques.calculerStats(planification, anciennesStats);
        
        assertEquals(2, statistiques.getNbMoinsDe20Dollars());
    }

    @Test
    public void ajouterNbEntre20Et40Dollars() {
        statistiques.calculerStats(planification, anciennesStats);

        assertEquals(2, statistiques.getNbEntre20Et40Dollars());
    }

    @Test
    public void ajouterNbPlusDe40Dollars() {
        statistiques.calculerStats(planification, anciennesStats);

        assertEquals(2, statistiques.getNbPlusDe40Dollars());
    }

    @Test
    public void ajouterNbParMarche() {
        statistiques.calculerStats(planification, anciennesStats);

        assertEquals(2, statistiques.getNbParMarche());
    }

    @Test
    public void validerDistanceMaximale() {
        statistiques.calculerStats(planification, anciennesStats);
        planification.getTabActivites().get(0).setDistance(35);
        statistiques.calculerStats(planification, anciennesStats);

        assertEquals(35, statistiques.getDistanceMaximale());
    }

    @Test
    public void validerPrixParEleveMaximal() {
        statistiques.calculerStats(planification, anciennesStats);
        planification.getTabActivites().get(0).setPrixParEleve("50.00 $");
        statistiques.calculerStats(planification, anciennesStats);

        assertEquals(50.00, statistiques.getPrixParElevesMaximal(), 0.001);
    }

    @Test
    public void afficherStatistiques() {
        System.setOut(new PrintStream(this.sortieConsole));
        Gestion.afficherStatistiquesFormat(anciennesStats);
        String sortieConsoleAttendue = "\n---------------------------------------------------\nNombre total d'activités soumises: 1\n\n"
                + "Nombre total d'activités de moins de 20$ : 1\nNombre total d'activités entre 20 et 40$ : 1\n"
                + "Nombre total d'activités de plus de 40$ : 1\n\nNombre total d'activités par marche : 1\n"
                + "Nombre total d'activités par métro: 1\nNombre total d'activités par autobus: 1\n\nDistance maximale soumise : 1 KM\n"
                + "Prix par élève maximal pour une activité : 1.00 $\n---------------------------------------------------\n";

                assertEquals(sortieConsoleAttendue, this.sortieConsole.toString());
    }
}
