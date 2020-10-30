import net.sf.json.JSONObject;

/**
 * Classe qui permet de construire un objet Statistiques et de calculer ses informations.
 * Cet objet contient toutes les informations relatives aux statistiques.
 */

public class Statistiques {

    private int totalActivites;
    private int nbMoinsDe20Dollars;
    private int nbEntre20Et40Dollars;
    private int nbPlusDe40Dollars;
    private int nbParMarche;
    private int nbParMetro;
    private int nbParAutobus;
    private int distanceMaximale;
    private double prixParElevesMaximal;

    public Statistiques() {
        this.totalActivites = 0;
        this.nbMoinsDe20Dollars = 0;
        this.nbEntre20Et40Dollars = 0;
        this.nbPlusDe40Dollars = 0;
        this.nbParMarche = 0;
        this.nbParMetro = 0;
        this.nbParAutobus = 0;
        this.distanceMaximale = 0;
        this.prixParElevesMaximal = 0.0;
    }

    public void calculerStats(Planification planification, JSONObject anciennesStats) {
        for (int i = 0; i < planification.getTabActivites().size(); i++) {
            Activite activite = planification.getTabActivites().get(i);
            calculerStatsParActivite(activite);
        }
        combinerStats(anciennesStats);
    }

    public void calculerStatsParActivite(Activite activite) {
        String prix = activite.getPrixParEleve();
        double prixParEleve = CalculActivites.formaterPrixADouble(prix);

        ajouterTotalActivite();
        calculerStatsPrixParEleves(prixParEleve);
        calculerStatsParTransport(activite.getTransport());
        validerStatsDistanceMaximale(activite.getDistance());
        validerStatsPrixParElevesMaximal(prixParEleve);
    }

    //Accumule les statistiques d'une exécution à l'autre
    public void combinerStats(JSONObject anciennesStats) {
        combinerStatsTotalActivites(anciennesStats.getInt("total_activites"));
        combinerStatsMoinsDe20Dollars(anciennesStats.getInt("activites_moins_de_20_dollars"));
        combinerStatsEntre20Et40Dollars(anciennesStats.getInt("activites_entre_20_et_40_dollars"));
        combinerStatsPlusde40Dollars(anciennesStats.getInt("activites_plus_de_40_dollars"));
        combinerStatsParMarche(anciennesStats.getInt("activites_par_marche"));
        combinerStatsParMetro(anciennesStats.getInt("activites_par_metro"));
        combinerStatsParAutobus(anciennesStats.getInt("activites_par_autobus"));
        validerStatsDistanceMaximale(anciennesStats.getInt("distance_maximale"));
        validerStatsPrixParElevesMaximal(anciennesStats.getDouble("prix_par_eleve_maximal"));
    }

    public void calculerStatsPrixParEleves(double prix) {
        if (prix < 20.00) {
            ajouterNbMoinsDe20Dollars();
        } else if (prix >= 20.00 && prix <= 40.00) {
            ajouterNbEntre20Et40Dollars();
        } else {
            ajouterNbPlusDe40Dollars();
        }
    }

    public void calculerStatsParTransport(int transport) {
        if (transport == Constantes.CODE_MARCHE) {
            ajouterNbParMarche();
        } else if (transport == Constantes.CODE_METRO) {
            ajouterNbParMetro();
        } else if (transport == Constantes.CODE_AUTOBUS) {
            ajouterNbParAutobus();
        }
    }

    public void validerStatsDistanceMaximale(int distance) {
        if (distance > distanceMaximale) {
            setDistanceMaximale(distance);
        }
    }

    public void validerStatsPrixParElevesMaximal(double prix) {
        if (prix > prixParElevesMaximal) {
            setPrixParElevesMaximal(prix);
        }
    }

    public void combinerStatsTotalActivites(int nbTotalAnciennesActivites) {
        totalActivites += nbTotalAnciennesActivites;
    }

    public void combinerStatsMoinsDe20Dollars(int nbAnciennesActivitesMoinsDe20Dollars) {
        nbMoinsDe20Dollars += nbAnciennesActivitesMoinsDe20Dollars;
    }

    public void combinerStatsEntre20Et40Dollars(int nbAnciennesActivitesEntre20Et40Dollars) {
        nbEntre20Et40Dollars += nbAnciennesActivitesEntre20Et40Dollars;
    }

    public void combinerStatsPlusde40Dollars(int nbAnciennesActivitesPlusDe40Dollars) {
        nbPlusDe40Dollars += nbAnciennesActivitesPlusDe40Dollars;
    }

    public void combinerStatsParMarche(int nbAnciennesActivitesAPied) {
        nbParMarche += nbAnciennesActivitesAPied;
    }

    public void combinerStatsParMetro(int nbAnciennesActivitesEnMetro) {
        nbParMetro += nbAnciennesActivitesEnMetro;
    }

    public void combinerStatsParAutobus(int nbAnciennesActivitesEnAutobus) {
        nbParAutobus += nbAnciennesActivitesEnAutobus;
    }

    public int getTotalActivites() {
        return totalActivites;
    }

    public void ajouterTotalActivite() {
        totalActivites += 1;
    }

    public int getNbMoinsDe20Dollars() {
        return nbMoinsDe20Dollars;
    }

    public void ajouterNbMoinsDe20Dollars() {
        nbMoinsDe20Dollars += 1;
    }

    public int getNbEntre20Et40Dollars() {
        return nbEntre20Et40Dollars;
    }

    public void ajouterNbEntre20Et40Dollars() {
        nbEntre20Et40Dollars += 1;
    }

    public int getNbPlusDe40Dollars() {
        return nbPlusDe40Dollars;
    }

    public void ajouterNbPlusDe40Dollars() {
        nbPlusDe40Dollars += 1;
    }

    public int getNbParMarche() {
        return nbParMarche;
    }

    public void ajouterNbParMarche() {
        nbParMarche += 1;
    }

    public int getNbParMetro() {
        return nbParMetro;
    }

    public void ajouterNbParMetro() {
        nbParMetro += 1;
    }

    public int getNbParAutobus() {
        return nbParAutobus;
    }

    public void ajouterNbParAutobus() {
        nbParAutobus += 1;
    }

    public int getDistanceMaximale() {
        return distanceMaximale;
    }

    public void setDistanceMaximale(int distanceMaximale) {
        this.distanceMaximale = distanceMaximale;
    }

    public double getPrixParElevesMaximal() {
        return prixParElevesMaximal;
    }

    public void setPrixParElevesMaximal(double prixParElevesMaximal) {
        this.prixParElevesMaximal = prixParElevesMaximal;
    }
}