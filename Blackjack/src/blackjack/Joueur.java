package blackjack;

/**
 *
 * @author KABA N'faly
 */
public class Joueur {

    private Main main; // la main d'un joueur
    private double budget;  // le budget du joueur
    private String nom; // le nom du joueur
    private double mise = 0; // le nom du joueur
    private boolean estAssurance = false; // determine si le joueur a demandé une assurance
    private boolean estPartager = false; // determine si le joueur a demandé de partager son jeu

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
     * modifie la valeur du nom
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
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

    /**
     * return la valeur du choix assurance
     * @return 
     */
    public boolean isAssurance() {
        return estAssurance;
    }

    /**
     * modifie la valeur du choix assurance
     * @param estAssurance nouvelle valeur de l'assurance
     */
    public void setEstAssurance(boolean estAssurance) {
        this.estAssurance = estAssurance;
    }
    
    /**
     * return oui si le jeu a ete partage et non sinon
     * @return 
     */
    public boolean isPartager() {
        return estPartager;
    }
    /**
     * modifie la valeur du choix partager
     * @param estPartager nouvelle valeur du choix partager
     */
    public void setEstPartager(boolean estPartager) {
        this.estPartager = estPartager;
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
        main.somme();
        retour += main.toString();
        retour += "------ Mise --------\n";
        retour += mise + " euro(s)\n";
        retour += "------ Budget --------\n";
        retour += budget + " euro(s)\n";
        retour += "*************************************************";

        return retour;
    }
}
