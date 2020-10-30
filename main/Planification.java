import java.util.ArrayList;

/**
 * Classe qui permet de construire un objet Planification.
 * Cet objet contient toutes les informations relatives a la planification.
 */

public class Planification {

    private Eleves eleves;
    private ArrayList<Activite> tabActivites;

    public Planification(Eleves eleves, ArrayList<Activite> tabActivites) {
        this.eleves = eleves;
        this.tabActivites = tabActivites;
    }

    public Eleves getEleves() {
        return eleves;
    }

    public ArrayList<Activite> getTabActivites() {
        return tabActivites;
    }
}
