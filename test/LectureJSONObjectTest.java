import org.junit.*;
import static org.junit.Assert.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Classe qui permet de tester la manipulation de JSONObject.
 */

public class LectureJSONObjectTest {

    private String message = "";
    private Planification resultatPlanif = null;
    private JSONObject jsonPlanif;
    private JSONObject jsonActivite;
    private JSONArray jsonTabActivites;

    @Before
    public void setUpJSONOjectPlanif() {
        jsonPlanif = new JSONObject();
        jsonActivite = new JSONObject();
        jsonTabActivites = new JSONArray();
        jsonPlanif.put("nombre_eleves", 0);
        jsonPlanif.put("age", 0);
        jsonActivite.put("description", "");
        jsonActivite.put("nombre_parents_accompagnateurs", 0);
        jsonActivite.put("prix_unitaire_enfant", "0.00 $");
        jsonActivite.put("prix_unitaire_adulte", "0.00 $");
        jsonActivite.put("transport", 0);
        jsonActivite.put("distance", 0);
        jsonActivite.put("date", "2019-12-31");
        jsonTabActivites.add(jsonActivite);
        jsonPlanif.put("activites",jsonTabActivites);
    }

    @Test
    public void getPlanification() {
        resultatPlanif = Lecture.getPlanification(jsonPlanif);

        assertTrue(resultatPlanif != null);
    }

    @Test
    public void validerProprietesEleves() {
        jsonPlanif.remove("nombre_eleves");
        jsonPlanif.remove("age");
        Lecture.jsonPlanification = jsonPlanif;
        message = Erreurs.validerProprietes();

        assertEquals(Constantes.MSG_JSON_PROPRIETE_MANQUANTE + " nombre_eleves age", message);
    }

    @Test
    public void validerProprietesActivites() {
        jsonPlanif.remove("activites");
        jsonPlanif.remove("description");
        jsonPlanif.remove("distance");
        Lecture.jsonPlanification = jsonPlanif;
        message = Erreurs.validerProprietes();

        assertEquals(Constantes.MSG_JSON_PROPRIETE_MANQUANTE + " activites ...", message);
    }

    @Test
    public void validerProprietesTabActivitesInfo() {
        jsonPlanif.remove("activites", jsonTabActivites);
        jsonActivite.remove("description");
        jsonActivite.remove("date");
        jsonTabActivites.add(jsonActivite);
        jsonPlanif.put("activites", jsonTabActivites);
        Lecture.jsonPlanification = jsonPlanif;
        message = Erreurs.validerProprietes();

        assertEquals(Constantes.MSG_JSON_PROPRIETE_MANQUANTE + " description date", message);
    }

    @Test
    public void validerProprietesTabActivitesNbPrix() {
        jsonPlanif.remove("activites", jsonTabActivites);
        jsonActivite.remove("nombre_parents_accompagnateurs");
        jsonActivite.remove("prix_unitaire_enfant");
        jsonActivite.remove("prix_unitaire_adulte");
        jsonTabActivites.add(jsonActivite);
        jsonPlanif.put("activites", jsonTabActivites);
        Lecture.jsonPlanification = jsonPlanif;
        message = Erreurs.validerProprietes();

        assertEquals(Constantes.MSG_JSON_PROPRIETE_MANQUANTE + " nombre_parents_accompagnateurs prix_unitaire_enfant prix_unitaire_adulte", message);
    }

    @Test
    public void validerProprietesTabActivitesTranspDistance() {
        jsonPlanif.remove("activites", jsonTabActivites);
        jsonActivite.remove("transport");
        jsonActivite.remove("distance");
        jsonTabActivites.add(jsonActivite);
        jsonPlanif.put("activites", jsonTabActivites);
        Lecture.jsonPlanification = jsonPlanif;
        message = Erreurs.validerProprietes();

        assertEquals(Constantes.MSG_JSON_PROPRIETE_MANQUANTE + " transport distance", message);
    }
}
