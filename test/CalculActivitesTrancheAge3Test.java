import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les calculs qui menent au prix par eleve d'une activite selon les differents cas correspondant aux eleves
 * de la tranche d'age 3.
 */

public class CalculActivitesTrancheAge3Test {

    private Planification resultatPlanif;
    private Planification planification;
    private Eleves eleves;
    private Activite activite;
    private ArrayList<Activite> tabActivites;

    @Before
    public void setUpInfoPlanif() {
        eleves = new Eleves(65, 18);
        tabActivites = new ArrayList<>();
        activite = new Activite("", 0, "0.00 $", "0.00 $", 0, 0, "0.00 $", "0.00 $");
    }

    @Test
    public void calculerPrixParElevesMarche() {
        activite.setNombreParentsAccompagnateurs(8);
        activite.setPrixUnitaireEnfant("9.85 $");
        activite.setPrixUnitaireAdulte("9.85 $");
        activite.setDistance(22);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("20.25 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesMetro() {
        activite.setNombreParentsAccompagnateurs(10);
        activite.setTransport(1);
        activite.setDistance(27);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("16.60 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesAutobus() {
        activite.setNombreParentsAccompagnateurs(3);
        activite.setPrixUnitaireEnfant("29.99 $");
        activite.setPrixUnitaireAdulte("29.99 $");
        activite.setTransport(2);
        activite.setDistance(33);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("47.90 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesAutobusRabaisNbParents() {
        activite.setNombreParentsAccompagnateurs(25);
        activite.setPrixUnitaireEnfant("34.99 $");
        activite.setPrixUnitaireAdulte("34.99 $");
        activite.setTransport(2);
        activite.setDistance(60);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("75.70 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }
}
