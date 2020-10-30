public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            String fichierEntree = args[0];
            String fichierSortie = args[1];
            Erreurs.validerEntreesUtilisateur(fichierEntree, fichierSortie);
        } else if (args.length == 1) {
            Erreurs.validerOptionsStatistiques(args[0]);
        } else {
            Gestion.afficherNbArgumentsErreur();
        }
    }
}
