import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Classe qui permet de tester les erreurs possibles.
 */

public class ErreursTest {

    private String message;
    private Planification planification;
    private Eleves eleves;
    private Activite activite;
    private ArrayList<Activite> tabActivites;

    @Before
    public void setUp() {
        Erreurs.message = "";
        eleves = new Eleves(23, 4);
        activite = new Activite("Cinema", 0, "5.85 $", "0.00 $", 0, 6, "2019-10-03", "0.00 $");
        tabActivites = new ArrayList<>();
        tabActivites.add(activite);
        planification = new Planification(eleves, tabActivites);
    }

    @Test
    public void validerPlanificationNbElevesInf() {
        planification.getEleves().setNombreEleves(0);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_NOMBRE_ELEVES_0, message);
    }

    @Test
    public void validerPlanificationNbElevesSup() {
        planification.getEleves().setNombreEleves(33);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_NOMBRE_ELEVES_PLUS_32, message);
    }

    @Test
    public void validerPlanificationAgeInf() {
        planification.getEleves().setAge(0);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_AGE_INFERIEUR_4, message);
    }

    @Test
    public void validerPlanificationAgeSup() {
        planification.getEleves().setAge(18);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_AGE_SUPERIEUR_17, message);
    }

    @Test
    public void validerPlanificationDescriptionNonUnique() {
        planification.getTabActivites().add(activite);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_DESCRIPTION_NON_UNIQUE, message);
    }

    @Test
    public void validerPlanificationDescriptionVide() {
        planification.getTabActivites().get(0).setDescription("");
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_DESCRIPTION_VIDE, message);
    }

    @Test
    public void validerPlanificationNbAdultes() {
        planification.getTabActivites().get(0).setNombreParentsAccompagnateurs(25);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_PLUS_ADULTES_QUE_ENFANTS, message);
    }

    @Test
    public void validerPlanificationPrixEnfantsFormat() {
        planification.getTabActivites().get(0).setPrixUnitaireEnfant("5.85$");
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_MONTANT_INVALIDE, message);
    }

    @Test
    public void validerPlanificationPrixAdultesFormat() {
        planification.getTabActivites().get(0).setPrixUnitaireAdulte("0,00 $");
        message = Erreurs.validerPlanification(planification);

        assertEquals("", message);
    }

    @Test
    public void validerPlanificationPrixNeg() {
        planification.getTabActivites().get(0).setPrixUnitaireAdulte("-10.00 $");
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_MONTANT_NEGATIF, message);
    }

    @Test
    public void validerPlanificationDistanceNeg() {
        planification.getTabActivites().get(0).setDistance(-1);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_DISTANCE_NEGATIVE, message);
    }

    @Test
    public void validerPlanificationSupDistance() {
        planification.getTabActivites().get(0).setDistance(121);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_DISTANCE_SUPERIEURE_120KM, message);
    }

    @Test
    public void validerPlanificationFormatDate() {
        planification.getTabActivites().get(0).setDate("2019/12/31");
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_FORMAT_DATE, message);
    }

    @Test
    public void validerPlanificationDateLocal() {
        planification.getTabActivites().get(0).setDate("2019-12-32");
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_FORMAT_DATE, message);
    }

    @Test
    public void validerPlanificationTransport() {
        planification.getTabActivites().get(0).setTransport(3);
        message = Erreurs.validerPlanification(planification);

        assertEquals(Constantes.MSG_TYPE_TRANSPORT, message);
    }
}
