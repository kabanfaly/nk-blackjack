package blackjack;

/**
 *
 * @author CONDE Oumar
 */
public class Joueur {
    private Main main; // la main d'un joueur
    private int budget;  // le budget du joueur
    private String nom; // le nom du joueur

    public Joueur(Main main, int budget, String nom) {
        this.main = main;
        this.budget = budget;
        this.nom = nom;
    }

    public Joueur(int budget, String nom) {
        this.main = new Main();
        this.budget = budget;
        this.nom = nom;
    }
    /**
     * Accède au budget du joueur
     * @return le budget
     */
    public int getBudget() {
        return budget;
    }    
    /**
     * Modifie le budget du joueur
     * @param budget le nouveau budget du joueur
     */
    public void setBudget(int budget) {
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
    public String getNom(){
        return nom;
    }
    /**
     * Modifie la main du joueur
     * @param main la nouvelle main
     */
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void ajouterCarte(Carte c){
        main.ajouterCarte(c);
    }
    
}
