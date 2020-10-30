import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui charge le fichier d'entree json et qui en extrait toutes les informations.
 * Les informations extraites sont l'age du groupe, le nombre d'eleves et la liste des activites.
 * Les activites et leurs informations rattachees sont mises dans un Array.
 * Lecture du fichier statistiques.json modifie d'une execution a l'autre du programme ou lorsque l'utilisateur reinitialise les statistiques.
 */

public class Lecture {

    public static JSONObject jsonPlanification;
    public static ArrayList<Activite> tabActivites = new ArrayList<>();

    public static JSONObject objetDeclaration (String fichierEntree) {
        try {
            jsonPlanification = (JSONObject) JSONSerializer.toJSON(DiskFile.loadFileIntoString(fichierEntree));
        } catch (JSONException e) {
            System.out.println(Constantes.MSG_FICHIER_JSON_INVALIDE + fichierEntree);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println(Constantes.MSG_FICHIER_EXISTE_PAS + fichierEntree);
            System.exit(-1);
        } return jsonPlanification;
    }

    public static Planification getPlanification(JSONObject jsonPlanification) {
        Eleves eleves = new Eleves(jsonPlanification.getInt("nombre_eleves"), jsonPlanification.getInt("age"));
        Planification planification = new Planification(eleves, getTabActivites(jsonPlanification));
        return planification;
    }

    public static ArrayList<Activite> getTabActivites(JSONObject jsonPlanification) {
        JSONArray jsonTabActivites = jsonPlanification.getJSONArray("activites");

        for (int i = 0; i < jsonTabActivites.size(); i++) {
            JSONObject activites =  jsonTabActivites.getJSONObject(i);

            tabActivites.add(new Activite(activites.getString("description"),
                    activites.getInt("nombre_parents_accompagnateurs"), activites.getString("prix_unitaire_enfant"),
                    activites.getString("prix_unitaire_adulte"), activites.getInt("transport"),
                    activites.getInt("distance"), activites.getString("date"), "0.00 $"));
        } return tabActivites;
    }

    public static String validerObjetsActivite() {
        String proprietes = validerObjetsJSON();
        if (!proprietes.contains("activites")) {
            JSONArray jsonTabActivites = jsonPlanification.getJSONArray("activites");
            proprietes += validerInfoArray(jsonTabActivites)
                    + validerPrixArray(jsonTabActivites)
                    + validerDetailsArray(jsonTabActivites);

        } return proprietes;
    }

    public static String validerObjetsJSON() {
            String proprietes = "";
            if (!jsonPlanification.containsKey("nombre_eleves")) {
                proprietes += " nombre_eleves";
            } if (!jsonPlanification.containsKey("age")) {
                proprietes += " age";
            } if (!jsonPlanification.containsKey("activites")) {
                proprietes += " activites ...";
            } return proprietes;
    }

    public static String validerInfoArray(JSONArray jsonTabActivites) {
        String proprietes = "";
        for (int i = 0; i < jsonTabActivites.size(); i++) {
            if (!jsonTabActivites.getJSONObject(i).containsKey("description") && !proprietes.contains(" description")) {
                proprietes += " description";
            } if (!jsonTabActivites.getJSONObject(i).containsKey("nombre_parents_accompagnateurs")
                    && !proprietes.contains(" nombre_parents_accompagnateurs")) {
                proprietes += " nombre_parents_accompagnateurs";
            }
        } return proprietes;
    }

    public static String validerPrixArray(JSONArray jsonTabActivites) {
        String proprietes = "";
        for (int i = 0; i < jsonTabActivites.size(); i++) {
            if (!jsonTabActivites.getJSONObject(i).containsKey("prix_unitaire_enfant") && !proprietes.contains(" prix_unitaire_enfant")) {
                proprietes += " prix_unitaire_enfant";
            } if (!jsonTabActivites.getJSONObject(i).containsKey("prix_unitaire_adulte") && !proprietes.contains(" prix_unitaire_adulte")) {
                proprietes += " prix_unitaire_adulte";
            }
        } return proprietes;
    }

    public static String validerDetailsArray(JSONArray jsonTabActivites) {
        String proprietes = "";
        for (int i = 0; i < jsonTabActivites.size(); i++) {
            if (!jsonTabActivites.getJSONObject(i).containsKey("transport") && !proprietes.contains(" transport")) {
                proprietes += " transport";
            } if (!jsonTabActivites.getJSONObject(i).containsKey("distance") && !proprietes.contains(" distance")) {
                proprietes += " distance";
            } if (!jsonTabActivites.getJSONObject(i).containsKey("date") && !proprietes.contains(" date")) {
                proprietes += " date";
            }
        } return proprietes;
    }
}
