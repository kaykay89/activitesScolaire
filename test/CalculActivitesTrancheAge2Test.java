import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les calculs qui menent au prix par eleve d'une activite selon les differents cas correspondant aux eleves
 * de la tranche d'age 2.
 */

public class CalculActivitesTrancheAge2Test {

    private Planification resultatPlanif;
    private Planification planification;
    private Eleves eleves;
    private Activite activite;
    private ArrayList<Activite> tabActivites;

    @Before
    public void setUpInfoPlanif() {
        eleves = new Eleves(56, 12);
        tabActivites = new ArrayList<>();
        activite = new Activite("", 0, "0.00 $", "0.00 $", 0, 0, "0.00 $", "0.00 $");
    }

    @Test
    public void calculerPrixParElevesMarche() {
        activite.setNombreParentsAccompagnateurs(5);
        activite.setPrixUnitaireEnfant("7.85 $");
        activite.setPrixUnitaireAdulte("9.85 $");
        activite.setDistance(15);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("17.95 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesMetro() {
        activite.setNombreParentsAccompagnateurs(8);
        activite.setTransport(1);
        activite.setDistance(19);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("14.55 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }

    @Test
    public void calculerPrixParElevesAutobusRabaisNbParents() {
        activite.setNombreParentsAccompagnateurs(15);
        activite.setPrixUnitaireEnfant("22.99 $");
        activite.setPrixUnitaireAdulte("29.99 $");
        activite.setTransport(2);
        activite.setDistance(40);
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
        resultatPlanif = CalculActivites.calculerActivites(planification);

        assertEquals("51.10 $", resultatPlanif.getTabActivites().get(0).getPrixParEleve());
    }
}
