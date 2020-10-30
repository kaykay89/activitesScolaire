/**
 * Classe qui permet de construire un objet Activite.
 * Cet objet contient toutes les informations relatives a l'activite.
 */

public class Activite {

    private String description;
    private int nombreParentsAccompagnateurs;
    private String prixUnitaireEnfant;
    private String prixUnitaireAdulte;
    private int transport;
    private int distance;
    private String date;
    private String prixParEleve;

    public Activite(String description, int nbParentsAccompagnateurs, String prixUnitaireEnfant,
                    String prixUnitaireAdulte, int transport, int distance, String date, String prixParEleve) {
        this.description = description;
        this.nombreParentsAccompagnateurs = nbParentsAccompagnateurs;
        this.prixUnitaireEnfant = prixUnitaireEnfant;
        this.prixUnitaireAdulte = prixUnitaireAdulte;
        this.transport = transport;
        this.distance = distance;
        this.date = date;
        this.prixParEleve = Ecriture.formaterArgent(prixParEleve);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreParentsAccompagnateurs() {
        return nombreParentsAccompagnateurs;
    }

    public void setNombreParentsAccompagnateurs(int nombreParentsAccompagnateurs) {
        this.nombreParentsAccompagnateurs = nombreParentsAccompagnateurs;
    }

    public String getPrixUnitaireEnfant() {
        return prixUnitaireEnfant;
    }

    public void setPrixUnitaireEnfant(String prixUnitaireEnfant) {
        this.prixUnitaireEnfant = prixUnitaireEnfant;
    }

    public String getPrixUnitaireAdulte() {
        return prixUnitaireAdulte;
    }

    public void setPrixUnitaireAdulte(String prixUnitaireAdulte) {
        this.prixUnitaireAdulte = prixUnitaireAdulte;
    }

    public int getTransport() {
        return transport;
    }

    public void setTransport(int transport) {
        this.transport = transport;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrixParEleve() {
        return prixParEleve;
    }

    public void setPrixParEleve(String prixParEleve) {
        this.prixParEleve = Ecriture.formaterArgent(prixParEleve);
    }
}
