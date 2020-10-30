import java.util.ArrayList;

/**
 * Classe qui permet de calculer le prix par eleve de chacune des activites contenues dans le fichier d'entree.
 */

public class CalculActivites {

    public static int nombreEleves;
    public static int nombreParentsAccompagnateurs;
    public static int age;
    public static int transport;
    public static int distance;
    public static String date;
    public static double prixParEleve;

    public static Planification calculerActivites(Planification planification) {
        for (int i = 0; i < planification.getTabActivites().size(); i++) {
            Activite activite = planification.getTabActivites().get(i);
            nombreEleves = planification.getEleves().getNombreEleves();
            age = planification.getEleves().getAge();
            transport = activite.getTransport();
            distance = activite.getDistance();
            prixParEleve = calculerPrixParEleve(activite);
            String formatPrixParEleve = String.format("%.2f", prixParEleve);
            activite.setPrixParEleve(formatPrixParEleve);
        } return planification;
    }

    public static double calculerPrixParEleve(Activite activite) {
        nombreParentsAccompagnateurs = activite.getNombreParentsAccompagnateurs();
        int nbAdultes = nombreParentsAccompagnateurs + Constantes.NB_PROF;
        int nbPersTotal = nombreEleves + nbAdultes;
        double prixTransp = calculerPrixTransport(nbPersTotal, nbAdultes);
        double prixEntrees = (nombreEleves * formaterPrixADouble(activite.getPrixUnitaireEnfant()))
                + (nbAdultes * formaterPrixADouble(activite.getPrixUnitaireAdulte()));
        return prixParEleve = Math.ceil(((prixEntrees + prixTransp
                + (nombreEleves * Constantes.PRIX_GARDE)) / nombreEleves) * 20) / 20; //Arrondir 0.05 $ superieur
    }

    public static double calculerPrixTransport(int nbPersTotal, int nbAdultes) {
        double prixTransp = 0;

        if (transport == Constantes.CODE_MARCHE) {
            prixTransp = calculerPrixMarche(nbPersTotal);
        } else if (transport == Constantes.CODE_METRO) {
            prixTransp = calculerPrixMetro(nbAdultes);
        } else if (transport == Constantes.CODE_AUTOBUS) {
            prixTransp = calculerPrixAutobus(nbPersTotal);
        } return prixTransp;
    }

    public static double calculerPrixMarche(int nbPersTotal) {
        double prixMarche = nbPersTotal * Constantes.PRIX_MARCHE;
        return prixMarche;
    }

    public static double calculerPrixMetro(int nbAdultes) {
        double prixMetro = nbAdultes * Constantes.PRIX_METRO_TRANCHE_AGE_3;

        if (age <= Constantes.TRANCHE_AGE_1) {
            prixMetro += nombreEleves * Constantes.PRIX_METRO_TRANCHE_AGE_1;
        } else if (age >= Constantes.TRANCHE_AGE_2_DEB && age <= Constantes.TRANCHE_AGE_2_FIN) {
            prixMetro += nombreEleves * Constantes.PRIX_METRO_TRANCHE_AGE_2;
        } else if (age >= Constantes.TRANCHE_AGE_3) {
            prixMetro += nombreEleves * Constantes.PRIX_METRO_TRANCHE_AGE_3;
        } return prixMetro;
    }

    public static double calculerPrixAutobus(int nbPersTotal) {
        int nbAutobus = (int) Math.ceil((double) nbPersTotal / Constantes.AUTOBUS_CAPACITE_MAX);
        double prixAutPleinCap = Math.floor((double) nbPersTotal / Constantes.AUTOBUS_CAPACITE_MAX) * Constantes.PRIX_AUTOBUS_PLEIN;
        double prixAutobus = prixAutPleinCap;
        prixAutobus += calculerPrixDistanceAutobus(nbAutobus);
        prixAutobus -= calculerRabaisAutobus(prixAutobus);
        return prixAutobus;
    }

    public static double calculerPrixDistanceAutobus(int nbAutobus) {
        double prixDistance;

        if (distance < Constantes.AUTOBUS_TRANCHE_KM) {
            prixDistance = distance * Constantes.PRIX_AUTOBUS_MOINS_TRANCHE_KM * nbAutobus;
        } else {
            prixDistance = distance * Constantes.PRIX_AUTOBUS_TRANCHE_KM_ET_PLUS * nbAutobus;
        } return prixDistance;
    }

    public static double calculerRabaisAutobus(double prixAutobus) {
        double rabais = 0;

        if (age <= Constantes.TRANCHE_AGE_1 && nombreParentsAccompagnateurs >= Constantes.AUTOBUS_NB_PARENTS_RABAIS) {
            rabais = prixAutobus * Constantes.RABAIS_SIMULTANES;
        } else if (age <= Constantes.TRANCHE_AGE_1) {
            rabais = prixAutobus * Constantes.RABAIS_AUTOBUS_AGE;
        } else if (nombreParentsAccompagnateurs >= Constantes.AUTOBUS_NB_PARENTS_RABAIS) {
            rabais = prixAutobus * Constantes.RABAIS_AUTOBUS_PARENTS;
        } return rabais;
    }

    public static double calculerTotalPayable(ArrayList<Activite> tabActivites) {
        double totalPayable = 0;

        for (int i = 0; i < tabActivites.size(); i++) {
            totalPayable += formaterPrixADouble(tabActivites.get(i).getPrixParEleve());
        } return totalPayable;
    }

    public static double formaterPrixADouble(String prix) {
            String prixTmp = prix.substring(0, prix.length() - 2); //Retire l'espace et "$" apres le prix
            prixTmp = prixTmp.replace(",", "."); //Prend en compte la "," dans le prix et la remplace par un "."
            double prixEnDouble = Double.valueOf(prixTmp);
            return prixEnDouble;
    }
}
