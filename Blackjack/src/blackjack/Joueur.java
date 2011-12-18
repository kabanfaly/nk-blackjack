package blackjack;

/**
 *
 * @author CONDE Oumar
 */
public class Joueur {

    private Main main; // la main d'un joueur
    private double budget;  // le budget du joueur
    private String nom; // le nom du joueur
    private double mise = 0; // le nom du joueur

    public Joueur(Main main, double budget, String nom) {
        this.main = main;
        this.budget = budget;
        this.nom = nom;

    }

    public Joueur(double budget, String nom) {
        this.main = new Main();
        this.budget = budget;
        this.nom = nom;
    }

    /**
     * Accède au budget du joueur
     * @return le budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Modifie le budget du joueur
     * @param budget le nouveau budget du joueur
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

    /**
     * Accède aux main du joueur
     * @return 
     */
    public Main getMain() {
        return main;
    }

    /**
     * Accède au nom du joueur
     * @return le budget
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie la main du joueur
     * @param main la nouvelle main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    public void ajouterCarte(Carte c) {
        main.ajouterCarte(c);
    }

    /**
     * Accede a la mise du joueur
     * @return 
     */
    public double getMise() {
        return mise;
    }

    /**
     * Modifie la mise du joeur
     * @param mise nouvelle mise
     */
    public void setMise(double mise) {
        this.mise = mise;
    }

    public boolean estBudgetSuffisant(double mise) {
        return budget >= mise;
    }

    /**
     * Permet a un joueur de miser une somme
     * @param mise 
     */
    public void miser(double mise) {
        if (estBudgetSuffisant(mise)) {
            this.setMise(mise);
            budget -= mise;
        } else {
            System.out.println(nom + ", votre budget est insuffisant. Vous disposez de " + budget + "euro(s).");
        }
    }

    @Override
    public String toString() {
        String retour = "****************** " + nom + " *****************\n";
        retour += "------ Mains -------- \n";
        retour += main.toString();
        retour += "------ Mise --------\n";
        retour += mise + " euro(s)\n";
        retour += "------ Budget --------\n";
        retour += budget + " euro(s)\n";
        retour += "*************************************************";

        return retour;
    }
}
