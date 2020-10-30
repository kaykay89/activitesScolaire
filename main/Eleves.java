/**
 * Classe qui permet de construire un objet Eleves.
 * Cet objet contient toutes les informations relatives aux eleves.
 */

public class Eleves {

    private int nombreEleves;
    private int age;

    public Eleves(int nombreEleves, int age) {
        this.nombreEleves = nombreEleves;
        this.age = age;
    }

    public int getNombreEleves() {
        return nombreEleves;
    }

    public void setNombreEleves(int nombreEleves) {
        this.nombreEleves = nombreEleves;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
