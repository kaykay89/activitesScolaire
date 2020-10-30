import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les calculs qui menent au prix par eleve d'une activite selon les differents cas correspondant aux eleves
 * de la tranche d'age 1.
 */

public class CalculActivitesTrancheAge1Test {

    private Planification resultatPlanif;
    private Planification planification;
    private Eleves eleves;
    private Activite activite;
    private ArrayList<Activite> tabActivites;

    @Before
    public void setUpInfoPlanif() {
        eleves = new Eleves(25, 5);
        tabActivites = new ArrayList<>();
        activite = new Activite("", 0, "0.00 $", "0.00 $", 0, 0, "0.00 $", "0.00 $");
    }

    @Test
    public void calculerPrixParElevesMarche() {
        activite.setPrixUnitaireEnfant("5.85 $");
        activite.setPrixUnitaireAdulte("9.85 $");
        activite.setDistance(6);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("15.25 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesMetro() {
        activite.setNombreParentsAccompagnateurs(6);
        activite.setTransport(1);
        activite.setDistance(19);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("10.85 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesAutobusRabaisAge() {
        activite.setNombreParentsAccompagnateurs(3);
        activite.setPrixUnitaireEnfant("19.99 $");
        activite.setPrixUnitaireAdulte("29.99 $");
        activite.setTransport(2);
        activite.setDistance(35);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("38.85 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesAutobusRabaisSimultanes() {
        activite.setNombreParentsAccompagnateurs(5);
        activite.setPrixUnitaireEnfant("24.99 $");
        activite.setPrixUnitaireAdulte("34.99 $");
        activite.setTransport(2);
        activite.setDistance(45);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("57.65 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }
}
